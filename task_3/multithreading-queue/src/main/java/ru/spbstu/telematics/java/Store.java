package ru.spbstu.telematics.java;

// class for Store object.
public class Store {
    private TicketMachine ticketMachine; // Ticket machine.
    private DisplayScreen displayScreen; // Display screen.
    private int timeGivingTicket; // Time to give ticket to each customer.
    private int timeServing; // Time to serve each customer.
    private int firstGivenTicket; // The fist ticket is given to the customer.

    public Store(TicketMachine ticketMachine, DisplayScreen displayScreen, int timeGivingTicket, int timeServing) {
        this.ticketMachine = ticketMachine;
        this.displayScreen = displayScreen;
        this.timeGivingTicket = timeGivingTicket;
        this.timeServing = timeServing;
        this.firstGivenTicket = 0;
    }

    // Get ticket machine.
    public TicketMachine getTicketMachine() {
        return this.ticketMachine;
    }

    // Get display screen.
    public DisplayScreen getDisplayScreen() {
        return this.displayScreen;
    }

    // Giving ticket to customers.
    public synchronized int getNextTicket(int customerID) {

        // Time to give ticket to each customer (Simulate issuing ticket process to each customer).
        try {
            Thread.sleep(this.timeGivingTicket);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int ticketNumber = this.ticketMachine.getCurrentTicket();
        this.ticketMachine.setCurrentTicket(this.ticketMachine.getCurrentTicket() + 1);
        if (this.ticketMachine.getCurrentTicket() > this.ticketMachine.getMaxTicketNumber()) {
            this.ticketMachine.setCurrentTicket(1); // set ticket -> 1.
        }

        // Change can only be made when the first ticket is given to customer.
        if (this.firstGivenTicket == 0) {
            this.firstGivenTicket = ticketNumber;
            updateScreen(this.firstGivenTicket);
        }

        System.out.println("Customer_" + customerID + " took ticket " + ticketNumber);

        return ticketNumber;
    }

    // Serving customers.
    public synchronized void serve(int currentServingCustomer) {

        // Wait until the customer has the same number on the screen before serving.
        try {
            while (this.displayScreen.getCurrentServingTicket() != currentServingCustomer) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Display Screen: Ticket " + this.displayScreen.getCurrentServingTicket());

        // Time to serve each customer (Simulate serving process for each customer).
        try {
            Thread.sleep(this.timeServing);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Served ticket " + this.displayScreen.getCurrentServingTicket());

        // After serving, the number on the screen increases to 1.
        updateScreen(this.displayScreen.getCurrentServingTicket() + 1);

        notifyAll();
    }

    // Update current serving ticket on screen.
    public void updateScreen(int currentServingTicket) {
        if (currentServingTicket > this.ticketMachine.getMaxTicketNumber()) {
            this.displayScreen.setCurrentServingTicket(1);
        }
        else {
            this.displayScreen.setCurrentServingTicket(currentServingTicket);
        }
    }
}