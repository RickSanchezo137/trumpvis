package team.noname.trumpvis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import team.noname.trumpvis.pojo.Tweets;
import team.noname.trumpvis.pojo.help.HelpCalendarData;
import team.noname.trumpvis.pojo.help.HelpEmotionData;
import team.noname.trumpvis.pojo.help.HelpThemeData;
import team.noname.trumpvis.pojo.main.home.HomeMsg;
import team.noname.trumpvis.pojo.main.trend.Data;

import java.util.Date;
import java.util.List;

@Repository
public interface ITweetsDao {
    /**
     *  main_home
     */
    @Select("select CONCAT(year(date), '-', lpad(month(date), 2, 0)) as 'name', " +
            "count(1) as 'value.count', sum(retweets) as 'value.retweets', sum(favorites) as 'value.favorites' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by year(date), month(date) order by date;")
    List<HomeMsg> homeMsgOfTweetsByMonth(Date startTime, Date endTime);

    /**
     *  main_trend
     */
    @Select("select count(1) as 'count',  sum(retweets) as 'retweets', sum(favorites) as 'favorites', " +
            "CONCAT(year(date), '-', lpad(month(date), 2, 0)) as 'month' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by year(date), month(date) order by date;")
    List<Data> trendOfTweetsByMonth(Date startTime, Date endTime);

    /**
     *   help/calendar
     */
    @Select("select date as 'time', count(1) as 'count' from tweets " +
            "where date <= #{endTime} and date >= #{startTime} " +
            "group by date order by date;")
    List<HelpCalendarData> countOfTweetsByDay(Date startTime, Date endTime);

    /**
     *   help/wordcloud
     */
    @Select("select GROUP_CONCAT(CONCAT(content, ' ')) as 'content' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by year(date), month(date) order by date;")
    List<String> contentOfTweetsByMonth(Date startTime, Date endTime);

    /**
     *   help/theme
     */
    @Select("select CONCAT(year(date), '/', lpad(month(date), 2, 0)) as 'time', " +
            "GROUP_CONCAT(CONCAT(content, ' ')) as 'content' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by year(date), month(date) order by date;")
    List<HelpThemeData> theme_contentOfTweetsByMonth(Date startTime, Date endTime);

    /**
     *   help/emotion
     */
    @Select("select date, GROUP_CONCAT(CONCAT(content, ' ')) as 'content' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by date;")
    List<HelpEmotionData> contentOfTweetsByDay(Date startTime, Date endTime);

    /**
     *   help/relation
     */
    @Select("select GROUP_CONCAT(CONCAT(content, ' ')) as 'content' from tweets " +
            "where date < #{endTime} and date >= #{startTime} " +
            "group by date order by date;")
/*    @Select("select content from tweets " +
            "where date < #{endTime} and date >= #{startTime}")*/
    List<String> pureContentOfTweets(Date startTime, Date endTime);


    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    //用于清洗数据
    @Select("select * from tweets " +
            "where date < #{endTime} and date >= #{startTime}")
    List<Tweets> tweetsById(Date startTime, Date endTime);

    @Insert("insert into tweets_cleaned(id,content,date,retweets,favorites) values(#{id}, #{content}, #{date}, #{retweets}, #{favorites})")
    void insert(Tweets tweets);

}
