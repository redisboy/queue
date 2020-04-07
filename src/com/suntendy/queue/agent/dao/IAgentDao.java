package com.suntendy.queue.agent.dao;

import java.util.List;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.MessageVO;

public interface IAgentDao {
	//查询代理人信息
	public List<AgentVO> searchAgent(MessageVO messageVO) throws Exception;
	//代理人更新
	public int updateAgent(AgentVO agent) throws Exception;
	//代理人删除
	public int deleteAgent(AgentVO agent) throws Exception;
	//添加代理人
	public Integer addDlr(AgentVO agent) throws Exception;
	//代理人批量添加
	public void addAllAgent(List<AgentVO> agents) throws Exception;
	//需要添加业务，照片，指纹的数据
	public List<AgentVO> selectNullAgent()throws Exception;
	//通过ID找相应的批量录入代理人
	public List<AgentVO> selectExcel(String id)throws Exception;
	//MD5加密
	public String toMD5byJyw(String jyw)throws Exception;
	//统计代理人数
	public int countAgent(String name,String idCard,String id,String status,String unit,String pxzd,String pxfs,String deptCode)throws Exception;
}