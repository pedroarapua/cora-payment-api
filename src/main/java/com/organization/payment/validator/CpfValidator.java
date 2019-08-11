package com.organization.payment.validator;

import com.organization.payment.annotation.Cpf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

  @Override
  public void initialize(Cpf constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || value.isEmpty() || value.length() != 11 || isCpf(value);
  }

  private boolean isCpf(String cpf) {
    cpf = cpf.replace(".", "");
    cpf = cpf.replace("-", "");

    if (cpf.equals("00000000000")
        || cpf.equals("11111111111") 
        || cpf.equals("22222222222") 
        || cpf.equals("33333333333") 
        || cpf.equals("44444444444") 
        || cpf.equals("55555555555") 
        || cpf.equals("66666666666") 
        || cpf.equals("77777777777") 
        || cpf.equals("88888888888") 
        || cpf.equals("99999999999")) {
      return false;
    }   

    try {
      Long.parseLong(cpf);
    } catch (NumberFormatException e) {
      return false;
    }

    int d1;
    int d2;
    int digito1;
    int digito2;
    int resto;
    int digitoCpf;

    d1 = d2 = 0;
    digito1 = digito2 = resto = 0;

    for (int i = 1; i < cpf.length() - 1; i++) {
      digitoCpf = Integer.parseInt(cpf.substring(i - 1, i));

      // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
      // e assim por diante.
      d1 = d1 + (11 - i) * digitoCpf;

      // para o segundo digito repita o procedimento incluindo o primeiro
      // digito calculado no passo anterior.
      d2 = d2 + (12 - i) * digitoCpf;
    }

    // Primeiro resto da divisão por 11.
    resto = (d1 % 11);

    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
    // menos o resultado anterior.
    if (resto < 2) {
      digito1 = 0;
    } else {
      digito1 = 11 - resto;
    }

    d2 += 2 * digito1;

    // Segundo resto da divisão por 11.
    resto = (d2 % 11);

    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
    // menos o resultado anterior.
    if (resto < 2) {
      digito2 = 0;
    } else {
      digito2 = 11 - resto;
    }

    // Digito verificador do CPF que está sendo validado.
    String digit = cpf.substring(cpf.length() - 2, cpf.length());

    // Concatenando o primeiro resto com o segundo.
    String digitResult = String.valueOf(digito1) + String.valueOf(digito2);

    // comparar o digito verificador do cpf com o primeiro resto + o segundo
    // resto.
    return digit.equals(digitResult);
  }
}
