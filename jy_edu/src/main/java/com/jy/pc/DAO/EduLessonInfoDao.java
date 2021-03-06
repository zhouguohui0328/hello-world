package com.jy.pc.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduLessonInfoEntity;

public interface EduLessonInfoDao extends JpaRepository<EduLessonInfoEntity, String> {
	// GetById方法
	@Query(value = "select * from edu_lesson_info  where id =:id", nativeQuery = true)
	public EduLessonInfoEntity GetById(@Param("id") String id);

	// 分页与模糊查询
	@Query(value = "select * from edu_lesson_info  t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) and if(?3 !='',t.create_by like ?3,1=1) order by t.create_date desc", countQuery = "select count(*) from edu_lesson_info t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.status = ?2,1=1) and if(?3 !='',t.create_by like ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduLessonInfoEntity> findListByName(String name, String status, String createBy, Pageable pageable);

	// 移动端 - 首页-线下课程加载
	@Query(value = "select * from edu_lesson_info where lesson_day > now() and status = 0 order by lesson_day asc limit 2", nativeQuery = true)
	public List<EduLessonInfoEntity> getListByReading();
	
	// app课程列表加载
	@Query(value = "select * from edu_lesson_info  t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.create_by like ?2,1=1) and t.status='0' order by t.lesson_day asc",
			countQuery = "select count(*) from edu_lesson_info t  where if(?1 !='',t.title like ?1,1=1) and if(?2 !='',t.create_by like ?2,1=1) and t.status='0' order by t.lesson_day asc", nativeQuery = true)
	public Page<EduLessonInfoEntity> findLessonList(String name, String createBy, Pageable pageable);

}
