package com.ivanfrias.social.network.services;

import java.util.List;

import com.ivanfrias.social.network.models.Post;
import com.ivanfrias.social.network.models.dto.PostCreateDTO;

public interface PostService {
	public List<Post> findAll();
	public Post findById(Long id);
	public Post create(PostCreateDTO post);
	public Post update(PostCreateDTO post, Long id);
	public Post delete(Long id, Long userId);
	public List<Post> findByUserId(Long id);
}
