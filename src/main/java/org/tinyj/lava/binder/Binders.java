/*
Copyright 2016 Eric Karge <e.karge@struction.de>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.tinyj.lava.binder;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Binders {

  public static <X> ConsumerBinder<X> bind(Consumer<? super X> bound) {
    return new ConsumerBinder<>(bound);
  }

  public static <X, Y> BiConsumerBinder<X, Y> bind(BiConsumer<? super X, ? super Y> bound) {
    return new BiConsumerBinder<>(bound);
  }

  public static <X, R> FunctionBinder<X, R> bind(Function<? super X, ? extends R> bound) {
    return new FunctionBinder<>(bound);
  }

  public static <X, Y, R> BiFunctionBinder<X, Y, R> bind(BiFunction<? super X, ? super Y, ? extends R> bound) {
    return new BiFunctionBinder<>(bound);
  }
}
