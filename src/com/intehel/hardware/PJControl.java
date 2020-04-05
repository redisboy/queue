package com.intehel.hardware;

public class PJControl {
	static {
		System.loadLibrary("JPJControler");
	}
	/*
	 * iAddr表示评价器地址 默认为1 可通过拔码开关设置,Id表示系统编号，由系统任意编号 返回 0为正常 其他为失败
	 */
	public static native int startAppraise(int iAddr, String Id);
	// 取值结果为 Id(系统编号),评价值 如 10001,0
	// 评价值: 0未评价 1 满意 2一般 3 不满意 4非常满意
	public static native String getValue();
	
	public static void main(String[] arg) throws InterruptedException {
		System.out.println(PJControl.startAppraise(1, "A10001"));
		for (int i = 0; i < 20; i++) {
			System.out.println(i + ":" + PJControl.getValue());
			Thread.sleep(1000);
		}
	}
}