package org.tinyj.lava.fun;

import org.tinyj.lava.LavaFunction;

import java.util.function.Function;

public final class Identity<X extends R, R, E extends Exception>
    implements LavaFunction<X, R, E>, Function<X, R> {

  public static final Identity<?, ?, ?> IDENTITY = new Identity<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X extends R, R, E extends Exception> Identity<X, R, E>
  Identity() { return (Identity) IDENTITY; }

  private Identity() {}

  @Override
  public final R checkedApply(X x) { return x; }

  @Override
  public final R apply(X x) { return x; }
}
