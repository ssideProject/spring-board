package com.app.spring.model.dto.cookie;

public class SiteVO {
	private String subject;
	private String URL;
	private boolean rememberSite;
	
	public SiteVO(String sub, String url, boolean use){
		this.subject = sub;
		this.URL = url;
		this.rememberSite = use;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public boolean isRememberSite() {
		return rememberSite;
	}
	public void setRememberSite(boolean rememberSite) {
		this.rememberSite = rememberSite;
	}

	@Override
	public String toString() {
		return "SiteVO [subject=" + subject + ", URL=" + URL + ", rememberSite=" + rememberSite + "]";
	}
	
	
}
