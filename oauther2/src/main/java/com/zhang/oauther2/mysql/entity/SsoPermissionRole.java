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
public class SsoPermissionRole extends Model<SsoPermissionRole> {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField("permission_id")
    private Integer permissionId;

    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
