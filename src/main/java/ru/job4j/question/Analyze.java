package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> map = new HashMap<>();
        previous.forEach(user -> map.put(user.getId(), user.getName()));
        for (User user : current) {
            String rsl = map.get(user.getId());
            if (rsl == null) {
                added++;
            } else if (!rsl.equals(user.getName())) {
                changed++;
            }
            map.remove(user.getId());
        }
        return new Info(added, changed, map.size());
    }
}
