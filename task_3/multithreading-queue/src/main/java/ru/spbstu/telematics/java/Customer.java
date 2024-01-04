package ru.spbstu.telematics.java;

// class for customer objects.
public class Customer implements Runnable {
    private int customerID;
    private int customerTicket;
    private Store store;

    public Customer(int customerID, Store store) {
        this.customerID = customerID;
        this.store = store;
        this.customerTicket = 0;
    }

    @Override
    public void run() {

        // Receive ticket.
        this.customerTicket = this.store.getNextTicket(customerID);

        // Serve.
        this.store.serve(customerTicket);
    }
}
