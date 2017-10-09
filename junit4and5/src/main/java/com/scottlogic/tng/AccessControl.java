package com.scottlogic.tng;

class AccessControl {

    private Alert alertStatus = Alert.NONE;

    boolean canAccessReplicator(User user) {
        if (Alert.NONE.equals(alertStatus)) {
            return true;
        } else if (Alert.YELLOW.equals(alertStatus)) {
            return user.isCrew();
        } else {
            return user.isBridgeCrew();
        }
    }

    boolean canAccessTransporter(User user) {
        if (Alert.RED.equals(alertStatus)) {
            return user.isBridgeCrew();
        } else {
            return user.isCrew();
        }
    }

    boolean canAccessPhasers(User user) {
        return user.isBridgeCrew();
    }

    void setAlertStatus(Alert alertStatus) {
        if (alertStatus == null) {
            throw new IllegalArgumentException("Alert status cannot be set to null.");
        }
        this.alertStatus = alertStatus;
    }

    Alert getAlertStatus() {
        return alertStatus;
    }
}
