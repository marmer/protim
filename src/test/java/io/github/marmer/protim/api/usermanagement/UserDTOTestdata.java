package io.github.marmer.protim.api.usermanagement;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

class UserDTOTestdata {
    private final static EnhancedRandom builder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(42)
            .build();

    static UserDTO newUserDTO() {
        return builder.nextObject(UserDTO.class);
    }
}
