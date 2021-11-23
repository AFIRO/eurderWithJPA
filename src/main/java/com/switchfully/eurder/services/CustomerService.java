package com.switchfully.eurder.services;

import com.switchfully.eurder.dto.user.CreateUserDTO;
import com.switchfully.eurder.dto.user.UserDTO;
import com.switchfully.eurder.entities.User;
import com.switchfully.eurder.mappers.CustomerMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final ValidationService validationService;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository, ValidationService validationService) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.validationService = validationService;
    }

    public UserDTO saveNewUser(CreateUserDTO dto) {
        if (validationService.IsValidCreateUserDTO(dto)) {
            User newUser = customerMapper.toUser(dto);
            customerRepository.save(newUser);
            logger.info("User with the following id has been created: " + newUser.getUserId());
            return customerMapper.toDTO(newUser);
        } else
            throw new IllegalArgumentException("The parameters supplied for your user account are not valid");
    }

    public UserDTO saveNewAdmin(int authorisationId, CreateUserDTO dto) {
        validationService.assertAdmin(authorisationId);
        if (validationService.IsValidCreateUserDTO(dto)) {
            User newUser = customerMapper.toUser(dto);
            newUser.setAdmin();
            customerRepository.save(newUser);
            logger.info("User with the following id has been created: " + newUser.getUserId());
            return customerMapper.toDTO(newUser);
        } else
            throw new IllegalArgumentException("The parameters supplied for your user account are not valid");

    }


    public List<UserDTO> getAllUsers(int authorisationId) {
        validationService.assertAdmin(authorisationId);
        logger.info("Info of all users called by admin " + authorisationId);
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }


    public UserDTO getSpecificUser(int authorisationId, int customerId) {
        validationService.assertAdmin(authorisationId);
        logger.info("Info of User " + customerId + "called by admin " + authorisationId);
        return customerMapper.toDTO(customerRepository.findByUserId(customerId));
    }

}
