package io.github.marmer.protim.api.usermanagement;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserListEntryDTO {
    private String username;
}
