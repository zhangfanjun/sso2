package com.zhang.oauther2.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoPermissionRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
public interface SsoPermissionRoleMapper extends BaseMapper<SsoPermissionRole> {

    UserInfo getUserInfoByUserName(@Param("userName") String userName);
}
