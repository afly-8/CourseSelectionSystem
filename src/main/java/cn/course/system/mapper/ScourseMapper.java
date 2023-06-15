package cn.course.system.mapper;

import cn.course.system.common.vo.ScourseVO;
import cn.course.system.domain.Scourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiaole
* @description 针对表【scourse】的数据库操作Mapper
* @createDate 2023-02-11 15:54:47
* @Entity cn.course.system.domain.Scourse
*/
public interface ScourseMapper extends BaseMapper<Scourse> {

    List<Scourse> getCount();

    Page<ScourseVO> findByPage(@Param("page") Page<ScourseVO> page, @Param("queryStr") String queryStr);
}




