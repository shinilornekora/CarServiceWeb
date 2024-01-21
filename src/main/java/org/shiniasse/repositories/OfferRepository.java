package org.shiniasse.repositories;

import org.shiniasse.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String > {
    @Query("SELECT f FROM Offer f ORDER BY f.price ASC LIMIT 3")
    List<Offer> getSortedUpOffers();
    @Query("SELECT f FROM Offer f ORDER BY f.price DESC LIMIT 3")
    List<Offer> getSortedDownOffers();
}
