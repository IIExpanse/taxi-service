package clientservice.repository;

import clientservice.entities.Client;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Nested
    class SaveClient {
        @Test
        void shouldSaveClient() {
            val client = Client.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();

            val actual = clientRepository.save(client);

            val expected = Client.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
            assertEquals(1, clientRepository.count());
        }
    }

    @Nested
    class FindClientById {
        @Test
        void shouldFindClientById() {
            val client = Client.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            clientRepository.save(client);

            val actual = clientRepository.findById(1L);

            val expected = Client.builder()
                    .id(1L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertTrue(actual.isPresent());
            assertEquals(expected, actual.get());
        }
    }

    @Nested
    class UpdateClient {
        @Test
        void shouldUpdateClient() {
            val client = Client.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            clientRepository.save(client);

            val clientUpdate = Client.builder()
                    .id(1L)
                    .firstName("Petr")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(25)
                    .login("petr")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            val actual = clientRepository.save(clientUpdate);

            val expected = Client.builder()
                    .id(1L)
                    .firstName("Petr")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(25)
                    .login("petr")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
            assertEquals(expected, clientRepository.findById(1L).get());
        }
    }

    @Nested
    class DeleteClient {
        @Test
        void shouldDeleteClient() {
            val client = Client.builder()
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("qwe@qwe.ru")
                    .age(20)
                    .login("qwe")
                    .createdAt(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            clientRepository.save(client);

            clientRepository.deleteById(1L);

            assertEquals(0, clientRepository.count());
        }
    }
}
