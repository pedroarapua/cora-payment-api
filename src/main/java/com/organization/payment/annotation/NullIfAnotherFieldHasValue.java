package com.organization.payment.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.organization.payment.validator.NullIfAnotherFieldHasValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NullIfAnotherFieldHasValueValidator.class)
@Documented
public @interface NullIfAnotherFieldHasValue {

  String fieldName();

  String fieldValue();

  String dependFieldName();

  String message() default "NullIfAnotherFieldHasValue.message";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({TYPE, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    NullIfAnotherFieldHasValue[] value();
  }

}