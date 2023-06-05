package com.rapid.swifta.UserProps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.rapid.swifta.UserProps.Permission.*;


@RequiredArgsConstructor
public enum Role {


    ADMIN(Set.of(ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE, ADMIN_CREATE, MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE)),
    MERCHANT(Set.of(MERCHANT_READ,MERCHANT_UPDATE,MERCHANT_CREATE,MERCHANT_DELETE)),
    CLIENT(Set.of(CLIENT_READ,CLIENT_UPDATE,CLIENT_DELETE,CLIENT_CREATE)),
    DEVELOPER(Set.of(DEVELOPER_CREATE,DEVELOPER_READ,DEVELOPER_UPDATE,DEVELOPER_DELETE)),
    MANAGER(Set.of(MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE, MANAGER_CREATE));

    @Getter
    private final Set<Permission> permissions;

//    public List<SimpleGrantedAuthority> getAuthorities(){
//      var authorities  = getPermissions()
//              .stream()
//              .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
//
//      authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
//      return authorities;
//    }


    public static Role fromNumericValue(int numericValue) {
        return switch (numericValue) {
            case 1 -> ADMIN;
            case 2 -> MERCHANT;
            case 3 -> CLIENT;
            case 4 -> DEVELOPER;
            case 5 -> MANAGER;
            default -> throw new IllegalArgumentException("Invalid numeric value: " + numericValue);
        };
    } }
