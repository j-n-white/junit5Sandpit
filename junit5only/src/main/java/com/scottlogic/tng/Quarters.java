package com.scottlogic.tng;

/**
 * Represents a living quarters onboard the ship.
 */
class Quarters {
    private final int deck;
    private final int roomNumber;

    Quarters(int deck, int roomNumber) {
        this.deck = deck;
        this.roomNumber = roomNumber;
    }

    int getDeck() {
        return deck;
    }

    int getRoomNumber() {
        return roomNumber;
    }
}
