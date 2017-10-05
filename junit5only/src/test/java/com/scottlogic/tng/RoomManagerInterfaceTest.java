package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * a refactored version of {@link RoomManagerRefactoredTest} to implement an interface with additional tests.
 */
@DisplayName("The USS Enterprise room management system")
class RoomManagerInterfaceTest implements DiagnosticInfoTest<RoomManager> {

    private static final int ROOM1_DECK = 15;
    private static final int ROOM1_NUMBER = 234;
    private static final int ROOM2_DECK = 12;
    private static final int ROOM2_NUMBER = 4;
    private static final int ROOM3_DECK = 25;
    private static final int ROOM3_NUMBER = 1;
    private RoomManager testee;
    private Quarters room1;
    private Quarters room2;
    private Quarters room3;
    private User picard;
    private User lwaxana;

    @BeforeEach
    void setUp() {
        room1 = new Quarters(ROOM1_DECK, ROOM1_NUMBER);
        room2 = new Quarters(ROOM2_DECK, ROOM2_NUMBER);
        room3 = new Quarters(ROOM3_DECK, ROOM3_NUMBER);
        picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
        picard.setQuarters(room3);
        lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
        testee = new RoomManager(Arrays.asList(room1, room2));
    }

    @Override
    public RoomManager getInstance() {
        return testee;
    }

    @Override
    public String getExpectedSystemName() {
        return "Room Manager";
    }

    @Override
    public void accessWithUser(User user) {
        testee.allocateQuarters(user);
    }

    @Nested
    @DisplayName("when there are unallocated rooms")
    class WhenThereAreUnallocatedRooms {

        @Nested
        @DisplayName("and the user has no quarters")
        class userHasNoQuarters {

            @BeforeEach
            void setUp() {
                testee.allocateQuarters(lwaxana);
            }

            @Test
            @DisplayName("updates the user to have quarters.")
            void allocatesQuartersToUser() {
                assertEquals(room1, lwaxana.getQuarters());
            }

            @Test
            @DisplayName("updates the unallocated quarters list.")
            void removesAllocatedQuarters() {
                List<Quarters> unallocatedQuarters = testee.getUnallocatedQuarters();
                assertEquals(1, unallocatedQuarters.size(), "Incorrect allocated quarters remaining");
                assertEquals(room2, unallocatedQuarters.get(0));
            }
        }

        @Nested
        @DisplayName("and the user has quarters")
        class UserHasQuarters {

            @BeforeEach
            void setUp() {
                testee.allocateQuarters(picard);
            }

            @Test
            @DisplayName("does not update the user's quarters.")
            void doesNotUpdateQuarters() {
                assertEquals(room3, picard.getQuarters());
            }

            @Test
            @DisplayName("does not update the unallocated quarters list.")
            void allocatedQuartersUnchanged() {
                List<Quarters> unallocatedQuarters = testee.getUnallocatedQuarters();
                assertEquals(2, unallocatedQuarters.size());
                assertAll(
                        () -> assertEquals(room1, unallocatedQuarters.get(0)),
                        () -> assertEquals(room2, unallocatedQuarters.get(1))
                );
            }
        }
    }

    @Nested
    @DisplayName("when there are no unallocated rooms")
    class WhenThereAreNoUnallocatedRooms {

        @BeforeEach
        void setUp() {
            testee = new RoomManager(Collections.emptyList());
        }

        @Nested
        @DisplayName("and the user has no quarters")
        class userHasNoQuarters {

            private IllegalStateException actualException;

            @BeforeEach
            void setUp() {
                actualException = assertThrows(IllegalStateException.class, () -> testee.allocateQuarters(lwaxana));
            }

            @Test
            @DisplayName("throws an exception.")
            void exceptionIsThrown() {
                assertEquals("Unable to allocate quarters for Lwaxana Troi", actualException.getMessage());
            }

            @Test
            @DisplayName("does not alter the unallocated quarters list.")
            void doesNotAlterAllocatedQuarters() {
                assertEquals(0, testee.getUnallocatedQuarters().size());
            }
        }

        @Nested
        @DisplayName("and the user has quarters")
        class userHasQuarters {

            @BeforeEach
            void setUp() {
                testee.allocateQuarters(picard);
            }

            @Test
            @DisplayName("does not update the user's quarters.")
            void doesNotAllocateQuartersToUser() {
                assertEquals(room3, picard.getQuarters());
            }

            @Test
            @DisplayName("does not alter the unallocated quarters list.")
            void unallocatedQuartersUnchanged() {
                assertEquals(0, testee.getUnallocatedQuarters().size());
            }
        }
    }
}