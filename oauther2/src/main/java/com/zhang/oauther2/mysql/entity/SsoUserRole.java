package com.zhang.oauther2.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class SsoUserRole extends Model<SsoUserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Integer userId;

    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
