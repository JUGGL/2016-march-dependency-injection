# Dagger Dependency Injection Example

## Reference Links
http://google.github.io/dagger/users-guide
http://code.tutsplus.com/tutorials/dependency-injection-with-dagger-2-on-android--cms-23345

## Special considerations for Dagger 2
Dagger 2 uses an annotation processor to handle DI, and thus we have
a few extra steps to wire up the dependencies:

1. Create a class annotated with `@Module` which `@Provides` dependencies
1. Create an interface annotated with `@Component` which defines the `@Module` to be used and the dependency which it provides
1. If you are using Java 6/7, you need the file `src/main/resources/META-INF/services/javax.annotation.processing.Processor` to tell the compiler which annotation processors to use