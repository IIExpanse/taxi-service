package clientservice.controller;

import clientservice.dto.ClientRequestDto;
import clientservice.dto.ClientResponseDto;
import clientservice.entities.Client;
import clientservice.exception.NotFoundException;
import clientservice.service.ClientService;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
class ClientControllerTest extends AbstractControllerTest {
    @MockBean
    private ClientService clientService;

    @Nested
    class GetClient {
        @Test
        void shouldGetClientWhenClientExistById() {
            when(clientService.getClientById(1)).thenReturn(
                    Client.builder()
                            .id(1L)
                            .firstName("Ivan")
                            .lastName("Ivanov")
                            .email("qwe@as.eu")
                            .age(20)
                            .login("qwe")
                            .build());

            val actual = getData("/clients/1", ClientResponseDto.class);

            val expected = ClientResponseDto.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .build();
            assertEquals(expected, actual);

        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            when(clientService.getClientById(1)).thenThrow(new NotFoundException("Client not found"));

            val actual = testNotFound(HttpMethod.GET, "/clients/1");

            assertEquals(404, actual.andReturn().getResponse().getStatus());
        }

    }

    @Nested
    class PostClient {
        @Test
        void shouldPostClientWhenClientExistById() {
            val clientRequestDto = ClientRequestDto.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientService.createClient(clientRequestDto)).thenReturn(
                    Client.builder()
                            .id(1L)
                            .firstName("Ivan")
                            .lastName("Ivanov")
                            .email("qwe@qwe.ru")
                            .age(20)
                            .login("qwe")
                            .build());

            val actual = sendData(HttpMethod.POST, "/clients", clientRequestDto, ClientResponseDto.class);

            val expected = ClientResponseDto.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .build();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class PutClient {
        @Test
        void shouldPutClientWhenClientExistById() {
            val clientRequestDto = ClientRequestDto.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientService.updateClient(clientRequestDto)).thenReturn(Client.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build());

            val actual = sendData(HttpMethod.PUT, "/clients", clientRequestDto, ClientResponseDto.class);

            val expected = ClientResponseDto.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            val clientRequestDto = ClientRequestDto.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientService.updateClient(clientRequestDto))
                    .thenThrow(new NotFoundException("Client not found"));

            val actual = testNotFound(HttpMethod.PUT, "/clients", clientRequestDto);

            assertEquals(404, actual.andReturn().getResponse().getStatus());
        }

    }

    @Nested
    class DeleteClient {
        @Test
        void shouldDeleteClientWhenClientExistById() {
            when(clientService.deleteClient(1)).thenReturn(Client.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build());

            val actual = sendData(HttpMethod.DELETE, "/clients/1", null, ClientResponseDto.class);

            val expected = ClientResponseDto.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            when(clientService.deleteClient(1)).thenThrow(new NotFoundException("Client not found"));

            testNotFound(HttpMethod.DELETE, "/clients/1");
        }
    }

}