package br.com.listtta.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listtta.backend.model.entities.BlogPosts;

public interface BlogRepository extends JpaRepository<BlogPosts, Long> {
    
}
