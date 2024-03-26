package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.blgposts.BlogPostDto;
import br.com.listtta.backend.model.dto.blgposts.NewPostDto;
import br.com.listtta.backend.model.entities.BlogPost;
import br.com.listtta.backend.service.BlogPostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
@RequiredArgsConstructor
public class BlogPostsController {

    private final BlogPostsService blogPostsService;

    @PostMapping
    @RequestMapping("new-post")
    public ResponseEntity<BlogPost> publishNewPost(@RequestBody @Valid NewPostDto newPostDto) {
        return new ResponseEntity<>(blogPostsService.publishNewBlogPost(newPostDto), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("list/{blogPostId}")
    public ResponseEntity<BlogPostDto> getBlogPost(@PathVariable Long blogPostId){
        return new ResponseEntity<>(blogPostsService.getBlogPost(blogPostId), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<BlogPostDto>> getAllPosts() {
        return new ResponseEntity<>(blogPostsService.getAllPosts(), HttpStatus.OK);
    }
}
