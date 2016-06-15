/**
 * 测试javabean的概念
 * JavaBean特征：
 * 		有public无参构造器
 * 		属性private，通过public方法取赋值，比如set\get
 * 		基于事件思想的触发，多用于swing
 * 有java.beans包的支持
 */
package com.zhaiqianfeng.Lab.JavaBean;

public class LabApp {

	public static void main(String[] args) {
		MyBean myBean=new MyBean();
		myBean.setUserName("james");
		myBean.setUserName("zhaiqianfeng");
		
		System.out.println("over");
	}

}