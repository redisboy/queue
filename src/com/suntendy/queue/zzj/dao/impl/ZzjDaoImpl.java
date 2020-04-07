package com.suntendy.queue.zzj.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.zzj.dao.IZzjDao;
import com.suntendy.queue.zzj.domain.Zzj;

public class ZzjDaoImpl extends BaseDao<Zzj, String> implements IZzjDao {

	@Override
	public int delZzj(Zzj zzj) throws Exception {
		return getSqlMapClientTemplate().delete("Zzj.del", zzj.getQhjip());
	}

	@Override
	public List<Zzj> queryZzj(Zzj zzj) throws Exception {
		String[] properties = {"deptcode","depthall","qhjip"};
		Object[] propertyValues = {zzj.getDeptcode(),zzj.getDepthall(),zzj.getQhjip()};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	@Override
	public String saveZzj(Zzj zzj) throws Exception {
		return this.insert(zzj, "saveZzj");
	}

	@Override
	public int updateZzj(Zzj zzj) throws Exception {
		return getSqlMapClientTemplate().update("Zzj.update", zzj);
	}

	@Override
	public int czZzj(Zzj zzj) throws Exception {
		return getSqlMapClientTemplate().update("Zzj.czZzj", zzj);
	}

}
