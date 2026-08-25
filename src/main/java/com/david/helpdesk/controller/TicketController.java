package com.david.helpdesk.controller;

import com.david.helpdesk.dto.TicketRequestDTO;
import com.david.helpdesk.dto.TicketResponseDTO;
import com.david.helpdesk.model.Ticket;
import com.david.helpdesk.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService=ticketService;
    }

    @GetMapping("/tickets")
    public List<TicketResponseDTO> getTickets(){
        return ticketService.getTickets();
    }

    @PostMapping("/tickets")
    public TicketResponseDTO addTicket(@RequestBody TicketRequestDTO request){
        return ticketService.addTicket(request);
    }

    @GetMapping("/tickets/{id}")
    public TicketResponseDTO getTicketById(@PathVariable int id){
        return ticketService.getTicketById(id);
    }

    @PutMapping("/tickets/{id}")
    public TicketResponseDTO updateTicket(@PathVariable int id, @RequestBody TicketRequestDTO request){
        return ticketService.updateTicket(id,request);
    }

    @DeleteMapping("/tickets/{id}")
    public TicketResponseDTO deleteTicket(@PathVariable int id){
        return ticketService.deleteTicket(id);
    }
//    @GetMapping("/tickets/filter")
//    public List<Ticket> filterTickets(@RequestParam String status){
//
//        List<Ticket> filteredTickets = new ArrayList<>();
//
//        for(Ticket ticket:tickets){
//
//            if(ticket.getStatus().equalsIgnoreCase(status)){
//                filteredTickets.add(ticket);
//            }
//        }
//        return filteredTickets;
//    }

//    @GetMapping("/tickets/filterByTitle")
//    public List<Ticket> filterTicketsByTitle(@RequestParam String title){
//
//        List<Ticket> filteredTickets= new ArrayList<>();
//
//        for(Ticket ticket:tickets){
//
//            if(ticket.getTitle().equalsIgnoreCase(title)){
//                filteredTickets.add(ticket);
//            }
//        }
//        return filteredTickets;
//    }
}
