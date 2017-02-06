package org.tinyj.lava.fun;

import org.tinyj.lava.LavaPredicate;

import java.util.Objects;
import java.util.function.Predicate;

public final class IsEqualTo<X, E extends Exception>
    implements LavaPredicate<X, E>, Predicate<X> {

  private final Object reference;

  public IsEqualTo(Object reference) { this.reference = reference; }

  @Override
  public final boolean checkedTest(X x) {
    return Objects.equals(reference, x);
  }

  @Override
  public final boolean test(X x) {
    return Objects.equals(reference, x);
  }
}
