package com.suntendy.queue.yygn.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.yygn.domain.Yyxz;
import com.suntendy.queue.yygn.dao.IYyxzDao;



public class YyxzDaoImpl extends BaseDao<Yyxz, String>  implements IYyxzDao{

	@Override
	public Integer addYyxz(Yyxz yyxz) throws Exception {
		this.getSqlMapClientTemplate().insert("Yyxz.addyyxz",yyxz);
		return 0;
	}

	@Override
	public Integer updateYyxz(Yyxz yyxz) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("Yyxz.updateyyxz", yyxz);
		return 0;
	}

	@Override
	public List<Yyxz> getYyxzList() throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
		String[] propertyValues = {};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}


	
	
	
}
