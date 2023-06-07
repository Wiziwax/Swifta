package com.rapid.swifta.RestControllers;

import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favourites")
public class FavouritesRestController {

    @Autowired
    private FavouritesService favouritesService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public Favourites createFavourites(@RequestBody Favourites favourites){
        return favouritesService.createFavourites(favourites);
    }

    @GetMapping("")
    public Page<Favourites> getAll(@RequestParam Integer userId, Pageable pageable){
        return favouritesService.getAllFavourites(userId, pageable);
    }

    @DeleteMapping("")
    public void removeFavourites(@RequestBody Favourites favourites){
        favouritesService.deleteFavouritesById(favourites);
    }

}
