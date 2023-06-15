package cn.course.system.mapper;

import cn.course.system.domain.News;
import cn.course.system.domain.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
* @author xiaole
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2023-02-07 21:49:33
* @Entity cn.course.system.domain.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {
    Page<Teacher> findByPage(@Param("page") Page<Teacher> page, @Param("queryStr") String queryStr);

}




