package com.jy.pc.Controller;

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

import com.jy.pc.Entity.CaseInfoCommentEntity;
import com.jy.pc.Entity.CaseInfoEntity;
import com.jy.pc.Entity.CaseInfoReplyEntity;
import com.jy.pc.Service.CaseInfoCommentService;
import com.jy.pc.Service.CaseInfoReplyService;
import com.jy.pc.Service.CaseInfoService;
//看图识病回复表Controller
@Controller
@RequestMapping(value = "/caseInfoComment")
public class CaseInfoCommentController {
	@Autowired
	private CaseInfoCommentService caseInfoCommentService;
	@Autowired
	private CaseInfoReplyService caseInfoReplyService;
	@Autowired
	private CaseInfoService caseInfoService;

	@RequestMapping(value = "/findCaseList")
	@ResponseBody
	public Map<String, Object> findCasePage(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "commentContent", defaultValue = "") String commentContent,
			@RequestParam(name = "commentUserName", defaultValue = "") String commentUserName,
			@RequestParam(name = "name", defaultValue = "") String name, @RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable = new PageRequest(page - 1, size);
		try {
			
			Page<List<Map<String, Object>>> caseInfoCommentList = caseInfoCommentService.findPageByCase(name, commentUserName, commentContent, pageable);
			map.put("code", "200");// 查询成功
			map.put("data", caseInfoCommentList);
		} catch (Exception e) {
			map.put("code", "500");// 查询失败
			map.put("msg", "查询失败");
			e.printStackTrace();
		}
		return map;
	}

	// 新增
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest res, HttpServletResponse req,CaseInfoCommentEntity caseInfoComment) {
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			caseInfoCommentService.saveCaseInfo(caseInfoComment);
			CaseInfoEntity caseInfo = caseInfoService.findBId(caseInfoComment.getCaseId());
			caseInfo.setCommentNum(caseInfo.getCommentNum()+1);
			caseInfoService.update(caseInfo);
			map.put("code", "200");
			map.put("message", "添加成功");
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "添加失败");
		}
		return map;
	}

	//删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CaseInfoCommentEntity caseInfoComment = caseInfoCommentService.findId(id);
			CaseInfoEntity caseInfo = caseInfoService.findBId(caseInfoComment.getCaseInfoEntity().getId());
			caseInfo.setCommentNum(caseInfo.getCommentNum()-1);
			caseInfoService.update(caseInfo);
			List<CaseInfoReplyEntity> caseInfoReply = caseInfoReplyService.findByCommentId(id);
			for(int i=0;i<caseInfoReply.size();i++) {
				caseInfoReplyService.delete(caseInfoReply.get(i).getId());
			}
			caseInfoCommentService.delete(id);
			map.put("code", "200");
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "删除失败");
			e.printStackTrace();
		}
		return map;
	}
	
	// 修改状态
	@RequestMapping(value = "/enable")
	@ResponseBody
	public Map<String, String> setSelect(HttpServletRequest res, HttpServletResponse req,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "id") String id) {
		Map<String, String> map = new HashMap<String, String>();
		CaseInfoCommentEntity caseInfoCommentEntity = caseInfoCommentService.findId(id);
		caseInfoCommentEntity.setStatus(status);
		boolean result = true;
		if (status.equals(1)) {
			caseInfoCommentEntity.setStatus(1);
			map.put("code", "200");
			map.put("message", "禁用成功");
		} else if (status.equals(0)) {
			caseInfoCommentEntity.setStatus(status);
			map.put("code", "200");
			map.put("message", "启用成功");
			result = false;
		}
		caseInfoCommentService.updateEnable(caseInfoCommentEntity, result);
		return map;
	}

}