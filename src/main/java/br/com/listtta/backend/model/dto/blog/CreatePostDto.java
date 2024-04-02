package br.com.listtta.backend.model.dto.blog;

import java.util.Date;

import br.com.listtta.backend.model.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {
    private Long postId;
    private Users author;
    private Date createdDate;
    private String postCategory;
    private String post;
}
