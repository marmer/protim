package io.github.marmer.protim.api.configuration;

import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(chain = true)
public class ProtimErrorDTO {
    private final String errorMsg;

    public ProtimErrorDTO(final Exception exception) {
        this.errorMsg = exception.getMessage();
    }
}
