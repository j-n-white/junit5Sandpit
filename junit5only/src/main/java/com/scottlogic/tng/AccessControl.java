package com.scottlogic.tng;

class AccessControl implements DiagnosticInfo {

    private Alert alertStatus = Alert.NONE;
    private User lastUserToAccessSystem;

    boolean canAccessReplicator(User user) {
        lastUserToAccessSystem = user;
        if (Alert.NONE.equals(alertStatus)) {
            return true;
        } else if (Alert.YELLOW.equals(alertStatus)) {
            return user.isCrew();
        } else {
            return user.isBridgeCrew();
        }
    }

    boolean canAccessTransporter(User user) {
        lastUserToAccessSystem = user;
        if (Alert.RED.equals(alertStatus)) {
            return user.isBridgeCrew();
        } else {
            return user.isCrew();
        }
    }

    boolean canAccessPhasers(User user) {
        lastUserToAccessSystem = user;
        return user.isBridgeCrew();
    }

    void setAlertStatus(Alert alertStatus) {
        this.alertStatus = alertStatus;
    }

    Alert getAlertStatus() {
        return alertStatus;
    }

    @Override
    public String getSystemName() {
        return "Access Control";
    }

    @Override
    public User getLastUserToAccessSystem() {
        return lastUserToAccessSystem;
    }
}
