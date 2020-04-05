package com.suntendy.queue.agent.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.suntendy.queue.agent.dao.IAgentDao;
import com.suntendy.queue.agent.dao.IReviewDao;
import com.suntendy.queue.agent.dao.IWorkDao;
import com.suntendy.queue.agent.services.IAgentService;
import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.MessageVO;
import com.suntendy.queue.agent.vo.ReviewVO;
import com.suntendy.queue.agent.vo.WorkVO;
import com.suntendy.queue.util.DateUtils;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.trff.TrffUtils;

public class AgentServiceImpl implements IAgentService {
	private IAgentDao agentDao;
	private IWorkDao workDao;
	private IReviewDao reviewDao;

	public void setReviewDao(IReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	public void setWorkDao(IWorkDao workDao) {
		this.workDao = workDao;
	}

	public void setAgentDao(IAgentDao agentDao) {
		this.agentDao = agentDao;
	}

	// 查询代理人
	public List<AgentVO> searchAgent(MessageVO messageVO) {
		try {
			return agentDao.searchAgent(messageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 代理人删除
	public int deleteAgent(String id) throws Exception {
		AgentVO agent = new AgentVO();
		agent.setStatus("0");
		agent.setId(Integer.parseInt(id));
		agent.setLogout_date(DateUtils.dateToString());
		//int result = agentDao.updateAgent(agent);

		// 代理人删除
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		MessageVO mvo = new MessageVO();
		mvo.setId(id);
		mvo.setDeptCode(deptCode);
		agent = agentDao.searchAgent(mvo).get(0);
		agent.setBj("2");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] results = TrffUtils.updateAgent(agent);
			if (!"1".equals(results[0])) {
				System.out.println("删除六合一代理人异常");
				throw new Exception("[代理人数据上传->删除]：" + results[1]);
			}
		}
		System.out.println("删除本地记录");
		int result = agentDao.deleteAgent(agent);
		workDao.deleteWork(id);
		System.out.println("result=="+result);
		return result;
	}

	// 代理人登记
	public void addDlr(AgentVO agent) throws Exception {
		String today = DateUtils.dateToString(); // 将日期时间格式化

		List<WorkVO> datas = new ArrayList<WorkVO>();
		String[] codes = agent.getCode();
		String[] strokes = agent.getStroke();//业务笔数
		
		for(int i=0;i<codes.length;i++){
			WorkVO work = new WorkVO();
			work.setJyw("," + agent.getId() + "," + codes[i] + "," + "1," + today + ",,," + agent.getUser());
			work.setAgent_id(agent.getId() + "");
			work.setUser(agent.getUser());
			work.setCreat_date(today);
			work.setWork_id(codes[i]);
			//work.setStroke(strokes[i]);
			if("".equals(strokes[i])){
				work.setStroke("0");
			}else{
				work.setStroke(strokes[i]);
			}
			datas.add(work);
			
		/*	if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0")){
				CacheManager cacheManager = CacheManager.getInstance();
				String deptCode = cacheManager.getOfDeptCache("deptCode");
				String deptHall = cacheManager.getOfDeptCache("deptHall");
				//获取业务类型dmz和业务类别(01:机动车，02驾驶人)
				List<Business> businessList = businessDao.getBusinessList(codes[i], "", "", deptCode, deptHall);
				Business business = businessList.get(0);
				System.out.println("上传代理人权限参数:"+business.getFlag()+"#"+business.getDmz());
				work.setWork_id(business.getFlag()+";"+business.getDmz());
				String[] result = TrffUtils.updateAgentLevel(work);
				if (!"1".equals(result[0])) {
					throw new Exception("[代理人权限数据上传->添加]：" + result[1]);
				}
			}*/
		}
		try {
			workDao.addWork(datas);

			agentDao.addDlr(agent);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReviewVO review = new ReviewVO();
		review.setValidity(agent.getValidity());
		review.setIdcard(agent.getIdCard());
		review.setPid(agent.getUser());
		review.setCheckdate(today);
		reviewDao.addReview(review);

		agent.setBj("1");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] result = TrffUtils.updateAgent(agent);
			if (!"1".equals(result[0])) {
				throw new Exception("[代理人数据上传->添加]：" + result[1]);
			}
		}
	}

	// 代理人信息修改
	public int updateDlr(AgentVO agent) throws Exception {
		
		String today = DateUtils.dateToString(); // 将日期时间格式化

		String[] codes = agent.getCode();
		String[] strokes = agent.getStroke();//业务笔数
		List<WorkVO> list;
		for(int i=0;i<codes.length;i++){
			agent.setYwbs(strokes[i]);
			agent.setWorkid(codes[i]);
			WorkVO workVO = new WorkVO();
			workVO.setAgent_id(String.valueOf(agent.getId()));
			workVO.setWork_id(agent.getWorkid());
			list = workDao.searchWork(workVO);
			if(list.size()>0){
				agent.setModified_date(today);
				workDao.updWork(agent);				
			}else {
				workVO.setJyw("," + agent.getId() + "," + codes[i] + "," + "1," + today + ",,," + agent.getUser());
				workVO.setAgent_id(agent.getId() + "");
				workVO.setUser(agent.getUser());
				workVO.setCreat_date(today);
				workVO.setWork_id(codes[i]);
				workVO.setStroke(strokes[i]);
				if("".equals(strokes[i])){
					workVO.setStroke("0");
				}else{
					workVO.setStroke(strokes[i]);
				}
				
				workDao.addWork(workVO);
			}
		}
		int result = agentDao.updateAgent(agent);

		// 代理人更新
		MessageVO mvo = new MessageVO();
		mvo.setId(agent.getId() + "");
		mvo.setDeptCode(agent.getDeptCode());
		agent = agentDao.searchAgent(mvo).get(0);
		//远程表状态为2时是删除，更新的时候先删除后添加
		agent.setBj("2");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] results = TrffUtils.updateAgent(agent);
			if (!"1".equals(results[0])) {
				throw new Exception("[代理人数据上传->更新]：" + results[1]);
			}
		}
		agent.setBj("1");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] result1 = TrffUtils.updateAgent(agent);
			if (!"1".equals(result1[0])) {
				throw new Exception("[代理人数据上传->添加]：" + result1[1]);
			}
		}
		return result;
	}

	// 代理人年检状态修改
	public void updateAgent(AgentVO agent) throws Exception {
		String today = DateUtils.dateToString(); // 将日期时间格式化
		agent.setModified_date(today);
		// 修改代理人表数据
		agentDao.updateAgent(agent);
		// 根据ID更新work表里面的状态和修改时间
		//workDao.updWork(agent);
		
		
		// 添加work表数据
		List<WorkVO> datas = new ArrayList<WorkVO>();
		String[] codes = agent.getCode();
		String[] strokes = agent.getStroke();//业务笔数
		List<WorkVO> list;
		for(int i=0;i<codes.length;i++){
			agent.setYwbs(strokes[i]);
			agent.setWorkid(codes[i]);
			WorkVO workVO = new WorkVO();
			workVO.setAgent_id(String.valueOf(agent.getId()));
			workVO.setWork_id(agent.getWorkid());
			list = workDao.searchWork(workVO);
			if(list.size()>0){
				workDao.updWork(agent);				
			}else {
				workVO.setJyw("," + agent.getId() + "," + codes[i] + "," + "1," + today + ",,," + agent.getUser());
				workVO.setAgent_id(agent.getId() + "");
				workVO.setUser(agent.getUser());
				workVO.setCreat_date(today);
				workVO.setWork_id(codes[i]);
				workVO.setStroke(strokes[i]);
				if("".equals(strokes[i])){
					workVO.setStroke("0");
				}else{
					workVO.setStroke(strokes[i]);
				}
				
				workDao.addWork(workVO);
			}
		}
//		for(int i=0;i<codes.length;i++){
//			WorkVO work = new WorkVO();
//			work.setJyw("," + agent.getId() + "," + codes[i] + ",1," + today + ",," + today + "," + agent.getUser());
//			work.setAgent_id(agent.getId() + "");
//			work.setUser(agent.getUser());
//			work.setCreat_date(today);
//			work.setModified_date("");
//			work.setWork_id(codes[i]);
//			work.setStroke(strokes[i]);
//			datas.add(work);
			/*
			if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0")){
				CacheManager cacheManager = CacheManager.getInstance();
				String deptCode = cacheManager.getOfDeptCache("deptCode");
				String deptHall = cacheManager.getOfDeptCache("deptHall");
				//获取业务类型dmz和业务类别(01:机动车，02驾驶人)
				List<Business> businessList = businessDao.getBusinessList(codes[i], "", "", deptCode, deptHall);
				Business business = businessList.get(0);
				System.out.println("更新代理人权限参数:"+business.getFlag()+"#"+business.getDmz());
				work.setWork_id(business.getFlag()+";"+business.getDmz());
				String[] result = TrffUtils.updateAgentLevel(work);
				if (!"1".equals(result[0])) {
					throw new Exception("[代理人权限数据上传->更新]：" + result[1]);
				}
			}*/
//		}
//		workDao.addWork(datas);
		
		// 添加REVIEW表数据
		ReviewVO review = new ReviewVO();
		review.setValidity(agent.getValidity());
		review.setIdcard(agent.getIdCard());
		review.setPid(agent.getUser());
		review.setCheckdate(today);
		reviewDao.addReview(review);

		// 代理人更新
		MessageVO mvo = new MessageVO();
		mvo.setId(agent.getId() + "");
		mvo.setDeptCode(agent.getDeptCode());
		agent = agentDao.searchAgent(mvo).get(0);
		agent.setBj("2");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] result = TrffUtils.updateAgent(agent);
			if (!"1".equals(result[0])) {
				throw new Exception("[代理人数据上传->更新]：" + result[1]);
			}
		}
		agent.setBj("1");
		if(CacheManager.getInstance().getSystemConfig("isUseInterface").equals("0") && CacheManager.getInstance().getSystemConfig("jklx").equals("0")){
			agent.setJyw(agentDao.toMD5byJyw(agent.getJyw()));//MD5加密jyw
			String[] result1 = TrffUtils.updateAgent(agent);
			if (!"1".equals(result1[0])) {
				throw new Exception("[代理人数据上传->添加]：" + result1[1]);
			}
		}
	}

	public AgentVO searchRepeatById(String id,String deptCode) {
		try {
			MessageVO messageVO = new MessageVO();
			messageVO.setId(id);
			messageVO.setDeptCode(deptCode);
			List<AgentVO> list = searchAgent(messageVO);
			if (null != list && !list.isEmpty()) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String searchRepeatByIDCard(String idCard) {
		String flag = "";
		try {
			MessageVO messageVO = new MessageVO();
			messageVO.setIdCard(idCard);
			List<AgentVO> list = searchAgent(messageVO);
			if (null != list && !list.isEmpty()) {
				flag = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	public void addAllAgent(List<AgentVO> agents) throws Exception {
		agentDao.addAllAgent(agents);
	}

	public List<AgentVO> selectExcel(String id) throws Exception {
		return agentDao.selectExcel(id);
	}

	public List<AgentVO> searchNullAgent() throws Exception {
		return agentDao.selectNullAgent();
	}

	public void updateAgent2(AgentVO agent) throws Exception {
		String today = DateUtils.dateToString(); //将日期时间格式化
		for (int i = 0; i < agent.getCode().length; i++) {
			WorkVO work =new WorkVO();
			work.setAgent_id(agent.getId()+"");
			work.setWork_id(agent.getCode()[i]);
			work.setStroke(agent.getStroke()[i]);
			work.setCreat_date(today);
			work.setUser(agent.getUser());
			work.setJyw(","+agent.getId()+","+agent.getCode()[i]+","+"1,"+today+",,,"+agent.getUser());
			workDao.addWork(work);
		}
		ReviewVO review=new ReviewVO();
		review.setPid(agent.getUser());
		review.setIdcard(agent.getIdCard());
		review.setValidity(agent.getValidity());
		review.setCheckdate(today);
		reviewDao.addReview(review);
	}
	
	//MD5加密
	public String toMD5byJyw(String jyw)throws Exception{
		return agentDao.toMD5byJyw(jyw);
	}
	public int countAgent(String name,String idCard,String id,String status,String unit,String pxzd,String pxfs,String deptCode)throws Exception{
		return agentDao.countAgent(name,idCard,id,status,unit,pxzd,pxfs,deptCode);
	}
}