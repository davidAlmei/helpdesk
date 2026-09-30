package com.david.helpdesk.service;

import com.david.helpdesk.dto.LoginRequestDTO;
import com.david.helpdesk.dto.LoginResponseDTO;
import com.david.helpdesk.dto.RegistrationRequestDTO;
import com.david.helpdesk.exception.EmailAlreadyExistsException;
import com.david.helpdesk.exception.EmailNotFoundException;
import com.david.helpdesk.exception.InvalidCredentials;
import com.david.helpdesk.model.User;
import com.david.helpdesk.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(RegistrationRequestDTO request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already Exists");
        }
        String hashedPass = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getName(), request.getEmail());
        user.setPassword(hashedPass);

        userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request){

        User user= userRepository.findByEmail(request.getEmail());

        if(user==null){
            throw new EmailNotFoundException("User not registered");
        }

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidCredentials("Credentials entered are not valid");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setMessage("Login Successful");

        return response;
    }
}
