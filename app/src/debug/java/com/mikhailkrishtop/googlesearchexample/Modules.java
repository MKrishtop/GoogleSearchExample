package com.mikhailkrishtop.googlesearchexample;

final class Modules {
    static Object[] list() {
        return new Object[] {
                new AppModule(),
                new DebugAppModule()
        };
    }

    private Modules() {
        // No instances.
    }
}
