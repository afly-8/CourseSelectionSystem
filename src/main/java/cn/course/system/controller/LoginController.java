package cn.course.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 允许跨域
@CrossOrigin(origins = "*")
@Controller
public class LoginController {

    @RequestMapping(value = {"/","/static/login.html"})
    public String index(){
        return "login";
    }

}
