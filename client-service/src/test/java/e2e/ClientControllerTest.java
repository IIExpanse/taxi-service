package e2e;

import clientservice.ClientApplication;
import clientservice.controller.AbstractControllerTest;
import clientservice.dto.ClientResponseDto;
import clientservice.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClientApplication.class)
@Sql(scripts = "/db/test-data.sql", config = @SqlConfig(commentPrefix = "--#"))
public class ClientControllerTest extends AbstractControllerTest {
    @Test
    void testCreateClient() {
        final Client client = Client.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("zzz@zzz.zz")
                .age(20)
                .login("zzz")
                .build();
        final ClientResponseDto clientResponseDto = sendData(HttpMethod.POST, "/api/clients", client, ClientResponseDto.class);
        assertEquals(client.getFirstName(), clientResponseDto.getFirstName());
    }

    @Test
    void testGetClientById() {
        final ClientResponseDto clientResponseDto = getData("/api/clients/1", ClientResponseDto.class);
        assertEquals("John", clientResponseDto.getFirstName());
    }

    @Test
    void testUpdateClient() {
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
        final ClientResponseDto clientResponseDto =
                sendData(HttpMethod.DELETE, "/api/clients/1", null, ClientResponseDto.class);
        assertEquals("John", clientResponseDto.getFirstName());
    }
}
