package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Repositories.FavouritesRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Favourites createFavourites(Favourites favourites) {
        if (favouritesRepository.existsByCreatedByAndUserFavourite(favourites.getCreatedBy(), favourites.getUserFavourite())){

           favouritesRepository.deleteAll(favouritesRepository.findAllByCreatedByAndUserFavourite(favourites.getCreatedBy(),
                   favourites.getUserFavourite()));
        }

        else {
            return favouritesRepository.save(favourites);
        }
        return favouritesRepository.findById(3).get();
    }

    @Override
    public void deleteFavouritesById(Favourites favourites) {
        favouritesRepository.deleteAll(favouritesRepository.findAllByCreatedByAndUserFavourite(favourites.getCreatedBy(),
                favourites.getUserFavourite()));
    }

    @Override
    public Page<User> getAllFavourites(Integer userId, Pageable pageable) {
        Page<Favourites> favourites =favouritesRepository.getAllByCreatedBy(userId, pageable);
        List<Integer> favouritesLists = new ArrayList<>();

        for(Favourites favourites1: favourites){
            favouritesLists.add(favourites1.getUserFavourite());
        }

        return userRepository.findAll(favouritesLists, pageable);
    }
}
