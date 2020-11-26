package team.noname.trumpvis.pojo.main.trend;

import java.util.List;

public class TrendMsg {
    private List<DataMsg> data;
    private TimeMsg time;

    public List<DataMsg> getData() {
        return data;
    }

    public void setData(List<DataMsg> data) {
        this.data = data;
    }

    public TimeMsg getTime() {
        return time;
    }

    public void setTime(TimeMsg time) {
        this.time = time;
    }
}
