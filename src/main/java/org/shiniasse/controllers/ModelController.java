package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.shiniasse.dtos.ModelDTO;
import org.shiniasse.entities.enums.Category;
import org.shiniasse.services.implementations.BrandServiceImpl;
import org.shiniasse.services.implementations.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/model")
public class ModelController {

    ModelServiceImpl modelService;
    BrandServiceImpl brandService;

    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public String getModels(Model model) {
        model.addAttribute("models", modelService.getAllModels());
        return "model-all";
    }


    @GetMapping("/get/{id}")
    public String getModel(@PathVariable String id, Model model) {
        model.addAttribute("model", modelService.getModel(id));
        return "model-all";
    }

    @GetMapping("/getByBrand/{brandId}")
    public String getAllModelsByBrandId(Model model, @PathVariable String brandId) {
        model.addAttribute("models", modelService.getModelsByBrandId(brandId));
        model.addAttribute("brand", brandService.getBrand(brandId));
        return "model-all";
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("object", "model");
        model.addAttribute("categories", Category.values());
        return "page-add";
    }

    @ModelAttribute("modelDTO")
    public ModelDTO initModel() {
        return new ModelDTO();
    }

    @PostMapping("/add")
    public String addModel(@Valid ModelDTO modelDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (modelDTO.getId() != null){
            redirectAttributes.addFlashAttribute("flag","true");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelDTO", modelDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelDTO",
                    bindingResult);
            return "redirect:/model/add";
        }
        modelService.saveModel(modelDTO);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateModel( @PathVariable String id, Model model) {
        model.addAttribute("object", "model");
        model.addAttribute("modelDTO", modelService.getModel(id));
        model.addAttribute("flag","true");
        model.addAttribute("categories", Category.values());
        return "page-add";
    }

    @GetMapping("/remove/{id}")
    public String removeModel(@PathVariable String id, Model model) {
        modelService.deleteModel(id);
//        model.addAttribute("models", modelService.getAllModels());
        return "redirect:/";
    }
}
