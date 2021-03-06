package com.jy.pc.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jy.pc.Entity.JurisdictionEntity;
import com.jy.pc.Entity.RelationEntity;
import com.jy.pc.Entity.RolesEntity;
import com.jy.pc.Service.JurisdictionService;
import com.jy.pc.Service.RelationService;
import com.jy.pc.Service.RolesService;

@Controller
@RequestMapping(value = "/role")
@ResponseBody
public class RolesController {
	@Autowired
	private RolesService rolesService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private JurisdictionService jurisdictionService;

	// 角色添加
	@RequestMapping(value = "/add")
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req) {
		
		String s = res.getParameter("roleEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		RolesEntity rolesEntity = jsonObject.toJavaObject(RolesEntity.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );// 格式化时间		
		String time=DateFormat.getDateTimeInstance().format(new Date());
		try {
			rolesEntity.setCreateTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//角色权限连接表实体
		RelationEntity relationEntity = new RelationEntity();
		//权限表实体
		JurisdictionEntity jurisdictionEntity = new JurisdictionEntity();
		jurisdictionEntity = jurisdictionService.findId(rolesEntity.getLimitId());
		
		rolesEntity.setLimitName(jurisdictionEntity.getName());
		RolesEntity role = rolesService.save(rolesEntity);
		//添加角色权限关联表
		relationEntity.setRoleId(role.getId());
		relationEntity.setLimitId(rolesEntity.getLimitId());
		jurisdictionEntity = jurisdictionService.findId(rolesEntity.getLimitId());
		rolesEntity.setLimitName(jurisdictionEntity.getName());
		relationService.save(relationEntity);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "添加成功");
		return map;
	}
	
	//角色修改
	@RequestMapping(value = "/update")
	public Map<String, String> update(HttpServletRequest res,HttpServletResponse req) {

		Map<String, String> map = new HashMap<String, String>();
		String s = res.getParameter("rolesEntity");
		JSONObject jsonObject = JSONObject.parseObject(s);
		RolesEntity rolesEntity = jsonObject.toJavaObject(RolesEntity.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );// 格式化时间		
		String time=DateFormat.getDateTimeInstance().format(new Date());
		try {
			rolesEntity.setEditTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//权限id/名称同步修改
		JurisdictionEntity jurisdictionEntity = new JurisdictionEntity();
		jurisdictionEntity = jurisdictionService.findId(rolesEntity.getLimitId());
		rolesEntity.setLimitName(jurisdictionEntity.getName());
		rolesService.update(rolesEntity);		
		//修改关联表
		RelationEntity relationEntity = new RelationEntity();
		relationEntity = relationService.findRelationId(rolesEntity.getId());
		relationEntity.setLimitId(rolesEntity.getLimitId());
		relationService.update(relationEntity);
		
		map.put("message","修改成功");
		return map;
	}

	//角色删除
	@RequestMapping(value = "/delete")
	public Map<String,Object> delete(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="id")String id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//删除关联表
		RolesEntity rolesEntity = new RolesEntity();
		rolesEntity = rolesService.findId(id);
		RelationEntity relationEntity = new RelationEntity();
		relationEntity = relationService.findRelationId(rolesEntity.getId());
		relationService.delete(relationEntity.getId());
		
		rolesService.delete(id);
		map.put("status", "0");
		map.put("message", "删除成功");
		return map;
	}

	//查询所有
	@RequestMapping(value = "/findAll")
	public Map<String,Object> findAll(HttpServletRequest res,HttpServletResponse req) {

		Map<String,Object> map = new HashMap<String,Object>();
		List<RolesEntity> rolesList = rolesService.findAll();
		map.put("status", "0");
		map.put("message", "查询成功");
		map.put("data", rolesList);
		return map;
	}

	//条件查询
	@RequestMapping(value = "/findById")
	public Map<String,Object> findById(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="id")String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		RolesEntity rolesEntity = rolesService.findId(id);
		if(rolesEntity!=null) {
			map.put("status", "0");
			map.put("data", rolesEntity);
		}else {
			map.put("status", "1");
		}
		return map;
	}
	
	//查询
	@RequestMapping(value = "/findByName")
	public Map<String,Object> findByName(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="roleName")String roleName,
			@RequestParam(name="roleType")Integer roleType,
			@RequestParam(name="page")Integer page,
			@RequestParam(name="size")Integer size) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pageable pageable = new PageRequest(page-1,size);
		Page<RolesEntity> roleList=  rolesService.findListByName(roleName, roleType,pageable);
		map.put("status", "0");//成功
		map.put("message","查询成功");
		map.put("data", roleList);
		return map;
	}
	
	//启用/禁用
	@RequestMapping(value="/enable")
	public Map<String, String> opensulf(HttpServletRequest res,HttpServletResponse req,
			@RequestParam(name="state")Integer state,@RequestParam(name="id")String id) {
		
		Map<String, String> map = new HashMap<String, String>();
		RolesEntity roleEntity = rolesService.findId(id);
		roleEntity.setState(state);
		roleEntity.getState();
		if(state.equals(0)) {
			roleEntity.setState(1);
			map.put("status", "0");
			map.put("message","禁用成功");
		}
		else if(state.equals(1)){
			roleEntity.setState(0);
			map.put("status", "0");
			map.put("message","启用成功");
		}
		rolesService.update(roleEntity);
		return map;
	}
}