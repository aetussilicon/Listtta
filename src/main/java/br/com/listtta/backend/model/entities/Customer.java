package br.com.listtta.backend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends AbstractUser {
    //TODO Profile Picture

}
