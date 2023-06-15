package cn.course.system.service.impl;

import cn.course.system.common.utils.MD5Utils;
import cn.course.system.common.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.Student;
import cn.course.system.service.StudentService;
import cn.course.system.mapper.StudentMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author xiaole
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-02-10 19:14:03
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public ResultVO checkLogin(String sid, String passwd) {

        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        QueryWrapper<Student> login = wrapper.select().eq("sid", sid);
        List<Student> logins  = studentMapper.selectList(login);
        if (logins.size() == 0) {
            return new ResultVO(ResStatus.NO, "登陆失败，用户名或密码不正确！", null);
        } else {
            String md5Pwd = MD5Utils.md5(passwd);
            assert md5Pwd != null;
            if (md5Pwd.equals(logins.get(0).getPasswd())){
                Map<String, Object> map = new HashMap<>();
                map.put("sid", logins.get(0).getSid());
                JwtBuilder builder = Jwts.builder();
                // 使用jwt生成token
                String token = builder
                        .setSubject(logins.get(0).getName())
                        .setIssuedAt(new Date()).setId(logins.get(0).getSid()+"")
                        .setClaims(map)
                        .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                        .signWith(SignatureAlgorithm.HS256, "XiaoLe999")
                        .compact();
                Student student = new Student();
                student.setName(logins.get(0).getName());
                student.setSid(logins.get(0).getSid());
                return new ResultVO(ResStatus.OK, token, student);
            } else {
                return new ResultVO(ResStatus.NO, "登陆失败，用户名或密码不正确！", null);
            }
        }
    }

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<Student> page = new Page<>(currentPage,pageSize);
        studentMapper.findByPage(page, queryString);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Transactional
    public ResultVO userRegister(Student student) {
        synchronized (this) {
            // 1.根据用户查询，这个用户是否被注册
            QueryWrapper<Student> wrapper = new QueryWrapper<>();
            QueryWrapper<Student> username = wrapper.select().eq("sid", student.getSid());
            List<Student> students = studentMapper.selectList(username);
            // 2.如果没被注册则进行保存
            if (students.size() == 0) {
                String md5Pwd = MD5Utils.md5(student.getPasswd());
                Student student1 = new Student();
                student1.setSid(student.getSid());
                student1.setName(student.getName());
                student1.setPhone(student.getPhone());
                student1.setPasswd(md5Pwd);
                int i = studentMapper.insert(student1);
                if (i > 0) {
                    student1.setPasswd(null);
                    return new ResultVO(ResStatus.OK, "添加成功！", student1);
                } else {
                    return new ResultVO(ResStatus.NO, "添加失败！", null);
                }
            } else {
                return new ResultVO(ResStatus.NO, "学生已被注册！", null);
            }
        }
    }
}




