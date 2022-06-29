package wpi.owning.field;

import org.checkerframework.checker.calledmethods.qual.EnsuresCalledMethods;
import org.checkerframework.checker.mustcall.qual.InheritableMustCall;

@InheritableMustCall("finalizer") class App {
    private final Foo checkFieldsFoo;

    public App() {
        this.checkFieldsFoo = new Foo();
    }

    @EnsuresCalledMethods(
            value = {"this.checkFieldsFoo"},
            methods = {"a"})
    void finalizer() {
        this.checkFieldsFoo.a();
    }

    @InheritableMustCall("a") static class Foo {
        void a() {}

        void c() {}
    }

    Foo makeFoo() {
        return new Foo();
    }

    @InheritableMustCall("b") static class FooField {
        private final Foo finalOwningFoo;

        public FooField() {
            this.finalOwningFoo = new Foo();
        }

        @EnsuresCalledMethods(
                value = {"this.finalOwningFoo"},
                methods = {"a"})
        void b() {
            this.finalOwningFoo.a();
            this.finalOwningFoo.c();
        }
    }
}

