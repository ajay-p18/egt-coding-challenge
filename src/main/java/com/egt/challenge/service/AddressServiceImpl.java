package com.egt.challenge.service;

import com.egt.challenge.model.Address;
import com.egt.challenge.model.Person;
import com.egt.challenge.repo.AddressRepository;
import com.egt.challenge.repo.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @NonNull
    private final AddressRepository addressRepository;
    @NonNull
    private final PersonRepository personRepository;

    // TODO create methods to create, read, update, and delete Persons as outlined in the README


    public List<Address> findAll(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public boolean isValid(Address a, boolean isUpdate) throws Exception {

        if(isUpdate){
            Optional<Address> optionalAddress = addressRepository.findById(a.getId()); //looking for the address to see if they exist in the DB

            if (!optionalAddress.isPresent()){
                throw new Exception("Address does not exist");
            }
        }

        if(!(StringUtils.isNotBlank(a.getZipCode()) && StringUtils.isNotBlank(a.getState()) && a.getPerson().getId() != null)){
            throw new Exception("Address requires a valid Person ID, Zipcode and State.");
        }
        return true;

    }

    public Address save(Address a) throws Exception {
        Address address= null;

        if(!isValid(a,false)){
            return null;
        }

        address = new Address(a.getId(), a.getPerson(), a.getStreet1(), a.getStreet2(), a.getCity(), a.getState(), a.getZipCode());
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) throws Exception{

        Address updatedAddress = null;

        System.out.println(address.getPerson().getId());
        System.out.println(address.toString());

        if(isValid(address, true)){

            if(address.getPerson().getId() != null){
                updatedAddress = addressRepository.findById(address.getId()).get();

                updatedAddress.setId(address.getId());
                updatedAddress.setPerson(address.getPerson());
                updatedAddress.setStreet1(address.getStreet1());
                updatedAddress.setStreet2(address.getStreet2());
                updatedAddress.setCity(address.getCity());
                updatedAddress.setState(address.getState());
                updatedAddress.setZipCode(address.getZipCode());
            }

        }

        System.out.println(updatedAddress.toString());

        if(updatedAddress.getPerson().getId() == null || isEmpty(updatedAddress.getPerson().getId())){
            throw new Exception("Person ID cannot be set to null/empty");
        }


        return updatedAddress;
    }

    public Optional<Address> findById(Long id) throws Exception {
        Optional<Address> address = addressRepository.findById(id);
        Address a = address.get();

        if(!isValid(a,false)){
            return null;
        }

        return address;
    }
    public void delete(Address address) throws Exception{

        if(!isValid(address, true)){

            System.out.println("Address does not exist.");
        }

        for(Person p: personRepository.findAll()){
            if(address.getStreet1().equals(p.getMainAddress().getStreet1()) && address.getStreet2().equals(p.getMainAddress().getStreet2()) &&
                    address.getCity().equals(p.getMainAddress().getCity()) && address.getState().equals(p.getMainAddress().getState())
                    && address.getZipCode().equals(p.getMainAddress().getZipCode())){

                throw new Exception("Cannot delete this Address record as it is someone's Main Address");
            }
        }

        addressRepository.delete(address);
    }


}
