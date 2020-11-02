package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.EduQuestionInfoEntity;
/**
 * 试题表Dao
 * */
public interface EduQuestionInfoDao extends JpaRepository<EduQuestionInfoEntity, String>{
	// 分页与模糊查询
	@Query(value = "select * from edu_question_info  t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.qu_type like ?2,1=1) and if(?3 !='',t.status like ?3,1=1) order by t.create_date desc",
			countQuery = "select count(*) from edu_question_info t  where if(?1 !='',t.create_by like ?1,1=1) and if(?2 !='',t.qu_type like ?2,1=1) and if(?3 !='',t.status like ?3,1=1) order by t.create_date desc", nativeQuery = true)
	public Page<EduQuestionInfoEntity> findListByName(String createBy,String quType, String status, Pageable pageable);

	// 通过id查询
	@Query(value = "select * from edu_question_info t where t.id =:id", nativeQuery = true)
	public EduQuestionInfoEntity findId(@Param("id") String id);
}