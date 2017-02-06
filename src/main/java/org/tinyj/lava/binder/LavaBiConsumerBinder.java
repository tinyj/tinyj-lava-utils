package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiConsumer;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaRunnable;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

public class LavaBiConsumerBinder<X, Y, E extends Exception>
    implements LavaBiConsumer<X, Y, E> {

  protected final LavaBiConsumer<X, Y, E> bound;

  public LavaBiConsumerBinder(LavaBiConsumer<? super X, ? super Y, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiConsumer.castDown(bound);
  }

  @Override
  public void checkedAccept(X x, Y y) throws E { bound.checkedAccept(x, y); }

  public LavaBiConsumerBinder<Y, X, E>
  flip() { return new LavaBiConsumerBinder<>((y, x) -> bound.checkedAccept(x, y)); }

  public LavaRunnable<E>
  bind(X x, Y y) { return () -> bound.checkedAccept(x, y); }

  public LavaConsumerBinder<Y, E>
  bindFirst(X x) { return new LavaConsumerBinder<>(y -> bound.checkedAccept(x, y)); }

  public LavaConsumerBinder<X, E>
  bindSecond(Y y) { return new LavaConsumerBinder<>(x -> bound.checkedAccept(x, y)); }

  public LavaRunnable<?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedAccept(x.checkedGet(), y.checkedGet());
  }

  public <U, V> LavaBiConsumerBinder<U, V, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiConsumerBinder<>((u, v) -> bound.checkedAccept(x.checkedApply(u), y.checkedApply(v)));
  }

  public LavaConsumerBinder<Y, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaConsumerBinder<>(y -> bound.checkedAccept(x.checkedGet(), y));
  }

  public <U> LavaBiConsumerBinder<U, Y, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiConsumerBinder<>((u, y) -> bound.checkedAccept(x.checkedApply(u), y));
  }

  public LavaConsumerBinder<X, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaConsumerBinder<>(x -> bound.checkedAccept(x, y.checkedGet()));
  }

  public <V> LavaBiConsumerBinder<X, V, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiConsumerBinder<>((x, v) -> bound.checkedAccept(x, y.checkedApply(v)));
  }

  public LavaBiConsumer<X, Y, E>
  bound() {
    return bound;
  }
}
