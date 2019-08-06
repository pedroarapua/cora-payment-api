package com.cora.payment.commons;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pojo.tester.api.FieldPredicate;
import pl.pojo.tester.api.assertion.Method;


@RunWith(MockitoJUnitRunner.class)
public abstract class EntityGenericTest<M extends Serializable> {

  protected PojoClass pojoClass;

  @Before
  public void setUp() {
    this.pojoClass = PojoClassFactory.getPojoClass((Class<?>) ((ParameterizedType) this.getClass()
        .getGenericSuperclass())
        .getActualTypeArguments()[0]);
  }

  @Test
  public void serializable() {
    Stream.of(pojoClass.getClazz().getGenericInterfaces())
    .filter(i -> i.getTypeName().equals("java.io.Serializable"))
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not implement serializable"));
  }

  @Test
  public void annotationEntity() {
    Stream.of(pojoClass.getClazz().getAnnotations())
    .filter(annotation -> annotation instanceof Entity)
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not entity annotation"));
  }

  @Test
  public void annotationTable() {
    Stream.of(pojoClass.getClazz().getAnnotations())
    .filter(annotation -> annotation instanceof Table)
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not table annotation"));
  }

  @Test
  public void noArgsConstructor() {
    Stream.of(pojoClass.getClazz().getConstructors())
    .filter(constructor -> constructor.getParameterTypes().length == 0)
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not NoArgsContructor"));
  }

  @Test
  public void allArgsConstructor() {
    Stream.of(pojoClass.getClazz().getConstructors())
    .filter(constructor -> constructor.getParameterTypes().length > 0)
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not AllArgsContructor"));
  }

  @Test
  public void builder() {
    Stream.of(this.pojoClass.getClazz().getDeclaredClasses())
    .filter(c -> c.getName().contains("Builder"))
    .findFirst()
    .orElseThrow(() -> new RuntimeException("class does not builder"));
  }

  @Test
  public void serialVersionUid() {
    ValidatorBuilder.create()
    .with(new SerializableMustHaveSerialVersionUIDRule())
    .build()
    .validate(pojoClass);
  }

  @Test
  public void equalsId() {
    String idName = Stream.of(pojoClass.getClazz().getDeclaredFields())
        .filter(field -> field.getName().equals("id"))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("class does not id"))
        .getName();
    assertPojoMethodsFor(pojoClass.getClazz(), FieldPredicate.include(idName))
    .testing(Method.EQUALS)
    .testing(Method.HASH_CODE)
    .areWellImplemented();
  }

  @Test
  public void checkNecessaryMethods() {
    assertPojoMethodsFor(pojoClass.getClazz())
    .testing(Method.GETTER)
    .testing(Method.SETTER)
    .testing(Method.CONSTRUCTOR)
    .testing(Method.TO_STRING)
    .areWellImplemented();
  }

}

