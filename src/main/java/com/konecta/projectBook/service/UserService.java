package com.konecta.projectBook.service;

import com.konecta.projectBook.model.User;

public interface UserService {

	void save(User user);
    User findByUsername(String username);
}
