package com.bezkoder.spring.jpa.h2.util;

public final class Roles {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_AUTHOR = "ROLE_AUTHOR";

    //    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    //    @PreAuthorize("hasAnyRole('" + Roles.ROLE_ADMIN + "','" + Roles.ROLE_AUTHOR + "')")
    //    @PreAuthorize("hasRole('" + Roles.ROLE_USER + "')")

}