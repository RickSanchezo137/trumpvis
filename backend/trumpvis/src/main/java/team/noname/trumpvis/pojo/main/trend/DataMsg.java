package team.noname.trumpvis.pojo.main.trend;

import java.util.List;

public class DataMsg {
    private String name;
    private List<Integer> data;

    public DataMsg(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
