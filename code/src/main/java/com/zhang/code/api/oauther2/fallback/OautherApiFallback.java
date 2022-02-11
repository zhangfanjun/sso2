package com.zhang.code.api.oauther2.fallback;

import com.zhang.code.api.oauther2.OautherApi;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * @Description: 类功能介绍
 * @CreateDate: 2020/5/26 15:01
 * @Author: WangChao
 * @Version: 1.0
 */

@Component
public class OautherApiFallback implements OautherApi {

	@Override
	public Object postAccessToken(MultiValueMap<String, String> map) {
		return "获取token失败";
	}
}