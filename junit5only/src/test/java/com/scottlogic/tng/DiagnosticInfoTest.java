package com.scottlogic.tng;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public interface DiagnosticInfoTest<T extends DiagnosticInfo> {

    T getInstance();
    String getExpectedSystemName();
    void accessWithUser(User user);

    @Test
    @DisplayName("returns the system name.")
    default void returnsSystemName() {
        assertEquals(getExpectedSystemName(), getInstance().getSystemName());
    }

    @Test
    @DisplayName("returns the last user who accessed the system.")
    default void returnsLastUserToAccessSystem() {
        User riker = new User("William Riker", UserType.BRIDGE_CREW);
        accessWithUser(riker);
        assertEquals(riker, getInstance().getLastUserToAccessSystem());
    }
}
