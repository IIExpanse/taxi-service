package clientservice.controller;

import clientservice.converters.ClientToClientResponseDtoConverter;
import clientservice.dto.ClientRequestDto;
import clientservice.dto.ClientResponseDto;
import clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientToClientResponseDtoConverter converter;

    @GetMapping("/{id}")
    public ClientResponseDto getClient(@PathVariable final int id) {
        return converter.convert(clientService.getClientById(id));
    }

    @PostMapping
    public ClientResponseDto postClient(@RequestBody final ClientRequestDto clientRequestDto) {
        return converter.convert(clientService.createClient(clientRequestDto));
    }

    @PutMapping("/{id}")
    public ClientResponseDto putClient(@PathVariable final int id, @RequestBody final ClientRequestDto clientRequestDto) {
        return converter.convert(clientService.updateClient(id, clientRequestDto));
    }

    @DeleteMapping("/{id}")
    public ClientResponseDto deleteClient(@PathVariable final int id) {
        return converter.convert(clientService.deleteClient(id));
    }
}
