package cn.course.system.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class UsersVO implements Serializable {
    /**
     * 主键id 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名 用户名
     */
    private String username;

    /**
     * 密码 密码
     */
//    @TableField(select = false) // 查询保护
    private String password;

    /**
     * 手机号 手机号
     */
    private String userMobile;

    /**
     * 邮箱地址 邮箱地址
     */
    private String userEmail;

    /**
     * 性别 M(男) or F(女)
     */
    private String userSex;

    /**
     * 注册时间 创建时间
     */
    private Date userRegtime;

    /**
     * 更新时间 更新时间
     */
    private Date userModtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}