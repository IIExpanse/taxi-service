package clientservice.service;

import clientservice.dto.ClientRequestDto;
import clientservice.entities.Client;
import clientservice.exception.NotFoundException;
import clientservice.repository.ClientRepository;
import clientservice.time.DateTimeProvider;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


@Service
@RequiredArgsConstructor
@Validated
public class ClientService {
    private final ClientRepository clientRepository;
    private final DateTimeProvider dateTimeProvider;

    public Client createClient(@Valid final ClientRequestDto clientRequestDto) {
        val client = Client.builder()
                .firstName(clientRequestDto.getFirstName())
                .lastName(clientRequestDto.getLastName())
                .email(clientRequestDto.getEmail())
                .age(clientRequestDto.getAge())
                .login(clientRequestDto.getLogin())
                .createdAt(dateTimeProvider.nowAsInstant())
                .build();
        return clientRepository.save(client);
    }

    public Client getClientById(final long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public Client updateClient(@Valid final ClientRequestDto clientRequestDto) {
        val client = clientRepository.findById(clientRequestDto.getId()).orElseThrow(() -> new NotFoundException("Client not found"));
        client.setId(clientRequestDto.getId());
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setEmail(clientRequestDto.getEmail());
        client.setAge(clientRequestDto.getAge());
        client.setLogin(clientRequestDto.getLogin());
        return clientRepository.save(client);
    }

    public Client deleteClient(final long id) {
        val client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        clientRepository.deleteById(id);
        return client;
    }
}
