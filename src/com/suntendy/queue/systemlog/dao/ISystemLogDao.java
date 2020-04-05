package com.suntendy.queue.systemlog.dao;

import java.util.Date;
import java.util.List;

import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;

public interface ISystemLogDao {
	/**
	 * 查询登录日志
	 * @throws Exception
	 */
	public List<Loginls> querylogin(String tjms,String ksrq, String jsrq,Loginls loginls)throws Exception;
	/**
	 * 查询操作日志
	 * @throws Exception
	 */
	public List<Operate> queryOperate(String tjms,String ksrq, String jsrq,Operate operate)throws Exception;
	/**
	 * 查询安全日志
	 * @throws Exception
	 */
	public List<Security> querySecurity(String tjms,String ksrq, String jsrq,Security security)throws Exception;
	/**
	 *   插入登录信息到日志
	 * @param loginls  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addLoginls(Loginls loginls) throws Exception;
	
	/**
	 *   插入登录信息到日志-退出按钮
	 * @param loginls  字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer updateLoginlsExit(Date date) throws Exception;
	
	
	/**
	 *   插入操作信息到日志
	 * @param Operate字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addOperate(Operate operate) throws Exception;
	
	
	/**
	 *   插入安全信息到日志
	 * @param Security字段对象
	 * @return         执行行数
	 * @throws Exception
	 */
	public Integer addSecurity(Security security) throws Exception;
	
	/**
	 *   查询本次登录离上一次登录成功内的登录失败数据
	 */
	public List<Loginls> queryLoginErr(Loginls loginls) throws Exception;
	/**
	 *   查询上一次登录成功数据
	 */
	public List<Loginls> queryLoginSuu(Loginls loginls) throws Exception;
	/**
	 *   查询一小时内登录的次数
	 */
	public String queryLoginCount(Loginls loginls) throws Exception;
	/**
	 *   根据id查询操作日记
	 */
	public List<Operate> qureyDetailed(String id) throws Exception;
	/**
	 *   根据id查询安全日记
	 */
	public List<Security> qureyDetailedSe(String id) throws Exception;
}
