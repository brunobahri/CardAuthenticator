package CardAuthenticator.controller;

import CardAuthenticator.model.Card;
import CardAuthenticator.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/validateCard")
    public ResponseEntity<Map<String, Object>> validateCard(@RequestBody Card card) {
        Map<String, Object> response = new HashMap<>();
        String cardBrand = cardService.identifyCardBrand(card.getCardNumber());

        if (!cardService.validateCardNumberWithLuhn(card.getCardNumber())) {
            response.put("error", "Número do cartão inválido");
            return ResponseEntity.badRequest().body(response);
        }
        if (!cardService.validateCardExpiryDate(card.getExpiryDate())) {
            response.put("error", "Data de validade do cartão inválida");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", "Cartão válido");
        response.put("brand", cardBrand);
        return ResponseEntity.ok(response);
    }
}