package com.firs.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firs.server.Entity.Tuser;
import com.firs.server.dao.UserDao;
import com.firs.server.mq.JmsSender;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	private JmsSender sender;
	
	@GetMapping(path = "/send")
	public void send() {
		for (int i = 1; i < 6; i++) {
			this.sender.sendByQueue("hello activemq queue " + i);
			this.sender.sendByTopic("hello activemq topic " + i);
		}
	}
	
	@PostMapping(path = "/get")
    public Tuser getUserInfo(@RequestParam String sname)
    {
        Tuser user = userDao.findOneBySname(sname);
        System.out.println(user);
        return user;
    }
}
