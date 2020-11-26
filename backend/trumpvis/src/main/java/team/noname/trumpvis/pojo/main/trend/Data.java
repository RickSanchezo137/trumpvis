package team.noname.trumpvis.pojo.main.trend;

import java.util.List;

public class Data {
    private Integer count;
    private Integer retweets;
    private Integer favorites;
    public String month;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
