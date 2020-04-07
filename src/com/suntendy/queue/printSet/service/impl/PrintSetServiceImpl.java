package com.suntendy.queue.printSet.service.impl;

import java.util.List;

import com.suntendy.queue.printSet.dao.IPrintSetDao;
import com.suntendy.queue.printSet.domain.Print;
import com.suntendy.queue.printSet.service.IPrintSetService;

public class PrintSetServiceImpl implements IPrintSetService {

	private IPrintSetDao printSetDao;

	public IPrintSetDao getPrintSetDao() {
		return printSetDao;
	}

	public void setPrintSetDao(IPrintSetDao printSetDao) {
		this.printSetDao = printSetDao;
	}

	public void PrintSet(Print print) throws Exception {
		printSetDao.PrintSet(print);
	}

	public List<Print> getPrint(String deptCode, String deptHall) throws Exception {
		return printSetDao.getPrint(deptCode, deptHall);
	}

}
