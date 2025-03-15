package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.UserChangePasswordRequest;
import com.example.E_Commerce_API.dto.request.UserDeleteAccountRequest;
import com.example.E_Commerce_API.dto.response.UserEditResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/edit")
public class UsersController {
    private final UsersService usersService;
    private final AuthenticationHelperService authenticationHelperService;

    @PatchMapping("/name")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<UserEditResponse> editUserName(@RequestParam String name) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        UserEditResponse editedName = usersService.editUserName(currentUserEmail, name);
        return ResponseEntity.ok(editedName);
    }

    @PatchMapping("/number")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<UserEditResponse> editUserNumber(@RequestParam String number) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        UserEditResponse editedNumber = usersService.editUserNumber(currentUserEmail, number);
        return ResponseEntity.ok(editedNumber);
    }

    @PatchMapping("/change-password")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<UserEditResponse> changePassword(@RequestBody @Valid UserChangePasswordRequest userChangePasswordRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        UserEditResponse newPassword = usersService.changePassword(currentUserEmail, userChangePasswordRequest);
        return ResponseEntity.ok(newPassword);
    }

    @DeleteMapping("/delete-account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public void deleteAccount(@RequestBody @Valid UserDeleteAccountRequest userDeleteAccountRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        usersService.deleteAccount(currentUserEmail, userDeleteAccountRequest);
    }
}
