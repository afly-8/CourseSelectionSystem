package cn.course.system.service.impl;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.Course;
import cn.course.system.service.CourseService;
import cn.course.system.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
* @author xiaole
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-02-07 21:08:45
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Resource
    private CourseMapper courseMapper;

    @Override
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<Course> page = new Page<>(currentPage,pageSize);
        courseMapper.findByPage(page, queryString);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Course> getCourse(String queryStr) {
        return courseMapper.getCourse(queryStr);
    }
}




