package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.models.dto.UserRegisterDTO;


public interface UserService {
	public List<UserNopassDTO> findAll();
	public UserNopassDTO findById(Long id);
	public UserNopassDTO create(UserRegisterDTO user);
	public UserNopassDTO update(UserRegisterDTO user, Long id);
	public UserNopassDTO delete(Long id);
	public List<PostDTO> listPostByUserId(Long id);
	public List<Long> findAllUsersId();
}
