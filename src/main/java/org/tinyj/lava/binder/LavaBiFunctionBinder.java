package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

public class LavaBiFunctionBinder<X, Y, R, E extends Exception>
    implements LavaBiFunction<X, Y, R, E> {

  protected final LavaBiFunction<X, Y, R, E> bound;

  public LavaBiFunctionBinder(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiFunction.castDown(bound);
  }

  public LavaBiFunction<Y, X, R, E>
  flip() { return (y, x) -> bound.checkedApply(x, y); }

  public LavaSupplier<R, E>
  bind(X x, Y y) { return () -> bound.checkedApply(x, y);}

  public LavaFunctionBinder<Y, R, E>
  bindFirst(X x) { return new LavaFunctionBinder<>(y -> bound.checkedApply(x, y)); }

  public LavaFunctionBinder<X, R, E>
  bindSecond(Y y) { return new LavaFunctionBinder<>(x -> bound.checkedApply(x, y)); }

  public LavaSupplier<R, ?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedApply(x.checkedGet(), y.checkedGet());
  }

  public <U, V> LavaBiFunctionBinder<U, V, R, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiFunctionBinder<>((u, v) -> bound.checkedApply(x.checkedApply(u), y.checkedApply(v)));
  }

  public LavaFunctionBinder<Y, R, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaFunctionBinder<>(y -> bound.checkedApply(x.checkedGet(), y));
  }

  public <U> LavaBiFunctionBinder<U, Y, R, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiFunctionBinder<>((u, y) -> bound.checkedApply(x.checkedApply(u), y));
  }

  public LavaFunctionBinder<X, R, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaFunctionBinder<>(x -> bound.checkedApply(x, y.checkedGet()));
  }

  public <V> LavaBiFunctionBinder<X, V, R, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiFunctionBinder<>((x, v) -> bound.checkedApply(x, y.checkedApply(v)));
  }

  @Override
  public R
  checkedApply(X x, Y y) throws E { return bound.checkedApply(x, y); }

  public LavaBiFunction<X, Y, R, E>
  bound() { return bound; }
}
