package cn.course.system.service.impl;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.domain.Course;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.Teacher;
import cn.course.system.service.TeacherService;
import cn.course.system.mapper.TeacherMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author xiaole
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2023-02-07 21:49:33
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Resource
    private TeacherMapper teacherMapper;


    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<Teacher> page = new Page<>(currentPage,pageSize);
        teacherMapper.findByPage(page, queryString);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean saveByID(String tid, String cid) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("tid", tid);
        Teacher teacher = teacherMapper.selectOne(wrapper);
        teacher.setCid(cid);
        String id = "t" + new Date().getTime() + RandomStringUtils.randomAlphabetic(5);
        teacher.setId(id);
        return teacherMapper.insert(teacher) > 0;
    }

    @Override
    public boolean updateByID(String tid, String cid) {
        QueryWrapper<Teacher> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("tid", tid);
        Teacher teacher = teacherMapper.selectOne(wrapper1);
        UpdateWrapper<Teacher> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq("cid", cid);
        teacher.setCid(cid);
        return teacherMapper.update(teacher, wrapper2) > 0;
    }
}




