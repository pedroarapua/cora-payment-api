package com.cora.payment.factory;

import java.io.Serializable;

public abstract class AbstractFactory<M extends Serializable> {

  abstract M simple();

}
