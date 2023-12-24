package org.shiniasse;

import org.shiniasse.dtos.*;
import org.shiniasse.entities.enums.Category;
import org.shiniasse.entities.enums.Engine;
import org.shiniasse.entities.enums.Role;
import org.shiniasse.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService<String> brandService;
    private final ModelService<String> modelService;
    private final OfferService<String> offerService;
    private final UserService<String> userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(BrandService<String> brandService, ModelService<String> modelService,
                           OfferService<String> offerService, UserService<String> userService,
                           UserRoleService<String> userRoleService, PasswordEncoder passwordEncoder) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        BrandDTO brandDTO1 = new BrandDTO();
        BrandDTO brandDTO2 = new BrandDTO();
        BrandDTO brandDTO3 = new BrandDTO();
        BrandDTO brandDTO4 = new BrandDTO();
        BrandDTO brandDTO5 = new BrandDTO();
        BrandDTO brandDTO6 = new BrandDTO();
        BrandDTO brandDTO7 = new BrandDTO();
        brandDTO1.setName("Bmw");
        brandDTO2.setName("Kia");
        brandDTO3.setName("Audi");
        brandDTO4.setName("Lada");
        brandDTO5.setName("Chevrolet");
        brandDTO6.setName("Mercedes");
        brandDTO7.setName("Infinity");

        brandDTO1 = brandService.save(brandDTO1);
        brandDTO2 = brandService.save(brandDTO2);
        brandDTO3 = brandService.save(brandDTO3);
        brandDTO4 = brandService.save(brandDTO4);
        brandDTO5 = brandService.save(brandDTO5);
        brandDTO6 = brandService.save(brandDTO6);
        brandDTO7 = brandService.save(brandDTO7);

        ModelDTO modelDTO1 = new ModelDTO();
        modelDTO1.setName("BMW X3 SUV");
        modelDTO1.setCategory(Category.valueOf("Car"));
        modelDTO1.setStartYear(2016);
        modelDTO1.setEndYear(2020);
        modelDTO1.setImageUrl("/uploads/car1.jpg");
        modelDTO1.setBrand(brandDTO1);
        modelDTO1 = modelService.save(modelDTO1);

        ModelDTO modelDTO2 = new ModelDTO();
        modelDTO2.setName("BMW Z4 Roadster");
        modelDTO2.setCategory(Category.valueOf("Car"));
        modelDTO2.setStartYear(2016);
        modelDTO2.setImageUrl("/uploads/car1.jpg");
        modelDTO2.setEndYear(2020);
        modelDTO2.setBrand(brandDTO1);
        modelDTO2 = modelService.save(modelDTO2);

        ModelDTO modelDTO3 = new ModelDTO();
        modelDTO3.setName("Hard-pv");
        modelDTO3.setCategory(Category.valueOf("Car"));
        modelDTO3.setStartYear(2016);
        modelDTO3.setEndYear(2020);
        modelDTO3.setBrand(brandDTO2);
        modelDTO3 = modelService.save(modelDTO3);

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
        userDTO1.setEmail("example@gmail.com");
        userDTO1.setRole(userRoleDTO2);
        userDTO1 = userService.save(userDTO1);

        UserDTO userDTO0 = new UserDTO();
        userDTO0.setUsername("Admin");
        userDTO0.setPassword(passwordEncoder.encode("Admin"));
        userDTO0.setActive(true);
        userDTO0.setRole(new UserRoleDTO(Role.Admin));
        userService.save(userDTO0);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUsername("Jack");
        userDTO2.setPassword("yhnbvhj");
        userDTO2.setFirstName("Jack");
        userDTO2.setLastName("Frost");
        userDTO2.setEmail("example_next@gmail.com");
        userDTO2.setActive(true);
        userDTO2.setRole(userRoleDTO1);
        userDTO2 = userService.save(userDTO2);

        // Создание начальных данных для Offer
        OfferDTO offer1 = new OfferDTO();
        offer1.setMileage(600);
        offer1.setPrice(600_500.0);
        offer1.setEngine(Engine.DIESEL);
        offer1.setYear(2017);
        offer1.setModel(modelDTO1);
        offer1.setSeller(userDTO1);
        offer1.setDescription("Продаю ласточку");
        offerService.saveOffer(offer1);

        OfferDTO offer2 = new OfferDTO();
        offer2.setMileage(5000);
        offer2.setPrice(756_500.0);
        offer2.setEngine(Engine.DIESEL);
        offer2.setYear(2018);
        offer2.setModel(modelDTO2);
        offer2.setSeller(userDTO2);
        offer2.setDescription("Продаю BMW");
        offerService.saveOffer(offer2);

        System.out.println(brandDTO1);
    }
}
