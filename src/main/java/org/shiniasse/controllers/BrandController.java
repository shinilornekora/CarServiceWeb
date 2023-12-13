package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.shiniasse.dtos.BrandDTO;
import org.shiniasse.services.implementations.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandController {
    BrandServiceImpl brandService;
    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public String getBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brand-all";
    }
    @GetMapping("/get/{id}")
    public String getBrand(@PathVariable String id, Model model){
        model.addAttribute("brand", brandService.getBrand(id));
        return "brand-all";
    }

    @GetMapping("/add")
    public String addBrand(Model model){
        model.addAttribute("object", "brand");
        return "page-add";
    }

    @ModelAttribute("brandDTO")
    public BrandDTO initBrand() {
        return new BrandDTO();
    }
    @PostMapping("/add")
    public String addBrand(@Valid BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
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
        model.addAttribute("object", "brand");
        model.addAttribute("brandDTO", brandService.getBrand(id));
        model.addAttribute("flag","true");
        return "page-add";
    }

    @GetMapping("/remove/{id}")
    public String removeBrand(@PathVariable String id, Model model){
        brandService.deleteBrand(id);
//        model.addAttribute("brands", brandService.getAllBrands());
        return "redirect:/";
    }
}
