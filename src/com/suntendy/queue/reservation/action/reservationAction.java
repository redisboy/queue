package com.suntendy.queue.reservation.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.reservation.domain.Reservation;
import com.suntendy.queue.reservation.service.IReservationService;
import com.suntendy.queue.util.DateUtils;

public class reservationAction extends BaseAction {
	private IReservationService reservationService;

	public IReservationService getReservationService() {
		return reservationService;
	}

	public void setReservationService(IReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	
	public String queryReservation() throws Exception{
		HttpServletRequest request = getRequest();
		String idnumber = request.getParameter("idnumber");
		Reservation res = new Reservation();
		res.setSfzmhm(idnumber);
		res.setFlag("0");
		String yykssj = "",yyjssj="";
		String msg = "";
		boolean flag = false;
		JSONObject obj = new JSONObject();
		try {
			List<Reservation> list = reservationService.queryReservation(res);
			if (null !=list && list.size()>0) {
				yykssj = list.get(0).getYykssj();
				yyjssj = list.get(0).getYyjssj();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date datekssj = sdf.parse(yykssj);
				Date datejssj = sdf.parse(yyjssj);
				Date date = new Date();
				if (date.getTime() < datekssj.getTime()) {
					msg = "您的预约时间还没到!";
				}else if (date.getTime()>datekssj.getTime() && date.getTime()<datejssj.getTime()) {
					msg = list.get(0).getZjlx()+"@"+list.get(0).getYwlx();
					flag = true;
				}else if (date.getTime()>datejssj.getTime()) {
					msg = "您的预约时间已过!";
				} else {
					msg ="预约时间出现错误!";
				}
			}else {
				msg="您没有预约信息!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(msg);
		obj.put("flag", flag);
		obj.put("msg", msg);
		this.getResponse("text/html").getWriter().println(obj.toString());
		return null;
	}
	
	

}
