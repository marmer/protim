package io.github.marmer.protim.test;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import java.nio.charset.StandardCharsets;

public class Testdataprovider {
    private static EnhancedRandom random() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .seed(42)
                .scanClasspathForConcreteTypes(true)
                .collectionSizeRange(1, 1)
                .charset(StandardCharsets.UTF_8)
                .build();
    }

    public static <T> T newInstanceOf(final Class<T> type) {
        return random().nextObject(type);
    }
}
