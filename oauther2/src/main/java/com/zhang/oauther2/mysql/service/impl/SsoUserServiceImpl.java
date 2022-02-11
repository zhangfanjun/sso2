package com.zhang.oauther2.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoUser;
import com.zhang.oauther2.mysql.mapper.SsoUserMapper;
import com.zhang.oauther2.mysql.service.ISsoUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author java
 * @since 2022-02-11
 */
@Service
public class SsoUserServiceImpl extends ServiceImpl<SsoUserMapper, SsoUser> implements ISsoUserService {

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        return this.baseMapper.getUserInfoByUserName(username);
    }
}
