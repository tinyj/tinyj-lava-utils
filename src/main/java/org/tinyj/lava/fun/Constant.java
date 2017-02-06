package org.tinyj.lava.fun;

import org.tinyj.lava.LavaSupplier;

import java.util.function.Supplier;

public final class Constant<R, E extends Exception>
    implements LavaSupplier<R, E>, Supplier<R> {

  private final R value;

  public Constant(R value) { this.value = value; }

  @Override
  public final R checkedGet() { return value; }

  @Override
  public final R get() { return value; }
}
