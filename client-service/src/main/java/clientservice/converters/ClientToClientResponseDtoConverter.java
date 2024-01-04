package clientservice.converters;

import clientservice.dto.ClientResponseDto;
import clientservice.entities.Client;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ClientToClientResponseDtoConverter extends Converter<Client, ClientResponseDto> {
    @Override
    ClientResponseDto convert(final Client client);
}