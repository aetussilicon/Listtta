package br.com.listtta.backend.service;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserRoles;
import org.hibernate.usertype.UserType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Random;

@Component
public class PuidGenerator {

    public String puidGenerator(ProfessionalsType type) {
        String customerPrefix = "CUSTOM"; //Customer
        String tattooPrefix = "TATINK"; //Tattoo Ink
        String bodyPiercerPrefix = "BODSTL"; // Body Style

        String random = generateRandomNumber();
        String puid = null;

        if (type == null) {
            puid = customerPrefix + random;
        } else if (type == ProfessionalsType.TATTOO) {
            puid = tattooPrefix + random;
        } else if (type == ProfessionalsType.PIERCER) {
            puid = bodyPiercerPrefix + random;
        }
        return puid;
    }
    
    private String generateRandomNumber() {
        int number = 14;

        StringBuilder sb = new StringBuilder();
        Random randomNumber = new SecureRandom();

        for (int i = 0; i < number; i++) {
            sb.append(randomNumber.nextInt(10));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PuidGenerator generator = new PuidGenerator();
        ProfessionalsType tattoo = ProfessionalsType.TATTOO;
        ProfessionalsType piercer = ProfessionalsType.PIERCER;
        ProfessionalsType vazio = null;

        System.out.println(generator.puidGenerator(vazio));
    }

}
