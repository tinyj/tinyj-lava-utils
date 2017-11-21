package org.tinyj.lava.binder;

import org.tinyj.lava.*;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on {@link LavaConsumer}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} unwrap these results.
 *
 * @param <X> the type of the argument to the operation
 */
public class LavaConsumerBinder<X, E extends Exception>
    implements LavaConsumer<X, E> {

  protected final LavaConsumer<X, E> bound;

  public LavaConsumerBinder(LavaConsumer<? super X, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaConsumer.castDown(bound);
  }

  /**
   * Curry argument with {@code x}.
   */
  public LavaRunnable<E>
  bind(X x) { return () -> bound.checkedAccept(x); }

  /**
   * Link the argument to supplied value. {@code x} is invoked each time the
   * resulting {@link LavaRunnable} is invoked and the results is supplied as argument
   * to the curried {@link LavaConsumer}.
   */
  public LavaRunnable<?>
  linkTo(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return () -> bound.checkedAccept(x.checkedGet());
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link LavaConsumer} is
   * invoked and the result is supplied as argument to the curried {@link LavaConsumer}.
   */
  public <U> LavaConsumerBinder<U, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaConsumerBinder<>(u -> bound.checkedAccept(x.checkedApply(u)));
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link LavaBiConsumer} is
   * invoked and the result is supplied as argument to the curried {@link LavaConsumer}.
   */
  public <U, V> LavaBiConsumerBinder<U, V, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiConsumerBinder<>((u, v) -> bound.checkedAccept(x.checkedApply(u, v)));
  }

  /**
   * @return the wrapped {@link LavaConsumer}
   */
  public LavaConsumer<X, E>
  bound() { return bound; }

  public <U extends X, V> LavaBiConsumer<U, V, E>
  acceptFirst() { return ((u, v) -> bound.checkedAccept(u)); }

  public <U, V extends X> LavaBiConsumer<U, V, E>
  acceptSecond() { return ((u, v) -> bound.checkedAccept(v)); }

  @Override
  public void checkedAccept(X x) throws E { bound.checkedAccept(x); }
}
