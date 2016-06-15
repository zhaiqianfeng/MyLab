package com.zhaiqianfeng.Lab.JavaBean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyBean implements PropertyChangeListener {
	private int userId;
	private String userName;
	private PropertyChangeSupport pcs=new PropertyChangeSupport(this);
	
	public MyBean(){
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
		String oldName=this.userName;
		this.userName = userName;
		pcs.firePropertyChange("name", oldName, userName);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(String.format("%s,%s,%s", evt.getPropertyName(),evt.getOldValue(),evt.getNewValue()));
	}
}
