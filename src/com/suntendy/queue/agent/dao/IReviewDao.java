package com.suntendy.queue.agent.dao;

import com.suntendy.queue.agent.vo.ReviewVO;

public interface IReviewDao {
	public String addReview(ReviewVO review) throws Exception;
}
