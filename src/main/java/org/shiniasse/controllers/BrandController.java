package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shiniasse.dtos.BrandDTO;
import org.shiniasse.services.implementations.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    BrandServiceImpl brandService;
    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        LOG.log(Level.INFO, "Setting brand service");
        this.brandService = brandService;
    }

    @GetMapping("")
    public String getBrands(Model model, Principal principal) {
        if (principal != null) {
            LOG.log(Level.INFO, "Get all brands for user " + principal.getName());
        }
        model.addAttribute("brands", brandService.getAllBrands());
        return "brand-all";
    }
    @GetMapping("/get/{id}")
    public String getBrand(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Get brand of id " + id);
        model.addAttribute("brand", brandService.getBrand(id));
        return "brand-all";
    }

    @GetMapping("/add")
    public String addBrand(Model model, Principal principal) {
        LOG.log(Level.INFO, "Adding brand process started by user " + principal.getName());

        model.addAttribute("object", "brand");
        return "page-add";
    }

    @ModelAttribute("brandDTO")
    public BrandDTO initBrand() {
        return new BrandDTO();
    }
    @PostMapping("/add")
    public String addBrand(@Valid BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        LOG.log(Level.INFO, "Adding brand with name " + brandDTO.getName() + " by " + principal.getName());

        if (brandDTO.getId() != null){
            redirectAttributes.addFlashAttribute("flag","true");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandDTO", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.saveBrand(brandDTO);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateBrand(Model model, @PathVariable String id){
        LOG.log(Level.INFO, "Updating the brand with id " + id);

        model.addAttribute("object", "brand");
        model.addAttribute("brandDTO", brandService.getBrand(id));
        model.addAttribute("flag","true");
        return "page-add";
    }

    @GetMapping("/remove/{id}")
    public String removeBrand(@PathVariable String id, Model model, Principal principal){
        LOG.log(Level.INFO, "Removing brand with id " + id + " by user " + principal.getName());
        brandService.deleteBrand(id);

        return "redirect:/";
    }
}
