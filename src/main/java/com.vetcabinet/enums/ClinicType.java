package com.vetcabinet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClinicType {
    CLINIC ("Клиника"),
    HOSPITAL ("Госпиталь"),
    PRIVATE_CABINET ("Частный кабинет"),
    CALLING_SERVICE ("Вызывная служба"),
    STATE_CLINIC ("СББЖ");

    private final String type;
}
