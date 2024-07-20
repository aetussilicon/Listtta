package br.com.listtta.backend.model.entities.newsletter;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "newsletter_emails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "letterId")
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsletter_emails_seq_generator")
    @SequenceGenerator(name = "newsletter_emails_seq_generator", sequenceName = "newsletter_emails_seq", allocationSize = 1)
    @Column(name = "letter_id")
    private Long letterId;
    private String email;

    @Column(name = "created_date")
    private Date createdDate;
}
