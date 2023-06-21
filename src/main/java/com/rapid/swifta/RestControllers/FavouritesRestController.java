package com.rapid.swifta.RestControllers;

import com.rapid.swifta.DTOs.RequestBodies.FavouritesRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.InnerService.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favourites")
public class FavouritesRestController {

    @Autowired
    private FavouritesService favouritesService;



    @PostMapping("add")
    public Favourites createFavourites(@RequestBody FavouritesRequestBody favourites){
        return favouritesService.createFavourites(favourites);
    }

    @GetMapping("getall")
    public Page<UserResponse> getAll(@RequestParam Integer userId, Pageable pageable){
        return favouritesService.getAllFavourites(userId, pageable);
    }

    @DeleteMapping("remove")
    public void removeFavourites(@RequestBody Favourites favourites){
        favouritesService.deleteFavouritesById(favourites);
    }

}
