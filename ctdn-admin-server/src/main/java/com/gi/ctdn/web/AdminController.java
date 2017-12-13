package com.gi.ctdn.web;

import com.gi.ctdn.pojo.Admin;
import com.gi.ctdn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
public class AdminController {

    @Autowired
    AdminService service;

}
