package cn.course.system.service;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.domain.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xiaole
* @description 针对表【news(新闻表)】的数据库操作Service
* @createDate 2023-02-06 14:17:32
*/
public interface NewsService extends IService<News> {

    // 获取所有新闻

    List<News> getAll();
    PageResult findByPage(QueryPageBean queryPageBean);

    boolean save(News news);
    boolean update(News news);
    boolean remove(String id);
}
