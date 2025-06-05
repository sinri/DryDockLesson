package io.github.sinri.drydock.lesson.receptionist.open;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.OrganizationTableRow;
import io.github.sinri.keel.web.http.receptionist.ApiMeta;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

@ApiMeta(routePath = "/api/open/get-organization-list")
public class GetOrganizationListReceptionist extends OpenAkagiReceptionist {
    public GetOrganizationListReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                       AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                       return akagiAction.fetchOrganizationList();
                   })
                   .compose(list -> {
                       JsonArray array = new JsonArray();
                       for (OrganizationTableRow row : list) {
                           array.add(row.toJsonObject());
                       }
                       return Future.succeededFuture(array);
                   });
    }
}
