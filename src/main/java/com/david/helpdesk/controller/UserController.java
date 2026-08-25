package com.david.helpdesk.controller;

import com.david.helpdesk.dto.TicketResponseDTO;
import com.david.helpdesk.dto.UserRequestDTO;
import com.david.helpdesk.dto.UserResponseDTO;
import com.david.helpdesk.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO){
        return userService.addUser(userRequestDTO);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}/tickets")
    public List<TicketResponseDTO> getUserTickets(@PathVariable int id) {
        return userService.getAllTickets(id);
    }
}
