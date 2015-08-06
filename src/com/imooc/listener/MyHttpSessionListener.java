package com.imooc.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.imooc.entity.User;
import com.imooc.util.SessionUtil;
@WebListener
//统计在线人数
//这个在线人数统计有个问题：num++ 不是原子操作，并发量大的情况下可能会有影响。建议增加将num++封装成一个方法，用同步（synchronized）的方式进行处理。
public class MyHttpSessionListener implements HttpSessionListener {
	
	private int userNumber = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		userNumber++;
		arg0.getSession().getServletContext().setAttribute("userNumber", userNumber);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		userNumber--;
		arg0.getSession().getServletContext().setAttribute("userNumber", userNumber);
		
		ArrayList<User> userList = null;//在线用户List
		
		userList = (ArrayList<User>)arg0.getSession().getServletContext().getAttribute("userList");
		
		if(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId())!=null){
			userList.remove(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId()));
		}
		
	}

}
