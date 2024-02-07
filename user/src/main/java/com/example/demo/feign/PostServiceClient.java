package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.dto.PostDTO;


@FeignClient(name = "post-service")
public interface PostServiceClient {
	@GetMapping("/post/list/{userId}")
	public List<PostDTO> findPostsByUserId(@PathVariable Long userId);
}
