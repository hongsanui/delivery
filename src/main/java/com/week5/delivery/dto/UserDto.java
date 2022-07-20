package com.week5.delivery.dto;

import com.week5.delivery.domain.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String password;
    private String username;
    private boolean owner = false;
    private String ownerToken = "";
}
