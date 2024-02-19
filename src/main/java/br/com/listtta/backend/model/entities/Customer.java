package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer extends AbstractUser {
    //TODO Profile Picture

}
