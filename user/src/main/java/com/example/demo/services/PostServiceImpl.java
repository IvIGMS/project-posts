package com.example.demo.services;

import com.example.demo.feign.PostServiceClient;
import com.example.demo.models.dto.PostCreateDTO;
import com.example.demo.models.dto.PostCreateDTO_;
import com.example.demo.models.dto.PostDTO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    UserService userService;

    @Autowired
    PostServiceClient postServiceClient;

    @Override
    public PostDTO createPost(HttpServletRequest request, PostCreateDTO post) {
        // Obtenemos el claims
        Claims claims = userService.getClaimsToken(request);
        // Rescatamos el id.
        int id = (int) claims.get("id");
        long id_ = (long) id;

        return postServiceClient.create(new PostCreateDTO_(id_, post.getText()));
    }

    @Override
    public List<PostDTO> listPostByUser(HttpServletRequest request) {
        // Obtenemos el claims
        Claims claims = userService.getClaimsToken(request);
        // Rescatamos el id.
        int id = (int) claims.get("id");
        long id_ = (long) id;

        return userService.listPostByUserId(id_);
    }
}
