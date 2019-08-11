package com.organization.payment.annotation;

import com.organization.payment.validator.CpfValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates a CPF (Cadastro de Pessoa Física - Brazilian individual taxpayer registry number).
 *
 * @author Pedro Henrique F. Dias
 */
@Constraint(validatedBy = {CpfValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Cpf {

  String message() default "invalid cpf";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
