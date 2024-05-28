package CardAuthenticator.unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CardAuthenticator.service.CardService;

class CardServiceTest {

    private CardService cardService;

    @BeforeEach
    void setUp() {
        cardService = new CardService();
    }

    @Test
    void testIdentifyCardBrand_Visa() {
        String cardNumber = "4532015112830366"; 
        assertEquals("Visa", cardService.identifyCardBrand(cardNumber));
    }

    @Test
    void testIdentifyCardBrand_MasterCard() {
        String cardNumber = "5500005555555559"; 
        assertEquals("MasterCard", cardService.identifyCardBrand(cardNumber));
    }

    @Test
    void testIdentifyCardBrand_Unknown() {
        String cardNumber = "1234567890123456"; 
        assertEquals("Desconhecido", cardService.identifyCardBrand(cardNumber));
    }

    @Test
    void testValidateCardNumberWithLuhn_Valid() {
        assertTrue(cardService.validateCardNumberWithLuhn("4532015112830366")); 
    }

    @Test
    void testValidateCardNumberWithLuhn_Invalid() {
        assertFalse(cardService.validateCardNumberWithLuhn("4532015112830367")); 
    }

    @Test
    void testValidateCardExpiryDate_Valid() {
        assertTrue(cardService.validateCardExpiryDate("12/24")); 
    }

    @Test
    void testValidateCardExpiryDate_Invalid() {
        assertFalse(cardService.validateCardExpiryDate("12/20")); 
    }
}