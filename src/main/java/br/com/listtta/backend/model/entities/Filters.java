package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "filterId")
public class Filters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_id")
    private Long filterId;

    @Column(name = "filter_name")
    private String filterName;

    @Column(name = "display_name")
    private String displayName;

}