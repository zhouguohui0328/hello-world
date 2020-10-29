package com.jy.pc.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.pc.Entity.EduManualInfoEntity;
/**
 * 手册Service
 * */
public interface EduManualInfoService {
	
	// 分页与模糊查询
	public Page<EduManualInfoEntity> findListByName(String title,String createBy, String vocationId, String labelId, Pageable pageable);

	// 通过id查询
	public EduManualInfoEntity findId(String id);
	//添加
	public EduManualInfoEntity save(EduManualInfoEntity eduManualInfoEntity);
	
	//修改
	public void update(EduManualInfoEntity eduManualInfoEntity);
	
	//删除
	public void delete(String id);
	
	//调整状态
	void enable(EduManualInfoEntity eduManualInfoEntity,boolean result);

}
