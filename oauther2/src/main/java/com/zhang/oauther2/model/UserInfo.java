package com.zhang.oauther2.model;

import com.zhang.oauther2.mysql.entity.SsoRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class UserInfo implements Serializable {

    private String userId;
    private String userName;
    private String password;
    /**
     * 账户未过期
     */
    private boolean accountNonExpired = true;
    /**
     * 账户未锁定
     */
    private boolean accountNonLocked = true;
    /**
     * 凭证未过期
     */
    private boolean credentialsNonExpired = true;
    /**
     * 账户启动
     */
    private boolean enabled = true;

    /**
     * 账户权限
     */
    private List<SsoRole> rolesList;
}
