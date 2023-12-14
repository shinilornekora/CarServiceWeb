package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    ModelServiceImpl modelService;
    BrandServiceImpl brandService;

    @Autowired
    public void setModelService(ModelServiceImpl modelService) {
        LOG.log(Level.INFO, "Settings the model service");
        this.modelService = modelService;
    }

    @Autowired
    public void setBrandService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public String getModels(Model model) {
        LOG.log(Level.INFO, "Get all the models");

        model.addAttribute("models", modelService.getAllModels());
        return "model-all";
    }


    @GetMapping("/get/{id}")
    public String getModel(@PathVariable String id, Model model) {
        LOG.log(Level.INFO, "Get model with id " + id);

        model.addAttribute("model", modelService.getModel(id));
        return "model-all";
    }

    @GetMapping("/getByBrand/{brandId}")
    public String getAllModelsByBrandId(Model model, @PathVariable String brandId) {
        LOG.log(Level.INFO, "Get all models by brand " + brandId);

        model.addAttribute("models", modelService.getModelsByBrandId(brandId));
        model.addAttribute("brand", brandService.getBrand(brandId));
        return "model-all";
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        LOG.log(Level.INFO, "Going to the page of adding model");

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
        LOG.log(Level.INFO, "Add model with name " + modelDTO.getName());

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
        LOG.log(Level.INFO, "Update model with id " + id);

        model.addAttribute("object", "model");
        model.addAttribute("modelDTO", modelService.getModel(id));
        model.addAttribute("flag","true");
        model.addAttribute("categories", Category.values());
        return "page-add";
    }

    @GetMapping("/remove/{id}")
    public String removeModel(@PathVariable String id, Model model) {
        LOG.log(Level.INFO, "Remove the model with id " + id);

        modelService.deleteModel(id);
        return "redirect:/";
    }
}
