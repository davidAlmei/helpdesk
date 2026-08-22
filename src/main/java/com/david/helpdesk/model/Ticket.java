package com.david.helpdesk.model;

public class Ticket {

    private int id;

    private String title;

    private String description;

    private TicketStatus status;

    private TicketPriority priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketPriority getPriority() { return priority;}

    public void setPriority(TicketPriority priority) { this.priority = priority;}

    public Ticket(int id, String title, String description, TicketStatus status, TicketPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Ticket(){

    }
}
