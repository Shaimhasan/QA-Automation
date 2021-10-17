package hooks;

import common.TestContext;
import objectmatcher.FetchPageObjects;
import io.cucumber.core.api.Scenario;
import io.cucumber.java8.En;

public class AutoEngHooksWeb implements En {

    public AutoEngHooksWeb() {
        Before(35, (Scenario scenario) -> {
            TestContext.getInstance().setOfPO().addAll(FetchPageObjects.populateListOfPO());
        });

        After(30, (Scenario scenario) -> {
        });
    }
}