package com.suntendy.queue.agent.dao.impl;

import com.suntendy.queue.agent.dao.IReviewDao;
import com.suntendy.queue.agent.vo.ReviewVO;
import com.suntendy.queue.base.BaseDao;

public class ReviewDaoImpl extends BaseDao<ReviewVO,String>implements IReviewDao {

	public String addReview(ReviewVO review) throws Exception {
		return this.insert(review, BaseDao.INSERT);
	}
}
