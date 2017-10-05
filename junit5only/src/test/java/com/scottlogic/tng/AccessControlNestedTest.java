package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example class to show how nesting and display names can be used to logically group and label tests
 */
@DisplayName("The USS Enterprise access control")
class AccessControlNestedTest {

    private AccessControl testee;
    private User picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
    private User barclay = new User("Reg Barclay", UserType.CREW);
    private User lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);

    @BeforeEach
    void setUp() {
        testee = new AccessControl();
    }

    @Test
    @DisplayName("starts with no alert status")
    void startsWithNoAlert() {
        assertEquals(Alert.NONE, testee.getAlertStatus());
    }

    @Nested
    @DisplayName("when set to no alert status")
    class whenNoAlert {

        @Test
        @DisplayName("allows everyone to access the replicators")
        void canAccessReplicators() {
            assertTrue(testee.canAccessReplicator(picard));
            assertTrue(testee.canAccessReplicator(barclay));
            assertTrue(testee.canAccessReplicator(lwaxana));
        }

        @Test
        @DisplayName("only allows crew to access the the transporter")
        void canAccessTransporters() {
            assertTrue(testee.canAccessTransporter(picard));
            assertTrue(testee.canAccessTransporter(barclay));
            assertFalse(testee.canAccessTransporter(lwaxana));
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertTrue(testee.canAccessPhasers(picard));
            assertFalse(testee.canAccessPhasers(barclay));
            assertFalse(testee.canAccessPhasers(lwaxana));
        }
    }

    @Nested
    @DisplayName("when set to yellow alert")
    class whenYellowAlert {

        @BeforeEach
        void setUp() {
            testee.setAlertStatus(Alert.YELLOW);
        }

        @Test
        @DisplayName("only allows crew to access the replicators")
        void canAccessReplicators() {
            assertTrue(testee.canAccessReplicator(picard));
            assertTrue(testee.canAccessReplicator(barclay));
            assertFalse(testee.canAccessReplicator(lwaxana));
        }

        @Test
        @DisplayName("only allows crew to access the the transporter")
        void canAccessTransporters() {
            assertTrue(testee.canAccessTransporter(picard));
            assertTrue(testee.canAccessTransporter(barclay));
            assertFalse(testee.canAccessTransporter(lwaxana));
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertTrue(testee.canAccessPhasers(picard));
            assertFalse(testee.canAccessPhasers(barclay));
            assertFalse(testee.canAccessPhasers(lwaxana));
        }
    }

    @Nested
    @DisplayName("when set to red alert")
    class whenRedAlert {

        @BeforeEach
        void setUp() {
            testee.setAlertStatus(Alert.RED);
        }

        @Test
        @DisplayName("only allows bridge crew to access the replicators")
        void canAccessReplicators() {
            assertTrue(testee.canAccessReplicator(picard));
            assertFalse(testee.canAccessReplicator(barclay));
            assertFalse(testee.canAccessReplicator(lwaxana));
        }

        @Test
        @DisplayName("only allows bridge crew to access the the transporter")
        void canAccessTransporters() {
            assertTrue(testee.canAccessTransporter(picard));
            assertFalse(testee.canAccessTransporter(barclay));
            assertFalse(testee.canAccessTransporter(lwaxana));
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertTrue(testee.canAccessPhasers(picard));
            assertFalse(testee.canAccessPhasers(barclay));
            assertFalse(testee.canAccessPhasers(lwaxana));
        }
    }
}