package com.example.demo.controllers;

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

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.models.dto.UserRegisterDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
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
	
	@GetMapping("/{id}")
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
	
	@PutMapping("/{id}")
	public ResponseEntity<UserNopassDTO> update(@PathVariable Long id, @RequestBody UserRegisterDTO user) {
		UserNopassDTO currentUser = userService.update(user, id);
		if (currentUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserNopassDTO> delete(@PathVariable Long id){
		UserNopassDTO currentUser = userService.delete(id);
		if (currentUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	// Este metodo es solo para crear un post y poder verificar que existe el usuario que crea el post
	@GetMapping("/getUsersId")
	public ResponseEntity<List<Long>> getUsersId(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsersId());
	}
}








