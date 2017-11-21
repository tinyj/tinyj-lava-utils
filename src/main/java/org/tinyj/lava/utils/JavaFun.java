package org.tinyj.lava.utils;

import org.tinyj.lava.*;
import org.tinyj.lava.binder.*;

import java.util.Objects;
import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Utility class
 */
public class JavaFun {

  /** Static utility class, {@literal do not instantiate!} */
  protected JavaFun() { assert false : "JavaFun is a utility class, static use only."; }

  public final static
  Function<Object, ?> IDENTITY = x -> x;
  public final static
  BiFunction<Object, Object, ?> FIRST = (x, y) -> y;
  public final static
  BiFunction<Object, Object, ?> SECOND = (x, y) -> y;
  public final static
  Predicate<Object> IS_NULL = Objects::isNull;
  public final static
  Predicate<Object> NON_NULL = Objects::nonNull;
  public final static
  BiPredicate<Object, Object> EQUALS = Objects::equals;
  public final static
  Function<Object, String> TO_STRING = x -> x == null ? null : x.toString();

  /**
   * The no-operation, does absolutely nothing. This is equivalent to
   * {@code (...) -> {}}. Can be used where ever a {@link Runnable},
   * {@link Consumer}, or {@link BiConsumer} is required.
   */
  @SuppressWarnings({"MethodNameSameAsClassName", "unchecked"})
  public static <X, Y, E extends Exception> NoOp<X, Y, E>
  NoOp() { return NoOp.NO_OP; }

  /**
   * A constant, always returns {@code value}. This is equivalent to
   * {@code (...) -> value}. Can be used where ever a {@link Supplier},
   * {@link Function}, or {@link BiFunction} is required.
   */
  public static <X, Y, R, E extends Exception> Constant<X, Y, R, E>
  Constant(R value) { return new Constant<>(value); }

  /**
   * The {@code null} supplier, always returns {@code null}. This is equivalent
   * to {@code (...) -> null}. Can be used where ever a {@link Supplier},
   * {@link Function}, or {@link BiFunction} is required.
   */
  @SuppressWarnings("unchecked")
  public static <X, Y, R, E extends Exception> Constant<X, Y, R, E>
  Null() { return Constant(null); }

  /**
   * The identity function, returns it's argument. This is equivalent to
   * {@code x -> x}.
   */
  @SuppressWarnings("unchecked")
  public static <X extends R, R> Function<X, R>
  Identity() { return (Function) IDENTITY; }

  /**
   * The stringify function, returns the string representation of its argument.
   * This is equivalent to {@code x -> Objects.toString(x)}.
   */
  public static <X> Function<X, String>
  ToString() { return castDown(TO_STRING); }

  /**
   * Binary function returning its first argument. This is equivalent to
   * {@code (x, y) -> x}.
   */
  @SuppressWarnings("unchecked")
  public static <X extends R, Y, R> BiFunction<X, Y, R>
  First() { return (BiFunction) FIRST; }

  /**
   * Binary function returning its second argument. This is equivalent to
   * {@code (x, y) -> y}.
   */
  @SuppressWarnings("unchecked")
  public static <X, Y extends R, R> BiFunction<X, Y, R>
  Second() { return (BiFunction) SECOND; }

  /**
   * The {@literal true}-predicate, returns {@code true} for all tested values.
   * This is equivalent to {@code (...) -> true}. Can be used where ever a
   * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
   */
  @SuppressWarnings({"MethodNameSameAsClassName", "unchecked"})
  public static <X, Y, E extends Exception> True<X, Y, E>
  True() {
    return True.TRUE;
  }

  /**
   * The {@literal false}-predicate, returns {@code false} for all tested values.
   * This is equivalent to {@code (...) -> false}. Can be used where ever a
   * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
   */
  @SuppressWarnings({"MethodNameSameAsClassName", "unchecked"})
  public static <X, Y, E extends Exception> False<X, Y, E>
  False() {
    return False.FALSE;
  }

  /**
   * The "is {@code null}?"-predicate, returns true iff the tested value is
   * {@code null}. This is equivalent to {@code x -> x == null}.
   */
  public static <X> Predicate<X>
  IsNull() { return castDown(IS_NULL); }

  /**
   * The "is not {@code null}?"-predicate, returns true iff the tested value is
   * not {@code null}. This is equivalent to {@code x -> x != null}.
   */
  public static <X> Predicate<X>
  NonNull() { return castDown(NON_NULL); }

  /**
   * The {@literal equals} predicate, returns true iff arguments equals
   * {@code reference}. This is equivalent to
   * {@code x -> Objects.equals(x, reference)}.
   */
  public static <X, Y> Predicate<X>
  IsEqualTo(Y reference) { return x -> Objects.equals(x, reference); }

  /**
   * The {@literal equals} relation, returns true iff both arguments are equal.
   * This is equivalent to {@code (x, y) -> Objects.equals(x, y)}.
   */
  public static <X, Y> BiPredicate<X, Y>
  Equals() { return castDown(EQUALS); }

  /**
   * Convenience method to ensure given lambda implements {@link Runnable}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link Runnable} or a {@link Supplier} or a similar functional interface.
   * Writing {@code runnable(...)} is equivalent to {@code ((Runnable) ...)}.
   */
  public static Runnable
  runnable(Runnable runnable) { return runnable; }

  /**
   * Convenience method to ensure given lambda implements {@link Consumer}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link Consumer} or a {@link Function} or a similar functional interface.
   * Writing {@code consumer(...)} is equivalent to {@code ((Consumer<X>) ...)},
   * where {@code X} is inferred from the context and the given lambda expression.
   */
  public static <X> Consumer<X>
  consumer(Consumer<? super X> consumer) { return castDown(consumer); }

  /**
   * Convenience method to ensure given lambda implements {@link BiConsumer}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link BiConsumer} or a {@link BiFunction} or a similar functional interface.
   * Writing {@code biConsumer(...)} is equivalent to {@code ((BiConsumer<X, Y>) ...)},
   * where {@code X} and {@code Y} are inferred from the context and the given lambda expression.
   */
  public static <X, Y> BiConsumer<X, Y>
  biConsumer(BiConsumer<? super X, ? super Y> biConsumer) { return castDown(biConsumer); }

  /**
   * Convenience method to ensure given lambda implements {@link Supplier}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link Runnable} or a {@link Supplier} or a similar functional interface.
   * Writing {@code supplier(...)} is equivalent to {@code ((Supplier<R>) ...)},
   * where {@code R} is inferred from the context and the given lambda expression.
   */
  public static <R> Supplier<R>
  supplier(Supplier<? extends R> supplier) { return castDown(supplier); }

  /**
   * Convenience method to ensure given lambda implements {@link Function}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link Function} or a {@link Consumer} or a similar functional interface.
   * Writing {@code function(...)} is equivalent to {@code ((Function<X, R>) ...)},
   * where {@code X} and {@code R} are inferred from the context and the given lambda expression.
   */
  public static <X, R> Function<X, R>
  function(Function<? super X, ? extends R> function) { return castDown(function); }

  /**
   * Convenience method to ensure given lambda implements {@link BiFunction}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link BiFunction} or a {@link BiConsumer} or a similar functional interface.
   * Writing {@code biFunction(...)} is equivalent to {@code ((BiFunction<X, Y, R>) ...)},
   * where {@code X}, {@code Y} and {@code R} are inferred from the context and the given lambda expression.
   */
  public static <X, Y, R> BiFunction<X, Y, R>
  biFunction(BiFunction<? super X, ? super Y, ? extends R> biFunction) { return castDown(biFunction); }

  /**
   * Convenience method to ensure given lambda implements {@link Predicate}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link Predicate} or a {@link Function} or a similar functional interface.
   * Writing {@code predicate(...)} is equivalent to {@code ((Predicate<X>) ...)},
   * where {@code X} is inferred from the context and the given lambda expression.
   */
  public static <X> Predicate<X>
  predicate(Predicate<? super X> predicate) { return castDown(predicate); }

  /**
   * Convenience method to ensure given lambda implements {@link BiPredicate}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link BiPredicate} or a {@link BiFunction} or a similar functional interface.
   * Writing {@code biPredicate(...)} is equivalent to {@code ((BiPredicate<X, Y>) ...)},
   * where {@code X}, {@code Y} are inferred from the context and the given lambda expression.
   */
  public static <X, Y> BiPredicate<X, Y>
  biPredicate(BiPredicate<? super X, ? super Y> biPredicate) { return castDown(biPredicate); }

  /**
   * Convenience method to ensure given {@link Function} lambda fulfills the
   * criteria of an unary operation, i.e. argument and result type match.
   */
  public static <X> Function<X, X>
  unaryOp(Function<? super X, ? extends X> operator) { return castDown(operator); }

  /**
   * Convenience method to ensure given {@link BiFunction} lambda fulfills the
   * criteria of an binary operation, i.e. argument and result types match.
   */
  public static <X> BiFunction<X, X, X>
  binaryOp(BiFunction<? super X, ? super X, ? extends X> operator) { return castDown(operator); }

  /**
   * Convenience method to ensure given {@link BiPredicate} lambda fulfills the
   * criteria of an binary relation, i.e. argument types match.
   */
  public static <X> BiPredicate<X, X>
  relation(BiPredicate<? super X, ? super X> relation) { return castDown(relation); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaConsumerBinder
   */
  public static <X> JavaConsumerBinder<X>
  bind(Consumer<? super X> bound) { return new JavaConsumerBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaBiConsumerBinder
   */
  public static <X, Y> JavaBiConsumerBinder<X, Y>
  bind(BiConsumer<? super X, ? super Y> bound) { return new JavaBiConsumerBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaFunctionBinder
   */
  public static <X, R> JavaFunctionBinder<X, R>
  bind(Function<? super X, ? extends R> bound) { return new JavaFunctionBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaBiFunctionBinder
   */
  public static <X, Y, R> JavaBiFunctionBinder<X, Y, R>
  bind(BiFunction<? super X, ? super Y, ? extends R> bound) { return new JavaBiFunctionBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaPredicateBinder
   */
  public static <X> JavaPredicateBinder<X>
  bind(Predicate<? super X> bound) { return new JavaPredicateBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see JavaBiPredicateBinder
   */
  public static <X, Y> JavaBiPredicateBinder<X, Y>
  bind(BiPredicate<? super X, ? super Y> bound) { return new JavaBiPredicateBinder<>(bound); }

  /**
   * Extend a given {@link Runnable} to a {@link Consumer} ignoring its argument.
   */
  public static Consumer<Object>
  toConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return o -> runnable.run();
  }

  /**
   * Extend a {@link Runnable} to a {@link BiConsumer} ignoring its arguments.
   */
  public static BiConsumer<Object, Object>
  toBiConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.run();
  }

  /**
   * Extend a {@link Consumer} to a {@link BiConsumer} ignoring its second argument.
   */
  public static <X> BiConsumer<X, Object>
  toBiConsumer(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.accept(x);
  }

  /**
   * Extend a {@link Supplier} to a {@link Function} ignoring its argument.
   */
  public static <R> Function<Object, R>
  toFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return o -> supplier.get();
  }

  /**
   * Extend a {@link Supplier} to a {@link BiFunction} ignoring its arguments.
   */
  public static <R> BiFunction<Object, Object, R>
  toBiFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.get();
  }

  /**
   * Extend a {@link Function} to a {@link BiFunction} ignoring its second argument.
   */
  public static <X, Y, R> BiFunction<X, Y, R>
  toBiFunction(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return (x, o) -> function.apply(x);
  }

  /**
   * Extend a {@link BooleanSupplier} to a {@link Predicate} ignoring its argument.
   */
  public static Predicate<Object>
  toPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return o -> supplier.getAsBoolean();
  }

  /**
   * Extend a {@link BooleanSupplier} to a {@link BiPredicate} ignoring its arguments.
   */
  public static BiPredicate<Object, Object>
  toBiPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.getAsBoolean();
  }

  /**
   * Extend a {@link Predicate} to a {@link BiPredicate} ignoring its second argument.
   */
  public static <X> BiPredicate<X, Object>
  toBiPredicate(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.test(x);
  }

  /**
   * Flip arguments of a {@link BiConsumer}
   */
  public static <X, Y> BiConsumer<Y, X>
  flipArguments(BiConsumer<X, Y> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.accept(x, y);
  }

  /**
   * Flip arguments of a {@link BiFunction}
   */
  public static <X, Y, R> BiFunction<Y, X, R>
  flipArguments(BiFunction<X, Y, R> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.apply(x, y);
  }

  /**
   * Flip arguments of a {@link BiPredicate}
   */
  public static <X, Y, R> BiPredicate<Y, X>
  flipArguments(BiPredicate<X, Y> biPredicate) {
    requireNonNull(biPredicate);
    return (y, x) -> biPredicate.test(x, y);
  }

  /**
   * Turn a {@link Runnable} into a {@link LavaRunnable} declaring {@link RuntimeException} as exception limit.
   */
  public static LavaRunnable<RuntimeException>
  toLavaFun(Runnable runnable) {
    requireNonNull(runnable);
    return runnable::run;
  }

  /**
   * Turn a {@link Consumer} into a {@link LavaConsumer} declaring {@link RuntimeException} as exception limit.
   */
  public static <X> LavaConsumer<X, RuntimeException>
  toLavaFun(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return consumer::accept;
  }

  /**
   * Turn a {@link BiConsumer} into a {@link LavaBiConsumer} declaring {@link RuntimeException} as exception limit.
   */
  public static <X, Y> LavaBiConsumer<X, Y, RuntimeException>
  toLavaFun(BiConsumer<? super X, ? super Y> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::accept;
  }

  /**
   * Turn a {@link Supplier} into a {@link LavaSupplier} declaring {@link RuntimeException} as exception limit.
   */
  public static <R> LavaSupplier<R, RuntimeException>
  toLavaFun(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return supplier::get;
  }

  /**
   * Turn a {@link Function} into a {@link LavaFunction} declaring {@link RuntimeException} as exception limit.
   */
  public static <X, R> LavaFunction<X, R, RuntimeException>
  toLavaFun(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return function::apply;
  }

  /**
   * Turn a {@link BiFunction} into a {@link LavaBiFunction} declaring {@link RuntimeException} as exception limit.
   */
  public static <X, Y, R> LavaBiFunction<X, Y, R, RuntimeException>
  toLavaFun(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    requireNonNull(biFunction);
    return biFunction::apply;
  }

  /**
   * Turn a {@link Predicate} into a {@link LavaPredicate} declaring {@link RuntimeException} as exception limit.
   */
  public static <X> LavaPredicate<X, RuntimeException>
  toLavaFun(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return predicate::test;
  }

  /**
   * Turn a {@link BiPredicate} into a {@link LavaBiPredicate} declaring {@link RuntimeException} as exception limit.
   */
  public static <X, Y> LavaBiPredicate<X, Y, RuntimeException>
  toLavaFun(BiPredicate<? super X, ? super Y> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::test;
  }

  /**
   * Safely casts between different {@link Consumer} parametrization.
   *
   * @param <X> accepted input type
   * @param consumer consumer to adopt
   * @return {@code consumer} casted to the compatible parametrization {@link Consumer}{@code <X, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X> Consumer<X> castDown(Consumer<? super X> consumer) {
    return (Consumer<X>) consumer;
  }

  /**
   * Safely casts between different {@link BiConsumer} parametrization.
   *
   * @param <X> first accepted input type
   * @param <Y> second accepted input type
   * @param biConsumer bi-consumer to adopt
   * @return {@code biConsumer} casted to the compatible parametrization {@link BiConsumer}{@code <X, Y, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X, Y> BiConsumer<X, Y> castDown(BiConsumer<? super X, ? super Y> biConsumer) {
    return (BiConsumer<X, Y>) biConsumer;
  }

  /**
   * Safely casts between different {@link Supplier} parametrization.
   *
   * @param <R> required result type
   * @param supplier supplier to adopt
   * @return {@code supplier} casted to the compatible parametrization {@link Supplier}{@code <R, E>}
   */
  @SuppressWarnings("unchecked")
  public static <R> Supplier<R> castDown(Supplier<? extends R> supplier) {
    return (Supplier<R>) supplier;
  }

  /**
   * Safely casts between different {@link Function} parametrization.
   *
   * @param <X> accepted argument type
   * @param <R> required result type
   * @param function function to adopt
   * @return {@code function} casted to the compatible parametrization {@link Function}{@code <X, R, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X, R> Function<X, R> castDown(Function<? super X, ? extends R> function) {
    return (Function<X, R>) function;
  }

  /**
   * Safely casts between different {@link BiFunction} parametrization.
   *
   * @param <X> first accepted argument type
   * @param <Y> second accepted argument type
   * @param <R> required result type
   * @param biFunction bi-function to adopt
   * @return {@code biFunction} casted to the compatible parametrization {@link BiFunction}{@code <X, Y, R, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X, Y, R> BiFunction<X, Y, R> castDown(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    return (BiFunction<X, Y, R>) biFunction;
  }

  /**
   * Safely casts between different {@link Predicate} parametrization.
   *
   * @param <X> accepted argument type
   * @param predicate predicate to adopt
   * @return {@code predicate} casted to the compatible parametrization {@link Predicate}{@code <X, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X> Predicate<X> castDown(Predicate<? super X> predicate) {
    return (Predicate<X>) predicate;
  }

  /**
   * Safely casts between different {@link BiPredicate} parametrization.
   *
   * @param <X> first accepted argument type
   * @param <Y> second accepted argument type
   * @param biPredicate bi-predicate to adopt
   * @return {@code biPredicate} casted to the compatible parametrization {@link BiPredicate}{@code <X, E>}
   */
  @SuppressWarnings("unchecked")
  public static <X, Y> BiPredicate<X, Y> castDown(BiPredicate<? super X, ? super Y> biPredicate) {
    return (BiPredicate<X, Y>) biPredicate;
  }
}