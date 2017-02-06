package org.tinyj.lava.fun;

import org.tinyj.lava.LavaPredicate;

import java.util.function.Predicate;

public final class IsNull<X, E extends Exception>
    implements LavaPredicate<X, E>, Predicate<X> {

  public static final IsNull<?, ?> IS_NULL = new IsNull<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, E extends Exception> IsNull<X, E>
  IsNull() { return (IsNull) IS_NULL; }

  private IsNull() {}

  @Override
  public final boolean checkedTest(X x) { return x == null; }

  @Override
  public final boolean test(X x) { return x == null; }
}
