package com.david.helpdesk.service;

import com.david.helpdesk.dto.TicketRequestDTO;
import com.david.helpdesk.dto.TicketResponseDTO;
import com.david.helpdesk.exception.TicketNotFoundException;
import com.david.helpdesk.model.Ticket;
import com.david.helpdesk.model.TicketPriority;
import com.david.helpdesk.model.TicketStatus;
import com.david.helpdesk.model.User;
import com.david.helpdesk.repository.TicketRepository;
import com.david.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository,UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository=userRepository;
    }

    public List<TicketResponseDTO> getTickets(){
        List<Ticket> tickets= ticketRepository.findAll();

        List<TicketResponseDTO> responseDTO = new ArrayList<>();

        for(Ticket ticket:tickets){

            TicketResponseDTO response= new TicketResponseDTO();

            response.setId(ticket.getId());
            response.setTitle(ticket.getTitle());
            response.setDescription(ticket.getDescription());
            response.setStatus(ticket.getStatus());
            response.setPriority(ticket.getPriority());
            response.setUserId(ticket.getUser().getId());

            responseDTO.add(response);
        }
        return responseDTO;
    }

    public TicketResponseDTO addTicket(TicketRequestDTO request){

        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isEmpty()){
            throw new TicketNotFoundException("not found");
        }

        User actualUser= user.get();

        Ticket ticket= new Ticket(request.getTitle(),request.getDescription(),request.getStatus(),request.getPriority());

        ticket.setUser(actualUser);

        ticketRepository.save(ticket);

        TicketResponseDTO response= new TicketResponseDTO();

        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setPriority(ticket.getPriority());
        response.setUserId(ticket.getUser().getId());

        return response;
    }

    public TicketResponseDTO getTicketById(int id){
        Optional<Ticket> optionalTicket =  ticketRepository.findById(id);

        if(optionalTicket.isEmpty()){
            throw new TicketNotFoundException("Ticket with id "+ id + "not found");
        }
        
        Ticket ticket= optionalTicket.get();
        TicketResponseDTO response = new TicketResponseDTO();

        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setPriority(ticket.getPriority());
        response.setUserId(ticket.getUser().getId());

        return response;
    }

    public TicketResponseDTO updateTicket(int id,TicketRequestDTO request){

        Optional<Ticket> optionalTicket =  ticketRepository.findById(id);

        if(optionalTicket.isEmpty()){
            throw new TicketNotFoundException("ticket with id"+id+"not found");
        }

        Ticket ticket= optionalTicket.get();

        TicketResponseDTO response= new TicketResponseDTO();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());

        ticketRepository.save(ticket);

        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setPriority(ticket.getPriority());

        return response;
    }

    public TicketResponseDTO deleteTicket(int id){
        Optional<Ticket> optionalTicket =  ticketRepository.findById(id);

        if(optionalTicket.isEmpty()){
            throw new TicketNotFoundException("Ticket with id "+ id + "not found");
        }

        Ticket ticket = optionalTicket.get();

        TicketResponseDTO response = new TicketResponseDTO();

        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setPriority(ticket.getPriority());
        response.setUserId(ticket.getUser().getId());

        ticketRepository.delete(ticket);

        return response;
    }
}