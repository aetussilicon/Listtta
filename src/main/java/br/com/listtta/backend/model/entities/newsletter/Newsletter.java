package br.com.listtta.backend.model.entities.newsletter;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
