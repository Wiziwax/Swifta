package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {

    Page<Favourites> getAllByCreatedBy(Integer userId, Pageable pageable);

    boolean existsByCreatedByAndUserFavourite(Integer userId, Integer userFavourite);

    List<Favourites> findAllByCreatedByAndUserFavourite(Integer userId, Integer userFavourite);
}
