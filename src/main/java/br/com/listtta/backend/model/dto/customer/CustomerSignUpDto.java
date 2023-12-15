package br.com.listtta.backend.model.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerSignUpDto {

    private UUID customerId;

    @NotNull
    private String fullName;

    private String username;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String taxNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String state;

    @NotNull
    private String city;

    @NotNull
    private String district;

    @NotNull
    private String postalCode;

}
