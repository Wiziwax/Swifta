package com.rapid.swifta.Utils;

import com.rapid.swifta.DTOs.Responses.UserImageResponse;
import com.rapid.swifta.Entities.User;

public class UserUtils {

    public UserImageResponse userImageResponse(User user){
        return UserImageResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .mobile(user.getMobile())
                .image(user.getImage())
                .address(user.getAddress().getHouseNumber()+ ", "+
                        user.getAddress().getStreetName()+", "+
                        user.getAddress().getArea()+", "+
                        user.getAddress().getState()+", "+
                        user.getAddress().getCountry())
//                .serviceType(serviceTypeList(user.getServiceType()))
                .jobDescription(user.getJobDescription())
                .role(user.getRole())
//                .userImage(getAttachment(user.getUserId()))
                .rating(user.getRating())
                .jobCount(user.getJobCount())
                .rateCount(user.getRateCount())
                .isBusy(user.isBusy())
                .verified(user.isVerified()).build();
    }
}
