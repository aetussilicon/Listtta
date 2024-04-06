package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.blog.BlogPostDto;
import br.com.listtta.backend.model.dto.blog.CreatePostDto;
import br.com.listtta.backend.model.entities.BlogPosts;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.mapper.BlogPostsMapper;
import br.com.listtta.backend.repository.AddressRepository;
import br.com.listtta.backend.repository.BlogPostsRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogPostsService {

    private final BlogPostsRepository blogRepo;
    private final BlogPostsMapper blogMapper;
    private final FindUsersMethods findMethods;

    public BlogPosts createNewPost(CreatePostDto createPostDto) {
        Users postAuthor = findMethods.findUserByUserTag(createPostDto.getAuthorUserTag());

        createPostDto.setAuthorId(postAuthor);
        createPostDto.setAuthorName(postAuthor.getFullName());
        createPostDto.setCreatedDate(new Date());

        return blogRepo.save(blogMapper.createNewBlogPostsDtoToModel(createPostDto));
    }

    public BlogPostDto getPost(Long postId) {
        Optional<BlogPosts> findPostIdDatabase = blogRepo.findById(postId);
        if (findPostIdDatabase.isPresent()) {
            return blogMapper.postModelToDto(findPostIdDatabase.get());
        } throw new RuntimeException();
    }

    public List<BlogPostDto> listAllPosts(){
        return blogMapper.listPostModelsToDto(blogRepo.findAll());
    }

}
