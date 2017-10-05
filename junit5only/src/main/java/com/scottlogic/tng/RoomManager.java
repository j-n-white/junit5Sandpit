package com.scottlogic.tng;

import java.util.ArrayList;
import java.util.List;

class RoomManager implements DiagnosticInfo{

    private final List<Quarters> unallocatedQuarters = new ArrayList<>();
    private User lastUserToAccessSystem;

    RoomManager(List<Quarters> unallocatedQuarters) {
        this.unallocatedQuarters.addAll(unallocatedQuarters);
    }

    void allocateQuarters(User user) {
        lastUserToAccessSystem = user;
        if (user.getQuarters() == null) {
            if (unallocatedQuarters.isEmpty()) {
                throw new IllegalStateException("Unable to allocate quarters for " + user.getName());
            }
            user.setQuarters(unallocatedQuarters.get(0));
            unallocatedQuarters.remove(0);
        }
    }

    List<Quarters> getUnallocatedQuarters() {
        return unallocatedQuarters;
    }

    @Override
    public String getSystemName() {
        return "Room Manager";
    }

    @Override
    public User getLastUserToAccessSystem() {
        return lastUserToAccessSystem;
    }
}
