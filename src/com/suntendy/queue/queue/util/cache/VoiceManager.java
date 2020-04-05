package com.suntendy.queue.queue.util.cache;

import java.util.Comparator;
import java.util.TreeSet;

/*******************************************************************************
 * 描述: 声音数据管理 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-26 09:36:30 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class VoiceManager {

	private TreeSet<String> voiceList = new TreeSet<String>(new Comparator<String>() {
		@Override
		public int compare(String content1, String content2) {
			String firstTime = content1.split("@")[2];
			String secondTime = content2.split("@")[2];
			return firstTime.compareTo(secondTime);
		}
	});

	private final static VoiceManager voiceManager = new VoiceManager();

	private VoiceManager() {}

	public static VoiceManager getInstance() {
		return voiceManager;
	}

	/**
	 * 添加到播放列表
	 * @param voice 号和窗口号
	 */
	public void add(String voice) {
		synchronized (voiceList) {
			voiceList.add(voice);
		}
	}
	
	/**
	 * 获取播放声音
	 * @return 号和窗口号
	 */
	public String get() {
		synchronized (voiceList) {
			return voiceList.pollFirst();
		}
	}

	/**
	 * 清空列表
	 */
	protected void clearAll() {
		voiceList.clear();
	}
}