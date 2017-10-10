package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A JUnit 5 test created by converting the following JUnit 4 test {@link AccessControlJUnit4Test}
 */
class AccessControlJUnit5Test {

    private AccessControl testee;
    private User picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
    private User barclay = new User("Reg Barclay", UserType.CREW);
    private User lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
    private User q = new User("Q", null);

    @BeforeEach
    void setUp() {
        testee = new AccessControl();
    }

    @Test
    void bridgeCrewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    void crewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(barclay));
    }

    @Test
    void nonCrewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    void crewCanAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessReplicator(barclay));
    }

    @Test
    void nonCrewCannotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessReplicator(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessReplicatorsWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    void crewCannotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(barclay));
    }

    @Test
    void nonCrewCannotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessTransportersWhenNoAlertLevel() {
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    void crewCanAccessTransportersWhenNoAlertLevel() {
        assertTrue(testee.canAccessTransporter(barclay));
    }

    @Test
    void nonCrewCannotAccessTransportersWhenNoAlertLevel() {
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessTransportersWhenNoAlertLevel() {
        assertFalse(testee.canAccessTransporter(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    void crewCanAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessTransporter(barclay));
    }

    @Test
    void nonCrewCannotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessTransporter(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessTransportersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    void crewCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(barclay));
    }

    @Test
    void nonCrewCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessPhasersWhenNoAlertLevel() {
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    void crewCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    void crewCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessPhasersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    void crewCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(q),
                "User with null user type treated as non crew");
    }

    @Test
    void exceptionThrownIfTryToSetAlertStatusToNull() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> testee.setAlertStatus(null));
        assertEquals("Alert status cannot be set to null.", actual.getMessage());
    }
}