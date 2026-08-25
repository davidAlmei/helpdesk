package com.david.helpdesk.dto;

import com.david.helpdesk.model.Ticket;

import java.util.List;

public class UserResponseDTO {

    private int id;

    private String name;

    private String email;
    
    private List<Integer> ticketIds;

    public List<Integer> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Integer> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
