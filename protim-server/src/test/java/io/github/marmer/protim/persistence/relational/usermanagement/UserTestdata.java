package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.protim.service.usermanagement.User;

class UserTestdata {

    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .build();

    static User newUser() {
        return builder.nextObject(User.class);
    }

}
