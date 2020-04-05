package com.suntendy.queue.queue.domain;

/*******************************************************************************
 * 描述: 取号信息 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-24 13:51:27 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class Number {

	private String id; // 取号信息ID
	private String queueCode; // 队列编
	private String enterTime; // 取号时间
	private String queueValue; // 队列当前值
	private String serialNum; // 顺序号
	private String status; // 状态
	private String IDType; // 证件类型
	private String IDNumber; // 证件号码
	private String serverIp;
	private String personType; // 人员类型
	private String typeName; // 业务类型名称
	private String queueName; // 队列名称
	private String businessType; // 业务类型编号
	private String businessCount; // 业务笔数
	private String businessPrefix; // 业务类型前缀
	private String lsh;//6合1流水号
	private String deptCode; // 部门编号
	private String deptHall; // 大厅号
	private String nextQueueName;//下一级队列名称
	private String nextType;//下一级业务类型
	private String nextPreNum;//下一级业务类型
	private String code;//员工编号
	private String typeCount;//查询业务类型数量
	private String queueCount;//查询队列数量
	private String IDNumberB;//代办人证件号码
	private String zllx;//资料类型
	private String tjry;//统计人员
	private String passReason;//过号原因
	private String rzdbz;//人证对比值

	private String operNum; // 操作员编号
	private String xm; // 操作员姓名
	private String evaluResult; // 评价编号
	private String evaluTime; // 评价时间
	private String beginTime; // 开始办理时间
	private String endTime; // 结束办理时间
	private String barId; // 窗口号
	private byte[] photoBase64; // 照片
	private String reason; // 不满意原因
	private byte[] signature;// 办理人签名
	private byte[] quhaoPhoto;// 取号照片
	private byte[] ZWBase64;// 指纹信息
	private String flag;
	private String barIp;//窗口IP
	private String idnumbercount;//身份证统计
	private String ejcdbh;//二级菜单编号
	
	private String loginid;
	private String value;
	private String valuetime;
	private String endtime;
	private String uniqueid;
	private String clientno;
	private String blridnumber;//办理人证件号码
	private String czyh;//操作用户
	private byte[] sfzphoto;//身份证照片
	private String sfzphotoStr;//身份证照片Str
	private String quhaoPhotoStr;//取号照片Str
	private String photoBase64Str;//评价照片Str
	private byte[] gpyPhotoBase64;//高拍仪照片
	private byte[] gpyPhoto1;//高拍仪照片
	private byte[] gpyPhoto2;//高拍仪照片
	private byte[] gpyPhoto3;//高拍仪照片
	private byte[] gpyPhoto4;//高拍仪照片
	private byte[] gpyPhoto5;//高拍仪照片
	private byte[] gpyPhoto6;//高拍仪照片
	private byte[] gpyPhoto7;//高拍仪照片
	private byte[] gpyPhoto8;//高拍仪照片
	private byte[] gpyPhoto9;//高拍仪照片
	private byte[] gpyPhoto10;//高拍仪照片
	private byte[] gpyPhoto11;//高拍仪照片
	private byte[] gpyPhoto12;//高拍仪照片
	private byte[] gpyPhoto13;//高拍仪照片
	private String lrrq;//操作时间
	private String waitingarea;//等待区域
	private String pztime;//评价拍照时间
	private String operation;//操作
	private String outFlag;//接口调用评价标记1位接口调用
	private String out1;//用于安阳评价信息上传标记 null为未上传，1为上传
	private String nameA;//车主姓名
	private String nameB;//办理人姓名
	
	//高拍仪
	private byte[] photo1;
	private byte[] photo2;
	private byte[] photo3;
	
	//6合1
	private String xlh;
	private String sxh;//顺序号
	private String yhdh;//用户编号
	private String hjsj;//呼叫时间
	private String ywbj;//业务标记
	private String ybbs;
	private String pjjg;
	private String pjsj;//评价时间
	private String rztime;//日志写入时间
	
	private String hpzl;//号牌种类
	private String hphm;//号牌号码
	private String syr;//所有人
	private String zt;//状态
	private String dybj;//抵押标记
	private String yxqz;//检验有效期止
	private String qzbfqz;//强制报废期止
	private String djzsbh;//等级证书编号
	private String zjcx;//准驾车型
	private String ljjf;//累计积分
	private String syyxqz;//审验有效期止
	private String sfzmhm;//身份证明号码
	private String phoneNumber;//办理人手机号码
	private String carType;//办理人车辆类型
	private String carNumber;//办理人车牌号
	private String ischuli;//完结标记
	private String gqsj;//挂起时间
	private String jdcnum;//机动车号牌
	private String jdctypes;//机动车种类
	
	
	public String getJdcnum() {
		return jdcnum;
	}

	public void setJdcnum(String jdcnum) {
		this.jdcnum = jdcnum;
	}

	public String getJdctypes() {
		return jdctypes;
	}

	public void setJdctypes(String jdctypes) {
		this.jdctypes = jdctypes;
	}

	public String getGqsj() {
		return gqsj;
	}

	public void setGqsj(String gqsj) {
		this.gqsj = gqsj;
	}

	public String getIschuli() {
		return ischuli;
	}

	public void setIschuli(String ischuli) {
		this.ischuli = ischuli;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}

	public String getRzdbz() {
		return rzdbz;
	}

	public void setRzdbz(String rzdbz) {
		this.rzdbz = rzdbz;
	}

	public String getFlag() {
		return flag;
	}

	public String getNextQueueName() {
		return nextQueueName;
	}

	public void setNextQueueName(String nextQueueName) {
		this.nextQueueName = nextQueueName;
	}

	public String getNextType() {
		return nextType;
	}

	public void setNextType(String nextType) {
		this.nextType = nextType;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQueueCode() {
		return queueCode;
	}

	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getQueueValue() {
		return queueValue;
	}

	public void setQueueValue(String queueValue) {
		this.queueValue = queueValue;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIDType() {
		return IDType;
	}

	public void setIDType(String type) {
		IDType = type;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String number) {
		IDNumber = number;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessCount() {
		return businessCount;
	}

	public void setBusinessCount(String businessCount) {
		this.businessCount = businessCount;
	}

	public String getBusinessPrefix() {
		return businessPrefix;
	}

	public void setBusinessPrefix(String businessPrefix) {
		this.businessPrefix = businessPrefix;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptHall() {
		return deptHall;
	}

	public void setDeptHall(String deptHall) {
		this.deptHall = deptHall;
	}

	public String getOperNum() {
		return operNum;
	}

	public void setOperNum(String operNum) {
		this.operNum = operNum;
	}

	public String getEvaluResult() {
		return evaluResult;
	}

	public void setEvaluResult(String evaluResult) {
		this.evaluResult = evaluResult;
	}

	public String getEvaluTime() {
		return evaluTime;
	}

	public void setEvaluTime(String evaluTime) {
		this.evaluTime = evaluTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBarId() {
		return barId;
	}

	public void setBarId(String barId) {
		this.barId = barId;
	}

	public byte[] getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(byte[] photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getNextPreNum() {
		return nextPreNum;
	}

	public void setNextPreNum(String nextPreNum) {
		this.nextPreNum = nextPreNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValuetime() {
		return valuetime;
	}

	public void setValuetime(String valuetime) {
		this.valuetime = valuetime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getClientno() {
		return clientno;
	}

	public void setClientno(String clientno) {
		this.clientno = clientno;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public byte[] getQuhaoPhoto() {
		return quhaoPhoto;
	}

	public void setQuhaoPhoto(byte[] quhaoPhoto) {
		this.quhaoPhoto = quhaoPhoto;
	}

	public String getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(String typeCount) {
		this.typeCount = typeCount;
	}

	public String getXlh() {
		return xlh;
	}

	public void setXlh(String xlh) {
		this.xlh = xlh;
	}

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getYhdh() {
		return yhdh;
	}

	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}

	public String getHjsj() {
		return hjsj;
	}

	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}

	public String getYwbj() {
		return ywbj;
	}

	public void setYwbj(String ywbj) {
		this.ywbj = ywbj;
	}

	public String getYbbs() {
		return ybbs;
	}

	public void setYbbs(String ybbs) {
		this.ybbs = ybbs;
	}

	public String getPjjg() {
		return pjjg;
	}

	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}

	public String getPjsj() {
		return pjsj;
	}

	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}

	public String getBlridnumber() {
		return blridnumber;
	}

	public void setBlridnumber(String blridnumber) {
		this.blridnumber = blridnumber;
	}

	public String getBarIp() {
		return barIp;
	}

	public void setBarIp(String barIp) {
		this.barIp = barIp;
	}

	public String getCzyh() {
		return czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public byte[] getSfzphoto() {
		return sfzphoto;
	}

	public void setSfzphoto(byte[] sfzphoto) {
		this.sfzphoto = sfzphoto;
	}

	public String getSfzphotoStr() {
		return sfzphotoStr;
	}

	public void setSfzphotoStr(String sfzphotoStr) {
		this.sfzphotoStr = sfzphotoStr;
	}

	public String getQuhaoPhotoStr() {
		return quhaoPhotoStr;
	}

	public void setQuhaoPhotoStr(String quhaoPhotoStr) {
		this.quhaoPhotoStr = quhaoPhotoStr;
	}

	public String getPhotoBase64Str() {
		return photoBase64Str;
	}

	public void setPhotoBase64Str(String photoBase64Str) {
		this.photoBase64Str = photoBase64Str;
	}

	public String getWaitingarea() {
		return waitingarea;
	}

	public void setWaitingarea(String waitingarea) {
		this.waitingarea = waitingarea;
	}

	public String getRztime() {
		return rztime;
	}

	public void setRztime(String rztime) {
		this.rztime = rztime;
	}

	public String getPztime() {
		return pztime;
	}

	public void setPztime(String pztime) {
		this.pztime = pztime;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public byte[] getPhoto3() {
		return photo3;
	}

	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}

	public String getIdnumbercount() {
		return idnumbercount;
	}

	public void setIdnumbercount(String idnumbercount) {
		this.idnumbercount = idnumbercount;
	}

	public String getIDNumberB() {
		return IDNumberB;
	}

	public void setIDNumberB(String iDNumberB) {
		IDNumberB = iDNumberB;
	}

	public byte[] getGpyPhotoBase64() {
		return gpyPhotoBase64;
	}

	public void setGpyPhotoBase64(byte[] gpyPhotoBase64) {
		this.gpyPhotoBase64 = gpyPhotoBase64;
	}

	public byte[] getZWBase64() {
		return ZWBase64;
	}

	public void setZWBase64(byte[] base64) {
		ZWBase64 = base64;
	}

	public String getZllx() {
		return zllx;
	}

	public void setZllx(String zllx) {
		this.zllx = zllx;
	}

	public byte[] getGpyPhoto1() {
		return gpyPhoto1;
	}

	public void setGpyPhoto1(byte[] gpyPhoto1) {
		this.gpyPhoto1 = gpyPhoto1;
	}

	public byte[] getGpyPhoto2() {
		return gpyPhoto2;
	}

	public void setGpyPhoto2(byte[] gpyPhoto2) {
		this.gpyPhoto2 = gpyPhoto2;
	}

	public byte[] getGpyPhoto3() {
		return gpyPhoto3;
	}

	public void setGpyPhoto3(byte[] gpyPhoto3) {
		this.gpyPhoto3 = gpyPhoto3;
	}

	public byte[] getGpyPhoto4() {
		return gpyPhoto4;
	}

	public void setGpyPhoto4(byte[] gpyPhoto4) {
		this.gpyPhoto4 = gpyPhoto4;
	}

	public byte[] getGpyPhoto5() {
		return gpyPhoto5;
	}

	public void setGpyPhoto5(byte[] gpyPhoto5) {
		this.gpyPhoto5 = gpyPhoto5;
	}

	public byte[] getGpyPhoto6() {
		return gpyPhoto6;
	}

	public void setGpyPhoto6(byte[] gpyPhoto6) {
		this.gpyPhoto6 = gpyPhoto6;
	}

	public byte[] getGpyPhoto7() {
		return gpyPhoto7;
	}

	public void setGpyPhoto7(byte[] gpyPhoto7) {
		this.gpyPhoto7 = gpyPhoto7;
	}

	public byte[] getGpyPhoto8() {
		return gpyPhoto8;
	}

	public void setGpyPhoto8(byte[] gpyPhoto8) {
		this.gpyPhoto8 = gpyPhoto8;
	}

	public byte[] getGpyPhoto9() {
		return gpyPhoto9;
	}

	public void setGpyPhoto9(byte[] gpyPhoto9) {
		this.gpyPhoto9 = gpyPhoto9;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getSyr() {
		return syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getDybj() {
		return dybj;
	}

	public void setDybj(String dybj) {
		this.dybj = dybj;
	}

	public String getYxqz() {
		return yxqz;
	}

	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}

	public String getQzbfqz() {
		return qzbfqz;
	}

	public void setQzbfqz(String qzbfqz) {
		this.qzbfqz = qzbfqz;
	}

	public String getDjzsbh() {
		return djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getZjcx() {
		return zjcx;
	}

	public void setZjcx(String zjcx) {
		this.zjcx = zjcx;
	}

	public String getLjjf() {
		return ljjf;
	}

	public void setLjjf(String ljjf) {
		this.ljjf = ljjf;
	}

	public String getSyyxqz() {
		return syyxqz;
	}

	public void setSyyxqz(String syyxqz) {
		this.syyxqz = syyxqz;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public byte[] getGpyPhoto10() {
		return gpyPhoto10;
	}

	public void setGpyPhoto10(byte[] gpyPhoto10) {
		this.gpyPhoto10 = gpyPhoto10;
	}

	public byte[] getGpyPhoto11() {
		return gpyPhoto11;
	}

	public void setGpyPhoto11(byte[] gpyPhoto11) {
		this.gpyPhoto11 = gpyPhoto11;
	}

	public byte[] getGpyPhoto12() {
		return gpyPhoto12;
	}

	public void setGpyPhoto12(byte[] gpyPhoto12) {
		this.gpyPhoto12 = gpyPhoto12;
	}

	public String getTjry() {
		return tjry;
	}

	public void setTjry(String tjry) {
		this.tjry = tjry;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public byte[] getGpyPhoto13() {
		return gpyPhoto13;
	}

	public void setGpyPhoto13(byte[] gpyPhoto13) {
		this.gpyPhoto13 = gpyPhoto13;
	}

	public String getEjcdbh() {
		return ejcdbh;
	}

	public void setEjcdbh(String ejcdbh) {
		this.ejcdbh = ejcdbh;
	}


	public String getPassReason() {
		return passReason;
	}

	public void setPassReason(String passReason) {
		this.passReason = passReason;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOut1() {
		return out1;
	}

	public void setOut1(String out1) {
		this.out1 = out1;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueCount() {
		return queueCount;
	}

	public void setQueueCount(String queueCount) {
		this.queueCount = queueCount;
	}
	
	
}