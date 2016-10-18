package org.tinyj.lava.binder;

import java.util.function.*;

public class ConsumerBinder<X> {

  protected final Consumer<? super X> bound;

  public ConsumerBinder(Consumer<? super X> bound) {
    this.bound = bound;
  }

  public Runnable bind(X x) {
    return () -> bound.accept(x);
  }

  public Runnable linkTo(Supplier<? extends X> x) {
    return () -> bound.accept(x.get());
  }

  public <U> Consumer<U> linkTo(Function<? super U, ? extends X> x) {
    return (u) -> bound.accept(x.apply(u));
  }

  public <U, V> BiConsumer<U, V> linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    return (u, v) -> bound.accept(x.apply(u, v));
  }
}
