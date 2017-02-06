package org.tinyj.lava;

import org.tinyj.lava.binder.JavaBiConsumerBinder;
import org.tinyj.lava.binder.JavaBiFunctionBinder;
import org.tinyj.lava.binder.JavaConsumerBinder;
import org.tinyj.lava.binder.JavaFunctionBinder;
import org.tinyj.lava.fun.*;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

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

  public static Runnable
  NoOp() { return NoOp.NoOp(); }

  public static <R> Supplier<R>
  Constant(R constant) { return new Constant<>(constant); }

  public static <R> Supplier<R>
  Null() { return Null.Null(); }

  public static <X extends R, R> Function<X, R>
  Identity() { return Identity.Identity(); }

  public static <X> Function<X, String>
  ToString() { return ToString.ToString(); }

  public static <X extends R, Y, R> BiFunction<X, Y, R>
  First() { return First.First(); }

  public static <X, Y extends R, R> BiFunction<X, Y, R>
  Second() { return Second.Second(); }

  public static <X> Predicate<X>
  True() { return True.True(); }

  public static <X> Predicate<X>
  False() { return False.False(); }

  public static <X> Predicate<X>
  IsNull() { return IsNull.IsNull(); }

  public static <X> Predicate<X>
  NonNull() { return NonNull.NonNull(); }

  public static <X, Y> Predicate<X>
  IsEqualTo(Y y) { return new IsEqualTo<>(y); }

  public static <X, Y> BiPredicate<X, Y>
  Equals() { return Equals.Equals(); }

  public static Runnable
  runnable(Runnable runnable) { return runnable; }

  public static <X> Consumer<X>
  consumer(Consumer<? super X> consumer) { return castDown(consumer); }

  public static <X, Y> BiConsumer<X, Y>
  biConsumer(BiConsumer<? super X, ? super Y> biConsumer) { return castDown(biConsumer); }

  public static <R> Supplier<R>
  supplier(Supplier<? extends R> supplier) { return castDown(supplier); }

  public static <X, R> Function<X, R>
  function(Function<? super X, ? extends R> function) { return castDown(function); }

  public static <X, Y, R> BiFunction<X, Y, R>
  biFunction(BiFunction<? super X, ? super Y, ? extends R> biFunction) { return castDown(biFunction); }

  public static <X> Predicate<X>
  predicate(Predicate<? super X> predicate) { return castDown(predicate); }

  public static <X, Y> BiPredicate<X, Y>
  biPredicate(BiPredicate<? super X, ? super Y> biPredicate) { return castDown(biPredicate); }

  public static <X> Function<X, X>
  unaryOp(Function<? super X, ? extends X> operator) { return castDown(operator); }

  public static <X> BiFunction<X, X, X>
  binaryOp(BiFunction<? super X, ? super X, ? extends X> operator) { return castDown(operator); }

  public static <X> BiPredicate<X, X>
  relation(BiPredicate<? super X, ? super X> relation) { return castDown(relation); }

  public static <X> JavaConsumerBinder<X>
  bind(Consumer<? super X> bound) { return new JavaConsumerBinder<>(bound); }

  public static <X, Y> JavaBiConsumerBinder<X, Y>
  bind(BiConsumer<? super X, ? super Y> bound) { return new JavaBiConsumerBinder<>(bound); }

  public static <X, R> JavaFunctionBinder<X, R>
  bind(Function<? super X, ? extends R> bound) { return new JavaFunctionBinder<>(bound); }

  public static <X, Y, R> JavaBiFunctionBinder<X, Y, R>
  bind(BiFunction<? super X, ? super Y, ? extends R> bound) { return new JavaBiFunctionBinder<>(bound); }

  public Consumer<Object>
  toConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return o -> runnable.run();
  }

  public BiConsumer<Object, Object>
  toBiConsumer(Runnable runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.run();
  }

  public <X> BiConsumer<X, Object>
  toBiConsumer(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.accept(x);
  }

  public <R> Function<Object, R>
  toFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return o -> supplier.get();
  }

  public <R> BiFunction<Object, Object, R>
  toBiFunction(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.get();
  }

  public <X, R> BiFunction<X, Object, R>
  toBiFunction(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return (x, o) -> function.apply(x);
  }

  public Predicate<Object>
  toPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return o -> supplier.getAsBoolean();
  }

  public BiPredicate<Object, Object>
  toBiPredicate(BooleanSupplier supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.getAsBoolean();
  }

  public <X> BiPredicate<X, Object>
  toBiPredicate(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.test(x);
  }

  public static LavaRunnable<RuntimeException>
  toLavaFun(Runnable runnable) {
    requireNonNull(runnable);
    return runnable::run;
  }

  public static <X> LavaConsumer<X, RuntimeException>
  toLavaFun(Consumer<? super X> consumer) {
    requireNonNull(consumer);
    return consumer::accept;
  }

  public static <X, Y> LavaBiConsumer<X, Y, RuntimeException>
  toLavaFun(BiConsumer<? super X, ? super Y> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::accept;
  }

  public static <R> LavaSupplier<R, RuntimeException>
  toLavaFun(Supplier<? extends R> supplier) {
    requireNonNull(supplier);
    return supplier::get;
  }

  public static <X, R> LavaFunction<X, R, RuntimeException>
  toLavaFun(Function<? super X, ? extends R> function) {
    requireNonNull(function);
    return function::apply;
  }

  public static <X, Y, R> LavaBiFunction<X, Y, R, RuntimeException>
  toLavaFun(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    requireNonNull(biFunction);
    return biFunction::apply;
  }

  public static <X> LavaPredicate<X, RuntimeException>
  toLavaFun(Predicate<? super X> predicate) {
    requireNonNull(predicate);
    return predicate::test;
  }

  public static <X, Y> LavaBiPredicate<X, Y, RuntimeException>
  toLavaFun(BiPredicate<? super X, ? super Y> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::test;
  }

  public static <X, Y> BiConsumer<Y, X>
  flipArguments(BiConsumer<X, Y> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.accept(x, y);
  }

  public static <X, Y, R> BiFunction<Y, X, R>
  flipArguments(BiFunction<X, Y, R> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.apply(x, y);
  }

  @SuppressWarnings("unchecked")
  public static <X> Consumer<X> castDown(Consumer<? super X> consumer) {
    return (Consumer<X>) consumer;
  }

  @SuppressWarnings("unchecked")
  public static <X, Y> BiConsumer<X, Y> castDown(BiConsumer<? super X, ? super Y> biConsumer) {
    return (BiConsumer<X, Y>) biConsumer;
  }

  @SuppressWarnings("unchecked")
  public static <R> Supplier<R> castDown(Supplier<? extends R> supplier) {
    return (Supplier<R>) supplier;
  }

  @SuppressWarnings("unchecked")
  public static <X, R> Function<X, R> castDown(Function<? super X, ? extends R> function) {
    return (Function<X, R>) function;
  }

  @SuppressWarnings("unchecked")
  public static <X, Y, R> BiFunction<X, Y, R> castDown(BiFunction<? super X, ? super Y, ? extends R> biFunction) {
    return (BiFunction<X, Y, R>) biFunction;
  }

  @SuppressWarnings("unchecked")
  public static <X> Predicate<X> castDown(Predicate<? super X> predicate) {
    return (Predicate<X>) predicate;
  }

  @SuppressWarnings("unchecked")
  public static <X, Y> BiPredicate<X, Y> castDown(BiPredicate<? super X, ? super Y> biPredicate) {
    return (BiPredicate<X, Y>) biPredicate;
  }
}
