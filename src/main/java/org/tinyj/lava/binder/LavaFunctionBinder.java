package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

public class LavaFunctionBinder<X, R, E extends Exception>
    implements LavaFunction<X, R, E> {

  protected final LavaFunction<X, R, E> bound;

  public LavaFunctionBinder(LavaFunction<? super X, ? extends R, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaFunction.castDown(bound);
  }

  public LavaSupplier<R, E>
  bind(X x) { return () -> bound.checkedApply(x); }

  public LavaSupplier<R, ?>
  linkTo(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return () -> bound.checkedApply(x.checkedGet());
  }

  public <U> LavaFunctionBinder<U, R, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaFunctionBinder<>(u -> bound.checkedApply(x.checkedApply(u)));
  }

  public <U, V> LavaBiFunctionBinder<U, V, R, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiFunctionBinder<>((u, v) -> bound.checkedApply(x.checkedApply(u, v)));
  }

  @Override
  public R
  checkedApply(X x) throws E { return bound.checkedApply(x); }

  public LavaFunction<X, R, E>
  bound() { return bound; }
}
