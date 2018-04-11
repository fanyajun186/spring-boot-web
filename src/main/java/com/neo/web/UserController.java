package com.neo.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.dao.UserRepository;
import com.neo.domain.User;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
    	User user=userRepository.findByUserName("aa");
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return user;
    }
    
    @RequestMapping("/getUsers")
    @Cacheable(value="key-Users")
    public List<User> getUsers() {
    	List<User> users=userRepository.findAll();
    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return users;
    }
    
    
    @RequestMapping("/addUsers")
    @Cacheable(value="add-Users")
    public void addUsers() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
        String formattedDate = dateFormat.format(date);
        userRepository.save(new User("aa9", "aa9@126.com", "aa", "aa9",formattedDate));
        userRepository.save(new User("bb9", "bb9@126.com", "bb", "bb9",formattedDate));
        userRepository.save(new User("cc9", "cc9@126.com", "cc", "cc9",formattedDate));
    }
}