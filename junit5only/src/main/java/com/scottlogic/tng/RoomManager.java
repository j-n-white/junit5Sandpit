package com.scottlogic.tng;

import java.util.ArrayList;
import java.util.List;

class RoomManager {

    private final List<Quarters> unallocatedQuarters = new ArrayList<>();

    RoomManager(List<Quarters> unallocatedQuarters) {
        this.unallocatedQuarters.addAll(unallocatedQuarters);
    }

    void allocateQuarters(User user) {
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
}
