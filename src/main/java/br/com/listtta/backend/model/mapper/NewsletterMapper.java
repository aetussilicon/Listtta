package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.newsletter.NewsletterDTO;
import br.com.listtta.backend.model.entities.newsletter.Newsletter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {
    NewsletterMapper INSTACE = Mappers.getMapper(NewsletterMapper.class);

    Newsletter registerNewsletterEmail(NewsletterDTO newsletterDTO);

    NewsletterDTO entityToDto(Newsletter newsletter);
}
