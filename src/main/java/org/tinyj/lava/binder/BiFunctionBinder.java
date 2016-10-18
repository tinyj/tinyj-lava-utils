package org.tinyj.lava.binder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class BiFunctionBinder<X, Y, R> {

  protected final BiFunction<? super X, ? super Y, ? extends R> bound;

  public BiFunctionBinder(BiFunction<? super X, ? super Y, ? extends R> bound) {
    this.bound = bound;
  }

  public BiFunction<Y, X, R> flip() {
    return (y, x) -> bound.apply(x, y);
  }

  public Supplier<R> bind(X x, Y y) {
    return () -> bound.apply(x, y);
  }

  public Function<Y, R> bindFirst(X x) {
    return (y) -> bound.apply(x, y);
  }

  public Function<X, R> bindSecond(Y y) {
    return (x) -> bound.apply(x, y);
  }

  public Supplier<R> link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    return () -> bound.apply(x.get(), y.get());
  }

  public <U, V> BiFunction<U, V, R> link(Function<? super U, ? extends X> x,
                                         Function<? super V, ? extends Y> y) {
    return (u, v) -> bound.apply(x.apply(u), y.apply(v));
  }

  public Function<Y, R> linkFirst(Supplier<? extends X> x) {
    return (y) -> bound.apply(x.get(), y);
  }

  public <U> BiFunction<U, Y, R> linkFirst(Function<? super U, ? extends X> x) {
    return (u, y) -> bound.apply(x.apply(u), y);
  }

  public Function<X, R> linkSecond(Supplier<? extends Y> y) {
    return (x) -> bound.apply(x, y.get());
  }

  public <V> BiFunction<X, V, R> linkSecond(Function<? super V, ? extends Y> y) {
    return (x, v) -> bound.apply(x, y.apply(v));
  }
}
