package com.suntendy.queue.util.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.business.domain.Business;
import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.queue.util.cache.NumberManager;
import com.suntendy.queue.window.domain.Screen;

public class CacheManager {

	//队列信息
	private Map<String, Queue> queueCache = new ConcurrentHashMap<String, Queue>();
	
	//窗口信息
	private Map<String, Screen> windowCache = new ConcurrentHashMap<String, Screen>();
	
	//暂停信息
	private Map<String, String> pauseCache = new HashMap<String, String>();
	
	//系统配置信息
	private Map<String, String> systemConfigCache = new ConcurrentHashMap<String, String>();
	
	//登录信息 value格式：登录IP及相同用户登录是否同一IP(0:是,1:否) 如：IP@[0 OR 1]
	private Map<String, String> loginCache = new HashMap<String, String>();
	
	//更换电视视屏地址及名称
	private Map<String, String> replaceTV = new HashMap<String, String>();
	
	//管理部门和大厅号
	private Map<String, String> deptCache = new ConcurrentHashMap<String, String>();
	
	//用户参数缓存信息(硬件评价器使用)
	private Map<String, String> userParamsCache = new ConcurrentHashMap<String, String>();
	
	//LED屏初始化参数
	private Map<String, LED> ledCache = new ConcurrentHashMap<String, LED>();
	
	//LED发屏初始化参数
	private Map<String, LED> led_tvCache = new ConcurrentHashMap<String, LED>();
	
	//业务类型信息
	private Map<String, Business> businessTypeCache = new ConcurrentHashMap<String, Business>();
	
	//预约使用的临时缓存
	private Map<String, String> yuyueCache = new HashMap<String, String>();
	
	//用户登陆地址缓存
	private Map<String, String> yhCache = new HashMap<String, String>();
	
	private static CacheManager cacheManager = new CacheManager();

	private CacheManager() {}

	public static CacheManager getInstance() {
		return cacheManager;
	}
	
	/**********队列缓存操作方法**********/
	/**
	 * 添加队列信息
	 * @param key 队列编号
	 * @param value 队列信息
	 */
	public void addOfQueueCache(String key, Queue value) {
		synchronized (queueCache) {
			Queue queue = queueCache.get(key);
			if (null != queue) {
				value.setBeginNum(queue.getBeginNum());
			}
			
			queueCache.put(key, value);
		}
	}
	
	public Map<String, Queue> getQueueCache() {
		return queueCache;
	}
	
	/**
	 * 获取队列信息
	 * @param key 队列编号
	 * @return
	 */
	public Queue getQueue(String key) {
		return queueCache.get(key);
	}
	
	/**
	 * 删除队列
	 * @param key 队列编号
	 */
	public LED removeLedCache_TV(String key) {
		return led_tvCache.remove(key);
	}

	/**
	 * 删除队列
	 * @param key 队列编号
	 */
	public Queue removeQueue(String key) {
		return queueCache.remove(key);
	}
	/**********窗口缓存操作方法**********/
	/**
	 * 添加窗口
	 * @param key 窗口IP或窗口编号(父窗口)
	 * @param value 窗口信息
	 */
	public void addOfWindowCache(String key, Screen value) {
		windowCache.put(key, value);
	}
	
	/**
	 * 删除窗口
	 * @param key 窗口IP或窗口编号(父窗口)
	 */
	public void removeWindow(String key) {
		windowCache.remove(key);
	}
	
	public Map<String, Screen> getWindowCache() {
		return windowCache;
	}
	
	/**
	 * 根据IP获取窗口信息
	 * @param ip 窗口对应IP
	 * @return
	 */
	public Screen getWindow(String ip) {
		return windowCache.get(ip);
	}
	
	/**
	 * 判断LED屏地址是否存在
	 * @param address LED屏地址
	 * @return
	 */
	public boolean isExistOfAddress(String address) {
		Collection<Screen> screens = windowCache.values();
		for (Screen screen : screens) {
			if (address.equals(screen.getAddress())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断窗口编号是否存在
	 * @param windowCode 窗口编号
	 * @return
	 */
	public boolean isExistOfWindowCode(String windowCode) {
		Collection<Screen> screens = windowCache.values();
		for (Screen screen : screens) {
			if (windowCode.equals(screen.getWindowId()) || windowCode.equals(screen.getMenuAddress())) {
				return true;
			}
		}
		return false;
	}

	/**********暂停缓存操作方法**********/
	public void setPauseCache(String ip, String value) {
		pauseCache.put(ip, value);
	}
	
	public String pause(String ip) {
		return pauseCache.get(ip);
	}
	
	public boolean isPause(String ip) {
		String str = pause(ip);
		if (StringUtils.isEmpty(str)) {
			return false;
		} else {
			return "1".equals(str.split("@")[0]) ? true : false;
		}
	}

	/**********系统设置缓存操作方法**********/
	/**
	 * 添加系统设置
	 * @param key 配置名
	 * @param value 配置值
	 */
	public void addOfSystemConfig(String key, String value) {
		systemConfigCache.put(key, value);
	}
	
	/**
	 * 获取系统设置
	 * @param key 配置名
	 * @return 配置值
	 */
	public String getSystemConfig(String key) {
		synchronized (systemConfigCache) {
			return systemConfigCache.get(key);
		}
	}
	
	/**********登录信息缓存操作方法**********/
	/**
	 * 保存登录信息
	 * @param code 员工编号
	 * @param ip 登录IP
	 */
	public void addOfLoginCache(String code, String ip) {
		String ipAndResult = loginCache.get(code);
		if (StringUtils.isEmpty(ipAndResult)) {
			loginCache.put(code, ip + "@0");
		} else {
			String[] arr = ipAndResult.split("@");
			Number searchNumber = NumberManager.getInstance().fetchCallNumber(code);
			if (!ip.equals(arr[0]) && null != searchNumber) {
				loginCache.put(code, ip + "@1");
			} else {
				loginCache.put(code, ip + "@0");
			}
		}
	}
	
	/**
	 * 判断是否同一IP登录
	 * @param code 员工编号
	 * @return 是true 否false
	 */
	public boolean isSameIP(String code, String loginIP) {
		String cache = loginCache.get(code);
		if (StringUtils.isNotEmpty(cache)) {
			String cacheIP = cache.split("@")[0];
			return loginIP.equals(cacheIP) ? true : false;
		} else {
			return true;
		}
	}
	
	/**
	 * 判断员工是否重复登录
	 * @param code 员工编号
	 * @param loginIP 登录IP
	 * @param str 拼接信息
	 * @return
	 */
	public String userCompare(String code, String loginIP, String str) {
		String cache = loginCache.get(code);
		if (StringUtils.isNotEmpty(cache)) {
			String[] arr = cache.split("@");
			if (loginIP.equals(arr[0]) && "1".equals(arr[1])) {
				return "此账号正在其它窗口办理业务，<br/>" + str;
			} else if (!loginIP.equals(arr[0]) && "0".equals(arr[1])) {
				return "未叫号，" + str;
			}
		}
		
		return "";
	}
	
	/**
	 * 修改登录状态为0
	 * @param code
	 */
	public void updateLoginIpStatus(String code) {
		String[] arr = loginCache.get(code).split("@");
		loginCache.put(code, arr[0] + "@0");
	}
	
	/**********部门信息缓存操作方法**********/
	/**
	 * 添加部门信息
	 * @param key 参考数据库配置文件
	 * @param value
	 */
	protected void addOfDeptCache(String key, String value) {
		deptCache.put(key, value);
	}
	
	/**
	 * 获取部门信息
	 * @param key 参考数据库配置文件
	 * @return
	 */
	public String getOfDeptCache(String key) {
		return deptCache.get(key);
	}
	
	/**********操作员参数缓存操作方法**********/
	/**
	 * 添加操作员参数缓存
	 * @param key 操作员编号
	 * @param value JSON格式参数值
	 */
	public void addOfUserParamsCache(String key, String value) {
		userParamsCache.put(key, value);
	}
	
	/**
	 * 获取操作员参数缓存
	 * @param key 操作员编号
	 * @return
	 */
	public String getUserParams(String key) {
		return userParamsCache.get(key);
	}
	
	/**********LED屏初始化参数缓存操作方法**********/
	/**
	 * 添加LED屏初始化参数
	 * @param key 管理部门+大厅号
	 * @param value LED屏初始化参数
	 */
	public void addOfLedCache(String key, LED value) {
		if (ledCache.containsKey(key)) {
			synchronized (windowCache) {
				Collection<Screen> screens = windowCache.values();
				for (Screen screen : screens) {
					if (StringUtils.isEmpty(screen.getAddress())) {
						continue;
					}
					screen.setLed(value);
				}
			}
		}
		ledCache.put(key, value);
	}
	
	/**
	 * 获取LED初始化参数
	 * @param key 管理部门+大厅号
	 */
	public LED getLedCache(String key) {
		return ledCache.get(key);
	}
	
	public void addOfBusinessTypeCache(String key, Business value) {
		businessTypeCache.put(key, value);
	}
	
	public String getBusinessTypeOfQueueCode(String key) {
		synchronized (businessTypeCache) {
			return businessTypeCache.get(key).getQueueCode();
		}
	}
	
	public void removeBusiness(String key) {
		synchronized (businessTypeCache) {
			businessTypeCache.remove(key);
		}
	}
	
	protected void clearAll() {
		NumberManager.getInstance().clear();
		queueCache.clear();
		pauseCache.clear();
		loginCache.clear();
		windowCache.clear();
		systemConfigCache.clear();
	}

	public Map<String, String> getReplaceTV() {
		return replaceTV;
	}

	public void setReplaceTV(Map<String, String> replaceTV) {
		this.replaceTV = replaceTV;
	}
	public String getTVPath(String key) {
		return replaceTV.get(key);
	}

	public Map<String, LED> getLed_tvCache() {
		return led_tvCache;
	}

	public LED getLed_tvCache(String key) {
		return led_tvCache.get(key);
	}
	public void addOfLed_tvCache(String key, LED value) {
		led_tvCache.put(key, value);
	}
	/**
	 * 把预约传入的预约信息id加入到缓存
	 * @param sfzmhm 身份证号
	 * @param yuyueId 预约信息的id
	 */
	public void addYuYueToCache(String sfzmhm,String yuyueId){
		yuyueCache.put(sfzmhm, yuyueId);
	}
	/**
	 * 从缓存取出预约id
	 * @param sfzmhm 身份证号
	 * @return
	 */
	public String getYuYueIdInCache(String sfzmhm){
		String yuyueId = "";
		if(yuyueCache.get(sfzmhm)!=null){
			yuyueId = yuyueCache.get(sfzmhm);
		}
		return yuyueId;
	}
	
	public void deleteYuYueId(String sfzmhm){
		yuyueCache.remove(sfzmhm);
	}
	
	public String getYhCache(String dlip){
		return yhCache.get(dlip);
	}
	public void putYhCache(String dlip,String yh){
		yhCache.put(dlip, yh);
	}
	
}