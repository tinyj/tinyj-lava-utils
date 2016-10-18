package org.tinyj.lava.binder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionBinder<X, R> {

  protected final Function<? super X, ? extends R> bound;

  public FunctionBinder(Function<? super X, ? extends R> bound) {
    this.bound = bound;
  }

  public Supplier<R> bind(X x) {
    return () -> bound.apply(x);
  }

  public Supplier<R> linkTo(Supplier<? extends X> x) {
    return () -> bound.apply(x.get());
  }

  public <U> Function<U, R> linkTo(Function<? super U, ? extends X> x) {
    return (u) -> bound.apply(x.apply(u));
  }

  public <U, V> BiFunction<U, V, R> linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    return (u, v) -> bound.apply(x.apply(u, v));
  }
}
