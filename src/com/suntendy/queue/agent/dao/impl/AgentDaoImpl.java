package com.suntendy.queue.agent.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.agent.dao.IAgentDao;
import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.MessageVO;
import com.suntendy.queue.base.BaseDao;
public class AgentDaoImpl extends BaseDao<AgentVO, Integer> implements IAgentDao{
	//查询代理人信息
	public List<AgentVO> searchAgent(MessageVO messageVO) throws Exception {
		String[] properties = { "name", "idCard", "id", "status", "unit", "deptCode","pageStart","pageEnd" };
		Object[] propertyValues = { messageVO.getName(), messageVO.getIdCard(), messageVO.getId(), messageVO.getStatus(), messageVO.getUnit(),messageVO.getDeptCode(),messageVO.getPageStart(),messageVO.getPageEnd() };
		String orders = "";
		if (StringUtils.isNotEmpty(messageVO.getPxzd())) {
			orders = "b." + messageVO.getPxzd();
		}
		return this.findByMap(properties, propertyValues, orders, messageVO.getPxfs(), BaseDao.SELECTBYMAP);
	}
	
	public int updateAgent(AgentVO agent) throws Exception {
		return this.update(agent, BaseDao.UPDATE);
	}
	
	//添加代理人
	public Integer addDlr(AgentVO agent) throws Exception {
		return insert(agent, BaseDao.INSERT);
	}
	
	public void addAllAgent(List<AgentVO> agents) throws Exception {
		this.batchInsert(agents, "addAll");
	}
	public List<AgentVO> selectExcel(String id) throws Exception {
		String[] properties={"id"}; 
		Object[] propertyValues={id};
		return this.findByMap(properties, propertyValues,"","","selectNullAgent");
	}
	public List<AgentVO> selectNullAgent() throws Exception {
		String [] properties={};
		String [] propertyValues={};
		List<AgentVO> list=this.findByMap(properties, propertyValues, "t.id","asc","selectNullAgent");
		for (int i = 0; i < list.size(); i++) {
			AgentVO agent= list.get(i);
			if("0".equals(agent.getStatus())){
				agent.setStatus("注销");
			} else if("1".equals(agent.getStatus())){
				agent.setStatus("正常");
			} else if("2".equals(agent.getStatus())){
				agent.setStatus("暂停");
			}
			agent.setSelexcel("<a href=agentExcelUpd.action?id="+agent.getId()+"><img src=/queue/images/button_edit.jpg /></a>");
		}
		return list;
	}
	//MD5加密
	public String toMD5byJyw(String jyw)throws Exception{
		String[] properties={"jyw"}; 
		Object[] propertyValues={jyw};
		String jywString="";
		List<AgentVO> list= this.findByMap(properties, propertyValues,"","","toMD5byJyw");
		if(null != list && list.size()>0){
			AgentVO agentVO = list.get(0);
			jywString = agentVO.getJyw();
		}
		return jywString;
	}

	@Override
	public int deleteAgent(AgentVO agent) throws Exception {
		// TODO Auto-generated method stub
		return deleteById(agent.getId(), "deleteAgent");
	}
	//统计代理人数量
	public int countAgent(String name,String idCard,String id,String status,String unit,String pxzd,String pxfs,String deptCode)throws Exception{
		HashMap<String,String> hashMap = new HashMap<String, String>();
		String[]properties ={"name","idCard","id","status","unit","pxzd","pzfs","deptCode"};
		String[]propertiesValue = {name,idCard,id,status,unit,pxzd,pxfs,deptCode};
		for(int i=0;i<properties.length;i++){
			hashMap.put(properties[i],propertiesValue[i]);
		}
		return (Integer)this.getSqlMapClientTemplate().queryForObject(className+".countAgent", hashMap); 
	}
}