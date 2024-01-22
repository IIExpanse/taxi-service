package carservice.converter;

import carservice.dto.CarResponseDto;
import carservice.entity.Car;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CarToCarResponseDto extends Converter<Car, CarResponseDto>{
    @Override
    CarResponseDto convert(final Car car);
}
