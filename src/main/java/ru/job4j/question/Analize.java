package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<User, Integer> tempMap = new HashMap<>();
        Iterator<User> previousIterator = previous.iterator();
        Iterator<User> currentIterator = current.iterator();
        while (previousIterator.hasNext()) {
            User tempUser = previousIterator.next();
            tempMap.put(tempUser, tempUser.getId());
        }
        while (currentIterator.hasNext()) {
            User tempUser = currentIterator.next();
            if (tempMap.containsKey(tempUser)) {
                tempMap.remove(tempUser);
                continue;
            }
            if (tempMap.containsValue(tempUser.getId())) {
                int changed = info.getChanged();
                info.setChanged(++changed);
                continue;
            }
            int added = info.getAdded();
            info.setAdded(++added);
        }
        info.setDeleted(tempMap.size() - info.getChanged());
        return info;
    }
}
