package com.alkemy.java.util;

import com.alkemy.java.exception.NotValidRolException;
import static com.alkemy.java.util.Constants.INVALID_ROL;

public enum Roles {

    USER, ADMIN;

    public static Roles getRol(String rol) throws NotValidRolException {

        switch (rol) {
            case "client":
                return USER;

            case "admin":
                return ADMIN;

            default:
                throw new NotValidRolException(INVALID_ROL);
        }
    }
}
