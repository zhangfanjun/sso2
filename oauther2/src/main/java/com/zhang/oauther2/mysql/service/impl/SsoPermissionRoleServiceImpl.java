package com.zhang.oauther2.mysql.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.entity.SsoPermissionRole;
import com.zhang.oauther2.mysql.mapper.SsoPermissionRoleMapper;
import com.zhang.oauther2.mysql.service.ISsoPermissionRoleService;
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
public class SsoPermissionRoleServiceImpl extends ServiceImpl<SsoPermissionRoleMapper, SsoPermissionRole> implements ISsoPermissionRoleService {

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return this.baseMapper.getUserInfoByUserName(userName);

    }
}
