package com.ivanfrias.social.network.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanfrias.social.network.models.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	public List<Post> findByUserId(Long id);
}
