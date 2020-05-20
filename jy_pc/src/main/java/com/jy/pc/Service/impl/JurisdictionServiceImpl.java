package com.jy.pc.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.pc.DAO.JurisdictionDao;
import com.jy.pc.Entity.JurisdictionEntity;
import com.jy.pc.Service.JurisdictionService;

@Service
public class JurisdictionServiceImpl implements JurisdictionService{
	@Autowired
	private JurisdictionDao jurisdictionDao;
	@Override
	public void save(JurisdictionEntity jurisdiction) {
		// TODO Auto-generated method stub
		jurisdictionDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void update(JurisdictionEntity jurisdiction) {
		// TODO Auto-generated method stub
		
		jurisdictionDao.saveAndFlush(jurisdiction);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		jurisdictionDao.deleteById(id);
	}

	@Override
	public List<JurisdictionEntity> findAll() {
		// TODO Auto-generated method stub
		return jurisdictionDao.findAll();
	}

	@Override
	public JurisdictionEntity findId(String id) {
		// TODO Auto-generated method stub
		return jurisdictionDao.findId(id);
	}

	@Override
	public List<JurisdictionEntity> findListByName(String name, Integer type) {
		// TODO Auto-generated method stub
		//String jurName = "%"+name+"%";
		//String type = "%"+roleType+"%";
		return jurisdictionDao.findListByName(name, type);
	}

}