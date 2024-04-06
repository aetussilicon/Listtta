package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostsRepository extends JpaRepository<BlogPosts, Long> {
}
