package cn.course.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {

    private String cid;

    private String name;

    private String time;

    private String place;

    private Integer sum;

    private String type;

    @TableField(exist = false)
    private String tname;

    @TableField(exist = false)
    private String tid;

    @TableField(exist = false)
    private String num;

    private static final long serialVersionUID = 1L;
}