package br.com.listtta.backend.model.entities.filters;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filters_seq_generator")
    @SequenceGenerator(name = "filters_seq_generator", sequenceName = "filters_seq", allocationSize = 1)
    @Column(name = "filter_id")
    private Long filterId;

    @Column(name = "filter_name")
    private String filterName;

    @Column(name = "display_name")
    private String displayName;

}