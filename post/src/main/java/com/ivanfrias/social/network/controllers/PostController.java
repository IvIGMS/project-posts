package com.ivanfrias.social.network.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanfrias.social.network.models.Post;
import com.ivanfrias.social.network.models.dto.PostCreateDTO;
import com.ivanfrias.social.network.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Post>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAll());
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<Post>> list(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findByUserId(userId));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable Long id){
		Post post = postService.findById(id);
		if (post!=null){
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@PostMapping("/create")
	public ResponseEntity<Post> create(@RequestBody PostCreateDTO post){
		Post currentPost = postService.create(post);
		if (currentPost!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostCreateDTO post) {
		Post currentPost = postService.update(post, id);
		if (currentPost!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Post> delete(@PathVariable Long id){
		Post post = postService.delete(id);
		if (post!=null){
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
}










