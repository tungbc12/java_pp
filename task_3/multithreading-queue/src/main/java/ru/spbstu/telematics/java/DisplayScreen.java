package ru.spbstu.telematics.java;

// class for display screen object.
public class DisplayScreen {
    private int currentServingTicket;

    public DisplayScreen() {
        this.currentServingTicket = 0;
    }

    // Get and set current serving ticket number.
    public int getCurrentServingTicket() {
        return this.currentServingTicket;
    }

    public void setCurrentServingTicket(int currentServingTicket) {
        this.currentServingTicket = currentServingTicket;
    }
}
