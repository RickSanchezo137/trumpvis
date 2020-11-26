package team.noname.trumpvis.dao;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import team.noname.trumpvis.pojo.help.HelpCalendarMsg;
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
    List<HomeMsg> dataOfTweetsByMonth(Date startTime, Date endTime);

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
    List<HelpCalendarMsg>  dataOfTweetsByDay(Date startTime, Date endTime);
}
