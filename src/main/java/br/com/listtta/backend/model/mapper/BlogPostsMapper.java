package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.blog.BlogPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.blog.CreatePostDto;
import br.com.listtta.backend.model.entities.BlogPosts;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostsMapper {

    BlogPostsMapper INSTANCE = Mappers.getMapper(BlogPostsMapper.class);

    BlogPosts createNewBlogPostsDtoToModel (CreatePostDto createPostDto);

    BlogPostDto postModelToDto(BlogPosts blogPosts);
    List<BlogPostDto> listPostModelsToDto(List<BlogPosts> blogPostsList);
    
}
