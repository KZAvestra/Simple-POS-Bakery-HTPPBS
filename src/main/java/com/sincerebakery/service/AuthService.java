/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.service;

import com.sincerebakery.entity.User;
import com.sincerebakery.repository.UserRepository;

/**
 *
 * @author Silka
 */
public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    public User login(String username, String password) throws Exception {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }
        return userRepository.login(username.trim(), password);
    }
}
