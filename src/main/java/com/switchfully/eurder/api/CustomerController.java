package com.switchfully.eurder.api;

import com.switchfully.eurder.dto.user.CreateUserDTO;
import com.switchfully.eurder.dto.user.UserDTO;
import com.switchfully.eurder.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserDTO dto) {
        logger.info("New user creation called");
        return customerService.saveNewUser(dto);

    }

    @PostMapping(produces = "application/json", consumes = "application/json", params = "authorisationId")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createAdmin(@RequestParam(value = "authorisationId", required = false) int authorisationId, @RequestBody CreateUserDTO dto) {
        logger.info("New admin creation called by admin " + authorisationId);
        return customerService.saveNewAdmin(authorisationId, dto);

    }

    @GetMapping(produces = "application/json", params = "authorisationId")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsersAsAdmin(@RequestParam("authorisationId") int authorisationId) {
        logger.info("Get all users called by admin " + authorisationId);
        return customerService.getAllUsers(authorisationId);
    }

    @GetMapping(produces = "application/json", params = {"authorisationId", "customerId"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getSpecificUser(@RequestParam("authorisationId") int authorisationId, @RequestParam(value = "customerId", required = false) int customerId) {
        logger.info("Get specific users called by admin " + authorisationId + " for user " + customerId);
        return customerService.getSpecificUser(authorisationId, customerId);
    }
}
