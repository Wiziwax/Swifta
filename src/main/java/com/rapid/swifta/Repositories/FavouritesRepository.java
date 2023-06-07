package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
}
