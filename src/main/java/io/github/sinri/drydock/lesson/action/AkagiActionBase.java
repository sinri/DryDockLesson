package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.navy.AkagiMySQLConnection;
import io.github.sinri.keel.integration.mysql.action.NamedActionMixinInterface;

interface AkagiActionBase extends NamedActionMixinInterface<AkagiMySQLConnection, AkagiAction> {
}
