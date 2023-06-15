package cn.course.system.service.impl;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.News;
import cn.course.system.service.NewsService;
import cn.course.system.mapper.NewsMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author xiaole
* @description 针对表【news(新闻表)】的数据库操作Service实现
* @createDate 2023-02-06 14:17:32
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

    @Resource
    private NewsMapper newsMapper;

    public List<News> getAll() {
        return newsMapper.selectList(null);
    }

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<News> page = new Page<>(currentPage,pageSize);
        newsMapper.findByPage(page, queryString);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean save(News news) {
        String id = "n"+new Date().getTime() + RandomStringUtils.randomAlphabetic(5);
        news.setId(id);
        return newsMapper.insert(news) > 0;
    }

    @Override
    public boolean update(News news) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.eq("id", news.getId());
        return newsMapper.update(news, wrapper) > 0;
    }

    @Override
    public boolean remove(String id) {
        QueryWrapper<News> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return newsMapper.delete(wrapper) > 0;
    }
}




