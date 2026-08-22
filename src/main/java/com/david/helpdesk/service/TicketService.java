package com.david.helpdesk.service;

import com.david.helpdesk.model.Ticket;
import com.david.helpdesk.model.TicketPriority;
import com.david.helpdesk.model.TicketStatus;
import com.david.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(int id){
        return ticketRepository.findById(id);
    }

    public Ticket updateTicket(int id,Ticket updateticket){
        return ticketRepository.update(id, updateticket);
    }

    public Ticket deleteTicket(int id){
        return ticketRepository.deleteById(id);
    }

}
