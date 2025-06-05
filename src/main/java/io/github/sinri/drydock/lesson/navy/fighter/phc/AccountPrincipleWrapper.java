package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.keel.core.json.UnmodifiableJsonifiableEntityImpl;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;

public class AccountPrincipleWrapper extends UnmodifiableJsonifiableEntityImpl {
    public AccountPrincipleWrapper(@Nonnull JsonObject jsonObject) {
        super(jsonObject);
    }

    public Long getAccountId() {
        return readLong("account_id");
    }

    public String getAccountName() {
        return readString("account_name");
    }

    public String getDisplayName() {
        return readString("display_name");
    }

    public String getEmail() {
        return readString("email");
    }
}
