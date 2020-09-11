/**
 * FileName: UserRepository
 * Author:   嘉平十七
 * Date:     2020/8/19 13:35
 * Description: 用户
 */
package com.chenet.blog.dao;

import com.chenet.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}