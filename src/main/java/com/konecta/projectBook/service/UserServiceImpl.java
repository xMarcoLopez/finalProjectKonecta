package com.konecta.projectBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.konecta.projectBook.model.User;
import com.konecta.projectBook.model.UserStatus;
import com.konecta.projectBook.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(User user) {
		UserStatus userStatus = new UserStatus();
		userStatus.setId(1);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserStatus(userStatus);
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return  userRepository.findByUsername(username);
	}

}
