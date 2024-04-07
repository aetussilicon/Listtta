package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.blog.BlogPostDto;
import br.com.listtta.backend.model.dto.blog.CreatePostDto;
import br.com.listtta.backend.model.entities.BlogPosts;
import br.com.listtta.backend.service.BlogPostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
@RequiredArgsConstructor
public class BlogPostsController {

    private final BlogPostsService blogService;

    @PostMapping("publish")
    public ResponseEntity<BlogPosts> createNewPost(@RequestBody @Valid CreatePostDto createPostDto) {
        return new ResponseEntity<>(blogService.createNewPost(createPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("get/{postId}")
    public ResponseEntity<BlogPostDto> getBlogPost(@PathVariable Long postId) {
        return new ResponseEntity<>(blogService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("get/all")
    public ResponseEntity<List<BlogPostDto>> getAllPosts(){
        return new ResponseEntity<>(blogService.listAllPosts(), HttpStatus.OK);
    }
}
