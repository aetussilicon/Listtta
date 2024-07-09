package br.com.listtta.backend.model.dto.newsletter;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsletterDTO {
    private Long letterId;
    @Email
    @NotNull
    private String email;
    private Date createdDate;
}
