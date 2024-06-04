package pract.oop_java.pms.v1.utils;

import pract.oop_java.pms.v1.enums.EVisibility;
import pract.oop_java.pms.v1.models.User;

public class ServiceUtils {
    // method to check if a user is valid or deleted
    public static boolean isUserDeleted(User user) {
        return user.getVisibility().equals(EVisibility.VOIDED);
    }
}
