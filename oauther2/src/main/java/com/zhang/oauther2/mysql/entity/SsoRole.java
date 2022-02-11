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
public class SsoRole extends Model<SsoRole> {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField("role_name")
    private String roleName;

    @TableField("create_time")
    private Date createTime;

    @TableField("upate_time")
    private Date upateTime;

    @TableField("code")
    private String code;

    @TableField("status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
