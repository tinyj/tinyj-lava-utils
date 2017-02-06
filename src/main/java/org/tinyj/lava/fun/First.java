package org.tinyj.lava.fun;

import org.tinyj.lava.LavaBiFunction;

import java.util.function.BiFunction;

public final class First<X extends R, Y, R, E extends Exception>
    implements LavaBiFunction<X, Y, R, E>, BiFunction<X, Y, R> {

  public static final First<?, ?, ?, ?> FIRST = new First<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X extends R, Y, R, E extends Exception> First<X, Y, R, E>
  First() { return (First) FIRST; }

  private First() {}

  @Override
  public final R checkedApply(X x, Y y) { return x; }

  @Override
  public final R apply(X x, Y y) { return x; }
}
