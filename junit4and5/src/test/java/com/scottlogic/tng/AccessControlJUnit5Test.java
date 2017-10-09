package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void nonCrewCanNotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessReplicatorsWhenYellowAlert() {
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
    void crewCanNotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(barclay));
    }

    @Test
    void nonCrewCanNotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessReplicatorsWhenRedAlert() {
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
    void nonCrewCanNotAccessTransportersWhenNoAlertLevel() {
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessTransportersWhenNoAlertLevel() {
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
    void nonCrewCanNotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessTransportersWhenYellowAlert() {
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
    void crewCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(barclay));
    }

    @Test
    void nonCrewCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessPhasersWhenNoAlertLevel() {
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    void crewCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(q),
                "User with null user type treated as non crew");
    }

    @Test
    void bridgeCrewCanAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    void crewCanNotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCanNotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessPhasersWhenYellowAlert() {
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
    void crewCanNotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    void nonCrewCanNotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    void userWithNullUserTypeCanNotAccessPhasersWhenRedAlert() {
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