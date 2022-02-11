package com.zhang.oauther2.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SsoUser extends Model<SsoUser> {

    private static final long serialVersionUID = 1L;

    @TableId(type=IdType.AUTO)
    private Integer id;

    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 全名
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 微信登录用
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信登录用
     */
    @TableField("app_id")
    private String appId;

    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("delete")
    private Integer delete;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
