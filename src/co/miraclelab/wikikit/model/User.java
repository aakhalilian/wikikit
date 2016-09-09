package co.miraclelab.wikikit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.miraclelab.webframe.model.UserInfo;
@Document
public class User {
	@Id
	public String id;
	private String username;
	private String password;
	private UserInfo userInfo;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPassword(String password){
		return this.password.equals(password);
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
