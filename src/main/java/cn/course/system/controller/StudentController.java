package cn.course.system.controller;


import cn.course.system.common.utils.MD5Utils;
import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Scourse;
import cn.course.system.domain.Student;
import cn.course.system.domain.Users;
import cn.course.system.service.ScourseService;
import cn.course.system.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private ScourseService scourseService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody Student student, HttpServletResponse response){
        ResultVO resultVO = studentService.checkLogin(student.getSid(), student.getPasswd());
        if (resultVO.getCode() == ResStatus.OK) {
            response.setHeader("access-token", resultVO.getMsg());
            return resultVO;
        }
        else {
            return new ResultVO(ResStatus.NO, "登陆失败", null);
        }
    }

    @PostMapping("/scourse")
    public ResultVO courseSelection(String sid, String cid) {
        return scourseService.selectCourse(sid, cid);
    }

    @PostMapping("/findPage")
    public ResultVO getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = studentService.findByPage(queryPageBean);
        return new ResultVO(ResStatus.OK, "success!",page);
    }

    @PutMapping("/add")
    public ResultVO register(@RequestBody Student student) {
        return studentService.userRegister(student);
    }

    @PostMapping("/edit")
    public ResultVO edit(@RequestBody Student student) {
        student.setPasswd(MD5Utils.md5(student.getPasswd()));
        boolean update = studentService.updateById(student);
        if (update) {
            return new ResultVO(ResStatus.OK, "修改成功!");
        } else {
            return new ResultVO(ResStatus.NO, "修改失败!");
        }
    }

    @DeleteMapping("/{sid}")
    public ResultVO delete(@PathVariable String sid){
        boolean remove = studentService.removeById(sid);
        if (remove) {
            return new ResultVO(ResStatus.OK, "删除成功!");
        } else {
            return new ResultVO(ResStatus.NO, "删除失败!");
        }
    }
}
