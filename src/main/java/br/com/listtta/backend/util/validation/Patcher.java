package br.com.listtta.backend.util.validation;

import br.com.listtta.backend.model.entities.Users;
import org.hibernate.Internal;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static <T> void patch(T existingObject, T incompleteObject) throws IllegalAccessException {
        // Obter a classe do objeto existente
        Class<?> objectClass = existingObject.getClass();

        // Obter todos os campos declarados na classe
        Field[] fields = objectClass.getDeclaredFields();

        // Iterar sobre os campos
        for (Field field : fields) {
            // Tornar o campo acessível
            field.setAccessible(true);

            // Obter o valor do campo no objeto incompleto
            Object value = field.get(incompleteObject);

            // Se o valor não for nulo, atualizar o valor no objeto existente
            if (value != null) {
                field.set(existingObject, value);
            }

            // Tornar o campo inacessível novamente
            field.setAccessible(false);
        }
    }

}