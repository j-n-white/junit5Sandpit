package com.scottlogic.tng;

/**
 * An interface that might be implemented by all the ships systems to allow diagnostic information to be retrieved.
 */
public interface DiagnosticInfo {

    String getSystemName();
    User getLastUserToAccessSystem();
}
