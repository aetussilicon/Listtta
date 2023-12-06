package br.com.listtta.backend.service;

import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.Random;

public class UsernameGenerateService {
    public static String usernameGenerator(@NotNull String normalizedString) {

        char[] stringToSplit = normalizedString.toCharArray();
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

    public String removeAccents(String name) {

        String normalizedString = Normalizer.normalize(name, Normalizer.Form.NFD);
        return normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }
}
