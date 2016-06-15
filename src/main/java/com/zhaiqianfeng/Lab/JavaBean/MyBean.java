package com.zhaiqianfeng.Lab.JavaBean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 观察者模式
 * 把观察者注册到PropertyChangeSupport中，观察者需要实现PropertyChangeListener接口
 * 通过firePropertyChange把事件通知观察者
 *
 */
public class MyBean implements PropertyChangeListener {
	private int userId;
	private String userName;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public MyBean() {
		pcs.addPropertyChangeListener(this);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		String oldName = this.userName;
		this.userName = userName;
		pcs.firePropertyChange("name", oldName, userName);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(String.format("%s,%s,%s", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue()));
	}
}
