/**
 * @Ajax异步请求
 		var url = "demo.action";
 		var result = var result = new MyJqueryAjax(url).request();
 		result 即为返回值
 * @author Mr.Liu
 * @date Sep 26,2013 
 * @param v_url url
 * @param data null 可不加
 * @param func null 可不加
 * @param dataType: html 可不加
 * @version v1.0.0
 */
MyJqueryAjax = function (v_url, data, func, dataType) {
	this.url = v_url;
	this.data = data;
	this.func = func;
	this.request = function () {
		var v_response;
		$.ajax({
			async:false, //同步请求
			url:v_url, //请求地址
			//contentType:"application/x-www-form-urlencoded;charset=UTF-8",
			data: data, //参数
			cache:false, //设置为 false 将不会从浏览器缓存中加载请求信息
			type:"POST", 
			dataType:dataType == null ? "text" : dataType, 
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				//alert("MyJqueryAjax Request Error!");
			}, 
			success:(func !== null && func != undefined) ? func : function (req) {
				v_response = req;
			}
		});
		return v_response;
	};
};

