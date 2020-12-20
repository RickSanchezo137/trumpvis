package team.noname.trumpvis.pojo.help;

import java.util.List;

public class HelpCalendarMsg {
    private String name;
    private List<String> range;
    private List<HelpCalendarData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRange() {
        return range;
    }

    public void setRange(List<String> range) {
        this.range = range;
    }

    public List<HelpCalendarData> getData() {
        return data;
    }

    public void setData(List<HelpCalendarData> data) {
        this.data = data;
    }
}
