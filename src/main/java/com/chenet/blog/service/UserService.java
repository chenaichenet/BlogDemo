/**
 * FileName: UserService
 * Author:   嘉平十七
 * Date:     2020/8/19 13:33
 * Description:
 */
package com.chenet.blog.service;

import com.chenet.blog.domain.User;

public interface UserService {
    User checkUser(String username,String password);
}
