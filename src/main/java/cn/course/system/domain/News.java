package cn.course.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName news
 */
@TableName(value ="news")
@Data
public class News implements Serializable {
    private String id;

    private String title;

    private String releaseTime;

    private String sign;

    private static final long serialVersionUID = 1L;
}