package io.github.marmer.protim.persistence.relational.usermanagement;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinition;
import io.github.benas.randombeans.api.EnhancedRandom;

public class RoleDBOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .exclude(new FieldDefinition<>("id", Long.class, RoleDBO.class))
            .exclude(new FieldDefinition<>("version", Long.class, RoleDBO.class))
            .seed(42)
            .build();

    public static RoleDBO newRoleDBO() {
        return builder.nextObject(RoleDBO.class);
    }
}
