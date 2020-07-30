package com.jy.pc.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.MenuEntity;

public interface MenuService {
	// 搜索
	public List<MenuEntity> findListByName(String name);

	// 添加
	public MenuEntity save(MenuEntity menuEntity);

	// 修改
	public void update(MenuEntity menuEntity);

	// 删除
	public void delete(String id);

	// 主鍵查詢
	public MenuEntity findId(String id);
	
	// 查询是否有子菜单
	public boolean hasSubMenu(String parentId);
}
