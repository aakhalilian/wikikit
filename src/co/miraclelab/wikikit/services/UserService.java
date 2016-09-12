package co.miraclelab.wikikit.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.miraclelab.webframe.utilities.ServiceAccessor;
import co.miraclelab.wikikit.model.User;
import co.miraclelab.wikikit.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public static final String USERVAR="authenticatedUser";
	
	public boolean isExist(String username){
		return(userRepository.findByUsername(username)!=null);
	}
	
	public void createUser(User user) throws Throwable{
		if(this.isExist(user.getUsername())){
			throw new Throwable("user-already-exist");
		}
		userRepository.save(user);		
	}
	
	public boolean login(String username, String password) throws Throwable{
		User user=userRepository.findByUsername(username);
		if(user==null){
			throw new Throwable("user-not-exist");
		}
		if(user.isPassword(password)){
			HttpSession session=ServiceAccessor.getServletRequest().getSession();
			session.setAttribute(USERVAR, user);
			return true;
		}else
		return false;
	}
	
	public User getLoggedinUser(){
		HttpSession session=ServiceAccessor.getServletRequest().getSession();
		return (User) session.getAttribute(USERVAR);
	}
	
	public void logout(){
		HttpSession session=ServiceAccessor.getServletRequest().getSession();
		session.removeAttribute(USERVAR);
	}
}
