package org.tinyj.lava.binder;

import org.tinyj.lava.*;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on {@link LavaPredicate}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} unwrap these results.
 *
 * @param <X> the type of the input to the predicate
 */
public class LavaPredicateBinder<X, E extends Exception>
    implements LavaPredicate<X, E> {

  protected final LavaPredicate<X, E> bound;

  public LavaPredicateBinder(LavaPredicate<? super X, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaPredicate.castDown(bound);
  }

  /**
   * Curry argument with {@code x}.
   */
  public LavaCondition<E>
  bind(X x) { return () -> bound.checkedTest(x); }

  /**
   * Link the argument to supplied value. {@code x} is invoked each time the
   * resulting {@link LavaCondition} is invoked and the results is supplied as
   * argument to the curried {@link LavaPredicate}.
   */
  public LavaCondition<E>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.checkedTest(x.get());
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link LavaPredicate} is
   * invoked and the result is supplied as argument to the curried {@link LavaPredicate}.
   */
  public <U> LavaPredicateBinder<U, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaPredicateBinder<>(u -> bound.checkedTest(x.checkedApply(u)));
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link LavaBiPredicate} is
   * invoked and the result is supplied as argument to the curried {@link LavaPredicate}.
   */
  public <U, V> LavaBiPredicateBinder<U, V, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiPredicateBinder<>((u, v) -> bound.checkedTest(x.checkedApply(u, v)));
  }

  /**
   * @return the wrapped {@link LavaPredicate}
   */
  public LavaPredicate<X, E>
  bound() { return bound; }

  public <U extends X, V> LavaBiPredicate<U, V, E>
  testFirst() { return ((u, v) -> bound.checkedTest(u)); }

  public <U, V extends X> LavaBiPredicate<U, V, E>
  testSecond() { return ((u, v) -> bound.checkedTest(v)); }

  @Override
  public boolean
  checkedTest(X x) throws E { return bound.checkedTest(x); }
}
