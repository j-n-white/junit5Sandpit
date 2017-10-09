package com.scottlogic.tng;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AccessControlJUnit4Test {

    private AccessControl testee;
    private User picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
    private User barclay = new User("Reg Barclay", UserType.CREW);
    private User lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
    private User q = new User("Q", null);

    @Before
    public void setUp() {
        testee = new AccessControl();
    }

    @Test
    public void bridgeCrewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    public void crewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(barclay));
    }

    @Test
    public void nonCrewCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue(testee.canAccessReplicator(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanAccessReplicatorsWhenNoAlertLevel() {
        assertTrue("User with null user type treated as non crew", testee.canAccessReplicator(q));
    }

    @Test
    public void bridgeCrewCanAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    public void crewCanAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessReplicator(barclay));
    }

    @Test
    public void nonCrewCanNotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessReplicator(q));
    }

    @Test
    public void bridgeCrewCanAccessReplicatorsWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    public void crewCanNotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(barclay));
    }

    @Test
    public void nonCrewCanNotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse("User with null user type treated as non crew", testee.canAccessReplicator(q));
    }

    @Test
    public void bridgeCrewCanAccessTransportersWhenNoAlertLevel() {
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    public void crewCanAccessTransportersWhenNoAlertLevel() {
        assertTrue(testee.canAccessTransporter(barclay));
    }

    @Test
    public void nonCrewCanNotAccessTransportersWhenNoAlertLevel() {
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessTransportersWhenNoAlertLevel() {
        assertFalse("User with null user type treated as non crew", testee.canAccessTransporter(q));
    }

    @Test
    public void bridgeCrewCanAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    public void crewCanAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessTransporter(barclay));
    }

    @Test
    public void nonCrewCanNotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessTransporter(q));
    }

    @Test
    public void bridgeCrewCanAccessTransportersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    public void crewCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(barclay));
    }

    @Test
    public void nonCrewCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse("User with null user type treated as non crew", testee.canAccessTransporter(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenNoAlertLevel() {
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessPhasersWhenNoAlertLevel() {
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCanNotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCanNotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCanNotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCanNotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCanNotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test (expected = IllegalArgumentException.class)
    public void exceptionThrownIfTryToSetAlertStatusToNull() {
        try {
            testee.setAlertStatus(null);
            fail("Exception shoudl have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Alert status cannot be set to null.", e.getMessage());
            throw e;
        }

    }
}