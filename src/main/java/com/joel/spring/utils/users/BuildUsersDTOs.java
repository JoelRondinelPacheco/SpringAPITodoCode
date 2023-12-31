package com.joel.spring.utils.users;

import com.joel.spring.dtos.users.UserPersonalInfoDTO;
import com.joel.spring.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildUsersDTOs {
    public UserPersonalInfoDTO userPersonalInfoDTO(UserEntity user) {
        return UserPersonalInfoDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
