package com.switchfully.eurder.services;

import com.switchfully.eurder.dto.item.CreateItemDTO;
import com.switchfully.eurder.dto.item.UpdateItemDTO;
import com.switchfully.eurder.dto.user.CreateUserDTO;
import com.switchfully.eurder.entities.User;
import com.switchfully.eurder.exceptions.AuthorisationException;
import com.switchfully.eurder.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final CustomerRepository customerRepository;

    @Autowired
    public ValidationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public boolean IsValidCreateUserDTO(CreateUserDTO dto) {
        return isValidStringInput(dto.getFirstName())
                && isValidStringInput(dto.getLastName())
                && isValidStringInput(dto.getAddress())
                && isValidStringInput(dto.getEmail())
                && isValidStringInput(dto.getPhoneNumber());
    }


    public boolean isValidCreateItemDTO(CreateItemDTO dto) {
        return isValidStringInput(dto.getName())
                && isValidStringInput(dto.getDescription())
                && isValidDoubleInput(dto.getPrice());

    }

    public boolean isValidUpdateItemDTO(UpdateItemDTO dto) {
        return isValidStringInput(dto.getName())
                && isValidStringInput(dto.getDescription())
                && isValidDoubleInput(dto.getPrice());
    }




    public void assertAdmin(int id) {
        User toValidateAgainst = customerRepository.findByUserId(id);
        if (toValidateAgainst == null || !toValidateAgainst.isAdmin())
            throw new AuthorisationException();
    }


    private boolean isValidStringInput(String input) {
        return !(input == null || input.isEmpty() || input.isBlank());
    }


    private boolean isValidDoubleInput(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The parameter for the price is not valid.");
        }
    }


}
