package com.david.helpdesk.controller;

import com.david.helpdesk.dto.LoginRequestDTO;
import com.david.helpdesk.dto.LoginResponseDTO;
import com.david.helpdesk.dto.RegistrationRequestDTO;
import com.david.helpdesk.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequestDTO request){
        authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request){
        return authService.login(request);
    }
}
