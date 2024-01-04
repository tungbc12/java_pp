package ru.spbstu.telematics.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketMachineTest {
    @Test
    public void testGetCurrentTicket() {
        TicketMachine ticketMachine = new TicketMachine(10, 1);
        assertEquals(1, ticketMachine.getCurrentTicket());
    }

    @Test
    public void testSetCurrentTicket() {
        TicketMachine ticketMachine = new TicketMachine(10, 1);
        ticketMachine.setCurrentTicket(5);
        assertEquals(5, ticketMachine.getCurrentTicket());
    }
}

