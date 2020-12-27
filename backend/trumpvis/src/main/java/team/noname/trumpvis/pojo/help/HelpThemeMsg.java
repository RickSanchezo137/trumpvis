package team.noname.trumpvis.pojo.help;

public class HelpThemeMsg {
    private String time;
    private Integer count;
    private String theme;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "HelpThemeMsg{" +
                "time='" + time + '\'' +
                ", count=" + count +
                ", theme='" + theme + '\'' +
                '}';
    }
}
