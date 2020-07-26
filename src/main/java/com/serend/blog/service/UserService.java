package com.serend.blog.service;

import com.serend.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
