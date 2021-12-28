package com.tkp.user.service;

import com.tkp.user.VO.Department;
import com.tkp.user.VO.ResponseTemplateVO;
import com.tkp.user.entity.User;
import com.tkp.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
    
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	
	public static int testStaticMethodInJunit(int input) {
		return input*2;
		
	}
}
