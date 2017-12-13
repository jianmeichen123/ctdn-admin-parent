package com.gi.ctdn.service;

import com.gi.ctdn.dao.AdminDao;
import com.gi.ctdn.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDao dao;

    public Admin getBylogin(String login){
        return dao.selectByLogin(login);
    }
}
