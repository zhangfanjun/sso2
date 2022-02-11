package com.zhang.oauther2.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
public interface SsoUserMapper extends BaseMapper<SsoUser> {

    UserInfo getUserInfoByUserName(String username);

}
