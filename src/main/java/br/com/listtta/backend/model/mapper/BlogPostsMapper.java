package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.blgposts.BlogPostDto;
import br.com.listtta.backend.model.dto.blgposts.NewPostDto;
import br.com.listtta.backend.model.entities.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostsMapper {

    BlogPostsMapper INSTANCE = Mappers.getMapper(BlogPostsMapper.class);

    BlogPost createNewBlogPostDtoToModel(NewPostDto newPostDto);
    BlogPostDto postModelToDto(BlogPost blogPost);
    List<BlogPostDto> allPostsToDto(List<BlogPost> blogPostList);
}
