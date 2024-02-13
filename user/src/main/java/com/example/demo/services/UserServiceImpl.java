package com.example.demo.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.dto.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.feign.PostServiceClient;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;


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
		currentUser.setPassword(new BCryptPasswordEncoder().encode(userRegister.getPassword()));
		
		User createdUser = userRepository.save(currentUser);
		
		UserNopassDTO userDTO = new UserNopassDTO(createdUser.getId(), createdUser.getUsername(), createdUser.getName(), createdUser.getSurname(), createdUser.getBirthdate(), createdUser.getEmail());
		return userDTO;
	}

	@Override
	public UserNopassDTO update(UserRegisterDTO userRegister, HttpServletRequest request) {
		// Obtenemos el claims
		Claims claims = getClaimsToken(request);
		// Rescatamos el id.
		int id = (int) claims.get("id");
		long id_ = (long) id;

		User currentUser = userRepository.findById(id_).orElse(null);
		if (currentUser!=null) {
			currentUser.setName(userRegister.getName());
			currentUser.setEmail(userRegister.getEmail());
			currentUser.setSurname(userRegister.getSurname());
			currentUser.setBirthdate(userRegister.getBirthdate());
			currentUser.setUsername(userRegister.getUsername());
			currentUser.setPassword(new BCryptPasswordEncoder().encode(userRegister.getPassword()));
			// Guardamos
			currentUser = userRepository.save(currentUser);
			return new UserNopassDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getSurname(), currentUser.getBirthdate(), currentUser.getEmail());
			
		} else {
			return null;
		}
	}

	@Override
	public UserNopassDTO delete(HttpServletRequest request) {
		// Obtenemos el claims
		Claims claims = getClaimsToken(request);
		// Rescatamos el id.
		int id = (int) claims.get("id");
		long id_ = (long) id;

		User currentUser = userRepository.findById(id_).orElse(null);
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
	public List<PostDTO> listPostByUser(HttpServletRequest request) {
		// Obtenemos el claims
		Claims claims = getClaimsToken(request);
		// Rescatamos el id.
		int id = (int) claims.get("id");
		long id_ = (long) id;

		return listPostByUserId(id_);
	}

	@Override
	public UserNopassDTO profile(HttpServletRequest request) {
		// Obtenemos el claims
		Claims claims = getClaimsToken(request);
		// Rescatamos el id.
		int id = (int) claims.get("id");
		long id_ = (long) id;

		return findById(id_);
	}

	@Override
	public PostDTO createPost(HttpServletRequest request, PostCreateDTO post) {
		// Obtenemos el claims
		Claims claims = getClaimsToken(request);
		// Rescatamos el id.
		int id = (int) claims.get("id");
		long id_ = (long) id;

		return postServiceClient.create(new PostCreateDTO_(id_, post.getText()));
	}

	public Claims getClaimsToken(HttpServletRequest request) {
		String secretKey = "mNajgf39sNvfDSjdnglo3129dfnLAS21nSlkl59";
		// Obtener el token del encabezado "Authorization"
		String token = request.getHeader("Authorization");


		// Verificar si el token comienza con "Bearer "
		if (token != null && token.startsWith("Bearer ")) {
			try {
				// Eliminar la parte "Bearer " del token para obtener solo el token JWT
				String jwtToken = token.substring(7); // 7 es la longitud de "Bearer "

				// Decodificar el token JWT y obtener los claims (información del usuario)
				// Ej: int id = (int) claims.get("id");
				return Jwts.parserBuilder()
						.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
						.build()
						.parseClaimsJws(jwtToken)
						.getBody();
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
}












