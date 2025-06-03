package io.github.sinri.drydock.lesson.receptionist;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.keel.logger.issue.center.KeelIssueRecordCenter;
import io.github.sinri.keel.web.http.receptionist.KeelWebFutureReceptionist;
import io.vertx.ext.web.RoutingContext;

import javax.annotation.Nonnull;

public abstract class AkagiReceptionist extends KeelWebFutureReceptionist {
    public AkagiReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }


    @Nonnull
    @Override
    protected KeelIssueRecordCenter issueRecordCenter() {
        return Main.getIssueRecordCenter();
    }
}
