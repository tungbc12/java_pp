package ru.spbstu.telematics.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StoreTest {
    @Test
    public void testGetNextTicket() {
        TicketMachine ticketMachine = new TicketMachine(10, 1);
        DisplayScreen displayScreen = new DisplayScreen();
        Store store = new Store(ticketMachine, displayScreen, 1000, 2000);

        int ticket = store.getNextTicket(1);
        assertEquals(1, ticket);

        ticket = store.getNextTicket(2);
        assertEquals(2, ticket);
    }

    @Test
    public void testServe() {
        TicketMachine ticketMachine = new TicketMachine(10, 1);
        DisplayScreen displayScreen = new DisplayScreen();
        Store store = new Store(ticketMachine, displayScreen, 1000, 2000);

        // Assume ticket 1 is already given to the customer.
        displayScreen.setCurrentServingTicket(1);

        store.serve(1); // Customer with ticket 1 is being served.

        assertEquals(2, displayScreen.getCurrentServingTicket());
    }
}
