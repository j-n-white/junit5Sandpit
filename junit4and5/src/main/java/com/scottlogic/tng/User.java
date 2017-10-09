package com.scottlogic.tng;

class User {
    private final String name;
    private final UserType userType;

    User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    String getName() {
        return name;
    }

    boolean isCrew() {
        return UserType.CREW.equals(userType) || isBridgeCrew();
    }

    boolean isBridgeCrew() {
        return UserType.BRIDGE_CREW.equals(userType);
    }
}
