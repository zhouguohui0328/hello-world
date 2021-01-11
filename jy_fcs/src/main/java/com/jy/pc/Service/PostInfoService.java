package com.jy.pc.Service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.PostInfoEntity;

public interface PostInfoService {

	// 农活预约总数
	public List<PostInfoEntity> findInva();

	// 添加
	public PostInfoEntity save(PostInfoEntity postInfo);

	// 修改
	public void update(PostInfoEntity invitation);

	// 查询
	public Page<PostInfoEntity> findListByName(String name, String createUser, Pageable pageable);

	// findById
	public PostInfoEntity findId(String id);

	public Page<PostInfoEntity> findListWithSub(String postType, Pageable pageable);

	void enable(PostInfoEntity invitation, boolean result);

	void audit(PostInfoEntity invitation, boolean result);

	// 删除
	public void delete(String id);

	PostInfoEntity Boutique(PostInfoEntity invitation, boolean result);

	// 添加帖子
	public PostInfoEntity saveAgr(String[] addItem, PostInfoEntity postInfoEntity);

	// 查询最新最火热议好评精品
	public Page<PostInfoEntity> findByType(String type, Pageable pageable);

	//	我的收藏查詢列表
	List<PostInfoEntity> findByUserId() throws ServiceException;

}
