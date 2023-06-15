package cn.course.system.mapper;

import cn.course.system.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiaole
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-02-07 21:08:45
* @Entity cn.course.system.domain.Course
*/
public interface CourseMapper extends BaseMapper<Course> {
    Page<Course> findByPage(@Param("page") Page<Course> page, @Param("queryStr") String queryStr);

    List<Course> getCourse(String queryStr);
}




