package br.com.listtta.backend.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Random;

@Component
public class UsernameGenerateService {

    public String usernameGenerator(@NotNull String name) {

        String cleanName = removeAccents(name);

        char[] stringToSplit = cleanName.toCharArray();
        int arrayLength = stringToSplit.length;
        int charsToExtract = 3;

        char[] firstThreeLettersOfFfirstName = new char[charsToExtract];
        char[] lastThreeLettersOfLastName = new char[charsToExtract];

        System.arraycopy(stringToSplit, 0, firstThreeLettersOfFfirstName, 0, 3);
        System.arraycopy(stringToSplit, arrayLength - charsToExtract, lastThreeLettersOfLastName, 0, charsToExtract);

        String str1 = new String(firstThreeLettersOfFfirstName).toLowerCase();
        String str2 = new String(lastThreeLettersOfLastName).toLowerCase();

        Random complement = new Random();

        int randomInt = complement.nextInt(1000,  9999 );

        return str1 + str2 + randomInt;
    }

    private String removeAccents(String name) {

        String normalizedString = Normalizer.normalize(name, Normalizer.Form.NFD);
        return normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

}
