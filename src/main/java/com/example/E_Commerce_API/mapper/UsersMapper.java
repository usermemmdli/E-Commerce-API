package com.example.E_Commerce_API.mapper;

import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dto.request.UserEditNameRequest;
import com.example.E_Commerce_API.dto.response.UserEditResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class UsersMapper {
//    public static Users toUsers(UserEditNameRequest userEditRequest) {
//        return Users.builder()
//                .name(userEditRequest.getName())
//                .build();
//    }

    public static UserEditResponse toUsersEditResponse(Users users) {
        return UserEditResponse.builder()
                .name(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .build();
    }
}
