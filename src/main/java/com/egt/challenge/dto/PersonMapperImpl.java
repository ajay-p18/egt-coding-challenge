package com.egt.challenge.dto;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonMapperImpl implements PersonMapper {
    @Override
    public PersonDto toDto(Person entity) {

        List<AddressDto> additionalAddresses = new ArrayList<>();

        for(Address address: entity.getAdditionalAddresses()){
            AddressDto addressDto = AddressDto.builder()
                    .id(address.getId())
                    .street1(address.getStreet1())
                    .street2(address.getStreet2())
                    .city(address.getCity())
                    .state(address.getState())
                    .zipCode(address.getZipCode())
                    .build();

            additionalAddresses.add(addressDto);
        }

        PersonDto personDto = new PersonDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getBirthDate(),
                entity.getMainAddress().getStreet1(), entity.getMainAddress().getStreet2(),entity.getMainAddress().getCity(),
                entity.getMainAddress().getState(), entity.getMainAddress().getZipCode(),additionalAddresses);

        return personDto;
    }




    @Override
    public Person toEntity(PersonDto dto) {

        //Address mainAddress = new Address(dto.getStreet1(), dto.getStreet2(), dto.getCity(), dto.getState(), dto.getZipCode());

        Address mainAddress = Address.builder()
                .street1(dto.getStreet1())
                .street2(dto.getStreet2())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .build();

        Set<Address> additionalAddresses = new HashSet<>();

        for(AddressDto additionalAddressDto: dto.getAdditionalAddresses()){

            Address address = Address.builder()
                    .street1(additionalAddressDto.getStreet1())
                    .street2(additionalAddressDto.getStreet2())
                    .city(additionalAddressDto.getCity())
                    .state(additionalAddressDto.getState())
                    .zipCode(additionalAddressDto.getZipCode())
                    .build();

            additionalAddresses.add(address);

        }

        Person person = new Person(dto.getId(),dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), mainAddress, additionalAddresses);
        return person;

    }
}
