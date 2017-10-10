package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A refactored version of {@link AccessControlGroupedAssertionTest} to show an alternate way the assertions could be
 * grouped.
 */
@DisplayName("The USS Enterprise access control")
class AccessControlAlternateGroupedAssertionTest {

    private AccessControl testee;
    private static User picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
    private static User barclay = new User("Reg Barclay", UserType.CREW);
    private static User lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
    private static User q = new User("Q", null);

    @BeforeEach
    void setUp() {
        testee = new AccessControl();
    }

    @Test
    @DisplayName("grants correct access to transporters")
    void correctAccessToTransporterIsGranted() {
        assertAll(
                () -> assertTransporterAccess(true,  picard,  Alert.NONE),
                () -> assertTransporterAccess(true,  barclay, Alert.NONE),
                () -> assertTransporterAccess(false, lwaxana, Alert.NONE),
                () -> assertTransporterAccess(false, q,       Alert.NONE),
                () -> assertTransporterAccess(true,  picard,  Alert.YELLOW),
                () -> assertTransporterAccess(true,  barclay, Alert.YELLOW),
                () -> assertTransporterAccess(false, lwaxana, Alert.YELLOW),
                () -> assertTransporterAccess(false, q,       Alert.YELLOW),
                () -> assertTransporterAccess(true,  picard,  Alert.RED),
                () -> assertTransporterAccess(false, barclay, Alert.RED),
                () -> assertTransporterAccess(false, lwaxana, Alert.RED),
                () -> assertTransporterAccess(false, q,       Alert.RED)
        );
    }
    @Test
    @DisplayName("grants correct access to replicators")
    void correctAccessToReplicatorGranted() {
        assertAll(
                () -> assertReplicatorAccess(true,  picard,  Alert.NONE),
                () -> assertReplicatorAccess(true,  barclay, Alert.NONE),
                () -> assertReplicatorAccess(true,  lwaxana, Alert.NONE),
                () -> assertReplicatorAccess(true,  q,       Alert.NONE),
                () -> assertReplicatorAccess(true,  picard,  Alert.YELLOW),
                () -> assertReplicatorAccess(true,  barclay, Alert.YELLOW),
                () -> assertReplicatorAccess(false, lwaxana, Alert.YELLOW),
                () -> assertReplicatorAccess(false, q,       Alert.YELLOW),
                () -> assertReplicatorAccess(true,  picard,  Alert.RED),
                () -> assertReplicatorAccess(false, barclay, Alert.RED),
                () -> assertReplicatorAccess(false, lwaxana, Alert.RED),
                () -> assertReplicatorAccess(false, q,       Alert.RED)
        );
    }

    @Test
    @DisplayName("grants correct access to phasers")
    void correctAccessToPhasersGranted() {
        assertAll(
                () -> assertPhaserAccess(true,  picard,  Alert.NONE),
                () -> assertPhaserAccess(false, barclay, Alert.NONE),
                () -> assertPhaserAccess(false, lwaxana, Alert.NONE),
                () -> assertPhaserAccess(false, q,       Alert.NONE),
                () -> assertPhaserAccess(true,  picard,  Alert.YELLOW),
                () -> assertPhaserAccess(false, barclay, Alert.YELLOW),
                () -> assertPhaserAccess(false, lwaxana, Alert.YELLOW),
                () -> assertPhaserAccess(false, q,       Alert.YELLOW),
                () -> assertPhaserAccess(true,  picard,  Alert.RED),
                () -> assertPhaserAccess(false, barclay, Alert.RED),
                () -> assertPhaserAccess(false, lwaxana, Alert.RED),
                () -> assertPhaserAccess(false, q,       Alert.RED)
        );
    }

    @Test
    @DisplayName("starts with no alert status")
    void startsWithNoAlert() {
        assertEquals(Alert.NONE, testee.getAlertStatus());
    }

    @Test
    @DisplayName("throws an exception when attempt is made to set alert status to null")
    void exceptionThrownIfTryToSetAlertStatusToNull() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> testee.setAlertStatus(null));
        assertEquals("Alert status cannot be set to null.", actual.getMessage());
    }

    private void assertTransporterAccess(boolean expected, User user, Alert alertStatus) {
        assertSystemAccess(expected, () -> testee.canAccessTransporter(user), user, alertStatus, "transporter");
    }

    private void assertReplicatorAccess(boolean expected, User user, Alert alertStatus) {
        assertSystemAccess(expected, () -> testee.canAccessReplicator(user), user, alertStatus, "replicator");
    }

    private void assertPhaserAccess(boolean expected, User user, Alert alertStatus) {
        assertSystemAccess(expected, () -> testee.canAccessPhasers(user), user, alertStatus, "phasers");
    }

    private void assertSystemAccess(boolean expected, Supplier<Boolean> callToService, User user, Alert alertStatus, String system) {
        testee.setAlertStatus(alertStatus);
        assertEquals(expected, callToService.get(),
                () -> generateFailureMessage(system, expected, user, alertStatus));
    }

    private String generateFailureMessage(String system, boolean expected, User user, Alert alertStatus) {
        String message = user.getName() + " should";
        if (!expected) {
            message += " not";
        }
        message += " be able to access the " + system + " when alert status is " + alertStatus;
        return message;
    }
}
