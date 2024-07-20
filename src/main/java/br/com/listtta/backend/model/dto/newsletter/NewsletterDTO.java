package br.com.listtta.backend.model.dto.newsletter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsletterDTO {
    private Long letterId;
    @Email
    @NotNull
    private String email;
    private Date createdDate;
}
