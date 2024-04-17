package br.com.listtta.backend.model.dto.blgposts;

import br.com.listtta.backend.model.entities.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewPostDto {

    private Long blogPostId;
    @NotNull private String postTitle;
    private Date createdDate;
    @NotNull private String content;
}
