package org.vetcabinet.patients.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdentifierType {
    MICROCHIP ("Чип"),
    TATTOO ("Клеймо"),
    MARK ("Бирка-метка");

    private final String type;
}
