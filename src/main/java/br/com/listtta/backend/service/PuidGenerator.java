package br.com.listtta.backend.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Random;

@Component
public class PuidGenerator {

    public String puidGenerator(@NotNull String name) {

        String cleanName = removeAccents(name);

        char[] stringToSplit = cleanName.toCharArray();
        int arrayLength = stringToSplit.length;
        int charsToExtract = 3;

        char[] firstThreeLettersOfFfirstName = new char[charsToExtract];
        char[] lastThreeLettersOfLastName = new char[charsToExtract];

        System.arraycopy(stringToSplit, 0, firstThreeLettersOfFfirstName, 0, 3);
        System.arraycopy(stringToSplit, arrayLength - charsToExtract, lastThreeLettersOfLastName, 0, charsToExtract);

        String str1 = new String(firstThreeLettersOfFfirstName).toUpperCase();
        String str2 = new String(lastThreeLettersOfLastName).toUpperCase();
        String str3 = generateRandomNumber();

        return str1 + str2 + str3;
    }

    private String removeAccents(String name) {

        String normalizedString = Normalizer.normalize(name, Normalizer.Form.NFD);
        return normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    private String generateRandomNumber() {
        int number = 4;

        StringBuilder sb = new StringBuilder();
        Random randomNumber = new SecureRandom();

        for (int i = 0; i < number; i++) {
            sb.append(randomNumber.nextInt(10));
        }

        return sb.toString();
    }

}
