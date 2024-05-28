package CardAuthenticator.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidateCardEndpoint_ValidCard_ReturnsSuccess() throws Exception {
        String cardJson = "{\"cardNumber\": \"4532015112830366\", \"expiryDate\": \"12/24\", \"cvv\": \"123\", \"cardHolderFirstName\": \"John\", \"cardHolderLastName\": \"Doe\"}";

        mockMvc.perform(post("/validateCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cardJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cartão válido"))
                .andExpect(jsonPath("$.brand").value("Visa"));
    }

    @Test
    public void testValidateCardEndpoint_InvalidCardNumber_ReturnsError() throws Exception {
        String invalidCardJson = "{\"cardNumber\": \"1234567890123456\", \"expiryDate\": \"12/24\", \"cvv\": \"123\", \"cardHolderFirstName\": \"John\", \"cardHolderLastName\": \"Doe\"}";

        mockMvc.perform(post("/validateCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidCardJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Número do cartão inválido"));
    }
}