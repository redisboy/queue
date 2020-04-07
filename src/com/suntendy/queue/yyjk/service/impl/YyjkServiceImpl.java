package com.suntendy.queue.yyjk.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.suntendy.queue.util.HttpRequestUtil.HttpRequestUtil;
import com.suntendy.queue.yyjk.dao.IYyjkDao;
import com.suntendy.queue.yyjk.domain.FoShanCLCYXX;
import com.suntendy.queue.yyjk.domain.GuangZhouYYXX;
import com.suntendy.queue.yyjk.domain.NanNingYYXX;
import com.suntendy.queue.yyjk.domain.ZhongShanYYXX;
import com.suntendy.queue.yyjk.service.IYyjkService;

public class YyjkServiceImpl implements IYyjkService{
	private IYyjkDao yyjkDao;
	
	public IYyjkDao getYyjkDao() {
		return yyjkDao;
	}
	public void setYyjkDao(IYyjkDao yyjkDao) {
		this.yyjkDao = yyjkDao;
	}
	/**
	 * 根据 身份证号 或 组织机构代码 查询南宁预约信息
	 */
	public List<NanNingYYXX> findNanNingYYXX(NanNingYYXX nanNingYYXX)
			throws Exception {
		return yyjkDao.findNanNingYYXX(nanNingYYXX);
	}
	@Override
	public void updateNanNingYYXX(NanNingYYXX nanNingYYXX) throws Exception {
		yyjkDao.updateNanNingYYXX(nanNingYYXX);
	}
	@Override
	public List<ZhongShanYYXX> findZhongShanYYXX(ZhongShanYYXX zhongShanYYXX)
			throws Exception {
		return yyjkDao.findZhongShanYYXX(zhongShanYYXX);
	}
	@Override
	public void updateZhongShanYYXX(ZhongShanYYXX zhongShanYYXX)
			throws Exception {
		yyjkDao.updateZhongShanYYXX(zhongShanYYXX);
	}
	@Override
	public List<GuangZhouYYXX> findGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX)
			throws Exception {
		return yyjkDao.findGuangZhouYYXX(guangZhouYYXX);
	}
	@Override
	public void updateGuangZhouYYXX(GuangZhouYYXX guangZhouYYXX) throws Exception {
		yyjkDao.updateGuangZhouYYXX(guangZhouYYXX);
	}
	@Override
	public void readAllYYXX(JSONObject json) throws Exception {
		//通过接口获取所有预约信息
		JSONArray jsonArray = json.getJSONArray("body");
		if(jsonArray.isEmpty()){
			System.out.println("查询到预约人数为0");
		}else{
			Object[] array = jsonArray.toArray();
			GuangZhouYYXX guangZhouYYXX = new GuangZhouYYXX();
			for(int i = 0 ; i < array.length ; i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				guangZhouYYXX.setId(jsonObject.get("yylsh").toString());
				GuangZhouYYXX guangZhouYYXX2 = new GuangZhouYYXX();
				guangZhouYYXX2.setId(jsonObject.get("yylsh").toString());;
				List<GuangZhouYYXX> findResult = yyjkDao.findGuangZhouYYXX(guangZhouYYXX2);
				if(findResult.size()==0){
					guangZhouYYXX.setSfzmhm(jsonObject.get("sfzmhm").toString());
					guangZhouYYXX.setAddress(jsonObject.get("fsbh").toString());
					guangZhouYYXX.setYyrq(jsonObject.get("yyrq").toString());
					guangZhouYYXX.setYysjd(jsonObject.get("yysj").toString());
					yyjkDao.addGuangZhouYYXX(guangZhouYYXX);
				}
			}
		}
		
	}
	@Override
	public List<FoShanCLCYXX> selectFoShanCLCYXX(FoShanCLCYXX foShanCLCYXX) throws Exception {
		return yyjkDao.selectFoShanCLCYXX(foShanCLCYXX);
	}
	
}
