package com.example.demo.services;

import com.example.demo.models.dto.PostCreateDTO;
import com.example.demo.models.dto.PostDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostService {
    PostDTO createPost(HttpServletRequest request, PostCreateDTO post);

    List<PostDTO> listPostByUser(HttpServletRequest request);

    PostDTO deletePost(HttpServletRequest request, Long tweetId);
}
