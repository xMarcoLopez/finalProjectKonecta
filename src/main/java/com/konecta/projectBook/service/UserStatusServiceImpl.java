package com.konecta.projectBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konecta.projectBook.model.UserStatus;
import com.konecta.projectBook.repository.UserStatusRepository;

@Service
public class UserStatusServiceImpl implements UserStatusService{

	@Autowired
	UserStatusRepository userStatusRepository;
	
	@Override
	public List<UserStatus> toList() {
		
		return userStatusRepository.findAll();
	}

}
