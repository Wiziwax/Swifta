package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Repositories.FavouritesRepository;
import com.rapid.swifta.Services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Override
    public Favourites createFavourites(Favourites favourites) {
        return favouritesRepository.save(favourites);
    }

    @Override
    public void deleteFavouritesById(Favourites favourites) {
        favouritesRepository.delete(favourites);
    }

    @Override
    public Page<Favourites> getAllFavourites(Integer userId, Pageable pageable) {
        return favouritesRepository.findAll(pageable);
    }
}
