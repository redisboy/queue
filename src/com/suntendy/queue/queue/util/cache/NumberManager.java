package com.suntendy.queue.queue.util.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.queue.domain.Queue;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.window.domain.Screen;

public class NumberManager {

	//单独大队列号码顺序
	private int singleIndex = 0;
	//单独大队列未叫号
	private TreeSet<Number> singleQueueNumberCache = new TreeSet<Number>(new Comparator<Number>() {
		@Override
		public int compare(Number num1, Number num2) {
			return num1.getEnterTime().compareTo(num2.getEnterTime());
		}
	});
	
	//各自队列未叫号总和
	private TreeSet<Number> multipQueueNumberCacheCount = new TreeSet<Number>(new Comparator<Number>() {
		@Override
		public int compare(Number num1, Number num2) {
			return num1.getEnterTime().compareTo(num2.getEnterTime());
		}
	});
	
	//各自队列未叫号码
	private Map<String, TreeSet<Number>> multipleQueueNumberCache = new HashMap<String, TreeSet<Number>>();
	
	//已叫号
	private Map<String, Number> callNumberCache = new ConcurrentHashMap<String, Number>();
	
	private NumberManager() {}
	private static NumberManager numberManager = new NumberManager();
	public static NumberManager getInstance() {
		return numberManager;
	}
	
	public void setSingleIndex(int singleIndex) {
		this.singleIndex = singleIndex;
	}
	
	public int getSingleIndex() {
		return this.singleIndex;
	}
	
	/**
	 * 添加队列的号码池，用于存储号码
	 * @param key 队列编号+队列前缀
	 * @param numberPool 队列号码池
	 */
	public void addQueueNumberPool(String key, TreeSet<Number> numberPool) {
		multipleQueueNumberCache.put(key, numberPool);
	}
	
	/**
	 * 删除队列的号码池
	 * @param key 队列编号+队列前缀
	 */
	public void removeQueueNumberPool(String key) {
		synchronized (multipleQueueNumberCache) {
			multipleQueueNumberCache.remove(key);
		}
	}
	
	/**
	 * 将号码添加到号码池
	 * @param key 队列编号+队列前缀，如果是单独大队列，可为空
	 * @param number 号码信息
	 */
	public void addNumberToNumberPool(String key, Number number) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				singleQueueNumberCache.add(number);
			}
		} else {
			synchronized (multipleQueueNumberCache) {
				multipleQueueNumberCache.get(key).add(number);
			}
		}
	}
	
	/**
	 * 将号码从号码池中删除
	 * @param key 队列编号+队列前缀，如果是单独大队列，可为空
	 * @param number 要删除的号码
	 */
	public void removeNumberFromNumberPool(String key, Number number) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				singleQueueNumberCache.remove(number);
			}
		} else {
			synchronized (multipleQueueNumberCache) {
				multipleQueueNumberCache.get(key).remove(number);
				multipQueueNumberCacheCount.remove(number);
			}
		}
	}
	
	public Set<Number> getSingleQueueNumberCache() {
		return singleQueueNumberCache;
	}
	
	/**
	 * 创建新号
	 * @param prefix 队列前缀
	 * @param queueCode 队列编号
	 * @return
	 */
	public String generateNumber(String prefix, String queueCode) {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		//判断是大队列模式还是小队列模式
		String serialNumType = cacheManager.getSystemConfig("serialNumType");
		
		synchronized (this) {
			String serialNum = "";
			if ("1".equals(serialNumType)) {
				serialNum = StringUtils.leftPad(String.valueOf(++ singleIndex), 5, '0');
			} else {
				Queue queue = cacheManager.getQueue(queueCode);
				int currentValue = Integer.parseInt(queue.getBeginNum()) + 1;
				serialNum = prefix + StringUtils.leftPad(String.valueOf(currentValue), 4, '0');
				queue.setBeginNum(String.valueOf(currentValue));
			}
			
			return deptCode + deptHall + serialNum;
		}
	}
	
	/**
	 * 将号码添加到号码池, 返回添加号码之前的号码总数
	 * @param number 号码信息
	 * @return 添加号码之前的号码总数
	 */
	public int saveNewNumber(Number number) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		System.out.println("将号码添加到号码池号码状态="+number.getStatus());
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				singleQueueNumberCache.add(number);
				return singleQueueNumberCache.size() - 1;
			}
		} else {
			synchronized (multipleQueueNumberCache) {
				String key = number.getQueueCode() + number.getBusinessPrefix();
				TreeSet<Number> selfNumberPool = multipleQueueNumberCache.get(key);
				selfNumberPool.add(number);
				multipQueueNumberCacheCount.add(number);
				return multipQueueNumberCacheCount.size() - 1;
			}
		}
	}
	
	/**
	 * 叫号
	 * @param businessType 业务类型(多种)
	 * @return
	 * @throws InterruptedException 
	 */
	public Number fetchNumber(String businessType) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		Number number= new Number();
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				if(singleQueueNumberCache.isEmpty()){
					number=null;
				}else{
			    number = singleQueueNumberCache.first();
				number.setStatus("2");
				return number;
			 }
			}
		} else {
			CacheManager cacheManager = CacheManager.getInstance();
			synchronized (multipleQueueNumberCache) {
				TreeSet<Number> tempDataList = new TreeSet<Number>(new Comparator<Number>() {
					@Override
					public int compare(Number num1, Number num2) {
						return num1.getEnterTime().compareTo(num2.getEnterTime());
					}
				});
				
				String[] types = businessType.split(",");
				for (String type : types) {
					String queueCode = cacheManager.getBusinessTypeOfQueueCode(type);
					Queue queue = cacheManager.getQueue(queueCode);
					String key = queue.getCode() + queue.getPrenum();
					TreeSet<Number> selfNumberPool = multipleQueueNumberCache.get(key);
					if (!selfNumberPool.isEmpty()) {
						tempDataList.add(selfNumberPool.first());
					}
				}
				
				if (!tempDataList.isEmpty()) {
				    number = tempDataList.first();
					number.setStatus("2");
					return number;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取窗口未办理号数
	 * @param loginIP 登录IP
	 * @return
	 */
	public int getCount(String loginIP) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				return singleQueueNumberCache.size();
			}
		} else {
			CacheManager cacheManager = CacheManager.getInstance();
			synchronized (multipleQueueNumberCache) {
				int count = 0;
				Screen screen = cacheManager.getWindow(loginIP);
				if (null != screen) { //绑定窗口
					String businessType = screen.getBusinessid();
					if (StringUtils.isNotEmpty(businessType)) { //有可办理的业务
						String[] types = businessType.split(",");
						List<String> tempList = new ArrayList<String>();
						
						for (String type : types) {
							String queueCode = cacheManager.getBusinessTypeOfQueueCode(type);
							if (!tempList.contains(queueCode)) {
								Queue queue = cacheManager.getQueue(queueCode);
								String key = queue.getCode() + queue.getPrenum();
								count += multipleQueueNumberCache.get(key).size();
								tempList.add(queueCode);
							}
						}
					}
				}
				return count;
			}
		}
	}
	
	/**
	 * 添加已叫号信息
	 * @param key 操作员编号
	 * @param value 号码信息
	 */
	public void addOfCallNumberCache(String key, Number value) {
		callNumberCache.put(key, value);
	}
	
	public Map<String, Number> getCallNumberCache() {
		return callNumberCache;
	}
	
	/**
	 * 将已叫号保存到已叫号缓存中
	 * @param operNum 用户编号
	 * @param number 号码信息
	 */
	public void saveCallNumber(String operNum, Number number) {
		String serialNumType = CacheManager.getInstance().getSystemConfig("serialNumType");
		System.out.println("将已叫号保存到已叫号缓存中时状态="+number.getStatus());
		if ("1".equals(serialNumType)) {
			synchronized (singleQueueNumberCache) {
				singleQueueNumberCache.remove(number);
			}
		} else {
			synchronized (multipleQueueNumberCache) {
				String key = number.getQueueCode() + number.getBusinessPrefix();
				multipleQueueNumberCache.get(key).remove(number);
				multipQueueNumberCacheCount.remove(number);
			}
		}
		
		callNumberCache.put(operNum, number);
	}
	
	public Number fetchCallNumber(String operNum) {
		return callNumberCache.get(operNum);
	}
	
	public void removeCallNumber(String operNum) {
		callNumberCache.remove(operNum);
	}
	
	public void clear() {
		singleIndex = 0;
		Collection<Queue> queues = CacheManager.getInstance().getQueueCache().values();
		for (Queue queue : queues) {
			queue.setBeginNum("0");
		}
		singleQueueNumberCache.clear();
		Collection<TreeSet<Number>> numberPools = multipleQueueNumberCache.values();
		for (TreeSet<Number> numberPool : numberPools) {
			numberPool.clear();
		}
		callNumberCache.clear();
	}
}