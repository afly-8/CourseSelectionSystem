package cn.course.system.service.impl;

import cn.course.system.common.utils.MD5Utils;
import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.common.vo.UsersVO;
import cn.course.system.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.Users;
import cn.course.system.mapper.UsersMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author xiaole
* @description 针对表【users(用户 )】的数据库操作Service实现
* @createDate 2022-10-22 10:56:29
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional
    public ResultVO userRegister(String name, String pwd) {
        synchronized (this) {
            // 1.根据用户查询，这个用户是否被注册
            QueryWrapper<Users> wrapper = new QueryWrapper<>();
            QueryWrapper<Users> username = wrapper.select().eq("username", name);
            List<Users> users = usersMapper.selectList(username);
            // 2.如果没被注册则进行保存
            if (users.size() == 0) {
                String md5Pwd = MD5Utils.md5(pwd);
                Users user = new Users();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                int i = usersMapper.insert(user);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "注册成功！", user);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败！", null);
                }
            } else {
                return new ResultVO(ResStatus.NO, "用户名被注册！", null);
            }
        }
    }


    @Override
    public ResultVO checkLogin(String name, String pwd) {

        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        QueryWrapper<Users> username = wrapper.select().eq("username", name);
        List<Users> users = usersMapper.selectList(username);
        if (users.size() == 0) {
            return new ResultVO(ResStatus.NO, "登陆失败，用户名或密码不正确！", null);
        } else {
            String md5Pwd = MD5Utils.md5(pwd);
            assert md5Pwd != null;
            if (md5Pwd.equals(users.get(0).getPassword())){
                Map<String, Object> map = new HashMap<>();
                map.put("key1", "value1");
                JwtBuilder builder = Jwts.builder();
                // 使用jwt生成token
                String token = builder
                        .setSubject(name)
                        .setIssuedAt(new Date()).setId(users.get(0).getUserId()+"")
                        .setClaims(map)
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                        .signWith(SignatureAlgorithm.HS256, "XiaoLe999")
                        .compact();
                UsersVO usersVO = new UsersVO();
                usersVO.setUsername(users.get(0).getUsername());
                usersVO.setUserSex(users.get(0).getUserSex());
                usersVO.setUserMobile(users.get(0).getUserMobile());
                return new ResultVO(ResStatus.OK, token, usersVO);
            } else {
                return new ResultVO(ResStatus.NO, "登陆失败，用户名或密码不正确！", null);
            }
        }
    }
}




