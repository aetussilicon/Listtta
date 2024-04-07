package br.com.listtta.backend.model.dto.blog;

import br.com.listtta.backend.model.entities.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreatePostDto {
    private Long postId;
    private Users authorId;
    @NotNull private String authorUserTag;
    private String authorName;
    private Date createdDate;
    @NotNull private String postCategory;
    @NotNull private String post;
}
