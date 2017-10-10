package com.scottlogic.tng;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit 4 test for {@link AccessControl}
 */
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
    public void nonCrewCannotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessReplicatorsWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessReplicator(q));
    }

    @Test
    public void bridgeCrewCanAccessReplicatorsWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessReplicator(picard));
    }

    @Test
    public void crewCannotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(barclay));
    }

    @Test
    public void nonCrewCannotAccessReplicatorsWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessReplicator(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessReplicatorsWhenRedAlert() {
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
    public void nonCrewCannotAccessTransportersWhenNoAlertLevel() {
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessTransportersWhenNoAlertLevel() {
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
    public void nonCrewCannotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessTransportersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessTransporter(q));
    }

    @Test
    public void bridgeCrewCanAccessTransportersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessTransporter(picard));
    }

    @Test
    public void crewCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(barclay));
    }

    @Test
    public void nonCrewCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessTransporter(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessTransportersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse("User with null user type treated as non crew", testee.canAccessTransporter(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenNoAlertLevel() {
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessPhasersWhenNoAlertLevel() {
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessPhasersWhenYellowAlert() {
        testee.setAlertStatus(Alert.YELLOW);
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test
    public void bridgeCrewCanAccessPhasersWhenRedLevel() {
        testee.setAlertStatus(Alert.RED);
        assertTrue(testee.canAccessPhasers(picard));
    }

    @Test
    public void crewCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(barclay));
    }

    @Test
    public void nonCrewCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse(testee.canAccessPhasers(lwaxana));
    }

    @Test
    public void userWithNullUserTypeCannotAccessPhasersWhenRedAlert() {
        testee.setAlertStatus(Alert.RED);
        assertFalse("User with null user type treated as non crew", testee.canAccessPhasers(q));
    }

    @Test (expected = IllegalArgumentException.class)
    public void exceptionThrownIfTryToSetAlertStatusToNull() {
        try {
            testee.setAlertStatus(null);
            fail("Exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Alert status cannot be set to null.", e.getMessage());
            throw e;
        }

    }
}