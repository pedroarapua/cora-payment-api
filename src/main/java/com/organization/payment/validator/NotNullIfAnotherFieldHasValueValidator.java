package com.organization.payment.validator;

import com.organization.payment.annotation.NotNullIfAnotherFieldHasValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class NotNullIfAnotherFieldHasValueValidator 
    implements ConstraintValidator<NotNullIfAnotherFieldHasValue, Object> {
  
  private String fieldName;
  private String expectedFieldValue;
  private String dependFieldName;

  @Override
  public void initialize(final NotNullIfAnotherFieldHasValue annotation) {
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

      if (expectedFieldValue.equals(fieldValue) && dependFieldValue == null) {
        return false;
      }

    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
    return true;
  }

}