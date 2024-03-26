package br.com.listtta.backend.model.dto.blgposts;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogPostDto {

    private String postTitle;
    private Date createdDate;
    private String content;

}
