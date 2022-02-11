package com.zhang.oauther2.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
public interface ISsoUserService extends IService<SsoUser> {

    UserInfo getUserInfoByUserName(String username);
}
