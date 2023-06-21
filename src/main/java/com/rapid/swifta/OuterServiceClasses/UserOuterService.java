package com.rapid.swifta.OuterServiceClasses;

import com.querydsl.core.BooleanBuilder;
import com.rapid.swifta.Entities.QUser;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.InnerService.UserService;
import com.rapid.swifta.UserProps.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserOuterService {

    @Autowired
    private UserService userService;

    public Page<User> getByFirstNameAndLastName(String firstName, Pageable pageable){
        QUser qUser = QUser.user;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.role.eq(Role.fromNumericValue(3)));
        booleanBuilder.and(qUser.firstName.eq(firstName));

        return userService.findByFirstAndLastName(booleanBuilder, pageable);
    }
}
