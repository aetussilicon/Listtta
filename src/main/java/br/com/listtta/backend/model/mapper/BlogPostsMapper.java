package br.com.listtta.backend.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.blog.CreatePostDto;
import br.com.listtta.backend.model.entities.BlogPosts;

@Mapper(componentModel = "spring")
public interface BlogPostsMapper {

    BlogPostsMapper INSTANCE = Mappers.getMapper(BlogPostsMapper.class);

    BlogPosts createNewBlogPostsDtoToModel (CreatePostDto createPostDto);
    
}
