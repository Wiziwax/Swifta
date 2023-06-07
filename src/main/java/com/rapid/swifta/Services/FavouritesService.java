package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouritesService {

    Favourites createFavourites(Favourites favourites);
    void deleteFavouritesById(Favourites favourites);
    Page<User> getAllFavourites(Integer userId, Pageable pageable);
}
