package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.keel.core.json.UnmodifiableJsonifiableEntityImpl;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;

public class ApiClientPrincipleWrapper extends UnmodifiableJsonifiableEntityImpl {
    public ApiClientPrincipleWrapper(@Nonnull JsonObject jsonObject) {
        super(jsonObject);
    }

    public Long getClientId() {
        return readLong("client_id");
    }

    public String getClientCode() {
        return readString("client_code");
    }
}
