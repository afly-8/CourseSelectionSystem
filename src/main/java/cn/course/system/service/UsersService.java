package cn.course.system.service;

import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaole
* @description 针对表【users(用户 )】的数据库操作Service
* @createDate 2022-10-22 10:56:29
*/
public interface UsersService extends IService<Users> {
    // 用户注册
    ResultVO userRegister(String name, String pwd);

    // 用户登录
    ResultVO checkLogin(String name, String pwd);
}
