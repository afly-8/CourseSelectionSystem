package cn.course.system.mapper;

import cn.course.system.domain.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
* @author xiaole
* @description 针对表【news(新闻表)】的数据库操作Mapper
* @createDate 2023-02-06 14:17:32
* @Entity cn.course.system.domain.News
*/
public interface NewsMapper extends BaseMapper<News> {
    Page<News> findByPage(@Param("page") Page<News> page, @Param("queryStr") String queryStr);
}




