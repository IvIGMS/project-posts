package com.ivanfrias.social.network.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8081/user")
public interface UserServiceClient {
	@GetMapping("/getUsersId")
	public List<Long> getUsersId();
}
