package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

public class JavaPredicateBinder<X>
    implements Predicate<X> {

  protected final Predicate<X> bound;

  public JavaPredicateBinder(Predicate<? super X> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  public BooleanSupplier
  bind(X x) { return () -> bound.test(x); }

  public BooleanSupplier
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.test(x.get());
  }

  public <U> JavaPredicateBinder<U>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaPredicateBinder<>(u -> bound.test(x.apply(u)));
  }

  public <U, V> JavaBiPredicateBinder<U, V>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiPredicateBinder<>((u, v) -> bound.test(x.apply(u, v)));
  }

  @Override
  public boolean
  test(X x) { return bound.test(x); }

  public Predicate<X>
  bound() { return bound; }
}
