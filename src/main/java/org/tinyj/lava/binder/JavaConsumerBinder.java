package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class JavaConsumerBinder<X>
    implements Consumer<X> {

  protected final Consumer<X> bound;

  public JavaConsumerBinder(Consumer<? super X> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  public Runnable
  bind(X x) { return () -> bound.accept(x); }

  public Runnable
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.accept(x.get());
  }

  public <U> JavaConsumerBinder<U>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaConsumerBinder<>(u -> bound.accept(x.apply(u)));
  }

  public <U, V> JavaBiConsumerBinder<U, V>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiConsumerBinder<>((u, v) -> bound.accept(x.apply(u, v)));
  }

  @Override
  public Consumer<X> andThen(Consumer<? super X> after) { return new JavaConsumerBinder<>(bound.andThen(after)); }

  @Override
  public void accept(X x) { bound.accept(x); }

  public Consumer<X>
  bound() { return bound; }
}
