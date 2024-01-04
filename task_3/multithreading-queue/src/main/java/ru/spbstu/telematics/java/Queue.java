package ru.spbstu.telematics.java;

public class Queue {
    public static void main(String[] args) {
        int timeServing = 2000; // Time to serve each customer.
        int timeGivingTicket = 1000; // Time to give ticket to each customer.
        int MAX_TICKET_NUMBER = 30; // Maximum number of tickets.
        int firstTicket = 1; // The first ticket number in ticket machine to give to customers.
        TicketMachine ticketMachine = new TicketMachine(MAX_TICKET_NUMBER, firstTicket);
        DisplayScreen displayScreen = new DisplayScreen();
        Store store = new Store(ticketMachine, displayScreen, timeGivingTicket, timeServing);

        int numberOfCustomers = 15; // All customers come to the store.

        // Create threads for each customer.
        for (int i = 1; i <= numberOfCustomers; i++) {
            Thread customerThread = new Thread(new Customer(i, store));
            customerThread.start();
        }
    }
}