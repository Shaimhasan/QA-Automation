package utility;

import io.qameta.allure.CommonJsonAggregator;
import io.qameta.allure.core.LaunchResults;
import io.qameta.allure.entity.GroupTime;
import io.qameta.allure.entity.Statistic;
import io.qameta.allure.summary.SummaryData;

import java.util.List;

public class SummaryPlugin extends CommonJsonAggregator {
    protected static final String JSON_FILE_NAME = "summary.json";

    public SummaryPlugin() {
        super("widgets", "summary.json");
    }

    protected SummaryData getData(List<LaunchResults> launches) {
        SummaryData data = (new SummaryData()).setStatistic(new Statistic()).setTime(new GroupTime()).setReportName("Adora Report");
        launches.stream().flatMap((launch) -> {
            return launch.getResults().stream();
        }).forEach((result) -> {
            data.getStatistic().update(result);
            data.getTime().update(result);
        });
        return data;
    }
}
