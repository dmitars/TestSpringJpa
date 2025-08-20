package org.example.service;

import org.example.model.Address;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressService {

    @Autowired
    private UserService userService;


    public void deleteAddress(Address address) {
        //if address.getUser().getAddresses().size() == 1
        //userService.remove(address.getUser())
        //addressRepository.remove(address);
    }
}
