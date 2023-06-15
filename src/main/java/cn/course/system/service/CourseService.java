package cn.course.system.service;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xiaole
* @description 针对表【course】的数据库操作Service
* @createDate 2023-02-07 21:08:45
*/
public interface CourseService extends IService<Course> {

    PageResult findByPage(QueryPageBean queryPageBean);

    List<Course> getCourse(String queryStr);
}
