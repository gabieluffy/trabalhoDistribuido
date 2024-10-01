package main.java.com.traballho.paypall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController

public class PaypallController {

    @Value("${notificar-service.url}")
    private String notificarServiceUrl;

    @PostMapping("/pagar")
    public ResponseEntity<String> pagar(@RequestBody Map<String, Object> pagamento) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(notificarServiceUrl + "/notificar", pagamento, String.class);
            return ResponseEntity.ok("Pagamento feito e notificação enviada.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao notificar o pagamento: " + e.getMessage());
        }
    }
}
    
