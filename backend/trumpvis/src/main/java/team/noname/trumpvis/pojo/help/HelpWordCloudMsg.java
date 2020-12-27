package team.noname.trumpvis.pojo.help;

public class HelpWordCloudMsg implements Comparable{
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HelpWordCloudMsg{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return (((HelpWordCloudMsg) o).value).compareTo(this.value);
    }
}
