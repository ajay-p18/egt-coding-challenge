package com.egt.challenge.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    private Long id;
    private Person person;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
}
