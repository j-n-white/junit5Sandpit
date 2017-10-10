package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class to show how assertions can be grouped together and nested so they are all run and then any failures are
 * reported together.
 */
@DisplayName("The USS Enterprise room management system")
class RoomManagerGroupedAssertionTest {

    private static final int ROOM1_DECK = 15;
    private static final int ROOM1_NUMBER = 234;
    private static final int ROOM2_DECK = 12;
    private static final int ROOM2_NUMBER = 4;
    private static final int ROOM3_DECK = 25;
    private static final int ROOM3_NUMBER = 1;
    private RoomManager testee;
    private User picard;
    private User lwaxana;

    @BeforeEach
    void setUp() {
        Quarters room1 = new Quarters(ROOM1_DECK, ROOM1_NUMBER);
        Quarters room2 = new Quarters(ROOM2_DECK, ROOM2_NUMBER);
        Quarters room3 = new Quarters(ROOM3_DECK, ROOM3_NUMBER);
        picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
        picard.setQuarters(room3);
        lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
        testee = new RoomManager(Arrays.asList(room1, room2));
    }

    @Test
    @DisplayName("allocates quarters if the user has none.")
    void allocatesQuarters() {
        testee.allocateQuarters(lwaxana);
        assertAll(
                () -> {
                    Quarters assignedQuarters = lwaxana.getQuarters();
                    assertNotNull(assignedQuarters, "Quarters should have been assigned to the user.");
                    assertAll(
                            () -> assertEquals(ROOM1_DECK, assignedQuarters.getDeck(), "Assigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM1_NUMBER, assignedQuarters.getRoomNumber(), "Assigned quarters has incorrect number")
                    );
                },
                () -> {
                    List<Quarters> unallocatedQuarters = testee.getUnallocatedQuarters();
                    assertEquals(1, unallocatedQuarters.size(), "Incorrect allocated quarters remaining");
                    Quarters unassignedQuarters = unallocatedQuarters.get(0);
                    assertAll(
                            () -> assertEquals(ROOM2_DECK, unassignedQuarters.getDeck(), "Unassigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM2_NUMBER, unassignedQuarters.getRoomNumber(), "Unassigned quarters has incorrect number")
                    );
                }
        );
    }

    @Test
    @DisplayName("does not not allocate quarters the user when they already have quarters.")
    void doesNotAllocatesQuartersWhenTheyAlreadyHaveSome() {
        testee.allocateQuarters(picard);
        assertAll(
                () -> {
                    Quarters assignedQuarters = picard.getQuarters();
                    assertNotNull(assignedQuarters, "User should have quarters.");
                    assertAll(
                            () -> assertEquals(ROOM3_DECK, assignedQuarters.getDeck(), "Assigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM3_NUMBER, assignedQuarters.getRoomNumber(), "Assigned quarters has incorrect number")
                    );
                },
                () -> {
                    List<Quarters> unallocatedQuarters = testee.getUnallocatedQuarters();
                    assertEquals(2, unallocatedQuarters.size(), "Incorrect allocated quarters remaining");
                    Quarters unassignedQuarters1 = unallocatedQuarters.get(0);
                    assertAll(
                            () -> assertEquals(ROOM1_DECK, unassignedQuarters1.getDeck(), "Unassigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM1_NUMBER, unassignedQuarters1.getRoomNumber(), "Unassigned quarters has incorrect number")
                    );
                    Quarters unassignedQuarters2 = unallocatedQuarters.get(1);
                    assertAll(
                            () -> assertEquals(ROOM2_DECK, unassignedQuarters2.getDeck(), "Unassigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM2_NUMBER, unassignedQuarters2.getRoomNumber(), "Unassigned quarters has incorrect number")
                    );
                }
        );
    }

    @Test
    @DisplayName("throws an exception when the user has no quarters and there are none available.")
    void exceptionThrownWhenNoQuartersAvailable() {
        testee.getUnallocatedQuarters().clear();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> testee.allocateQuarters(lwaxana));
        assertAll(
                () -> assertEquals("Unable to allocate quarters for Lwaxana Troi", exception.getMessage()),
                () -> assertEquals(0, testee.getUnallocatedQuarters().size(), "Incorrect allocated quarters remaining")
        );
    }

    @Test
    @DisplayName("does not update the user when they have quarters and there are none available.")
    void doesNotAllocatesQuartersWhenTheyAlreadyHaveSomeAndThereAreNoneAvailable() {
        testee.getUnallocatedQuarters().clear();
        testee.allocateQuarters(picard);
        assertAll(
                () -> {
                    Quarters assignedQuarters = picard.getQuarters();
                    assertNotNull(assignedQuarters, "User should have quarters.");
                    assertAll(
                            () -> assertEquals(ROOM3_DECK, assignedQuarters.getDeck(), "Assigned quarters has incorrect deck"),
                            () -> assertEquals(ROOM3_NUMBER, assignedQuarters.getRoomNumber(), "Assigned quarters has incorrect number")
                    );
                },
                () -> assertEquals(0, testee.getUnallocatedQuarters().size(), "Incorrect allocated quarters remaining")
        );
    }
}