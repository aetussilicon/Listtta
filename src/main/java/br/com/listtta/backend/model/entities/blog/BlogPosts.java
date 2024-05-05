package br.com.listtta.backend.model.entities.blog;

import br.com.listtta.backend.model.entities.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
    @SequenceGenerator(name = "blog_seq", sequenceName = "blog_seq", allocationSize = 1)
    @Column(name = "post_id")
    private Long postId;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Users authorId;
    private String authorUserTag;
    private String authorName;
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "post_category")
    private String postCategory;

    @Column(name = "post_content")
    private String post;
}
