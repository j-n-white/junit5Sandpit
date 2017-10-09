package com.scottlogic.tng;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("userType", userType)
                .build();
    }
}
