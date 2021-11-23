package com.switchfully.eurder.mappers;

import com.switchfully.eurder.dto.user.CreateUserDTO;
import com.switchfully.eurder.dto.user.UserDTO;
import com.switchfully.eurder.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public User toUser(CreateUserDTO dto) {
        return new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress(), dto.getPhoneNumber());
    }

    public UserDTO toDTO(User user) {
        return new UserDTO()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setAddress(user.getAddress())
                .setEmail(user.getEmail())
                .setPhoneNumber(user.getPhoneNumber())
                .setId(user.getUserId());
    }
}
