package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repojitory.UserRepojitory;

@Service
public class UserServiceImpl {

	public final UserRepojitory userRepojitory;
	@Autowired
	public UserServiceImpl(UserRepojitory userRepojitory) {
		this.userRepojitory = userRepojitory;
	}

	public List<UserEntity>  findAll(){
		return userRepojitory.findAll();
	}

	public UserEntity save(UserEntity userEntity) {
		return userRepojitory.save(userEntity) ;
	}

	public void delete(Integer userid) {
		userRepojitory.deleteById(userid);
	}

	public UserEntity find_by(Integer userid) {
		return userRepojitory.getOne(userid);
	}


}
