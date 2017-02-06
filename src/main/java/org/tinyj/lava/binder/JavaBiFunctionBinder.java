package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class JavaBiFunctionBinder<X, Y, R>
    implements BiFunction<X, Y, R> {

  protected final BiFunction<X, Y, R> bound;

  public JavaBiFunctionBinder(BiFunction<? super X, ? super Y, ? extends R> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  public BiFunction<Y, X, R>
  flip() { return (y, x) -> bound.apply(x, y); }

  public Supplier<R>
  bind(X x, Y y) { return () -> bound.apply(x, y);}

  public JavaFunctionBinder<Y, R>
  bindFirst(X x) { return new JavaFunctionBinder<>(y -> bound.apply(x, y)); }

  public JavaFunctionBinder<X, R>
  bindSecond(Y y) { return new JavaFunctionBinder<>(x -> bound.apply(x, y)); }

  public Supplier<R>
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.apply(x.get(), y.get());
  }

  public <U, V> JavaBiFunctionBinder<U, V, R>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiFunctionBinder<>((u, v) -> bound.apply(x.apply(u), y.apply(v)));
  }

  public JavaFunctionBinder<Y, R>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaFunctionBinder<>(y -> bound.apply(x.get(), y));
  }

  public <U> JavaBiFunctionBinder<U, Y, R>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiFunctionBinder<>((u, y) -> bound.apply(x.apply(u), y));
  }

  public JavaFunctionBinder<X, R>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaFunctionBinder<>(x -> bound.apply(x, y.get()));
  }

  public <V> JavaBiFunctionBinder<X, V, R>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiFunctionBinder<>((x, v) -> bound.apply(x, y.apply(v)));
  }

  @Override
  public <V> JavaBiFunctionBinder<X, Y, V>
  andThen(Function<? super R, ? extends V> after) { return new JavaBiFunctionBinder<>(bound.andThen(after)); }

  @Override
  public R
  apply(X x, Y y) { return bound.apply(x, y); }

  public BiFunction<X, Y, R>
  bound() { return bound; }
}
