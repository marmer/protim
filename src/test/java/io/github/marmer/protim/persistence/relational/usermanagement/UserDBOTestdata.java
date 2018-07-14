package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class UserDBOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .build();

    public static UserDBO newUserDBO() {
        return builder.nextObject(UserDBO.class);
    }
}
