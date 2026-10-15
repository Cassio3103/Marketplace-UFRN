package br.ufrn.imd.imdconecta.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = EmailInstitucionalValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailInstitucional {

    String message() default "Use seu e-mail institucional, que termina com @ufrn.edu.br";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}