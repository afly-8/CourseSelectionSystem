package cn.course.system.controller;

import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Users;
import cn.course.system.service.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class UsersController {

    @Resource
    private UsersService usersService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody Users users, HttpServletResponse response) {
        ResultVO resultVO = usersService.checkLogin(users.getUsername(), users.getPassword());
        if (resultVO.getCode() == ResStatus.OK) {
            response.setHeader("access-token", resultVO.getMsg());
            return resultVO;
        }
        else {
            return new ResultVO(ResStatus.NO, "登陆失败", null);
        }
    }

    @PostMapping("/register")
    public ResultVO register(Users users) {
        return usersService.userRegister(users.getUsername(), users.getPassword());
    }
    // 如果登录成功，则需要生成令牌token，并将token返回给前端
}
