package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaPredicate;
import org.tinyj.lava.LavaSupplier;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class LavaPredicateBinder<X, E extends Exception>
    implements LavaPredicate<X, E> {

  protected final LavaPredicate<X, E> bound;

  public LavaPredicateBinder(LavaPredicate<? super X, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaPredicate.castDown(bound);
  }

  public LavaSupplier<Boolean, E>
  bind(X x) { return () -> bound.checkedTest(x); }

  public LavaSupplier<Boolean, E>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.checkedTest(x.get());
  }

  public <U> LavaPredicateBinder<U, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaPredicateBinder<>(u -> bound.checkedTest(x.checkedApply(u)));
  }

  public <U, V> LavaBiPredicateBinder<U, V, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiPredicateBinder<>((u, v) -> bound.checkedTest(x.checkedApply(u, v)));
  }

  @Override
  public boolean
  checkedTest(X x) throws E { return bound.checkedTest(x); }

  public LavaPredicate<X, E>
  bound() { return bound; }
}
