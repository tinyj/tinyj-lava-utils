package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class JavaBiConsumerBinder<X, Y>
    implements BiConsumer<X, Y> {

  protected final BiConsumer<X, Y> bound;

  public JavaBiConsumerBinder(BiConsumer<? super X, ? super Y> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  @Override
  public void accept(X x, Y y) { bound.accept(x, y); }

  public JavaBiConsumerBinder<Y, X>
  flip() { return new JavaBiConsumerBinder<>((y, x) -> bound.accept(x, y)); }

  public Runnable
  bind(X x, Y y) { return () -> bound.accept(x, y); }

  public JavaConsumerBinder<Y>
  bindFirst(X x) { return new JavaConsumerBinder<>(y -> bound.accept(x, y)); }

  public JavaConsumerBinder<X>
  bindSecond(Y y) { return new JavaConsumerBinder<>(x -> bound.accept(x, y)); }

  public Runnable
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.accept(x.get(), y.get());
  }

  public <U, V> JavaBiConsumerBinder<U, V>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiConsumerBinder<>((u, v) -> bound.accept(x.apply(u), y.apply(v)));
  }

  public JavaConsumerBinder<Y>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaConsumerBinder<>(y -> bound.accept(x.get(), y));
  }

  public <U> JavaBiConsumerBinder<U, Y>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiConsumerBinder<>((u, y) -> bound.accept(x.apply(u), y));
  }

  public JavaConsumerBinder<X>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaConsumerBinder<>(x -> bound.accept(x, y.get()));
  }

  public <V> JavaBiConsumerBinder<X, V>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiConsumerBinder<>((x, v) -> bound.accept(x, y.apply(v)));
  }

  @Override
  public JavaBiConsumerBinder<X, Y> andThen(BiConsumer<? super X, ? super Y> after) {
    return new JavaBiConsumerBinder<>(bound.andThen(after));
  }

  public BiConsumer<X, Y>
  bound() { return bound; }
}
