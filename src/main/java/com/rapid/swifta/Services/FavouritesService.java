package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.Favourites;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavouritesService {

    Favourites createFavourites(Favourites favourites);
    void deleteFavouritesById(Favourites favourites);
    Page<Favourites> getAllFavourites(Integer userId, Pageable pageable);
}
