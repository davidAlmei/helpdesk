package com.david.helpdesk.repository;

import com.david.helpdesk.model.Ticket;
import com.david.helpdesk.model.TicketPriority;
import com.david.helpdesk.model.TicketStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    private final List<Ticket> tickets= new ArrayList<>();

    public TicketRepository() {
        tickets.add(new Ticket(1,"one","this is one", TicketStatus.OPEN, TicketPriority.LOW));
        tickets.add(new Ticket(2,"two","this is two",TicketStatus.CLOSED,TicketPriority.HIGH));
    }

    public List<Ticket> findAll(){
        return tickets;
    }

    public Ticket save(Ticket ticket){
        tickets.add(ticket);
        return ticket;
    }

    public Ticket findById(int id){
        for(Ticket ticket:tickets){
            if(ticket.getId()==id){
                return ticket;
            }
        }
        return null;
    }

    public Ticket update(int id, Ticket updateTicket){

        for(Ticket ticket:tickets){
            if(ticket.getId()==id){
                ticket.setTitle(updateTicket.getTitle());
                ticket.setDescription(updateTicket.getDescription());
                ticket.setStatus(updateTicket.getStatus());
                ticket.setPriority(updateTicket.getPriority());
                return ticket;
            }
        }

        return null;
    }

    public Ticket deleteById(int id){

        Ticket ticketToDelete= null;
        for(Ticket ticket:tickets){
            if(ticket.getId()==id){
                ticketToDelete=ticket;
                break;
            }
        }

        if(ticketToDelete!=null){
            tickets.remove(ticketToDelete);
            return ticketToDelete;
        }
        return null;
    }
}
