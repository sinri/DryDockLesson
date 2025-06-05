package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.navy.AkagiMySQLConnection;
import io.github.sinri.keel.integration.mysql.action.AbstractNamedMixinAction;

import javax.annotation.Nonnull;

public class AkagiAction extends AbstractNamedMixinAction<AkagiMySQLConnection, AkagiAction>
        implements ApiClientActionMixin, SessionActionMixin, AccountActionMixin, OrganizationActionMixin, QueueActionMixin, SundialActionMixin {
    public AkagiAction(@Nonnull AkagiMySQLConnection namedSqlConnection) {
        super(namedSqlConnection);
    }

    @Nonnull
    @Override
    public AkagiAction getImplementation() {
        return this;
    }
}
