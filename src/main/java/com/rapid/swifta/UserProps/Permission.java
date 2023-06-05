package com.rapid.swifta.UserProps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),


    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete"),


    DEVELOPER_READ("developer:read"),
    DEVELOPER_UPDATE("developer:update"),
    DEVELOPER_CREATE("developer:create"),
    DEVELOPER_DELETE("developer:delete"),


    MERCHANT_READ("MERCHANT:read"),
    MERCHANT_UPDATE("MERCHANT:update"),
    MERCHANT_CREATE("MERCHANT:create"),
    MERCHANT_DELETE("MERCHANT:delete"),


    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");

    @Getter
    private final String permission;

}
