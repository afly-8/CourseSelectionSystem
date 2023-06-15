package cn.course.system.service;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaole
* @description 针对表【student】的数据库操作Service
* @createDate 2023-02-10 19:14:03
*/
public interface StudentService extends IService<Student> {

    ResultVO checkLogin(String name, String pwd);

    PageResult findByPage(QueryPageBean queryPageBean);

    ResultVO userRegister(Student student);
}
