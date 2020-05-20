package com.jy.pc.Service;

import java.util.List;

import com.jy.pc.Entity.RolesEntity;

public interface RolesService {
	// 添加
	public void save(RolesEntity roles);

	// 修改
	public void update(RolesEntity roles);

	// 删除
	public void delete(String id);

	// 查询所有
	public List<RolesEntity> findAll();

	// 按条件查询
	public RolesEntity findId(String id);
	
	//搜索
	public List<RolesEntity> findListByName(String roleName,Integer roleType);

}