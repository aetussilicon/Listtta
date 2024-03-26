package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostsRepository extends JpaRepository<BlogPost, Long> {
}
