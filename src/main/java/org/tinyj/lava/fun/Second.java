package org.tinyj.lava.fun;

import org.tinyj.lava.LavaBiFunction;

import java.util.function.BiFunction;

public final class Second<X, Y extends R, R, E extends Exception>
    implements LavaBiFunction<X, Y, R, E>, BiFunction<X, Y, R> {

  public static final Second<?, ?, ?, ?> SECOND = new Second<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, Y extends R, R, E extends Exception> Second<X, Y, R, E>
  Second() { return (Second) SECOND; }

  private Second() {}

  @Override
  public final R checkedApply(X x, Y y) { return y; }

  @Override
  public final R apply(X x, Y y) { return y; }
}
