package org.shiniasse.services.implementations;

import org.modelmapper.ModelMapper;
import org.shiniasse.dtos.OfferDTO;
import org.shiniasse.entities.Models;
import org.shiniasse.entities.Offer;
import org.shiniasse.repositories.OfferRepository;
import org.shiniasse.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EnableCaching
@Service
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }
    @Cacheable("offers")
    @Override
    public List<OfferDTO> getAllOffers() {
        try {
            System.out.println(offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList()));
            return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
        } catch (DataAccessException e) {
            System.out.println("Failed to get " + e.getMessage());
        }
        return null;
    }
    @Cacheable("offers")
    @Override
    public void saveOffer(OfferDTO offerDTO) {
        try {
            offerRepository.saveAndFlush(modelMapper.map(offerDTO, Offer.class));
        } catch (DataAccessException e) {
            System.out.println("Failed to save offer " + e.getMessage());
        }
    }

    @Cacheable("offers")
    @Override
    public OfferDTO getOffer(String uuid) {
        try {
            return modelMapper.map(offerRepository.findById(uuid), OfferDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object with id " + uuid + " could not be found");
        }
    }

    @CacheEvict(cacheNames = "offers", allEntries = true)
    @Override
    public void updateOffer(OfferDTO offerDTO) {
        saveOffer(offerDTO);
    }

    @CacheEvict(cacheNames = "offers", allEntries = true)
    @Override
    public void deleteOffer(String uuid) {
        offerRepository.deleteById(uuid);
    }

    @Cacheable("offers")
    @Override
    public List<OfferDTO> getSortedUpOffers() {
        return offerRepository.getSortedUpOffers()
                .stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }

    @Cacheable("offers")
    @Override
    public List<OfferDTO> getSortedDownOffers() {
        return offerRepository.getSortedDownOffers()
                .stream().map(offer -> modelMapper.map(offer, OfferDTO.class)).collect(Collectors.toList());
    }
}
