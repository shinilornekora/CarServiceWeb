package org.shiniasse.services;

import org.shiniasse.dtos.OfferDTO;
import org.shiniasse.entities.Models;
import org.shiniasse.entities.Offer;

import java.util.List;

public interface OfferService<ID> {
    List<OfferDTO> getAllOffers();
    void saveOffer(OfferDTO offerDTO);
    OfferDTO getOffer(ID id);
    void updateOffer(OfferDTO offerDTO);
    void deleteOffer(ID uuid);
    List<OfferDTO> getSortedUpOffers();
    List<OfferDTO> getSortedDownOffers();
}
