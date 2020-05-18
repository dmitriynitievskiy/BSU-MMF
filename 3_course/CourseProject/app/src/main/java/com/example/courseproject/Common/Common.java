package com.example.courseproject.Common;

import com.example.courseproject.Model.User;

public class Common {
    public static User currentUser;

    public Common() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Common.currentUser = currentUser;
    }
}
