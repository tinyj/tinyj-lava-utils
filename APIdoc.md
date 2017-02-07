## org.tinyj.lava

### JavaFun
_[(src)](src/main/java/org/tinyj/lava/JavaFun.java)_

Utility class

**`NoOp()`**  
⇒ *`Runnable`*  
The no-operation, does absolutely nothing. This is equivalent to `() -> {}`

**`Constant(R value)`**  
⇒ *`Supplier<R>`*  
The constant supplier, always returns `value`. This is equivalent to `() -> value`

**`Null()`**  
⇒ *`Supplier<R>`*  
The `null` supplier, always returns `null`. This is equivalent to `() -> null`

**`Identity()`**  
⇒ *`Function<X, R>`*  
The identity function, returns it's argument. This is equivalent to `x -> x`

**`ToString()`**  
⇒ *`Function<X, String>`*  
The stringify function, returns the string representation of its argument. This is equivalent to `x -> Objects.toString(x)`

**`First()`**  
⇒ *`BiFunction<X, Y, R>`*  
Binary function returning its first argument. This is equivalent to `(x, y) -> x`

**`Second()`**  
⇒ *`BiFunction<X, Y, R>`*  
Binary function returning its second argument. This is equivalent to `(x, y) -> y`

**`True()`**  
⇒ *`Predicate<X>`*  
The _true_-predicate, returns `true` for all tested values. This is equivalent to `x -> true`

**`False()`**  
⇒ *`Predicate<X>`*  
The _false_-predicate, returns `false` for all tested values. This is equivalent to `x -> false`

**`IsNull()`**  
⇒ *`Predicate<X>`*  
The _"is `null`?"_-predicate, returns true iff the tested value is `null`. This is equivalent to `x -> x == null`

**`NonNull()`**  
⇒ *`Predicate<X>`*  
The _"is not `null`?"_-predicate, returns true iff the tested value is not `null`. This is equivalent to `x -> x != null`

**`IsEqualTo(Y reference)`**  
⇒ *`Predicate<X>`*  
The _equals_ predicate, returns true iff arguments equals `reference`. This is equivalent to `x -> Objects.equals(x, reference)`

**`Equals()`**  
⇒ *`BiPredicate<X, Y>`*  
The _equals_ relation, returns true iff both arguments are equal. This is equivalent to `(x, y) -> Objects.equals(x, y)`

**`runnable(Runnable runnable)`**  
⇒ *`Runnable`*  
Convenience method to ensure given lambda implements (#Runnable).
 This may be necessary e.g. when a method is overloaded to take either
 a (#Runnable) or a (#Supplier) or a similar functional interface.
 Writing `runnable(...)` is equivalent to `((Runnable) ...)`

**`consumer(Consumer<? super X> consumer)`**  
⇒ *`Consumer<X>`*  
Convenience method to ensure given lambda implements (#Consumer).
 This may be necessary e.g. when a method is overloaded to take either
 a (#Consumer) or a (#Function) or a similar functional interface.
 Writing `consumer(...)` is equivalent to `((Consumer<X>) ...)`,
 where `X` is inferred from the context and the given lambda expression.

**`biConsumer(BiConsumer<? super X, ? super Y> biConsumer)`**  
⇒ *`BiConsumer<X, Y>`*  
Convenience method to ensure given lambda implements (#BiConsumer).
 This may be necessary e.g. when a method is overloaded to take either
 a (#BiConsumer) or a (#BiFunction) or a similar functional interface.
 Writing `biConsumer(...)` is equivalent to `((BiConsumer<X, Y>) ...)`,
 where `X` and `Y` are inferred from the context and the given lambda expression.

**`supplier(Supplier<? extends R> supplier)`**  
⇒ *`Supplier<R>`*  
Convenience method to ensure given lambda implements (#Supplier).
 This may be necessary e.g. when a method is overloaded to take either
 a (#Runnable) or a (#Supplier) or a similar functional interface.
 Writing `supplier(...)` is equivalent to `((Supplier<R>) ...)`,
 where `R` is inferred from the context and the given lambda expression.

**`function(Function<? super X, ? extends R> function)`**  
⇒ *`Function<X, R>`*  
Convenience method to ensure given lambda implements (#Function).
 This may be necessary e.g. when a method is overloaded to take either
 a (#Function) or a (#Consumer) or a similar functional interface.
 Writing `function(...)` is equivalent to `((Function<X, R>) ...)`,
 where `X` and `R` are inferred from the context and the given lambda expression.

**`biFunction(BiFunction<? super X, ? super Y, ? extends R> biFunction)`**  
⇒ *`BiFunction<X, Y, R>`*  
Convenience method to ensure given lambda implements (#BiFunction).
 This may be necessary e.g. when a method is overloaded to take either
 a (#BiFunction) or a (#BiConsumer) or a similar functional interface.
 Writing `biFunction(...)` is equivalent to `((BiFunction<X, Y, R>) ...)`,
 where `X`, `Y` and `R` are inferred from the context and the given lambda expression.

**`predicate(Predicate<? super X> predicate)`**  
⇒ *`Predicate<X>`*  
Convenience method to ensure given lambda implements (#Predicate).
 This may be necessary e.g. when a method is overloaded to take either
 a (#Predicate) or a (#Function) or a similar functional interface.
 Writing `predicate(...)` is equivalent to `((Predicate<X>) ...)`,
 where `X` is inferred from the context and the given lambda expression.

**`biPredicate(BiPredicate<? super X, ? super Y> biPredicate)`**  
⇒ *`BiPredicate<X, Y>`*  
Convenience method to ensure given lambda implements (#BiPredicate).
 This may be necessary e.g. when a method is overloaded to take either
 a (#BiPredicate) or a (#BiFunction) or a similar functional interface.
 Writing `biPredicate(...)` is equivalent to `((BiPredicate<X, Y>) ...)`,
 where `X`, `Y` are inferred from the context and the given lambda expression.

**`unaryOp(Function<? super X, ? extends X> operator)`**  
⇒ *`Function<X, X>`*  
Convenience method to ensure given (#Function) lambda fulfills the
 criteria of an unary operation, i.e. argument and result type match.

**`binaryOp(BiFunction<? super X, ? super X, ? extends X> operator)`**  
⇒ *`BiFunction<X, X, X>`*  
Convenience method to ensure given (#BiFunction) lambda fulfills the
 criteria of an binary operation, i.e. argument and result types match.

**`relation(BiPredicate<? super X, ? super X> relation)`**  
⇒ *`BiPredicate<X, X>`*  
Convenience method to ensure given (#BiPredicate) lambda fulfills the
 criteria of an binary relation, i.e. argument types match.

**`toConsumer(Runnable runnable)`**  
⇒ *`Consumer<Object>`*  
Extend a given (#Runnable) to a (#Consumer) ignoring its argument.

**`toBiConsumer(Runnable runnable)`**  
⇒ *`BiConsumer<Object, Object>`*  
Extend a (#Runnable) to a (#BiConsumer) ignoring its arguments.

**`toBiConsumer(Consumer<? super X> consumer)`**  
⇒ *`BiConsumer<X, Object>`*  
Extend a (#Consumer) to a (#BiConsumer) ignoring its second argument.

**`toFunction(Supplier<? extends R> supplier)`**  
⇒ *`Function<Object, R>`*  
Extend a (#Supplier) to a (#Function) ignoring its argument.

**`toBiFunction(Supplier<? extends R> supplier)`**  
⇒ *`BiFunction<Object, Object, R>`*  
Extend a (#Supplier) to a (#BiFunction) ignoring its arguments.

**`toBiFunction(Function<? super X, ? extends R> function)`**  
⇒ *`BiFunction<X, Object, R>`*  
Extend a (#Function) to a (#BiFunction) ignoring its second argument.

**`toPredicate(BooleanSupplier supplier)`**  
⇒ *`Predicate<Object>`*  
Extend a (#BooleanSupplier) to a (#Predicate) ignoring its argument.

**`toBiPredicate(BooleanSupplier supplier)`**  
⇒ *`BiPredicate<Object, Object>`*  
Extend a (#BooleanSupplier) to a (#BiPredicate) ignoring its arguments.

**`toBiPredicate(Predicate<? super X> predicate)`**  
⇒ *`BiPredicate<X, Object>`*  
Extend a (#Predicate) to a (#BiPredicate) ignoring its second argument.

**`flipArguments(BiConsumer<X, Y> biConsumer)`**  
⇒ *`BiConsumer<Y, X>`*  
Flip arguments of a (#BiConsumer)

**`flipArguments(BiFunction<X, Y, R> biFunction)`**  
⇒ *`BiFunction<Y, X, R>`*  
Flip arguments of a (#BiFunction)

**`flipArguments(BiPredicate<X, Y> biPredicate)`**  
⇒ *`BiPredicate<Y, X>`*  
Flip arguments of a (#BiPredicate)

**`toLavaFun(Runnable runnable)`**  
⇒ *`<any>`*  
Turn a (#Runnable) into a (#LavaRunnable) declaring (#RuntimeException) as exception limit.

**`toLavaFun(Consumer<? super X> consumer)`**  
⇒ *`<any>`*  
Turn a (#Consumer) into a (#LavaConsumer) declaring (#RuntimeException) as exception limit.

**`toLavaFun(BiConsumer<? super X, ? super Y> biConsumer)`**  
⇒ *`<any>`*  
Turn a (#BiConsumer) into a (#LavaBiConsumer) declaring (#RuntimeException) as exception limit.

**`toLavaFun(Supplier<? extends R> supplier)`**  
⇒ *`<any>`*  
Turn a (#Supplier) into a (#LavaSupplier) declaring (#RuntimeException) as exception limit.

**`toLavaFun(Function<? super X, ? extends R> function)`**  
⇒ *`<any>`*  
Turn a (#Function) into a (#LavaFunction) declaring (#RuntimeException) as exception limit.

**`toLavaFun(BiFunction<? super X, ? super Y, ? extends R> biFunction)`**  
⇒ *`<any>`*  
Turn a (#BiFunction) into a (#LavaBiFunction) declaring (#RuntimeException) as exception limit.

**`toLavaFun(Predicate<? super X> predicate)`**  
⇒ *`<any>`*  
Turn a (#Predicate) into a (#LavaPredicate) declaring (#RuntimeException) as exception limit.

**`toLavaFun(BiPredicate<? super X, ? super Y> biPredicate)`**  
⇒ *`<any>`*  
Turn a (#BiPredicate) into a (#LavaBiPredicate) declaring (#RuntimeException) as exception limit.

**`castDown(Consumer<? super X> consumer)`**  
⇒ *`Consumer<X>`* _(`consumer` casted to the compatible parametrization (#Consumer)`<X, E>`)_  
Safely casts between different (#Consumer) parametrization

**`castDown(BiConsumer<? super X, ? super Y> biConsumer)`**  
⇒ *`BiConsumer<X, Y>`* _(`biConsumer` casted to the compatible parametrization (#BiConsumer)`<X, Y, E>`)_  
Safely casts between different (#BiConsumer) parametrization

**`castDown(Supplier<? extends R> supplier)`**  
⇒ *`Supplier<R>`* _(`supplier` casted to the compatible parametrization (#Supplier)`<R, E>`)_  
Safely casts between different (#Supplier) parametrization

**`castDown(Function<? super X, ? extends R> function)`**  
⇒ *`Function<X, R>`* _(`function` casted to the compatible parametrization (#Function)`<X, R, E>`)_  
Safely casts between different (#Function) parametrization

**`castDown(BiFunction<? super X, ? super Y, ? extends R> biFunction)`**  
⇒ *`BiFunction<X, Y, R>`* _(`biFunction` casted to the compatible parametrization (#BiFunction)`<X, Y, R, E>`)_  
Safely casts between different (#BiFunction) parametrization

**`castDown(Predicate<? super X> predicate)`**  
⇒ *`Predicate<X>`* _(`predicate` casted to the compatible parametrization (#Predicate)`<X, E>`)_  
Safely casts between different (#Predicate) parametrization

**`castDown(BiPredicate<? super X, ? super Y> biPredicate)`**  
⇒ *`BiPredicate<X, Y>`* _(`biPredicate` casted to the compatible parametrization (#BiPredicate)`<X, E>`)_  
Safely casts between different (#BiPredicate)parametrization

### LavaFun
_[(src)](src/main/java/org/tinyj/lava/LavaFun.java)_

Utility class

**`NoOp()`**  
⇒ *`<any>`*  
The no-operation, does absolutely nothing. This is equivalent to `() -> {}`

**`Constant(R constant)`**  
⇒ *`<any>`*  
The constant supplier, always returns `value`. This is equivalent to `() -> value`

**`Null()`**  
⇒ *`<any>`*  
The `null` supplier, always returns `null`. This is equivalent to `() -> null`

**`Identity()`**  
⇒ *`<any>`*  
The identity function, returns it's argument. This is equivalent to `x -> x`

**`ToString()`**  
⇒ *`<any>`*  
The stringify function, returns the string representation of its argument. This is equivalent to `x -> Objects.toString(x)`

**`First()`**  
⇒ *`<any>`*  
Binary function returning its first argument. This is equivalent to `(x, y) -> x`

**`Second()`**  
⇒ *`<any>`*  
Binary function returning its second argument. This is equivalent to `(x, y) -> y`

**`True()`**  
⇒ *`<any>`*  
The _true_-predicate, returns `true` for all tested values. This is equivalent to `x -> true`

**`False()`**  
⇒ *`<any>`*  
The _false_-predicate, returns `false` for all tested values. This is equivalent to `x -> false`

**`IsNull()`**  
⇒ *`<any>`*  
The _"is `null`?"_-predicate, returns true iff the tested value is `null`. This is equivalent to `x -> x == null`

**`NonNull()`**  
⇒ *`<any>`*  
The _"is not `null`?"_-predicate, returns true iff the tested value is not `null`. This is equivalent to `x -> x != null`

**`IsEqualTo(Y y)`**  
⇒ *`<any>`*  
The _equals_ predicate, returns true iff arguments equals `reference`. This is equivalent to `x -> Objects.equals(x, reference)`

**`Equals()`**  
⇒ *`<any>`*  
The _equals_ relation, returns true iff both arguments are equal. This is equivalent to `(x, y) -> Objects.equals(x, y)`

**`runnable(<any> runnable)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaRunnable).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaRunnable) or a (#LavaSupplier) or a similar functional interface.
 Writing `runnable(...)` is equivalent to `((LavaRunnable) ...)`

**`consumer(<any> consumer)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaConsumer).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaConsumer) or a (#LavaFunction) or a similar functional interface.
 Writing `consumer(...)` is equivalent to `((LavaConsumer<X>) ...)`,
 where `X` is inferred from the context and the given lambda expression.

**`biConsumer(<any> biConsumer)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaBiConsumer).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaBiConsumer) or a (#LavaBiFunction) or a similar functional interface.
 Writing `biConsumer(...)` is equivalent to `((LavaBiConsumer<X, Y>) ...)`,
 where `X` and `Y` are inferred from the context and the given lambda expression.

**`supplier(<any> supplier)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaSupplier).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaRunnable) or a (#LavaSupplier) or a similar functional interface.
 Writing `supplier(...)` is equivalent to `((LavaSupplier<R>) ...)`,
 where `R` is inferred from the context and the given lambda expression.

**`function(<any> function)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaFunction).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaFunction) or a (#LavaConsumer) or a similar functional interface.
 Writing `function(...)` is equivalent to `((LavaFunction<X, R>) ...)`,
 where `X` and `R` are inferred from the context and the given lambda expression.

**`biFunction(<any> biFunction)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaBiFunction).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaBiFunction) or a (#LavaBiConsumer) or a similar functional interface.
 Writing `biFunction(...)` is equivalent to `((LavaBiFunction<X, Y, R>) ...)`,
 where `X`, `Y` and `R` are inferred from the context and the given lambda expression.

**`predicate(<any> predicate)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaPredicate).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaPredicate) or a (#LavaFunction) or a similar functional interface.
 Writing `predicate(...)` is equivalent to `((LavaPredicate<X>) ...)`,
 where `X` is inferred from the context and the given lambda expression.

**`biPredicate(<any> biPredicate)`**  
⇒ *`<any>`*  
Convenience method to ensure given lambda implements (#LavaBiPredicate).
 This may be necessary e.g. when a method is overloaded to take either
 a (#LavaBiPredicate) or a (#LavaBiFunction) or a similar functional interface.
 Writing `biPredicate(...)` is equivalent to `((LavaBiPredicate<X, Y>) ...)`,
 where `X`, `Y` are inferred from the context and the given lambda expression.

**`unaryOp(<any> operator)`**  
⇒ *`<any>`*  
Convenience method to ensure given (#LavaFunction) lambda fulfills the
 criteria of an unary operation, i.e. argument and result type match.

**`binaryOp(<any> operator)`**  
⇒ *`<any>`*  
Convenience method to ensure given (#LavaBiFunction) lambda fulfills the
 criteria of an binary operation, i.e. argument and result types match.

**`relation(<any> relation)`**  
⇒ *`<any>`*  
Convenience method to ensure given (#LavaBiPredicate) lambda fulfills the
 criteria of an binary relation, i.e. argument types match.

**`toConsumer(<any> runnable)`**  
⇒ *`<any>`*  
Extend a given (#LavaRunnable) to a (#LavaConsumer) ignoring its argument.

**`toBiConsumer(<any> runnable)`**  
⇒ *`<any>`*  
Extend a (#LavaRunnable) to a (#LavaBiConsumer) ignoring its arguments.

**`toBiConsumer(<any> consumer)`**  
⇒ *`<any>`*  
Extend a (#LavaConsumer) to a (#LavaBiConsumer) ignoring its second argument.

**`toFunction(<any> supplier)`**  
⇒ *`<any>`*  
Extend a (#LavaSupplier) to a (#LavaFunction) ignoring its argument.

**`toBiFunction(<any> supplier)`**  
⇒ *`<any>`*  
Extend a (#LavaSupplier) to a (#LavaBiFunction) ignoring its arguments.

**`toBiFunction(<any> function)`**  
⇒ *`<any>`*  
Extend a (#LavaFunction) to a (#LavaBiFunction) ignoring its second argument.

**`toPredicate(<any> supplier)`**  
⇒ *`<any>`*  
Extend a (#LavaBooleanSupplier) to a (#LavaPredicate) ignoring its argument.

**`toBiPredicate(<any> supplier)`**  
⇒ *`<any>`*  
Extend a (#LavaBooleanSupplier) to a (#LavaBiPredicate) ignoring its arguments.

**`toBiPredicate(<any> predicate)`**  
⇒ *`<any>`*  
Extend a (#LavaPredicate) to a (#LavaBiPredicate) ignoring its second argument.

**`flipArguments(<any> biConsumer)`**  
⇒ *`<any>`*  
Flip arguments of a (#LavaBiConsumer)

**`flipArguments(<any> biFunction)`**  
⇒ *`<any>`*  
Flip arguments of a (#LavaBiFunction)

**`flipArguments(<any> biPredicate)`**  
⇒ *`<any>`*  
Flip arguments of a (#LavaBiPredicate)

**`toJavaFun(<any> runnable)`**  
⇒ *`Runnable`*  
Turn a (#LavaRunnable) declaring (#RuntimeException) as exception limit into a (#Runnable).

**`toJavaFun(<any> consumer)`**  
⇒ *`Consumer<X>`*  
Turn a (#LavaConsumer) declaring (#RuntimeException) as exception limit into a (#Consumer).

**`toJavaFun(<any> biConsumer)`**  
⇒ *`BiConsumer<X, Y>`*  
Turn a (#LavaBiConsumer) declaring (#RuntimeException) as exception limit into a (#BiConsumer).

**`toJavaFun(<any> supplier)`**  
⇒ *`Supplier<R>`*  
Turn a (#LavaSupplier) declaring (#RuntimeException) as exception limit into a (#Supplier).

**`toJavaFun(<any> function)`**  
⇒ *`Function<X, R>`*  
Turn a (#LavaFunction) declaring (#RuntimeException) as exception limit into a (#Function).

**`toJavaFun(<any> biFunction)`**  
⇒ *`BiFunction<X, Y, R>`*  
Turn a (#LavaBiFunction) declaring (#RuntimeException) as exception limit into a (#BiFunction).

**`toJavaFun(<any> predicate)`**  
⇒ *`Predicate<X>`*  
Turn a (#LavaPredicate) declaring (#RuntimeException) as exception limit into a (#Predicate).

**`toJavaFun(<any> biPredicate)`**  
⇒ *`BiPredicate<X, Y>`*  
Turn a (#LavaBiPredicate) declaring (#RuntimeException) as exception limit into a (#BiPredicate).

## org.tinyj.lava.binder

## org.tinyj.lava.fun

