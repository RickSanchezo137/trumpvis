package team.noname.trumpvis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.noname.trumpvis.dao.ITweetsDao;
import team.noname.trumpvis.pojo.main.trend.Data;
import team.noname.trumpvis.pojo.main.trend.DataMsg;
import team.noname.trumpvis.pojo.main.trend.TimeMsg;
import team.noname.trumpvis.pojo.main.trend.TrendMsg;
import team.noname.trumpvis.service.ITweetsService;
import team.noname.trumpvis.utils.JsonUtils;
import team.noname.trumpvis.utils.String2Date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TweetsServiceImpl implements ITweetsService {

    @Autowired
    private ITweetsDao iTweetsDao;

    /**
     *  main
     */
    @Override
    @Cacheable(value = "home", cacheManager = "jsonRedisCache")
    public String home_vis(String startTime, String endTime) {
        Date start_time = String2Date.str2YM(startTime);
        Date end_time = String2Date.str2YM(endTime);

        return JsonUtils.objectToJson(iTweetsDao.dataOfTweetsByMonth(start_time, end_time));
    }

    @Override
    @Cacheable(value = "trend", cacheManager = "jsonRedisCache")
    public String trend_vis(String startTime, String endTime) {
        Date start_time = String2Date.str2YM(startTime);
        Date end_time = String2Date.str2YM(endTime);

        List<Data> data = iTweetsDao.trendOfTweetsByMonth(start_time, end_time);

        TrendMsg trendMsg = new TrendMsg();
        List<DataMsg> dataMsgs= new ArrayList<>();
        TimeMsg timeMsg = new TimeMsg();

        List<Integer> count = new ArrayList<>();
        List<Integer> retweets = new ArrayList<>();
        List<Integer> favorites = new ArrayList<>();
        List<String> month = new ArrayList<>();
        for(Data data1: data){
            count.add(data1.getCount());
            retweets.add(data1.getRetweets());
            favorites.add(data1.getFavorites());
            month.add(data1.getMonth());
        }

        dataMsgs.add(new DataMsg("count", count));
        dataMsgs.add(new DataMsg("retweets", retweets));
        dataMsgs.add(new DataMsg("favorites", favorites));
        timeMsg.setMonth(month);

        trendMsg.setData(dataMsgs);
        trendMsg.setTime(timeMsg);

        return JsonUtils.objectToJson(trendMsg);
    }

    /**
     *  help
     */
    @Override
    public String help_vis_calendar(String startTime, String endTime) {
        Date start_time = String2Date.str2YMd(startTime);
        Date end_time = String2Date.str2YMd(endTime);

        return JsonUtils.objectToJson(iTweetsDao.dataOfTweetsByDay(start_time, end_time));
    }
}
