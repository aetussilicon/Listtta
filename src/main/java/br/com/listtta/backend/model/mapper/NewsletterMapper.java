package br.com.listtta.backend.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.newsletter.NewsletterDTO;
import br.com.listtta.backend.model.entities.newsletter.Newsletter;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {
    NewsletterMapper INSTACE = Mappers.getMapper(NewsletterMapper.class);

    Newsletter registerNewsletterEmail(NewsletterDTO newsletterDTO);

    NewsletterDTO entityToDto(Newsletter newsletter);
}
