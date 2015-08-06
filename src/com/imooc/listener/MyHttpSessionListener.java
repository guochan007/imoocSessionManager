package com.imooc.listener;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.imooc.entity.User;
import com.imooc.util.SessionUtil;
@WebListener
//ͳ����������
//�����������ͳ���и����⣺num++ ����ԭ�Ӳ������������������¿��ܻ���Ӱ�졣�������ӽ�num++��װ��һ����������ͬ����synchronized���ķ�ʽ���д���
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
		
		ArrayList<User> userList = null;//�����û�List
		
		userList = (ArrayList<User>)arg0.getSession().getServletContext().getAttribute("userList");
		
		if(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId())!=null){
			userList.remove(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId()));
		}
		
	}

}
