package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A refactored version of {@link AccessControlGroupedAssertionTest} to implement an interface with additional tests.
 */
@DisplayName("The USS Enterprise access control")
class AccessControlInterfaceTestTest implements DiagnosticInfoTest<AccessControl> {

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

    @Override
    public AccessControl getInstance() {
        return testee;
    }

    @Override
    public String getExpectedSystemName() {
        return "Access Control";
    }

    @Override
    public void accessWithUser(User user) {
        testee.canAccessPhasers(user);
    }

    @Nested
    @DisplayName("when set to no alert status")
    class whenNoAlert {

        @Test
        @DisplayName("allows everyone to access the replicators")
        void canAccessReplicators() {
            assertAll(
                    () -> assertTrue(testee.canAccessReplicator(picard), "Bridge crew should be able to access replicators"),
                    () -> assertTrue(testee.canAccessReplicator(barclay), "Crew should be able to access replicators"),
                    () -> assertTrue(testee.canAccessReplicator(lwaxana), "Non-crew should be able to access replicators")
            );
        }

        @Test
        @DisplayName("only allows crew to access the the transporter")
        void canAccessTransporters() {
            assertAll(
                    () -> assertTrue(testee.canAccessTransporter(picard), "Bridge crew should be able to access transporters"),
                    () -> assertTrue(testee.canAccessTransporter(barclay), "Crew should be able to access transporters"),
                    () -> assertFalse(testee.canAccessTransporter(lwaxana), "Non-crew should not be able to access transporters")
            );
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertAll(
                    () -> assertTrue(testee.canAccessPhasers(picard), "Bridge crew should be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(barclay), "Crew should not be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(lwaxana), "Non-crew should not be able to access phasers")
            );
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
            assertAll(
                    () -> assertTrue(testee.canAccessReplicator(picard), "Bridge crew should be able to access replicators"),
                    () -> assertTrue(testee.canAccessReplicator(barclay), "Crew should be able to access replicators"),
                    () -> assertFalse(testee.canAccessReplicator(lwaxana), "Non-crew should not be able to access replicators")
            );
        }

        @Test
        @DisplayName("only allows crew to access the the transporter")
        void canAccessTransporters() {
            assertAll(
                    () -> assertTrue(testee.canAccessTransporter(picard), "Bridge crew should be able to access transporters"),
                    () -> assertTrue(testee.canAccessTransporter(barclay), "Crew should be able to access transporters"),
                    () -> assertFalse(testee.canAccessTransporter(lwaxana), "Non-crew should not be able to access transporters")
            );
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertAll(
                    () -> assertTrue(testee.canAccessPhasers(picard), "Bridge crew should be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(barclay), "Crew should not be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(lwaxana), "Non-crew should not be able to access phasers")
            );
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
            assertAll(
                    () -> assertTrue(testee.canAccessReplicator(picard), "Bridge crew should be able to access replicators"),
                    () -> assertFalse(testee.canAccessReplicator(barclay), "Crew should be able to access replicators"),
                    () -> assertFalse(testee.canAccessReplicator(lwaxana), "Non-crew should not be able to access replicators")
            );
        }

        @Test
        @DisplayName("only allows bridge crew to access the the transporter")
        void canAccessTransporters() {
            assertAll(
                    () -> assertTrue(testee.canAccessTransporter(picard), "Bridge crew should be able to access transporters"),
                    () -> assertFalse(testee.canAccessTransporter(barclay), "Crew should not be able to access transporters"),
                    () -> assertFalse(testee.canAccessTransporter(lwaxana), "Non-crew should not be able to access transporters")
            );
        }

        @Test
        @DisplayName("only allows bridge crew to access the the phasers")
        void canAccessPhasers() {
            assertAll(
                    () -> assertTrue(testee.canAccessPhasers(picard), "Bridge crew should be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(barclay), "Crew should not be able to access phasers"),
                    () -> assertFalse(testee.canAccessPhasers(lwaxana), "Non-crew should not be able to access phasers")
            );
        }
    }
}