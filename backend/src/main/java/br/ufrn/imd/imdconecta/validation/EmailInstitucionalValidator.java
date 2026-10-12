package br.ufrn.imd.imdconecta.validation;

import jakarta.validation.ConstraintValidatorContext;

public class EmailInstitucionalValidator {

    public boolean isValid(String email, ConstraintValidatorContext context) {

        if(email == null){
            return false;
        }

        return email.endsWith("@ufrn.edu.br");
    }
}