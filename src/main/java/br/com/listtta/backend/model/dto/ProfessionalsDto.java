package br.com.listtta.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfessionalsDto {

    private UUID professionalId;
    private String fullName;
    private String username;
    private String phoneNumber;
    private String taxNumber;
    private String email;
    private String password;
    private String state;
    private String city;
    private String district;
    private String postalCode;
    private String instagramUrl;

}
