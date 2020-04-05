package com.suntendy.queue.window.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.opensymphony.webwork.ServletActionContext;
import com.suntendy.queue.advertise.domain.Advertise;
import com.suntendy.queue.advertise.service.IAdvertiseService;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.employee.util.ImageUtils;
import com.suntendy.queue.util.cache.CacheManager;

/*****************************************************************
 * 描述: 用户办理业务PC端信息展示 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 施海波 <br>
 * 创建日期: 2014-03-5 14:53:08 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************/

public class TVWindowAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IAdvertiseService advertiseService;
	private File file1;
	
	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	/**
	 * 电视窗口窗口显示
	 * @return
	 */
	public String tvWindow() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		//广告信息列表
		List<Advertise> msgList = advertiseService.getTVadvertise(deptCode, deptHall);
		if (null != msgList && !msgList.isEmpty()) {
			Advertise advertise = msgList.get(0);
			request.setAttribute("id", advertise.getId());
			request.setAttribute("message", advertise.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 调用EXE控制客户端IE
	 * @return
	 */
	public String  IEListen(){
		HttpServletRequest request = getRequest();
		String par =request.getParameter("pare");
		Runtime r = Runtime.getRuntime();
	    try {
	    	r.exec("C:\\服务器上运行的文件\\JhDoublePServer.exe " + par);
			request.setAttribute("msg", "完成执行！");
		} catch (IOException e) {
			e.printStackTrace();
			request.setAttribute("msg", "执行过程中发生异常！");
		}
		request.setAttribute("action", "window/ieListen.jsp");
		return SUCCESS;
	}
	
	/**
	 * ajax 获取广告信息
	 * @return
	 * @throws Exception
	 */
	public String getAdvertise() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		
		//广告信息列表
		List<Advertise> msgList = advertiseService.getTVadvertise(deptCode, deptHall);
		if (!msgList.isEmpty()) {
			Advertise advertise = msgList.get(0);
			this.getResponse().getWriter().print(advertise.getMessage());
		}
		return null;
	}
	
	public String update() {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		Advertise advertise = new Advertise();
		advertise.setTvUse("1");
		advertise.setId(request.getParameter("id"));
		advertise.setMessage(request.getParameter("message"));
		advertise.setDeptcode(cacheManager.getOfDeptCache("deptCode"));
		advertise.setDepthall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			if (StringUtils.isEmpty(advertise.getId())) {
				advertiseService.addAdvertise(advertise);
			} else {
				advertiseService.updateByCode(advertise.getId(), advertise.getMessage(), "");
			}
			request.setAttribute("msg", "广告信息修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "广告信息修改失败，<br>在执行过程中发生异常！");
		}

		request.setAttribute("action", "pcWindow.action");
		return SUCCESS;
	}

	/**
	 * 播放视频
	 * @return
	 * @throws Exception
	 */
	public String getImg() throws Exception{
		HttpServletResponse response=getResponse("image/jpeg");
		ServletContext d=ServletActionContext.getServletContext();
	    String path=d.getRealPath("/");
		File uploadDirFile=new File(path+"/TVupload");
		FileInputStream inputFile = new FileInputStream(uploadDirFile);  
		byte[] buffer = new byte[(int)uploadDirFile.length()];  
		inputFile.read(buffer);  
		inputFile.close();  
	    if(uploadDirFile.exists()){
	    	ServletOutputStream out=response.getOutputStream();
			out.write(new BASE64Decoder().decodeBuffer(new BASE64Encoder().encode(buffer)));
			out.flush();
			out.close();
	    }
				
		return null;
	}
	
	public String upload() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		String path = getHttpSession().getServletContext().getRealPath("/");
	    String filename = request.getParameter("filename");
	    filename = new String(filename.getBytes("iso8859_1"),"UTF-8");
	    ImageUtils.delFolder(path);
	//  如果文件夹不存在，则建立之
		File uploadDirFile=new File(path+"/TVupload");
		if(!uploadDirFile.exists()){ 
				uploadDirFile.mkdirs();
			}
		
        try {
            FileInputStream inputStream = new FileInputStream(this.getFile1());
            FileOutputStream outputStream = new FileOutputStream(path+"/TVupload/"+ filename);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "视频上传失败，<br>在执行过程中发生异常！");
        }
        request.setAttribute("msg", "视频上传成功！");
        
        //将视频路径放到缓存
		Map<String, String> replaceTV = new HashMap<String, String>();
		replaceTV.put("tvName", "/queue/TVupload/"+filename);
		CacheManager.getInstance().setReplaceTV(replaceTV);
        
        request.setAttribute("action", "pcWindow.action");	
		return SUCCESS;
	}
}