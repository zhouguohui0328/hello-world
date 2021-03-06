package com.jy.pc.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jy.pc.Entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity,String> {
	@Query(value="select * from sas_role t where t.id =:id",nativeQuery = true)
	public RoleEntity findId(@Param("id")String id);
	@Query(value="select * from sas_role t where if(?1 !='',t.name like ?1,1=1) order by t.add_date desc",
			countQuery="select count(*) from sas_role t where if(?1 !='',t.name like ?1,1=1) order by t.add_date desc",nativeQuery = true)
	public Page<RoleEntity> findListByName(String name,Pageable pageable);
}
