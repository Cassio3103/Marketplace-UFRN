package br.ufrn.imd.imdconecta.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailInstitucionalValidator implements ConstraintValidator<EmailInstitucional, String> {

    public boolean isValid(String email, ConstraintValidatorContext context) {

        if(email == null){
            return false;
        }

        return email.endsWith("@ufrn.edu.br");
    }
}