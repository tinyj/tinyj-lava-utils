package org.tinyj.lava.fun;

import org.tinyj.lava.LavaPredicate;

import java.util.function.Predicate;

public final class NonNull<X, E extends Exception>
    implements LavaPredicate<X, E>, Predicate<X> {

  public static final NonNull<?, ?> NON_NULL = new NonNull<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, E extends Exception> NonNull<X, E>
  NonNull() { return (NonNull) NON_NULL; }

  private NonNull() {}

  @Override
  public final boolean checkedTest(X x) { return x != null; }

  @Override
  public final boolean test(X x) { return x != null; }
}
