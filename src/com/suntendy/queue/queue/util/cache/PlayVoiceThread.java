package com.suntendy.queue.queue.util.cache;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.suntendy.queue.util.cache.CacheManager;

/*******************************************************************************
 * 描述: 播放声音线程，工程启动时自动运行 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-10-11 17:10:00 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class PlayVoiceThread extends Thread {
	
	private String filePath;
	
	public PlayVoiceThread(String filePath) {
		this.filePath = filePath + "/voice";
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String voice = VoiceManager.getInstance().get();
				if (StringUtils.isNotEmpty(voice)) {
					play(filePath, voice);
				}
			} catch (Exception e) {
				System.out.println("-------------------声音播放异常-------------------");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 播放声音
	 * @param filePath 文件路径
	 * @param voice 号和窗口号
	 */
	private void play(String filePath, String voice) {
		CacheManager cacheManager = CacheManager.getInstance();
		String num = cacheManager.getSystemConfig("voiceType");
		String serialNumType = cacheManager.getSystemConfig("serialNumType");
		
		filePath += num;
		String[] str = voice.split("@");
		List<String> voiceNames = new ArrayList<String>();
		voiceNames.add("tinkling.wav");
		getSoundName(str[0], voiceNames);
		if (3 < str.length) {
			voiceNames.add("please.wav");
			voiceNames.add(str[1] + ".wav");
			voiceNames.add(str[3] + ".wav");
			voiceNames.add("window.wav");
		} else {
			voiceNames.add(str[1] + "window.wav");
		}
		
//		for (String fileName : voiceNames) {
//			File file = new File(filePath + "/" + fileName);
//			try {
//				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
//				AudioFormat audioFormat = audioInputStream.getFormat();
//				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
//				SourceDataLine dataLineInfo = (SourceDataLine) AudioSystem.getLine(info);
//				dataLineInfo.open();
//				dataLineInfo.start();
//	
//				byte[] tempBuffer = new byte[512 * 1024];
//				int cnt = 0;
//				while (-1 != (cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length))) {
//					if (0 <= cnt)
//						dataLineInfo.write(tempBuffer, 0, cnt);
//				}
//				dataLineInfo.drain();
//				dataLineInfo.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		for (int i = 0, len = voiceNames.size(); i < len; ++i) {
			String fileName = voiceNames.get(i);
			try {
				// 用输入流打开一音频文件
				InputStream in = new FileInputStream(filePath + "/" + fileName);// FIlename
				// 从输入流中创建一个AudioStream对象
				AudioStream as = new AudioStream(in);
				// 用静态成员player.start播放音乐
				AudioPlayer.player.start(as);
				try {
					long millis = 450;
					if (i != len - 1) {
						if (0 == i) millis = 1000;
						else if (1 == i)
							millis = "1".equals(num) ? 600 : "2".equals(num) ? 500 : "3".equals(num) ? 750 : millis;
						else if ("1".equals(serialNumType)) {
							millis = 5 == i ? "3".equals(num) ? 1300 : 1000 : 7 == i ? "2".equals(num) ? 300 : 600 : millis;
						} else {
							millis = 6 == i ? "3".equals(num) ? 1300 : 1000 : 8 == i ? "2".equals(num) ? 300 : 600 : millis;
						}
					} else {
						millis = "1".equals(num) ? 3500 : 3000;
					}
					Thread.sleep(millis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void getSoundName(String str, List<String> voiceNames) {
		char[] chars = str.toCharArray();
		for (char c : chars) {
			voiceNames.add(c + ".wav");
		}
	}
}