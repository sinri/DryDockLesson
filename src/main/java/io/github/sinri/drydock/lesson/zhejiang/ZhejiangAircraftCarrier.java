package io.github.sinri.drydock.lesson.zhejiang;

import io.github.sinri.drydock.aviation.Bomber;
import io.github.sinri.drydock.aviation.Drone;
import io.github.sinri.drydock.aviation.Fighter;
import io.github.sinri.drydock.lesson.zhejiang.fighter.J20;
import io.github.sinri.drydock.naval.carrier.AircraftCarrier;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ZhejiangAircraftCarrier extends AircraftCarrier {

    @Override
    protected Bomber constructBomber() {
        return null;
    }

    @Override
    protected Drone constructDrone() {
        return null;
    }

    @Override
    protected Fighter constructFighter(@Nullable Integer port) {
        return new J20(this, Objects.requireNonNullElse(port, 8080));
    }

    @Nonnull
    @Override
    protected VertxOptions buildVertxOptions() {
        return new VertxOptions();
    }

    @Nonnull
    @Override
    protected Future<Void> prepare() {
        return Future.succeededFuture();
    }

    @Nonnull
    @Override
    protected String buildCliName() {
        return "";
    }

    @Nonnull
    @Override
    protected String buildCliDescription() {
        return "";
    }
}
