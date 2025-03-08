package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.UsersRepository;
import com.example.E_Commerce_API.dto.request.UserChangePasswordRequest;
import com.example.E_Commerce_API.dto.request.UserDeleteAccountRequest;
import com.example.E_Commerce_API.dto.response.UserEditResponse;
import com.example.E_Commerce_API.exception.InvalidPasswordException;
import com.example.E_Commerce_API.exception.InvalidUserEditRequestException;
import com.example.E_Commerce_API.mapper.UsersMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final AuthenticationHelperService authenticationHelperService;
    private final PasswordEncoder passwordEncoder;

    public UserEditResponse editUserName(String currentUserEmail, String name) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (name != null) {
            users.setName(name);
        } else {
            throw new InvalidUserEditRequestException("Name cannot be empty");
        }
        Users updatedUsers = usersRepository.save(users);
        return usersMapper.toUsersEditResponse(updatedUsers);
    }

    public UserEditResponse editUserNumber(String currentUserEmail, String number) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (number != null) {
            users.setPhoneNumber(number);
        } else {
            throw new InvalidUserEditRequestException("Phone number cannot be empty");
        }
        return usersMapper.toUsersEditResponse(usersRepository.save(users));
    }

    public UserEditResponse changePassword(String currentUserEmail, UserChangePasswordRequest userChangePasswordRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (userChangePasswordRequest.getOldPassword() != null && passwordEncoder.matches(userChangePasswordRequest.getOldPassword(), users.getPassword())) {
            users.setPassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
        } else {
            throw new InvalidPasswordException("Password does not match");
        }
        return usersMapper.toUsersEditResponse(usersRepository.save(users));
    }

    public void deleteAccount(String currentUserEmail, UserDeleteAccountRequest userDeleteAccountRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (userDeleteAccountRequest.getPassword() != null && passwordEncoder.matches(userDeleteAccountRequest.getPassword(), users.getPassword())) {
            usersRepository.delete(users);
        } else {
            throw new InvalidPasswordException("Password does not match");
        }
    }
}
