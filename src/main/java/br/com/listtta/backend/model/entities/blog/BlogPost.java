package br.com.listtta.backend.model.entities.blog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "blog_posts")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_posts_seq_generator")
    @SequenceGenerator(name = "blog_posts_seq_generator", sequenceName = "blog_posts_seq", allocationSize = 1)
    private Long blogPostId;

    @Column(name = "title")
    private String postTitle;

    @Column(name = "post_created_date")
    private Date createdDate;
    private String content;
}
