package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiPredicate;
import org.tinyj.lava.LavaBooleanSupplier;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

public class LavaBiPredicateBinder<X, Y, E extends Exception>
    implements LavaBiPredicate<X, Y, E> {

  protected final LavaBiPredicate<X, Y, E> bound;

  public LavaBiPredicateBinder(LavaBiPredicate<? super X, ? super Y, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiPredicate.castDown(bound);
  }

  public LavaBiPredicate<Y, X, E>
  flip() { return (y, x) -> bound.checkedTest(x, y); }

  public LavaBooleanSupplier<E>
  bind(X x, Y y) { return () -> bound.checkedTest(x, y);}

  public LavaPredicateBinder<Y, E>
  bindFirst(X x) { return new LavaPredicateBinder<>(y -> bound.checkedTest(x, y)); }

  public LavaPredicateBinder<X, E>
  bindSecond(Y y) { return new LavaPredicateBinder<>(x -> bound.checkedTest(x, y)); }

  public LavaBooleanSupplier<?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedTest(x.checkedGet(), y.checkedGet());
  }

  public <U, V> LavaBiPredicateBinder<U, V, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiPredicateBinder<>((u, v) -> bound.checkedTest(x.checkedApply(u), y.checkedApply(v)));
  }

  public LavaPredicateBinder<Y, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaPredicateBinder<>(y -> bound.checkedTest(x.checkedGet(), y));
  }

  public <U> LavaBiPredicateBinder<U, Y, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiPredicateBinder<>((u, y) -> bound.checkedTest(x.checkedApply(u), y));
  }

  public LavaPredicateBinder<X, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaPredicateBinder<>(x -> bound.checkedTest(x, y.checkedGet()));
  }

  public <V> LavaBiPredicateBinder<X, V, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiPredicateBinder<>((x, v) -> bound.checkedTest(x, y.checkedApply(v)));
  }

  @Override
  public boolean
  checkedTest(X x, Y y) throws E { return bound.checkedTest(x, y); }

  public LavaBiPredicate<X, Y, E>
  bound() { return bound; }
}
