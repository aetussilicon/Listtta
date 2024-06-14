package br.com.listtta.backend.util.validation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ControllersResponse {

    public Map<String, Object> controllersResponse(Object responseResult, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (exception == null) {
                response.put("Status", "Success");
                response.put("Data", responseResult);
            } else {
                throw exception;
            }
        } catch (Exception e) {
            response.put("Status", "Error");
            response.put("Error", e.getMessage());
        }
        return response;
    }
}
