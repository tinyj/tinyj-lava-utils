package org.tinyj.lava;

import org.tinyj.lava.binder.*;
import org.tinyj.lava.fun.*;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Utility class
 */
public class JavaFun {

  protected JavaFun() { assert false : "JavaFun is a utility class, static use only."; }

  public final static
  Runnable NO_OP = NoOp.NoOp();
  public final static
  Supplier<?> NULL = Null.Null();
  public final static
  Function<Object, ?> IDENTITY = Identity.Identity();
  public final static
  Function<Object, String> TO_STRING = ToString.ToString();
  public final static
  BiFunction<Object, Object, ?> FIRST = First.First();
  public final static
  BiFunction<Object, Object, ?> SECOND = Second.Second();
  public final static
  Predicate<Object> TRUE = True.True();
  public final static
  Predicate<Object> FALSE = False.False();
  public final static
  Predicate<Object> IS_NULL = IsNull.IsNull();
  public final static
  Predicate<Object> NON_NULL = NonNull.NonNull();
  public final static
  BiPredicate<Object, Object> EQUALS = Equals.Equals();

  /**
   * The no-operation, does absolutely nothing. This is equivalent to `() -> {}`
   */
  public static Runnable
  NoOp() { return NoOp.NoOp(); }

  /**
   * The constant supplier, always returns `value`. This is equivalent to `() -> value`
   */
  public static <R> Supplier<R>
  Constant(R value) { return new Constant<>(value); }

  /**
   * The `null` supplier, always returns `null`. This is equivalent to `() -> null`
   */
  public static <R> Supplier<R>
  Null() { return Null.Null(); }

  /**
   * The identity function, returns it's argument. This is equivalent to `x -> x`
   */
  public static <X extends R, R> Function<X, R>
  Identity() { return Identity.Identity(); }

  /**
   * The stringify function, returns the string representation of its argument. This is equivalent to `x -> Objects.toString(x)`
   */
  public static <X> Function<X, String>
  ToString() { return ToString.ToString(); }

  /**
   * Binary function returning its first argument. This is equivalent to `(x, y) -> x`
   */
  public static <X extends R, Y, R> BiFunction<X, Y, R>
  First() { return First.First(); }

  /**
   * Binary function returning its second argument. This is equivalent to `(x, y) -> y`
   */
  public static <X, Y extends R, R> BiFunction<X, Y, R>
  Second() { return Second.Second(); }

  /**
   * The _true_-predicate, returns `true` for all tested values. This is equivalent to `x -> true`
   */
  public static <X> Predicate<X>
  True() { return True.True(); }

  /**
   * The _false_-predicate, returns `false` for all tested values. This is equivalent to `x -> false`
   */
  public static <X> Predicate<X>
  False() { return False.False(); }

  /**
   * The _"is `null`?"_-predicate, returns true iff the tested value is `null`. This is equivalent to `x -> x == null`
   */
  public static <X> Predicate<X>
  IsNull() { return IsNull.IsNull(); }

  /**
   * The _"is not `null`?"_-predicate, returns true iff the tested value is not `null`. This is equivalent to `x -> x != null`
   */
  public static <X> Predicate<X>
  NonNull() { return NonNull.NonNull(); }

  /**
   * The _equals_ predicate, returns true iff arguments equals `reference`. This is equivalent to `x -> Objects.equals(x, reference)`
   */
  public static <X, Y> Predicate<X>
  IsEqualTo(Y reference) { return new IsEqualTo<>(reference); }

  /**
   * The _equals_ relation, returns true iff both arguments are equal. This is equivalent to `(x, y) -> Objects.equals(x, y)`
   */
  public static <X, Y> BiPredicate<X, Y>
  Equals() { return Equals.Equals(); }

  /**
   * Convenience method to ensure given lambda implements (#Runnable).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#Runnable) or a (#Supplier) or a similar functional interface.
   * Writing `runnable(...)` is equivalent to `((Runnable) ...)`
   */
  public static Runnable
  runnable(Runnable runnable) { return runnable; }

  /**
   * Convenience method to ensure given lambda implements (#Consumer).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#Consumer) or a (#Function) or a similar functional interface.
   * Writing `consumer(...)` is equivalent to `((Consumer<X>) ...)`,
   * where `X` is inferred from the context and the given lambda expression.
   */
  public static <X> Consumer<X>
  consumer(Consumer<? super X> consumer) { return castDown(consumer); }

  /**
   * Convenience method to ensure given lambda implements (#BiConsumer).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#BiConsumer) or a (#BiFunction) or a similar functional interface.
   * Writing `biConsumer(...)` is equivalent to `((BiConsumer<X, Y>) ...)`,
   * where `X` and `Y` are inferred from the context and the given lambda expression.
   */
  public static <X, Y> BiConsumer<X, Y>
  biConsumer(BiConsumer<? super X, ? super Y> biConsumer) { return castDown(biConsumer); }

  /**
   * Convenience method to ensure given lambda implements (#Supplier).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#Runnable) or a (#Supplier) or a similar functional interface.
   * Writing `supplier(...)` is equivalent to `((Supplier<R>) ...)`,
   * where `R` is inferred from the context and the given lambda expression.
   */
  public static <R> Supplier<R>
  supplier(Supplier<? extends R> supplier) { return castDown(supplier); }

  /**
   * Convenience method to ensure given lambda implements (#Function).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#Function) or a (#Consumer) or a similar functional interface.
   * Writing `function(...)` is equivalent to `((Function<X, R>) ...)`,
   * where `X` and `R` are inferred from the context and the given lambda expression.
   */
  public static <X, R> Function<X, R>
  function(Function<? super X, ? extends R> function) { return castDown(function); }

  /**
   * Convenience method to ensure given lambda implements (#BiFunction).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#BiFunction) or a (#BiConsumer) or a similar functional interface.
   * Writing `biFunction(...)` is equivalent to `((BiFunction<X, Y, R>) ...)`,
   * where `X`, `Y` and `R` are inferred from the context and the given lambda expression.
   */
  public static <X, Y, R> BiFunction<X, Y, R>
  biFunction(BiFunction<? super X, ? super Y, ? extends R> biFunction) { return castDown(biFunction); }

  /**
   * Convenience method to ensure given lambda implements (#Predicate).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#Predicate) or a (#Function) or a similar functional interface.
   * Writing `predicate(...)` is equivalent to `((Predicate<X>) ...)`,
   * where `X` is inferred from the context and the given lambda expression.
   */
  public static <X> Predicate<X>
  predicate(Predicate<? super X> predicate) { return castDown(predicate); }

  /**
   * Convenience method to ensure given lambda implements (#BiPredicate).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#BiPredicate) or a (#BiFunction) or a similar functional interface.
   * Writing `biPredicate(...)` is equivalent to `((BiPredicate<X, Y>) ...)`,
   * where `X`, `Y` are inferred from the context and the given lambda expression.
   */
  public static <X, Y> BiPredicate<X, Y>
  biPredicate(BiPredicate<? super X, ? super Y> biPredicate) { return castDown(biPredicate); }

  /**
   * Convenience method to ensure given (#Function) lambda fulfills the
   * criteria of an unary operation, i.e. argument and result type match.
   */
  public static <X> Function<X, X>
  unaryOp(Function<? super X, ? extends X> operator) { return castDown(operator); }

  /**
   * Convenience method to ensure given (#BiFunction) lambda fulfills the
   * criteria of an binary operation, i.e. argument and result types match.
   */
  public static <X> BiFunction<X, X, X>
  binaryOp(BiFunction<? super X, ? super X, ? extends X> operator) { return castDown(operator); }

  /**
   * Convenience method to ensure given (#BiPredicate) lambda fulfills the
   * criteria of an binary relation, i.e. argument types match.
   */
  public static <X> BiPredicate<X, X>
  relation(BiPredicate<? super X, ? super X> relation) { return castDown(relation); }

  /**
   * Start currying `bound`. See (#JavaConsumerBinder).
   */
  public static <X> JavaConsumerBinder<X>
  bind(Consumer<? super X> bound) { return new JavaConsumerBinder<>(bound); }

  /**
   * Start currying `bound`. See (#JavaBiConsumerBinder).
   */
  public static <X, Y> JavaBiConsumerBinder<X, Y>
  bind(BiConsumer<? super X, ? super Y> bound) { return new JavaBiConsumerBinder<>(bound); }

  /**
   * Start currying `bound`. See (#JavaFunctionBinder).
   */
  public static <X, R> JavaFunctionBinder<X, R>
  bind(Function<? super X, ? extends R> bound) { return new JavaFunctionBinder<>(bound); }

  /**
   * Start currying `bound`. See (#JavaBiFunctionBinder).
   */
  public static <X, Y, R> JavaBiFunctionBinder<X, Y, R>
  bind(BiFunction<? super X, ? super Y, ? extends R> bound) { return new JavaBiFunctionBinder<>(bound); }

  /**
   * Start currying `bound`. See (#JavaPredicateBinder).
   */
  public static <X> JavaPredicateBinder<X>
  bind(Predicate<? super X> bound) { return new JavaPredicateBinder<>(bound); }

  /**
   * Start currying `bound`. See (#JavaBiPredicateBinder).
   */
  public static <X, Y> JavaBiPredicateBinder<X, Y>
  bind(BiPredicate<? super X, ? super Y> bound) { return new JavaBiPredicateBinder<>(bound); }

  /**
   * Extend a given (#Runnable) to a (#Consumer) ignoring its argument.
   */
  public Consumer<Object>
  toConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return o -> runnable.run();
  }

  /**
   * Extend a (#Runnable) to a (#BiConsumer) ignoring its arguments.
   */
  public BiConsumer<Object, Object>
  toBiConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.run();
  }

  /**
   * Extend a (#Consumer) to a (#BiConsumer) ignoring its second argument.
   */
  public <X> BiConsumer<X, Object>
  toBiConsumer(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.accept(x);
  }

  /**
   * Extend a (#Supplier) to a (#Function) ignoring its argument.
   */
  public <R> Function<Object, R>
  toFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return o -> supplier.get();
  }

  /**
   * Extend a (#Supplier) to a (#BiFunction) ignoring its arguments.
   */
  public <R> BiFunction<Object, Object, R>
  toBiFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.get();
  }

  /**
   * Extend a (#Function) to a (#BiFunction) ignoring its second argument.
   */
  public <X, R> BiFunction<X, Object, R>
  toBiFunction(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return (x, o) -> function.apply(x);
  }

  /**
   * Extend a (#BooleanSupplier) to a (#Predicate) ignoring its argument.
   */
  public Predicate<Object>
  toPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return o -> supplier.getAsBoolean();
  }

  /**
   * Extend a (#BooleanSupplier) to a (#BiPredicate) ignoring its arguments.
   */
  public BiPredicate<Object, Object>
  toBiPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.getAsBoolean();
  }

  /**
   * Extend a (#Predicate) to a (#BiPredicate) ignoring its second argument.
   */
  public <X> BiPredicate<X, Object>
  toBiPredicate(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.test(x);
  }

  /**
   * Flip arguments of a (#BiConsumer)
   */
  public static <X, Y> BiConsumer<Y, X>
  flipArguments(BiConsumer<X, Y> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.accept(x, y);
  }

  /**
   * Flip arguments of a (#BiFunction)
   */
  public static <X, Y, R> BiFunction<Y, X, R>
  flipArguments(BiFunction<X, Y, R> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.apply(x, y);
  }

  /**
   * Flip arguments of a (#BiPredicate)
   */
  public static <X, Y, R> BiPredicate<Y, X>
  flipArguments(BiPredicate<X, Y> biPredicate) {
    requireNonNull(biPredicate);
    return (y, x) -> biPredicate.test(x, y);
  }

  /**
   * Turn a (#Runnable) into a (#LavaRunnable) declaring (#RuntimeException) as exception limit.
   */
  public static LavaRunnable<RuntimeException>
  toLavaFun(Runnable runnable) {
    requireNonNull(runnable);
    return runnable::run;
  }

  /**
   * Turn a (#Consumer) into a (#LavaConsumer) declaring (#RuntimeException) as exception limit.
   */
  public static <X> LavaConsumer<X, RuntimeException>
  toLavaFun(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return consumer::accept;
  }

  /**
   * Turn a (#BiConsumer) into a (#LavaBiConsumer) declaring (#RuntimeException) as exception limit.
   */
  public static <X, Y> LavaBiConsumer<X, Y, RuntimeException>
  toLavaFun(BiConsumer<? super X, ? super Y> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::accept;
  }

  /**
   * Turn a (#Supplier) into a (#LavaSupplier) declaring (#RuntimeException) as exception limit.
   */
  public static <R> LavaSupplier<R, RuntimeException>
  toLavaFun(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return supplier::get;
  }

  /**
   * Turn a (#Function) into a (#LavaFunction) declaring (#RuntimeException) as exception limit.
   */
  public static <X, R> LavaFunction<X, R, RuntimeException>
  toLavaFun(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return function::apply;
  }

  /**
   * Turn a (#BiFunction) into a (#LavaBiFunction) declaring (#RuntimeException) as exception limit.
   */
  public static <X, Y, R> LavaBiFunction<X, Y, R, RuntimeException>
  toLavaFun(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    requireNonNull(biFunction);
    return biFunction::apply;
  }

  /**
   * Turn a (#Predicate) into a (#LavaPredicate) declaring (#RuntimeException) as exception limit.
   */
  public static <X> LavaPredicate<X, RuntimeException>
  toLavaFun(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return predicate::test;
  }

  /**
   * Turn a (#BiPredicate) into a (#LavaBiPredicate) declaring (#RuntimeException) as exception limit.
   */
  public static <X, Y> LavaBiPredicate<X, Y, RuntimeException>
  toLavaFun(BiPredicate<? super X, ? super Y> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::test;
  }

  /**
   * Safely casts between different (#Consumer) parametrization
   *
   * @param <X> accepted input type
   * @param consumer consumer to adopt
   * @return `consumer` casted to the compatible parametrization (#Consumer)`<X, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X> Consumer<X> castDown(Consumer<? super X> consumer) {
    return (Consumer<X>) consumer;
  }

  /**
   * Safely casts between different (#BiConsumer) parametrization
   *
   * @param <X> first accepted input type
   * @param <Y> second accepted input type
   * @param biConsumer bi-consumer to adopt
   * @return `biConsumer` casted to the compatible parametrization (#BiConsumer)`<X, Y, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X, Y> BiConsumer<X, Y> castDown(BiConsumer<? super X, ? super Y> biConsumer) {
    return (BiConsumer<X, Y>) biConsumer;
  }

  /**
   * Safely casts between different (#Supplier) parametrization
   *
   * @param <R> required result type
   * @param supplier supplier to adopt
   * @return `supplier` casted to the compatible parametrization (#Supplier)`<R, E>`
   */
  @SuppressWarnings("unchecked")
  public static <R> Supplier<R> castDown(Supplier<? extends R> supplier) {
    return (Supplier<R>) supplier;
  }

  /**
   * Safely casts between different (#Function) parametrization
   *
   * @param <X> accepted argument type
   * @param <R> required result type
   * @param function function to adopt
   * @return `function` casted to the compatible parametrization (#Function)`<X, R, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X, R> Function<X, R> castDown(Function<? super X, ? extends R> function) {
    return (Function<X, R>) function;
  }

  /**
   * Safely casts between different (#BiFunction) parametrization
   *
   * @param <X> first accepted argument type
   * @param <Y> second accepted argument type
   * @param <R> required result type
   * @param biFunction bi-function to adopt
   * @return `biFunction` casted to the compatible parametrization (#BiFunction)`<X, Y, R, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X, Y, R> BiFunction<X, Y, R> castDown(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    return (BiFunction<X, Y, R>) biFunction;
  }

  /**
   * Safely casts between different (#Predicate) parametrization
   *
   * @param <X> accepted argument type
   * @param predicate predicate to adopt
   * @return `predicate` casted to the compatible parametrization (#Predicate)`<X, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X> Predicate<X> castDown(Predicate<? super X> predicate) {
    return (Predicate<X>) predicate;
  }

  /**
   * Safely casts between different (#BiPredicate)parametrization
   *
   * @param <X> first accepted argument type
   * @param <Y> second accepted argument type
   * @param biPredicate bi-predicate to adopt
   * @return `biPredicate` casted to the compatible parametrization (#BiPredicate)`<X, E>`
   */
  @SuppressWarnings("unchecked")
  public static <X, Y> BiPredicate<X, Y> castDown(BiPredicate<? super X, ? super Y> biPredicate) {
    return (BiPredicate<X, Y>) biPredicate;
  }
}
