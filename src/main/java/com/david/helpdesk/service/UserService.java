package com.david.helpdesk.service;

import com.david.helpdesk.dto.TicketResponseDTO;
import com.david.helpdesk.dto.UserRequestDTO;
import com.david.helpdesk.dto.UserResponseDTO;
import com.david.helpdesk.exception.TicketNotFoundException;
import com.david.helpdesk.model.Ticket;
import com.david.helpdesk.model.User;
import com.david.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    public UserResponseDTO addUser(UserRequestDTO request){

        UserResponseDTO response = new UserResponseDTO();

        User user = new User(request.getName(),request.getEmail());

        userRepository.save(user);

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }

    public List<UserResponseDTO> getUsers(){

        List<User> users= userRepository.findAll();

        List<UserResponseDTO> response = new ArrayList<>();

        for(User user: users){

            UserResponseDTO responseDTO = new UserResponseDTO();

            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());

            List<Integer> ticketIds = new ArrayList<>();
            for(Ticket ticket: user.getTickets()){
                ticketIds.add(ticket.getId());
            }
            responseDTO.setTicketIds(ticketIds);

            response.add(responseDTO);
        }
        return response;
    }

    public List<TicketResponseDTO> getAllTickets(int id){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new TicketNotFoundException("not found");
        }

        User actualUser= user.get();

        List<Ticket> tickets= actualUser.getTickets();

        List<TicketResponseDTO> response = new ArrayList<>();

        for(Ticket ticket: tickets){

            TicketResponseDTO responseDTO = new TicketResponseDTO();

            responseDTO.setId(ticket.getId());
            responseDTO.setTitle(ticket.getTitle());
            responseDTO.setDescription(ticket.getDescription());
            responseDTO.setStatus(ticket.getStatus());
            responseDTO.setPriority(ticket.getPriority());
            responseDTO.setUserId(ticket.getUser().getId());

            response.add(responseDTO);
        }

        return response;
    }
}
