package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shiniasse.dtos.OfferDTO;
import org.shiniasse.entities.enums.Engine;
import org.shiniasse.entities.enums.Transmission;
import org.shiniasse.services.implementations.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    OfferServiceImpl offerService;
    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        LOG.log(Level.INFO, "Settings the offer service");

        this.offerService = offerService;
    }

    @GetMapping("")
    public String getOffers(Model model){
        LOG.log(Level.INFO, "Get all offers");

        model.addAttribute("offers", offerService.getAllOffers());
        return "offer-all";
    }
    @GetMapping("/getPriceUp")
    public String getOfferPriceUp(Model model) {
        LOG.log(Level.INFO, "Get all offers in sorted up top-3 format");

        model.addAttribute("offers", offerService.getSortedUpOffers());
        return "offer-all";
    }
    @GetMapping("/getPriceDown")
    public String getOfferPriceDown(Model model) {
        LOG.log(Level.INFO, "Get all offers in sorted down top-3 format");

        model.addAttribute("offers", offerService.getSortedDownOffers());
        return "offer-all";
    }

    @GetMapping("/get/{id}")
    public String getOffer(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Get offer with id " + id);

        model.addAttribute("offer", offerService.getOffer(id));
        return "offer-all";
    }

    @GetMapping("/add")
    public String addOffer(Model model){
        LOG.log(Level.INFO, "Add offer");

        model.addAttribute("object", "offer");
        model.addAttribute("engine", Engine.values());
        model.addAttribute("transmission", Transmission.values());
        return "page-add";
    }
    @ModelAttribute("offerDTO")
    public OfferDTO initOffer() {
        return new OfferDTO();
    }
    @PostMapping("/add")
    public String addOffer(@Valid OfferDTO offerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        LOG.log(Level.INFO, "Add offer with price " + offerDTO.getPrice());

        if (offerDTO.getId() != null){
            redirectAttributes.addFlashAttribute("flag","true");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDTO", offerDTO);
            redirectAttributes.addAttribute("engine", Engine.values());
            redirectAttributes.addAttribute("transmission", Transmission.values());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO",
                    bindingResult);
            return "redirect:/offer/add";
        }
        offerService.saveOffer(offerDTO);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateOffer(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Update offer with id " + id);

        model.addAttribute("object", "offer");
        model.addAttribute("offerDTO", offerService.getOffer(id));
        model.addAttribute("flag","true");
        model.addAttribute("engine", Engine.values());
        model.addAttribute("transmission", Transmission.values());
        return "page-add";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Remove offer with id " + id);

        offerService.deleteOffer(id);
        return "redirect:/";
    }
}
