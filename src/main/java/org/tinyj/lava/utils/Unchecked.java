package org.tinyj.lava.utils;

import org.tinyj.lava.throwing.*;

import java.util.function.*;

public class Unchecked {

  protected Unchecked() { assert false : "Unchecked is a utility class, static use only."; }

  public static Runnable
  runnable(Runnable runnable) { return runnable; }

  public static <X> Consumer<X>
  consumer(Consumer<X> consumer) { return consumer; }

  public static <X, Y> BiConsumer<X, Y>
  biConsumer(BiConsumer<X, Y> biConsumer) { return biConsumer; }

  public static <R> Supplier<R>
  supplier(Supplier<R> supplier) { return supplier; }

  public static <X, R> Function<X, R>
  function(Function<X, R> function) { return function; }

  public static <X, Y, R> BiFunction<X, Y, R>
  biFunction(BiFunction<X, Y, R> biFunction) { return biFunction; }

  public static <X> Predicate<X>
  predicate(Predicate<X> predicate) { return predicate; }

  public static <X, Y> BiPredicate<X, Y>
  biPredicate(BiPredicate<X, Y> biPredicate) { return biPredicate; }

  public static <X> Function<X, X>
  unaryOp(Function<X, X> operator) { return operator; }

  public static <X> BiFunction<X, X, X>
  binaryOp(BiFunction<X, X, X> operator) { return operator; }

  public static <X> BiPredicate<X, X>
  relation(BiPredicate<X, X> relation) { return relation; }

  public static Runnable
  unchecked(ThrowingRunnable<? extends RuntimeException> runnable) { return runnable::run; }

  public static <X> Consumer<X>
  unchecked(ThrowingConsumer<? super X, ? extends RuntimeException> consumer) { return consumer::accept; }

  public static <X, Y> BiConsumer<X, Y>
  unchecked(ThrowingBiConsumer<? super X, ? super Y, ? extends RuntimeException> biConsumer) { return biConsumer::accept; }

  public static <R> Supplier<R>
  unchecked(ThrowingSupplier<? extends R, ? extends RuntimeException> supplier) { return supplier::get; }

  public static <X, R> Function<X, R>
  unchecked(ThrowingFunction<? super X, ? extends R, ? extends RuntimeException> function) { return function::apply; }

  public static <X, Y, R> BiFunction<X, Y, R>
  unchecked(ThrowingBiFunction<? super X, ? super Y, ? extends R, ? extends RuntimeException> biFunction) { return biFunction::apply; }

  public static <X> Predicate<X>
  unchecked(ThrowingPredicate<? super X, ? extends RuntimeException> predicate) { return predicate::test; }

  public static <X, Y> BiPredicate<X, Y>
  unchecked(ThrowingBiPredicate<? super X, ? super Y, ? extends RuntimeException> biPredicate) { return biPredicate::test; }
}
