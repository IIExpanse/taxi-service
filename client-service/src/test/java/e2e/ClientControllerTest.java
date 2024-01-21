package e2e;

import clientservice.ClientApplication;
import clientservice.controller.AbstractControllerTest;
import clientservice.dto.ClientResponseDto;
import clientservice.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClientApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientControllerTest extends AbstractControllerTest {
    @Test
    void testCreateClient() {
        final Client client = getDefaultClient();
        final ClientResponseDto clientResponseDto = createClient(client);
        assertEquals(client.getFirstName(), clientResponseDto.getFirstName());
    }

    @Test
    void testGetClientById() {
        createClient(getDefaultClient());
        final ClientResponseDto clientResponseDto = getData("/api/clients/1", ClientResponseDto.class);
        assertEquals("Ivan", clientResponseDto.getFirstName());
    }

    @Test
    void testUpdateClient() {
        createClient(getDefaultClient());
        final Client client = Client.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivan@ivan.by")
                .age(20)
                .login("ivan")
                .build();
        final ClientResponseDto clientResponseDto =
                sendData(HttpMethod.PUT, "/api/clients/1", client, ClientResponseDto.class);

        assertEquals(client.getFirstName(), clientResponseDto.getFirstName());
    }

    @Test
    void testDeleteClient() {
        createClient(getDefaultClient());
        final ClientResponseDto clientResponseDto =
                sendData(HttpMethod.DELETE, "/api/clients/1", null, ClientResponseDto.class);
        assertEquals("Ivan", clientResponseDto.getFirstName());
    }

    private Client getDefaultClient() {
        return Client.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("zzz@zzz.zz")
                .age(20)
                .login("zzz")
                .build();
    }

    private ClientResponseDto createClient(Client client) {
        return sendData(HttpMethod.POST, "/api/clients", client, ClientResponseDto.class);
    }
}
