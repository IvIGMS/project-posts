package com.ivanfrias.social.network.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserServiceClient {
	@GetMapping("/user/getUsersId")
	public List<Long> getUsersId();
}
