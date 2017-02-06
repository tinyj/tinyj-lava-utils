package org.tinyj.lava.fun;

import org.tinyj.lava.LavaFunction;

import java.util.function.Function;

public final class ToString<X, E extends Exception>
    implements LavaFunction<X, String, E>, Function<X, String> {

  public static final ToString<?, ?> TO_STRING = new ToString<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, E extends Exception> ToString<X, E>
  ToString() { return (ToString) TO_STRING; }

  private ToString() {}

  @Override
  public final String checkedApply(X x) { return x.toString(); }

  @Override
  public final String apply(X x) { return x.toString(); }
}
