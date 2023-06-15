package cn.course.system.mapper;

import cn.course.system.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
* @author xiaole
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-02-10 19:14:03
* @Entity cn.course.system.domain.Student
*/
public interface StudentMapper extends BaseMapper<Student> {

    Page<Student> findByPage(@Param("page") Page<Student> page, @Param("queryStr") String queryStr);
}




