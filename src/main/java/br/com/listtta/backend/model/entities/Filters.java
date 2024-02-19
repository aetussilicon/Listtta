package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "filters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filters {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "filter_id")
    private Long filterId;

    @Column(name = "filter_name")
    private String filterName;

    @Column(name = "display_name")
    private String displayName;

}