package com.ivanfrias.social.network.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanfrias.social.network.feign.UserServiceClient;
import com.ivanfrias.social.network.models.Post;
import com.ivanfrias.social.network.models.dto.PostCreateDTO;
import com.ivanfrias.social.network.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	UserServiceClient userServiceClient;

	@Autowired
	PostRepository postRepository;
	
	@Override
	public List<Post> findAll() {
		List<Post> posts = postRepository.findAll();
		return posts.stream()
				.sorted(Comparator.comparing(Post::getId))
			    .collect(Collectors.toList());
	}

	@Override
	public Post findById(Long id) {
		return postRepository.findById(id).orElse(null);
	}

	@Override
	public Post create(PostCreateDTO post) {
		// Comprobar si existe el user:
		//List<Long> ids = userServiceClient.getUsersId();
		//boolean isFound = ids.contains(post.getUserId());
		boolean isFound=true;
		if(isFound) {
			// Logica de crear post
			Post currentPost = new Post(post.getUserId(), post.getText());
			Post created = postRepository.save(currentPost);
			
			if(created.getText()==null) {
				return null;
			} else {
				return created;
			}
		} else {
			// Si no existe el userId.
			return null;
		}
	}

	@Override
	public Post update(PostCreateDTO post, Long id) {
		Post currentPost = findById(id);
		
		if (currentPost!=null) {
			// Solo podemos cambiar el texto, el user del post no puede cambiarse
			currentPost.setText(post.getText());
			Post created = postRepository.save(currentPost);
			if(created.getText()==null) {
				return null;
			} else {
				return created;
			}
		} else {
			return null;
		}
	}

	@Override
	public Post delete(Long id) {
		Post currentPost = findById(id);
		
		if(currentPost!=null) {
			postRepository.delete(currentPost);
			return currentPost;
		} else {
			return null;
		}
	}

	@Override
	public List<Post> findByUserId(Long id) {
		List<Post> posts = postRepository.findByUserId(id);
		// Ordenamos y retornamos de menor a mayor
		return posts.stream()
				.sorted(Comparator.comparing(Post::getCreatedAt).reversed())
			    .collect(Collectors.toList());
	}

}




