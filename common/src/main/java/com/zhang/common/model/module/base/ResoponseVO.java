package com.zhang.common.model.module.base;

import lombok.Data;


@Data
public class ResoponseVO<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T>ResoponseVO fault(String msg, T result) {
        ResoponseVO<T> re = new ResoponseVO<>();
        re.setCode(500);
        re.setMsg(msg);
        re.setData(result);
        return re;
    }

    public static <T> ResoponseVO success(T result) {
        ResoponseVO<T> re = new ResoponseVO<>();
        re.setCode(200);
        re.setData(result);
        return re;
    }
}
