package com.david.helpdesk.exception;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String msg){
        super(msg);
    }
}
