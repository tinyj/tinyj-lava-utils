package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class JavaFunctionBinder<X, R>
    implements Function<X, R> {

  protected final Function<X, R> bound;

  public JavaFunctionBinder(Function<? super X, ? extends R> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  public Supplier<R>
  bind(X x) { return () -> bound.apply(x); }

  public Supplier<R>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.apply(x.get());
  }

  public <U> JavaFunctionBinder<U, R>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaFunctionBinder<>(u -> bound.apply(x.apply(u)));
  }

  public <U, V> JavaBiFunctionBinder<U, V, R>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiFunctionBinder<>((u, v) -> bound.apply(x.apply(u, v)));
  }

  @Override
  public <U> JavaFunctionBinder<U, R>
  compose(Function<? super U, ? extends X> before) { return new JavaFunctionBinder<>(bound.compose(before)); }

  @Override
  public <V> JavaFunctionBinder<X, V>
  andThen(Function<? super R, ? extends V> after) { return new JavaFunctionBinder<>(bound.andThen(after)); }

  @Override
  public R
  apply(X x) { return bound.apply(x); }

  public Function<X, R>
  bound() { return bound; }
}
