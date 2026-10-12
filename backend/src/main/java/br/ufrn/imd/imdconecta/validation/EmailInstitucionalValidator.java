package br.ufrn.imd.imdconecta.validation;

import jakarta.validation.ConstraintValidatorContext;

public class EmailInstitucionalValidator {

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return false;
    }
}