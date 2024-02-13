package com.example.demo.controllers;

import com.example.demo.models.dto.PostCreateDTO;
import com.example.demo.models.dto.PostDTO;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(HttpServletRequest request, @RequestBody PostCreateDTO post){
        PostDTO createdPost = postService.createPost(request, post);
        if (createdPost!=null){
            return ResponseEntity.status(HttpStatus.OK).body(createdPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> list(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(postService.listPostByUser(request));
    }

    @DeleteMapping("/delete/{tweetId}")
    public ResponseEntity<PostDTO> delete(HttpServletRequest request, @PathVariable Long tweetId) {
        PostDTO postDTO = postService.deletePost(request, tweetId);
        if (postDTO!=null){
            return ResponseEntity.status(HttpStatus.OK).body(postDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
