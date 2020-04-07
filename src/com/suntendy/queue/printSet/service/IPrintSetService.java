package com.suntendy.queue.printSet.service;

import java.util.List;

import com.suntendy.queue.printSet.domain.Print;

public interface IPrintSetService {

	/**
	 * 更改打印条格式
	 * @param print
	 * @throws Exception
	 */
	public void PrintSet(Print print) throws Exception;
	
	/**
	 * 获取打印条内容
	 * @return
	 * @throws Exception
	 */
	public List<Print> getPrint(String deptCode,String deptHall) throws Exception;
}
