package org.tinyj.lava.utils;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A constant, always returns {@code value}. This is equivalent to
 * {@code (...) -> value}. Can be used where ever a {@link Supplier},
 * {@link Function}, or {@link BiFunction} is required.
 */
public final class Constant<X, Y, R, E extends Exception>
    implements Supplier<R>, FunctionOrBiFunction<X, Y, R>,
    LavaSupplier<R, E>, LavaFunction<X, R, E>, LavaBiFunction<X, Y, R, E> {

  private final R value;

  public Constant(R value) { this.value = value;}

  @Override
  public R apply(X x, Y y) { return value; }

  @Override
  public R apply(X x) { return value; }

  @Override
  public R get() { return value; }

  @Override
  public R checkedApply(X x, Y y) throws E { return value; }

  @Override
  public R checkedApply(X x) throws E { return value; }

  @Override
  public R checkedGet() throws E { return value; }
}
