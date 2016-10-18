package org.tinyj.lava.binder;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BiConsumerBinder<X, Y> {

  protected final BiConsumer<? super X, ? super Y> bound;

  public BiConsumerBinder(BiConsumer<? super X, ? super Y> bound) {
    this.bound = bound;
  }

  public BiConsumer<Y, X> flip() {
    return (y, x) -> bound.accept(x, y);
  }

  public Runnable bind(X x, Y y) {
    return () -> bound.accept(x, y);
  }

  public Consumer<Y> bindFirst(X x) {
    return (y) -> bound.accept(x, y);
  }

  public Consumer<X> bindSecond(Y y) {
    return (x) -> bound.accept(x, y);
  }

  public Runnable link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    return () -> bound.accept(x.get(), y.get());
  }

  public <U, V> BiConsumer<U, V> link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    return (u, v) -> bound.accept(x.apply(u), y.apply(v));
  }

  public Consumer<Y> linkFirst(Supplier<? extends X> x) {
    return (y) -> bound.accept(x.get(), y);
  }

  public <U> BiConsumer<U, Y> linkFirst(Function<? super U, ? extends X> x) {
    return (u, y) -> bound.accept(x.apply(u), y);
  }

  public Consumer<X> linkSecond(Supplier<? extends Y> y) {
    return (x) -> bound.accept(x, y.get());
  }

  public <V> BiConsumer<X, V> linkSecond(Function<? super V, ? extends Y> y) {
    return (x, v) -> bound.accept(x, y.apply(v));
  }
}
