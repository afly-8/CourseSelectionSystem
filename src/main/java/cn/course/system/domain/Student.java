package cn.course.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    @TableId
    private String sid;

    private String phone;

    private String passwd;

    private String name;

    private static final long serialVersionUID = 1L;
}