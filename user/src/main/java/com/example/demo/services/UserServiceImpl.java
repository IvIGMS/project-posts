package com.example.demo.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.feign.PostServiceClient;
import com.example.demo.models.User;
import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.models.dto.UserRegisterDTO;
import com.example.demo.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	PostServiceClient postServiceClient;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserNopassDTO> findAll() {
	    List<User> users = userRepository.findAll();
	    users = users.stream()
				.sorted(Comparator.comparing(User::getId))
			    .collect(Collectors.toList());
	    
	    return users.stream()
	            .map(user -> new UserNopassDTO(user.getId(),user.getUsername(),user.getName(),user.getSurname(),user.getBirthdate(),user.getEmail()))
	            .collect(Collectors.toList());
	}

	@Override
	public UserNopassDTO findById(Long id) {
		User currentUser = userRepository.findById(id).orElse(null);
		
		if(currentUser!=null) {
			return new UserNopassDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getSurname(), currentUser.getBirthdate(), currentUser.getEmail());
		} else {
			return null;
		}
	}

	@Override
	public UserNopassDTO create(UserRegisterDTO userRegister) {
		User currentUser = new User();
		currentUser.setName(userRegister.getName());
		currentUser.setEmail(userRegister.getEmail());
		currentUser.setSurname(userRegister.getSurname());
		currentUser.setBirthdate(userRegister.getBirthdate());
		currentUser.setUsername(userRegister.getUsername());
		currentUser.setPassword(userRegister.getPassword());
		
		User createdUser = userRepository.save(currentUser);
		
		UserNopassDTO userDTO = new UserNopassDTO(createdUser.getId(), createdUser.getUsername(), createdUser.getName(), createdUser.getSurname(), createdUser.getBirthdate(), createdUser.getEmail());
		return userDTO;
	}

	@Override
	public UserNopassDTO update(UserRegisterDTO userRegister, Long id) {
		User currentUser = userRepository.findById(id).orElse(null);
		if (currentUser!=null) {
			currentUser.setName(userRegister.getName());
			currentUser.setEmail(userRegister.getEmail());
			currentUser.setSurname(userRegister.getSurname());
			currentUser.setBirthdate(userRegister.getBirthdate());
			currentUser.setUsername(userRegister.getUsername());
			currentUser.setPassword(userRegister.getPassword());
			// Guardamos
			currentUser = userRepository.save(currentUser);
			return new UserNopassDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getSurname(), currentUser.getBirthdate(), currentUser.getEmail());
			
		} else {
			return null;
		}
	}

	@Override
	public UserNopassDTO delete(Long id) {
		User currentUser = userRepository.findById(id).orElse(null);
		if (currentUser!=null) {
			userRepository.delete(currentUser);
			return new UserNopassDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getSurname(), currentUser.getBirthdate(), currentUser.getEmail());
		} else {
			return null;
		}
	}

	@Override
	public List<PostDTO> listPostByUserId(Long id) {
		return postServiceClient.findPostsByUserId(id);
	}

	@Override
	public List<Long> findAllUsersId() {
		List<User> users = userRepository.findAll();
		return users.stream()
	            .map(user -> user.getId())
	            .collect(Collectors.toList());
	}

}












