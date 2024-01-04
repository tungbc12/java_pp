package ru.spbstu.telematics.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DisplayScreenTest {
    @Test
    public void testGetCurrentServingTicket() {
        DisplayScreen displayScreen = new DisplayScreen();
        assertEquals(0, displayScreen.getCurrentServingTicket());
    }

    @Test
    public void testSetCurrentServingTicket() {
        DisplayScreen displayScreen = new DisplayScreen();
        displayScreen.setCurrentServingTicket(3);
        assertEquals(3, displayScreen.getCurrentServingTicket());
    }
}
