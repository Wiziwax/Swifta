package com.rapid.swifta.DTOs.RequestBodies;

import lombok.Data;

@Data
public class FavouritesRequestBody {

    private Integer createdBy;
    private Integer userFavourite;
}
