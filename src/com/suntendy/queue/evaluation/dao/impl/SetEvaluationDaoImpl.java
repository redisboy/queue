package com.suntendy.queue.evaluation.dao.impl;

import java.util.List;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.evaluation.dao.ISetEvaluationDao;
import com.suntendy.queue.evaluation.domain.ValueRecord;
import com.suntendy.queue.util.DateUtils;

public class SetEvaluationDaoImpl extends BaseDao<ValueRecord, Integer>  implements ISetEvaluationDao {
	
	@Override
	public void add(List<ValueRecord> datas) throws Exception {
		batchInsert(datas, BaseDao.INSERT);
	}

	public void updatePj(List<ValueRecord> datas) throws Exception {
		batchUpdate(datas, BaseDao.UPDATE);
	}

	public List<ValueRecord> getPj(String deptCode, String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall" };
		String[] propertyValues = { deptCode, deptHall };
		return findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}

	public List<ValueRecord> getValidPj(String deptCode, String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall" };
		String[] propertyValues = { deptCode, deptHall };
		return findByMap(properties, propertyValues, "", "", "getValidPj");
	}
	/**
	 * 查看摄像头及手写板图片
	 * @return List
	 */
	public List<ValueRecord> getPhotoAll(ValueRecord valueRecord ) throws Exception {
		if (valueRecord.getTjms().equals("0")) {
			if (valueRecord.getKsrq() != "" && valueRecord.getJsrq() != "") {
				valueRecord.setKsrq("to_date('" + valueRecord.getKsrq() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				valueRecord.setJsrq("to_date('" + valueRecord.getJsrq() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}
		} else if (valueRecord.getTjms().equals("1")) {
			String currentTime = DateUtils.dateToString("yyyy-MM-dd"); // 当天时间
			String currentTime1 = "'" + currentTime + " 00:00:00" + "'";
			String currentTime2 = "'" + currentTime + " 23:59:59" + "'";
			valueRecord.setKsrq( "to_date(" + currentTime1 + ",'yyyy-mm-dd hh24:mi:ss')");
			valueRecord.setJsrq("to_date(" + currentTime2 + ",'yyyy-mm-dd hh24:mi:ss')");
		} else if (valueRecord.getTjms().equals("2")) {
			valueRecord.setKsrq("to_date(to_char(sysdate,'YYYY-MM')||'01 00:00:00','YYYY-MM-DD hh24:mi:ss')");
			valueRecord.setJsrq("to_date(to_char(last_day(sysdate),'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		} else if (valueRecord.getTjms().equals("3")) {
			valueRecord.setKsrq("to_date(to_char(trunc(sysdate, 'Q'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			valueRecord.setJsrq("to_date(to_char(add_months(TRUNC(SYSDATE,'Q'),+3)-1,'YYYY-MM-DD')||' 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
		} else if (valueRecord.getTjms().equals("4")) {
			valueRecord.setKsrq("to_date(to_char(trunc(sysdate,'yyyy'),'yyyy-mm-dd')||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
			valueRecord.setJsrq("to_date(to_char(add_months(trunc(sysdate,'yyyy'),12)-1,'yyyy-mm-dd')||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
			String[] properties = { "ksrq", "jsrq" , "idnumber","deptCode","deptHall","id"};
			String[] propertyValues = { valueRecord.getKsrq(), valueRecord.getJsrq() ,valueRecord.getIdnumber(),valueRecord.getDeptCode(),valueRecord.getDeptHall(),valueRecord.getId()};
			return this.findByMap(properties, propertyValues, "", "", "getPhotoAll");
	}
}
