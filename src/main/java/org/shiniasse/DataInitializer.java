package org.shiniasse;

import org.shiniasse.dtos.*;
import org.shiniasse.entities.enums.Category;
import org.shiniasse.entities.enums.Engine;
import org.shiniasse.entities.enums.Role;
import org.shiniasse.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService<String> brandService;
    private final ModelService<String> modelService;
    private final OfferService<String> offerService;
    private final UserService<String> userService;

    @Autowired
    public DataInitializer(BrandService brandService, ModelService modelService,
                           OfferService offerService, UserService userService,
                           UserRoleService userRoleService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BrandDTO brandDTO1 = new BrandDTO();
        brandDTO1.setName("Exelero");
//        получаем сохранённый бренд обратно уже с id для дальшейших операций
        brandDTO1 = brandService.saveAndGetBrand(brandDTO1);

        BrandDTO brandDTO2 = new BrandDTO();
        brandDTO2.setName("Vortex");
        brandDTO2 = brandService.saveAndGetBrand(brandDTO2);

        ModelDTO modelDTO1 = new ModelDTO();
        modelDTO1.setName("Midiat");
        modelDTO1.setCategory(Category.valueOf("Car"));
        modelDTO1.setStartYear(2016);
        modelDTO1.setEndYear(2020);
        modelDTO1.setBrand(brandDTO1);
        modelDTO1 = modelService.saveAndGetModel(modelDTO1);

        ModelDTO modelDTO2 = new ModelDTO();
        modelDTO2.setName("Soft-xp");
        modelDTO2.setCategory(Category.valueOf("Car"));
        modelDTO2.setStartYear(2016);
        modelDTO2.setEndYear(2020);
        modelDTO2.setBrand(brandDTO2);
        modelDTO2 = modelService.saveAndGetModel(modelDTO2);

        ModelDTO modelDTO3 = new ModelDTO();
        modelDTO3.setName("Hard-pv");
        modelDTO3.setCategory(Category.valueOf("Car"));
        modelDTO3.setStartYear(2016);
        modelDTO3.setEndYear(2020);
        modelDTO3.setBrand(brandDTO2);
        modelDTO3 = modelService.saveAndGetModel(modelDTO3);

        UserRoleDTO userRoleDTO1 = new UserRoleDTO();
        userRoleDTO1.setRole(Role.valueOf("Admin"));

        UserRoleDTO userRoleDTO2 = new UserRoleDTO();
        userRoleDTO2.setRole(Role.valueOf("User"));

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setUsername("Mik");
        userDTO1.setPassword("123456");
        userDTO1.setFirstName("Miki");
        userDTO1.setLastName("Mouse");
        userDTO1.setActive(false);
        userDTO1.setRole(userRoleDTO2);
        userDTO1 = userService.saveAndGetUser(userDTO1);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUsername("Jack");
        userDTO2.setPassword("yhnbvhj");
        userDTO2.setFirstName("Jack");
        userDTO2.setLastName("Frost");
        userDTO2.setActive(true);
        userDTO2.setRole(userRoleDTO1);
        userDTO2 = userService.saveAndGetUser(userDTO2);

        // Создание начальных данных для Offer
        OfferDTO offer1 = new OfferDTO();
        offer1.setMileage(600);
        offer1.setPrice(600_500.0);
        offer1.setEngine(Engine.DIESEL);
        offer1.setYear(2017);
        offer1.setModel(modelDTO1);
        offer1.setSeller(userDTO1);
        offer1.setDescription("seme text");
        offerService.saveOffer(offer1);

        OfferDTO offer2 = new OfferDTO();
        offer2.setMileage(5000);
        offer2.setPrice(756_500.0);
        offer2.setEngine(Engine.DIESEL);
        offer2.setYear(2018);
        offer2.setModel(modelDTO2);
        offer2.setSeller(userDTO2);
        offer2.setDescription("some text");
        offerService.saveOffer(offer2);

        System.out.println(brandDTO1);

    }
}
