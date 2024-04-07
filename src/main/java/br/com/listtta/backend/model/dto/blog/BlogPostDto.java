package br.com.listtta.backend.model.dto.blog;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogPostDto {

    private Long PostId;
    private String authorUserTag;
    private String authorName;
    private Date createdDate;
    private String postCategory;
    private String post;
}

