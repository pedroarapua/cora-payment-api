package com.organization.payment.validator;

import org.apache.commons.beanutils.BeanUtils;

import com.organization.payment.annotation.NullIfAnotherFieldHasValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullIfAnotherFieldHasValueValidator implements ConstraintValidator<NullIfAnotherFieldHasValue, Object> {
  private String fieldName;
  private String expectedFieldValue;
  private String dependFieldName;

  @Override
  public void initialize(final NullIfAnotherFieldHasValue annotation) {
    fieldName = annotation.fieldName();
    expectedFieldValue = annotation.fieldValue();
    dependFieldName = annotation.dependFieldName();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext ctx) {

    if (value == null) {
      return true;
    }

    try {
      final String fieldValue = BeanUtils.getProperty(value, fieldName);
      final String dependFieldValue = BeanUtils.getProperty(value, dependFieldName);

      if (expectedFieldValue.equals(fieldValue) && dependFieldValue != null) {
        return false;
      }

    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
    return true;
  }

}