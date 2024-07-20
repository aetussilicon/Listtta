package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.newsletter.NewsletterDTO;
import br.com.listtta.backend.model.entities.newsletter.Newsletter;
import br.com.listtta.backend.model.mapper.NewsletterMapper;
import br.com.listtta.backend.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository repository;
    private final NewsletterMapper mapper;

    public NewsletterDTO registerNewNewsletterEmail(NewsletterDTO newsletterDTO) {
        // repository.findByEmail(newsletterDTO.getEmail()).orElseThrow(RuntimeException::new);

        newsletterDTO.setCreatedDate(new Date());

        Newsletter newEmail = repository.save(mapper.registerNewsletterEmail(newsletterDTO));
        return mapper.entityToDto(newEmail);
    }
}
