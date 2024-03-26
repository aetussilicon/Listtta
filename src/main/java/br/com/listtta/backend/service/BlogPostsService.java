package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.blgposts.BlogPostDto;
import br.com.listtta.backend.model.dto.blgposts.NewPostDto;
import br.com.listtta.backend.model.entities.BlogPost;
import br.com.listtta.backend.model.mapper.BlogPostsMapper;
import br.com.listtta.backend.repository.BlogPostsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogPostsService {

    private final BlogPostsRepository blogPostsRepository;
    private final BlogPostsMapper blogPostsMapper;
    public BlogPost publishNewBlogPost(NewPostDto newPostDto) {
        newPostDto.setCreatedDate(new Date());
        return  blogPostsRepository.save(blogPostsMapper.createNewBlogPostDtoToModel(newPostDto));
    }

    public BlogPostDto getBlogPost(Long postId){
        Optional<BlogPost> checkPostInDatabase = blogPostsRepository.findById(postId);
        if (checkPostInDatabase.isPresent()) {
            return blogPostsMapper.postModelToDto(checkPostInDatabase.get());
        } throw new RuntimeException();
    }

    public List<BlogPostDto> getAllPosts() {
        return blogPostsMapper.allPostsToDto(blogPostsRepository.findAll());
    }

}
