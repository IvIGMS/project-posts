package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.dto.PostCreateDTO;
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

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.models.dto.UserRegisterDTO;
import com.example.demo.services.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<List<UserNopassDTO>> list(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/list/posts/{userId}")
	public ResponseEntity<List<PostDTO>> list(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(userService.listPostByUserId(userId));
	}

	@GetMapping("/list/posts")
	public ResponseEntity<List<PostDTO>> list(HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(userService.listPostByUser(request));
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<UserNopassDTO> findById(@PathVariable Long id){
		UserNopassDTO user = userService.findById(id);
		if (user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserNopassDTO> create(@RequestBody UserRegisterDTO user){
		UserNopassDTO currentUser = userService.create(user);
		if (currentUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}

	@GetMapping("/profile")
	public ResponseEntity<UserNopassDTO> profile(HttpServletRequest request){
		UserNopassDTO currentUser = userService.profile(request);
		if (currentUser!=null){
			return ResponseEntity.status(HttpStatus.OK).body(currentUser);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserNopassDTO> update(HttpServletRequest request, @RequestBody UserRegisterDTO user) {
		UserNopassDTO currentUser = userService.update(user, request);
		if (currentUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<UserNopassDTO> delete(HttpServletRequest request){
		UserNopassDTO currentUser = userService.delete(request);
		if (currentUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}

	@PostMapping("/create/post")
	public ResponseEntity<PostDTO> createPost(HttpServletRequest request, @RequestBody PostCreateDTO post){
		PostDTO createdPost = userService.createPost(request, post);
		if (createdPost!=null){
			return ResponseEntity.status(HttpStatus.OK).body(createdPost);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}








