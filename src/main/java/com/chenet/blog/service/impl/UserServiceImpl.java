/**
 * FileName: UserServiceImpl
 * Author:   嘉平十七
 * Date:     2020/8/19 13:34
 * Description:
 */
package com.chenet.blog.service.impl;

import com.chenet.blog.dao.UserRepository;
import com.chenet.blog.domain.User;
import com.chenet.blog.service.UserService;
import com.chenet.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}