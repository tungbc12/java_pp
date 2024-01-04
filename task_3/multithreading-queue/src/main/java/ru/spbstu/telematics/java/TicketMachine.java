package ru.spbstu.telematics.java;

// class for ticket machine object.
public class TicketMachine {
    private int MAX_TICKET_NUMBER;
    private int firstTicket;
    private int currentTicket;

    public TicketMachine(int MT, int firstTicket) {
        this.MAX_TICKET_NUMBER = MT;
        this.firstTicket = firstTicket;
        this.currentTicket = firstTicket;
    }

    // Get max ticket number.
    public int getMaxTicketNumber() {
        return this.MAX_TICKET_NUMBER;
    }

    // Get and set fist ticket in ticket machine.
    public int getFirstTicket() {
        return this.firstTicket;
    }

    public void setFirstTicket(int firstTicket) {
        this.firstTicket = firstTicket;
    }

    // Get and set current ticket number.
    public int getCurrentTicket() {
        return this.currentTicket;
    }

    public void setCurrentTicket(int currentTicket) {
        this.currentTicket = currentTicket;
    }
}
