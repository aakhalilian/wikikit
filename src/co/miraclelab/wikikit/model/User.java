package co.miraclelab.wikikit.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import co.miraclelab.webframe.utilities.EncryptService;
import co.miraclelab.webframe.utilities.ServiceAccessor;

public class User extends Storable {
	
	private String username;
	private String password;
	private UserInfo userInfo;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) throws UnsupportedEncodingException, GeneralSecurityException {
		EncryptService encrypt=ServiceAccessor.getEncryptService();
		String encryptedPassword=encrypt.encrypt(password);
		this.password = encryptedPassword;
	}
	
	public boolean isPassword(String password) throws GeneralSecurityException, IOException{
		EncryptService encrypt=ServiceAccessor.getEncryptService();
		String decryptedPassword=encrypt.decrypt(this.password);
		return decryptedPassword.equals(password);
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
