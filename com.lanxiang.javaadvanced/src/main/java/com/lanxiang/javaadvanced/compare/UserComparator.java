package com.lanxiang.javaadvanced.compare;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class UserComparator implements Comparator<User> {

    private static Collator collator = Collator.getInstance(Locale.CHINESE);

    private int sortType;

    public UserComparator(int sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(User o1, User o2) {
        if (sortType == 1) {
            Integer id1 = o1.getId();
            Integer id2 = o2.getId();
            return id1.compareTo(id2);
//            return id1 > id2 ? 1 : (id1 == id2 ? 0 : -1);
        } else {
            String name1 = o1.getName();
            String name2 = o2.getName();
            return collator.getCollationKey(name1).compareTo(collator.getCollationKey(name2));
        }
    }
}
