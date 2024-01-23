package clientservice.controller;

import clientservice.converters.ClientToClientResponseDtoConverter;
import clientservice.dto.ClientRequestDto;
import clientservice.dto.ClientResponseDto;
import clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientToClientResponseDtoConverter converter;

    @GetMapping("/{id}")
    public ClientResponseDto getClient(@PathVariable final Long id) {
        return converter.convert(clientService.getClientById(id));
    }

    @PostMapping
    public ClientResponseDto postClient(@RequestBody final ClientRequestDto clientRequestDto) {
        return converter.convert(clientService.createClient(clientRequestDto));
    }

    @PutMapping
    public ClientResponseDto putClient(@RequestBody final ClientRequestDto clientRequestDto) {
        return converter.convert(clientService.updateClient(clientRequestDto));
    }

    @DeleteMapping("/{id}")
    public ClientResponseDto deleteClient(@PathVariable final Long id) {
        return converter.convert(clientService.deleteClient(id));
    }
}
