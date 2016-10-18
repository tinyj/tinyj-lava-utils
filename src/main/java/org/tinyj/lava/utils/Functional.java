package org.tinyj.lava.utils;

import org.tinyj.lava.throwing.ThrowingBiFunction;
import org.tinyj.lava.throwing.ThrowingFunction;
import org.tinyj.lava.throwing.ThrowingRunnable;
import org.tinyj.lava.throwing.ThrowingSupplier;

public class Functional {

  protected Functional() {
    assert false : "Functional is a utility class, static use only.";
  }

  public final static
  ThrowingRunnable<RuntimeException> NO_OP = () -> {};

  public final static
  ThrowingFunction<?, ?, RuntimeException> IDENTITY = x -> x;

  public final static
  ThrowingBiFunction<?, ?, ?, RuntimeException> FIRST = (x, y) -> x;

  public final static
  ThrowingBiFunction<?, ?, ?, RuntimeException> SECOND = (x, y) -> y;

  @SuppressWarnings("unchecked")
  public static ThrowingRunnable<?> noOp() { return NO_OP; }

  public static <R> ThrowingSupplier<R, ?> constant(R constant) { return () -> constant; }

  @SuppressWarnings("unchecked")
  public static <T> ThrowingFunction<? extends T, T, ?> identity() { return (ThrowingFunction) IDENTITY; }

  @SuppressWarnings("unchecked")
  public static <T> ThrowingBiFunction<? extends T, Object, T, ?> first() { return (ThrowingBiFunction) FIRST; }

  @SuppressWarnings("unchecked")
  public static <T> ThrowingBiFunction<Object, ? extends T, T, ?> second() { return (ThrowingBiFunction) SECOND; }
}
