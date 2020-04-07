package com.suntendy.queue.queue.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.queue.dao.ISaveSfzPhotoDao;
import com.suntendy.queue.queue.domain.NumberIdPhoto;
import com.suntendy.queue.util.DateUtils;

public class SaveShzPhotoDaoImpl extends BaseDao<NumberIdPhoto, String> implements ISaveSfzPhotoDao {

	public void insertSfzPhoto(NumberIdPhoto numphoto) throws Exception {
		this.insert(numphoto, "saveSfzPhoto");
	}

	public List<NumberIdPhoto> queryPhotoBy(NumberIdPhoto numphoto)
			throws Exception {
		String[] properties = {"numberId"};
		String[] propertyValues = {numphoto.getNumberId()};
		return this.findByMap(properties, propertyValues, "", "", "querySfzPhoto");
	}

	public void updateSfzPhoto(NumberIdPhoto numphoto) throws Exception {
			this.getSqlMapClientTemplate().update("NumberIdPhoto.updateSfzPhoto",numphoto);
	}

}
