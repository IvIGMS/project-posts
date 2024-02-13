package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dto.PostCreateDTO;
import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.models.dto.UserRegisterDTO;

import javax.servlet.http.HttpServletRequest;


public interface UserService {
	public List<UserNopassDTO> findAll();
	public UserNopassDTO findById(Long id);
	public UserNopassDTO create(UserRegisterDTO user);
	public UserNopassDTO update(UserRegisterDTO user, HttpServletRequest request);
	public UserNopassDTO delete(HttpServletRequest request);
	public List<PostDTO> listPostByUserId(Long id);
	UserNopassDTO profile(HttpServletRequest request);

	PostDTO createPost(HttpServletRequest request, PostCreateDTO post);

	List<PostDTO> listPostByUser(HttpServletRequest request);
}
