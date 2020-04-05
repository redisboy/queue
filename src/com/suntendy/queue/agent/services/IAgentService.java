package com.suntendy.queue.agent.services;

import java.util.List;

import com.suntendy.queue.agent.vo.AgentVO;
import com.suntendy.queue.agent.vo.MessageVO;

/**
 * 
 * 代理人接口
 */
public interface IAgentService {
	// 查询代理人信息
	public List<AgentVO> searchAgent(MessageVO messageVO) throws Exception;

	// 代理人注销
	public int deleteAgent(String id) throws Exception;

	// 添加代理人
	public void addDlr(AgentVO agent) throws Exception;

	// 更新代理人信息
	public int updateDlr(AgentVO agent) throws Exception;

	// 代理人年检状态修改
	public void updateAgent(AgentVO agent) throws Exception;

	public AgentVO searchRepeatById(String id,String deptCode) throws Exception;

	// 代理人身份证重复查询
	public String searchRepeatByIDCard(String idCard) throws Exception;
	
	// 批量添加代理人
	public void addAllAgent(List<AgentVO> agents) throws Exception;

	// 需要添加业务，照片，指纹的数据
	public List<AgentVO> searchNullAgent() throws Exception;

	// 通过ID找相应的批量录入代理人
	public List<AgentVO> selectExcel(String id) throws Exception;

	// 代理人批量录入状态添加
	public void updateAgent2(AgentVO agent) throws Exception;
	
	//MD5加密
	public String toMD5byJyw(String jyw)throws Exception;
	//统计代理人人数
	public int countAgent(String name,String idCard,String id,String status,String unit,String pxzd,String pxfs,String deptCode)throws Exception;
}