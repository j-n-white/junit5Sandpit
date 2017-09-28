package com.scottlogic.tng;

class User {
    private final String name;
    private final UserType userType;
    private Quarters quarters;

    User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    String getName() {
        return name;
    }

    void setQuarters(Quarters quarters) {
        this.quarters = quarters;
    }

    Quarters getQuarters() {
        return quarters;
    }

    boolean isCrew() {
        return UserType.CREW.equals(userType) || isBridgeCrew();
    }

    boolean isBridgeCrew() {
        return UserType.BRIDGE_CREW.equals(userType);
    }
}
