package com.rapid.swifta.Services;

import com.rapid.swifta.DTOs.RequestBodies.FavouritesRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Favourites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouritesService {

    Favourites createFavourites(FavouritesRequestBody favourites);
    void deleteFavouritesById(Favourites favourites);
    Page<UserResponse> getAllFavourites(Integer userId, Pageable pageable);
}
