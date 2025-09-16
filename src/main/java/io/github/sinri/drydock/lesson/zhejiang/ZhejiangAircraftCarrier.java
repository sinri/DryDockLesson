package io.github.sinri.drydock.lesson.zhejiang;

import io.github.sinri.drydock.aviation.aircraft.Bomber;
import io.github.sinri.drydock.aviation.aircraft.Drone;
import io.github.sinri.drydock.aviation.aircraft.Fighter;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrier;
import io.github.sinri.drydock.lesson.zhejiang.fighter.J20;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;
import picocli.CommandLine;

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

    @Override
    protected VertxOptions buildVertxOptions(@Nonnull CommandLine.ParseResult parseResult) {
        return new VertxOptions();
    }

    @Override
    protected Future<Void> loadRemoteConfiguration(@Nonnull CommandLine.ParseResult parseResult) {
        return Future.succeededFuture();
    }

    @Nonnull
    @Override
    protected Future<Void> prepare(@Nonnull CommandLine.ParseResult parseResult) {
        return Future.succeededFuture();
    }

    @Nonnull
    @Override
    protected Future<Void> ready(@Nonnull CommandLine.ParseResult parseResult) {
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
