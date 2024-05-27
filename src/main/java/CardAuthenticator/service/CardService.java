package CardAuthenticator.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private static final Map<String, String> CARD_PREFIXES = new HashMap<>();

    static {
        CARD_PREFIXES.put("4", "Visa");
        CARD_PREFIXES.put("51", "MasterCard");
        CARD_PREFIXES.put("52", "MasterCard");
        CARD_PREFIXES.put("53", "MasterCard");
        CARD_PREFIXES.put("54", "MasterCard");
        CARD_PREFIXES.put("55", "MasterCard");
        CARD_PREFIXES.put("34", "American Express");
        CARD_PREFIXES.put("37", "American Express");
        CARD_PREFIXES.put("6011", "Discover");
        CARD_PREFIXES.put("65", "Discover");
        // Adicione mais prefixos conforme necessário
    }

    public String identifyCardBrand(String cardNumber) {
        for (Map.Entry<String, String> entry : CARD_PREFIXES.entrySet()) {
            if (cardNumber.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Desconhecido"; // Retorna Desconhecido se nenhum prefixo corresponder
    }

    public boolean validateCardNumberWithLuhn(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public boolean validateCardExpiryDate(String expiryDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expYearMonth = YearMonth.parse(expiryDate, formatter);
            YearMonth currentYearMonth = YearMonth.now();
            return expYearMonth.isAfter(currentYearMonth) || expYearMonth.equals(currentYearMonth);
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao parsear a data: " + expiryDate);
            e.printStackTrace();
            return false;
        }
    }
}