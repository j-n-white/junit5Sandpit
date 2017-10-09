package com.scottlogic.tng;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("The USS Enterprise access control")
class AccessControlParameterisedTest {

    private AccessControl testee;
    private static User picard = new User("Jean-luc Picard", UserType.BRIDGE_CREW);
    private static User barclay = new User("Reg Barclay", UserType.CREW);
    private static User lwaxana = new User("Lwaxana Troi", UserType.NON_CREW);
    private static User q = new User("Q", null);

    @BeforeEach
    void setUp() {
        testee = new AccessControl();
    }

    @ParameterizedTest(name = "User {1}, when Alert level is {2} should have access to transporters of {0}")
    @MethodSource("transporterTestProvider")
    void returnsCorrectAccessForTransporter(boolean expected, User user, Alert alertStatus) {
        testee.setAlertStatus(alertStatus);
        assertEquals(expected, testee.canAccessTransporter(user),
                () -> generateFailureMessage("transporter", expected, user, alertStatus));
    }

    private static Stream<Arguments> transporterTestProvider() {
        return Stream.of(
                Arguments.of(true,  picard,  Alert.NONE),
                Arguments.of(true,  barclay, Alert.NONE),
                Arguments.of(false, lwaxana, Alert.NONE),
                Arguments.of(false, q,       Alert.NONE),
                Arguments.of(true,  picard,  Alert.YELLOW),
                Arguments.of(true,  barclay, Alert.YELLOW),
                Arguments.of(false, lwaxana, Alert.YELLOW),
                Arguments.of(false, q,       Alert.YELLOW),
                Arguments.of(true,  picard,  Alert.RED),
                Arguments.of(false, barclay, Alert.RED),
                Arguments.of(false, lwaxana, Alert.RED),
                Arguments.of(false, q,       Alert.RED)
        );
    }

    @ParameterizedTest(name = "User {1}, when Alert level is {2} should have access to replicators of {0}")
    @MethodSource("replicatorTestProvider")
    void returnsCorrectAccessForReplicators(boolean expected, User user, Alert alertStatus) {
        testee.setAlertStatus(alertStatus);
        assertEquals(expected, testee.canAccessReplicator(user),
                () -> generateFailureMessage("replicator", expected, user, alertStatus));
    }

    private static Stream<Arguments> replicatorTestProvider() {
        return Stream.of(
                Arguments.of(true,  picard,  Alert.NONE),
                Arguments.of(true,  barclay, Alert.NONE),
                Arguments.of(true,  lwaxana, Alert.NONE),
                Arguments.of(true,  q,       Alert.NONE),
                Arguments.of(true,  picard,  Alert.YELLOW),
                Arguments.of(true,  barclay, Alert.YELLOW),
                Arguments.of(false, lwaxana, Alert.YELLOW),
                Arguments.of(false, q,       Alert.YELLOW),
                Arguments.of(true,  picard,  Alert.RED),
                Arguments.of(false, barclay, Alert.RED),
                Arguments.of(false, lwaxana, Alert.RED),
                Arguments.of(false, q,       Alert.RED)
        );
    }

    @ParameterizedTest(name = "User {1}, when Alert level is {2} should have access to phasers of {0}")
    @MethodSource("phaserTestProvider")
    void returnsCorrectAccessForPhasers(boolean expected, User user, Alert alertStatus) {
        testee.setAlertStatus(alertStatus);
        assertEquals(expected, testee.canAccessPhasers(user),
                () -> generateFailureMessage("phasers", expected, user, alertStatus));
    }

    private static Stream<Arguments> phaserTestProvider() {
        return Stream.of(
                Arguments.of(true,  picard,  Alert.NONE),
                Arguments.of(false, barclay, Alert.NONE),
                Arguments.of(false, lwaxana, Alert.NONE),
                Arguments.of(false, q,       Alert.NONE),
                Arguments.of(true,  picard,  Alert.YELLOW),
                Arguments.of(false, barclay, Alert.YELLOW),
                Arguments.of(false, lwaxana, Alert.YELLOW),
                Arguments.of(false, q,       Alert.YELLOW),
                Arguments.of(true,  picard,  Alert.RED),
                Arguments.of(false, barclay, Alert.RED),
                Arguments.of(false, lwaxana, Alert.RED),
                Arguments.of(false, q,       Alert.RED)
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

    private String generateFailureMessage(String system, boolean expected, User user, Alert alertStatus) {
        String message = user.getName() + " should";
        if (!expected) {
            message += " not";
        }
        message += " be able to access the " + system + " when alert status is " + alertStatus;
        return message;
    }
}
