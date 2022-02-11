package com.zhang.oauther2.service;

import com.zhang.oauther2.model.UserInfo;
import com.zhang.oauther2.mysql.service.ISsoPermissionRoleService;
import com.zhang.oauther2.mysql.service.ISsoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISsoUserService userService;

    /**
     * 登录的密码都是123456
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userService.getUserInfoByUserName(username);
        log.info("用户信息：{}",userInfo);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userInfo.getRolesList())) {
            userInfo.getRolesList().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getCode()))
            );
        }

        return new User(username, this.passwordEncoder.encode(userInfo.getPassword()), userInfo.isEnabled(),
                userInfo.isAccountNonExpired(), userInfo.isCredentialsNonExpired(),
                userInfo.isAccountNonLocked(), authorities);
    }
}
