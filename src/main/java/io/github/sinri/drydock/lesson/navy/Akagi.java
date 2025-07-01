package io.github.sinri.drydock.lesson.navy;

import io.github.sinri.drydock.aviation.aircraft.Bomber;
import io.github.sinri.drydock.aviation.aircraft.Drone;
import io.github.sinri.drydock.aviation.aircraft.Fighter;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrier;
import io.github.sinri.drydock.lesson.navy.bomber.SuiseiBomber;
import io.github.sinri.drydock.lesson.navy.drone.SakuraDrone;
import io.github.sinri.drydock.lesson.navy.fighter.ZeroFighter;
import io.github.sinri.keel.integration.mysql.KeelMySQLDataSourceProvider;
import io.github.sinri.keel.integration.mysql.NamedMySQLDataSource;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;
import picocli.CommandLine;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Akagi extends AircraftCarrier {
    private NamedMySQLDataSource<AkagiMySQLConnection> mySQLDataSource;

    public NamedMySQLDataSource<AkagiMySQLConnection> getMySQLDataSource() {
        return mySQLDataSource;
    }

    @Override
    protected Bomber constructBomber() {
        return new SuiseiBomber(this);
    }

    @Override
    protected Drone constructDrone() {
        return new SakuraDrone(this);
    }

    @Override
    protected Fighter constructFighter(@Nullable Integer port) {
        return new ZeroFighter(this, Objects.requireNonNullElse(port, 8080));
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
        mySQLDataSource = KeelMySQLDataSourceProvider.initializeNamedMySQLDataSource(AkagiMySQLConnection.DATASOURCE_NAME, AkagiMySQLConnection::new);
        return Future.succeededFuture();
    }

    @Nonnull
    @Override
    protected Future<Void> ready(@Nonnull CommandLine.ParseResult parseResult) {
        getUnitLogger().notice(getClass().getName() + " READY");
        return Future.succeededFuture();
    }

    @Nonnull
    @Override
    protected String buildCliName() {
        return "Akagi";
    }

    @Nonnull
    @Override
    protected String buildCliDescription() {
        return "Akagi is a sample project to showcase DryDock 2.0.x, provide services in a modular way.";
    }
}
