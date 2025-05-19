package com.egt.challenge.dto;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class AddressMapperImpl implements AddressMapper{
    @Override
    public AddressDto toDto(Address entity) {

        PersonDto personDto = PersonDto.builder()
                .id(entity.getPerson().getId())
                .firstName(entity.getPerson().getFirstName())
                .lastName(entity.getPerson().getLastName())
                .birthDate(entity.getPerson().getBirthDate())
                .street1(entity.getStreet1())
                .street2(entity.getStreet2())
                .city(entity.getCity())
                .state(entity.getState())
                .zipCode(entity.getZipCode())
                .build();

        AddressDto addressDto = new AddressDto(entity.getId(), personDto, entity.getStreet1(), entity.getStreet2(), entity.getCity(), entity.getState(), entity.getZipCode());
        return addressDto;
    }


    @Override
    public Address toEntity(AddressDto dto) {

        Person person = Person.builder()
                .id(dto.getPerson().getId())
                .firstName(dto.getPerson().getFirstName())
                .lastName(dto.getPerson().getLastName())
                .birthDate(dto.getPerson().getBirthDate())
                .build();

        Address address = new Address(dto.getId(), person, dto.getStreet1(), dto.getStreet2(), dto.getCity(), dto.getState(), dto.getZipCode());
        return address;
    }
}
