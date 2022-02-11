package com.zhang.oauther2.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoPermissionRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
public interface ISsoPermissionRoleService extends IService<SsoPermissionRole> {

    UserInfo getUserInfoByUserName(String username);
}
