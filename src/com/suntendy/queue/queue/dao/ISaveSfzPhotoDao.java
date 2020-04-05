package com.suntendy.queue.queue.dao;

import java.util.List;

import com.suntendy.queue.queue.domain.NumberIdPhoto;

public interface ISaveSfzPhotoDao {
	/**
	 * 保存身份证照片
	 * @param numphoto
	 * @throws Exception
	 */
	public void insertSfzPhoto(NumberIdPhoto numphoto) throws Exception;
	/**
	 * 更新身份证照片
	 * @param numphoto
	 * @throws Exception
	 */
	public void updateSfzPhoto(NumberIdPhoto numphoto) throws Exception;
	/**
	 * 根据条件查询身份证照片
	 * @param numphoto
	 * @return
	 * @throws Exception
	 */
	public List<NumberIdPhoto> queryPhotoBy(NumberIdPhoto numphoto) throws Exception;

}
