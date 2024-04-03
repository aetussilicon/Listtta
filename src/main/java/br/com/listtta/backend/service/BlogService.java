package br.com.listtta.backend.service;

import org.springframework.stereotype.Service;

import br.com.listtta.backend.repository.BlogRepository;
import br.com.listtta.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
   
    private final BlogRepository blogRepository;
    private final UsersRepository usersRepository;

}
