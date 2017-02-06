package org.tinyj.lava.binder;

import org.tinyj.lava.*;

import static java.util.Objects.requireNonNull;

public class LavaConsumerBinder<X, E extends Exception>
    implements LavaConsumer<X, E> {

  protected final LavaConsumer<X, E> bound;

  public LavaConsumerBinder(LavaConsumer<? super X, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaConsumer.castDown(bound);
  }

  public LavaRunnable<E>
  bind(X x) { return () -> bound.checkedAccept(x); }

  public LavaRunnable<?>
  linkTo(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return () -> bound.checkedAccept(x.checkedGet());
  }

  public <U> LavaConsumerBinder<U, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaConsumerBinder<>(u -> bound.checkedAccept(x.checkedApply(u)));
  }

  public <U, V> LavaBiConsumerBinder<U, V, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiConsumerBinder<>((u, v) -> bound.checkedAccept(x.checkedApply(u, v)));
  }

  @Override
  public void checkedAccept(X x) throws E { bound.checkedAccept(x); }

  public LavaConsumer<X, E>
  bound() { return bound; }
}
