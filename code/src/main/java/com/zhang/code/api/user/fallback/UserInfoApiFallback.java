package com.zhang.code.api.user.fallback;

import com.zhang.code.api.user.UserInfoApi;
import com.zhang.common.model.module.base.ResoponseVO;
import org.springframework.stereotype.Component;

/**
 * @Description: 类功能介绍
 * @CreateDate: 2020/5/26 15:01
 * @Author: WangChao
 * @Version: 1.0
 */
@Component
public class UserInfoApiFallback implements UserInfoApi {
	@Override
	public ResoponseVO returnUserName() {
		return ResoponseVO.fault("请求熔断",null);
	}
}