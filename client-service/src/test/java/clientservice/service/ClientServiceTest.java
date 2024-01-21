package clientservice.service;

import clientservice.dto.ClientRequestDto;
import clientservice.entities.Client;
import clientservice.exception.NotFoundException;
import clientservice.repository.ClientRepository;
import clientservice.time.DateTimeProvider;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private DateTimeProvider dateTimeProvider;
    @InjectMocks
    private ClientService clientService;

    @Nested
    class CreateClient {
        @Test
        void shouldCreateClient() {
            when(dateTimeProvider.nowAsInstant()).thenReturn(Instant.parse("2021-03-01T12:15:30Z"));
            val returnClient = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qee.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            when(clientRepository.save(any())).thenReturn(returnClient);

            val clientRequestDto = ClientRequestDto.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qee.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            val actual = clientService.createClient(clientRequestDto);

            val expected = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qee.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetClientById {
        @Test
        void shouldGetClientById() {
            val returnClient = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientRepository.findById(1)).thenReturn(Optional.of(returnClient));

            val actual = clientService.getClientById(1);

            val expected = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(clientRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> clientService.getClientById(1));
        }
    }

    @Nested
    class UpdateClient {
        @Test
        void shouldUpdateClient() {
            val returnClient = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientRepository.findById(1)).thenReturn(Optional.of(returnClient));
            when(clientRepository.save(any())).thenReturn(returnClient);

            val clientRequestDto = ClientRequestDto.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            val actual = clientService.updateClient(1, clientRequestDto);

            val expected = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(clientRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> clientService.updateClient(1, null));
        }
    }

    @Nested
    class DeleteClient {
        @Test
        void shouldDeleteClient() {
            val returnClient = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            when(clientRepository.findById(1)).thenReturn(Optional.of(returnClient));

            val actual = clientService.deleteClient(1);

            val expected = Client.builder()
                    .id(1)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(clientRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> clientService.deleteClient(1));
        }
    }
}