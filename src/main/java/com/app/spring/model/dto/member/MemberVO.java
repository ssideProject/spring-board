package com.app.spring.model.dto.member;

import java.sql.Date;

public class MemberVO {
	String name;
	String age;
	String id;
	String passwd;
	private Date reg; //뒤늦게 날짜를 추가했다.
	private Date updt; // 여기도 같이 추가를 해버렸다. 칼럼을 추가하니 데이터는 오늘날로 자동으로 들어갔다.
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getReg() {
		return reg;
	}
	public void setReg(Date reg) {
		this.reg = reg;
	}
	public Date getUpdt() {
		return updt;
	}
	public void setUpdt(Date updt) {
		this.updt = updt;
	}
	
	
}