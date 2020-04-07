package com.suntendy.core.util.convert;

class child {
	static int num = 0;
	String name = "qqqqqq";
	static String name2 = "wwwwwwwwwww";
	static child parentClass = new child();
	child(){
		System.out.println("这里是构造函数*************");
	}
	{
		name2 = "11111";
		System.out.println("name1:" + name);
		System.out.println("这里是块1============");
	}
	static {
		num += 1;
		System.out.println("name2:" + name2);
		System.out.println("这里是静态初始化块*************" + num);
	}

}