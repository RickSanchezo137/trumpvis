package team.noname.trumpvis.pojo;

import java.util.Date;

public class Tweets {
    private String id;
    private String content;
    private Date date;
    private Integer retweets;
    private Integer favorites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
