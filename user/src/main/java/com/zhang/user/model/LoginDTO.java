package com.zhang.user.model;

import lombok.Data;

//import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class LoginDTO implements Serializable {
    private String userName;
    private String password;
}
