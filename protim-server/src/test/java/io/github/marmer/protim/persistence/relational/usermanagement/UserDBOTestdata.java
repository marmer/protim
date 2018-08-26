package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinition;
import io.github.benas.randombeans.api.EnhancedRandom;

public class UserDBOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .exclude(new FieldDefinition<>("id", Long.class, UserDBO.class))
            .exclude(new FieldDefinition<>("version", Long.class, UserDBO.class))
            .exclude(new FieldDefinition<>("id", Long.class, RoleDBO.class))
            .exclude(new FieldDefinition<>("version", Long.class, RoleDBO.class))
            .build();

    public static UserDBO newUserDBO() {
        return builder.nextObject(UserDBO.class);
    }
}
