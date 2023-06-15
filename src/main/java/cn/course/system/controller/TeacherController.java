package cn.course.system.controller;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Course;
import cn.course.system.domain.News;
import cn.course.system.domain.Teacher;
import cn.course.system.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teacher")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("/findPage")
    public ResultVO getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = teacherService.findByPage(queryPageBean);
        return new ResultVO(ResStatus.OK, "success!",page);
    }

    @GetMapping("/getAll")
    public ResultVO getAll(){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        List<Teacher> teachersList = teacherService.list(wrapper.select("DISTINCT tid, name, phone"));
        return new ResultVO(ResStatus.OK, "success!",teachersList);
    }

    @PutMapping("/")
    public ResultVO addTeacher(@RequestBody Teacher teacher){
        String id = "t" + new Date().getTime() + RandomStringUtils.randomAlphabetic(5);
        teacher.setId(id);
        boolean save = teacherService.save(teacher);
        if (save) {
            return new ResultVO(ResStatus.OK, "添加成功！");
        } else {
            return new ResultVO(ResStatus.NO, "添加失败！");
        }
    }

    @PostMapping("/")
    public ResultVO updateTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.updateById(teacher);
        if (save) {
            return new ResultVO(ResStatus.OK, "修改成功！");
        } else {
            return new ResultVO(ResStatus.NO, "修改失败！");
        }
    }

    @DeleteMapping("/{id}")
    public ResultVO removeTeacher(@PathVariable String id){
        boolean save = teacherService.removeById(id);
        if (save) {
            return new ResultVO(ResStatus.OK, "修改成功！");
        } else {
            return new ResultVO(ResStatus.NO, "修改失败！");
        }
    }
}
