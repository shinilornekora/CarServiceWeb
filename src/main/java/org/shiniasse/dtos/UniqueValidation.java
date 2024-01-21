package org.shiniasse.dtos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.shiniasse.repositories.UserRepository;

public class UniqueValidation implements ConstraintValidator<Unique, String> {
    private final UserRepository userRepository;

    public UniqueValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findUserByUsername(name).isEmpty();
    }
}
