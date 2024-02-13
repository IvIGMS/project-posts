package com.example.demo.feign;

import java.util.List;

import com.example.demo.models.dto.PostCreateDTO_;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.dto.PostDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "post-service")
public interface PostServiceClient {
	@GetMapping("/post/list/{userId}")
	public List<PostDTO> findPostsByUserId(@PathVariable Long userId);

	@PostMapping("/post/create")
	public PostDTO create(@RequestBody PostCreateDTO_ post);
}
