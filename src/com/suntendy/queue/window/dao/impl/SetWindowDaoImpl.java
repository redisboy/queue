package com.suntendy.queue.window.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntendy.queue.base.BaseDao;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.dao.ISetWindowDao;
import com.suntendy.queue.window.domain.Screen;

public class SetWindowDaoImpl extends BaseDao<Screen, Integer>  implements ISetWindowDao{

	public List<Screen> getWindowList(String deptCode, String deptHall,
			String id, String ip, String type,String businessid,String ckip) throws Exception {
		String[] properties = { "deptCode", "deptHall", "id", "ip", "type","businessid","ckip" };
		String[] propertyValues = { deptCode, deptHall, id, ip, type, businessid,ckip};
		return this.findByMap(properties, propertyValues, "", "", BaseDao.SELECTBYMAP);
	}
	
	public List<Screen> getckxx(String deptCode, String deptHall,String ckip) throws Exception {
		String[] properties = { "deptCode", "deptHall","ckip" };
		String[] propertyValues = { deptCode, deptHall,ckip};
		return this.findByMap(properties, propertyValues, "", "", "getckxx");
	}

	public int updateScreenByCode(String barid, String com1, String address,
			String stoptime, String interval, String word, String color,
			String speed,String queueid) {
		String[] properties = { "barid", "com1","address","stoptime","interval","word","color","speed","queueid"};
		
	    String[] propertyValues = { barid,com1,address,stoptime,interval,word,color,speed,queueid};
	    try {
			return this.updateByMap(Integer.valueOf(barid), properties, propertyValues, BaseDao.UPDATEBYMAP);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Integer addBar(Screen screen) throws Exception {
		return this.insert(screen, "insertBar");
	}

	public Integer addCaller(Screen screen) throws Exception {
		return insert(screen, "insertCaller");
	}

	public Integer addScreen(Screen screen) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		screen.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		screen.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		return insert(screen, "insertScreen");
	}

	public Integer addValue(Screen screen) throws Exception {
		return insert(screen, "insertValue");
	}

	public int updateBar(Screen screen) throws Exception {
		String[] properties = { "pj" };
	    String[] propertyValues = { screen.getAllownovalue() };
		return this.updateByMap(Integer.valueOf(screen.getId()), 
				properties, propertyValues, "updateBar");
	}

	public int updateCaller(Screen screen) throws Exception {
		String[] properties = { "com2", "ipaddress","ckip"};
	    String[] propertyValues = { screen.getCom2(), screen.getBarip(),screen.getCkip() };
		return this.updateByMap(Integer.valueOf(screen.getId()),
					properties, propertyValues, "updateCaller");
	}
	
	public int updateCallerByIp(Screen screen) throws Exception {
	    return this.getSqlMapClientTemplate().update("Screen.updateCallerByIp", screen);
	}

	public int updateScreen(Screen screen) throws Exception {
		String[] properties = { "barid","ledWindowHeight", "ledWindowWidth", "com1", "address", "stoptime", "interval", "word", "color", "speed", "businessid",
				"priority", "menuAddress", "windowid", "nextWindow", "content", "align", "showNum", "lattice", "color1",
				"openInterFace","isOpenOldDevice","isOpenInformation","windowGDContent","isgd","content3","content4","color2","color3","threegd","fourgd" };
		String[] propertyValues = { screen.getAddress(),screen.getLedWindowHeight(),screen.getLedWindowWidth(), screen.getComnum(), screen.getAddress(), screen.getStoptime(),
				screen.getInterval(), screen.getWord(), screen.getColor(), screen.getSpeed(), screen.getBusinessid(),
				screen.getPriority(), screen.getMenuAddress(), screen.getWindowId(), screen.getNextWindow(),
				screen.getContent(), screen.getAlign(), screen.getShowNum(), screen.getLattice(),screen.getColor1(),screen.getOpenInterFace(),
				screen.getIsOpenOldDevice(),screen.getIsOpenInformation(),screen.getWindowGDContent(),screen.getIsgd(),
				screen.getContent3(),screen.getContent4(),screen.getColor2(),screen.getColor3(),screen.getThreegd(),screen.getFourgd() };
		return this.updateByMap(Integer.valueOf(screen.getId()), properties,propertyValues, "updateWindow");
	}

	public int updateValue(Screen screen) throws Exception {
		String[] properties = { "defaultvalue" };
	    String[] propertyValues = { screen.getValuemust() };
		return this.updateByMap(Integer.valueOf(screen.getId()), properties,
				propertyValues, "updateValue");
	}

	public int delBar(String barid) throws Exception {  
		return this.deleteById(Integer.valueOf(barid), "deleteBar");
	}

	public int delCaller(String barid) throws Exception {
		return this.deleteById(Integer.valueOf(barid), "deleteCaller");
	}

	public int delScreen(String barid) throws Exception {
		return this.deleteById(Integer.valueOf(barid), "deleteScreen");
	}

	public int delValue(String barid) throws Exception {
		return this.deleteById(Integer.valueOf(barid), "deleteValue");
	}

	public List<Screen> getAddress() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		String[] properties = { "deptCode", "deptHall" };
	    String[] propertyValues = { deptCode, deptHall };
	    return this.findByMap(properties, propertyValues, "", "", "getAddress");
	}

	public int updateWindowById(Screen screen) throws Exception {
		String[] properties = {"businessid","Priority" };
	    String[] propertyValues = {screen.getBusinessid(),screen.getPriority() };
		return this.updateByMap(Integer.valueOf(screen.getId()), properties,propertyValues, "updateWindowById");
	}
	public List<Screen> getAddressAndStatuss() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		String[] properties = { "deptCode", "deptHall" };
	    String[] propertyValues = { deptCode, deptHall };
	    return this.findByMap(properties, propertyValues, "", "", "getAddressAndStatuss");
	}

	@Override
	public List<Screen> getCountShul(String deptCode,String deptHall) throws Exception {
		String[] properties = { "deptCode", "deptHall" };
	    String[] propertyValues = { deptCode, deptHall };
	    return this.findByMap(properties, propertyValues, "", "", "getCountShul");
	}

	@Override
	public List<Screen> querybarid(Screen screen) throws Exception {
		String[] properties = { "barip"};
	    String[] propertyValues = {screen.getBarip()};
	    return this.findByMap(properties, propertyValues, "", "", "querybarid");
	}

	@Override
	public List<Screen> searchBar(Screen screen) throws Exception {
		// TODO Auto-generated method stub
		String[] properties = {};
	    String[] propertyValues = {};
	    return this.findByMap(properties, propertyValues, "", "", "searchBar");
	}

	@Override
	public List<Screen> queryEveryScreenYWL(String deptCode,String deptHall) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptCode", deptCode);
		map.put("deptHall", deptHall);
		return this.getSqlMapClientTemplate().queryForList("Screen.queryEveryScreenYWL",map);
	}
}