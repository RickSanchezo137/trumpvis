package team.noname.trumpvis.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.noname.trumpvis.dao.ITweetsDao;
import team.noname.trumpvis.pojo.help.*;
import team.noname.trumpvis.pojo.main.trend.Data;
import team.noname.trumpvis.pojo.main.trend.DataMsg;
import team.noname.trumpvis.pojo.main.trend.TimeMsg;
import team.noname.trumpvis.pojo.main.trend.TrendMsg;
import team.noname.trumpvis.service.ITweetsService;
import team.noname.trumpvis.utils.*;

import java.util.*;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheManager = "jsonRedisCache")
public class TweetsServiceImpl implements ITweetsService {

    @Autowired
    private ITweetsDao iTweetsDao;

    @Autowired
    private ITweetsService iTweetsService;

    @Qualifier("emotionRedisTemplate")
    @Autowired
    private RedisTemplate jsonRedisTemplate;

    /**
     *  main
     */
    @Override
    @Cacheable(value = "home", cacheManager = "jsonRedisCache")
    public String home_vis(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        return JSON.toJSONString(iTweetsDao.homeMsgOfTweetsByMonth(start_time, end_time));
    }

    @Override
    @Cacheable(value = "trend")
    public String trend_vis(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        List<Data> data = iTweetsDao.trendOfTweetsByMonth(start_time, end_time);

        TrendMsg trendMsg = new TrendMsg();
        List<DataMsg> dataMsgs= new ArrayList<>();
        TimeMsg timeMsg = new TimeMsg();

        List<Integer> retweets = new ArrayList<>();
        List<Integer> favorites = new ArrayList<>();
        List<String> month = new ArrayList<>();
        for(Data data1: data){
            retweets.add(data1.getRetweets());
            favorites.add(data1.getFavorites());
            month.add(data1.getMonth());
        }

        dataMsgs.add(new DataMsg("retweets", retweets));
        dataMsgs.add(new DataMsg("favorites", favorites));
        timeMsg.setMonth(month);

        trendMsg.setData(dataMsgs);
        trendMsg.setTime(timeMsg);

        return JSON.toJSONString(trendMsg);
    }

    /**
     *  help_calendar
     */
    @Override
    @Cacheable(value = "calendar")
    public String help_vis_calendar() {
        List<HelpCalendarMsg> helpCalendarMsgs = new ArrayList<>();
        String start = "01-01";
        String middle1 = "06-30";
        String middle2 = "07-01";
        String end = "12-31";
        for(int i = 2009; i <= 2020; i++){
            List<String> y1st = new ArrayList<>();
            List<String> y2st = new ArrayList<>();
            y1st.add(i + "-" + start);
            y1st.add(i + "-" + middle1);
            y2st.add(i + "-" + middle2);
            y2st.add(i + "-" + end);

            HelpCalendarMsg helpCalendarMsg = new HelpCalendarMsg();
            helpCalendarMsg.setName(i + " 1st");
            helpCalendarMsg.setRange(y1st);
            helpCalendarMsg.setData(iTweetsDao.countOfTweetsByDay(StringDateTransferUtils.str2date_YMD(y1st.get(0)), StringDateTransferUtils.str2date_YMD(y1st.get(1))));
            helpCalendarMsgs.add(helpCalendarMsg);

            helpCalendarMsg = new HelpCalendarMsg();
            helpCalendarMsg.setName(i + " 2st");
            helpCalendarMsg.setRange(y2st);
            helpCalendarMsg.setData(iTweetsDao.countOfTweetsByDay(StringDateTransferUtils.str2date_YMD(y2st.get(0)), StringDateTransferUtils.str2date_YMD(y2st.get(1))));
            helpCalendarMsgs.add(helpCalendarMsg);
        }

        return JSON.toJSONString(helpCalendarMsgs);
    }

    /**
     *  help_wordcloud
     */
    @Override
    @Cacheable(value = "wordcloud")
    public String help_vis_wordcloud(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        Date start = StringDateTransferUtils.str2date_YM(TweetsTimeRange.START_MONTH, 0);
        Date end = StringDateTransferUtils.str2date_YM(TweetsTimeRange.END_MONTH, 1);
        if(start_time.compareTo(start) < 0){
            start_time = start;
        }
        if(end_time.compareTo(end) > 0){
            end_time = end;
        }

        List<String> contents = iTweetsDao.contentOfTweetsByMonth(start_time, end_time);

        String text = Java2Python.doPost(JSON.toJSONString(contents), "HTTP://127.0.0.1:5000/keywords");
        List<HelpWordCloudMsg> result = KeyWordResultProcess.getResult(text);

        Collections.sort(result);
        int min = result.get(result.size() - 1).getValue();
        int max = result.get(0).getValue();
        if(min < 0){
            for(int i = 0; i < result.size(); i++){
                HelpWordCloudMsg helpWordCloudMsg = result.get(i);
                helpWordCloudMsg.setValue(helpWordCloudMsg.getValue() + min * -1);
            }
        }
        if(max == 0){
            for(int i = 0; i < result.size(); i++){
                HelpWordCloudMsg helpWordCloudMsg = result.get(i);
                helpWordCloudMsg.setValue(helpWordCloudMsg.getValue() + 400);
            }
        }

        List<HelpWordCloudMsg> final_results = new ArrayList<>();
        int topK = 100;
        for (int i = 0; i < result.size() && i < topK; i++){
            //老出现这个奇奇怪怪的字符
            if(result.get(i).getName().equals("…")){
                topK += 1;
                continue;
            }
            //
            final_results.add(result.get(i));
        }
        return JSON.toJSONString(final_results);
    }

    @Override
    @Cacheable(value = "theme")
    public String help_vis_theme(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        String jsonKeywords = iTweetsService.help_vis_wordcloud(startTime, endTime);
        List<HelpWordCloudMsg> keywords = (JSON.parseArray(jsonKeywords, HelpWordCloudMsg.class));

        int themeNums = 4;
        String[] themeList = new String[themeNums];
        for(int i = 0; i < themeNums; i++){
            themeList[i] = keywords.get(i).getName();
        }

        List<HelpThemeData> themeData = iTweetsDao.theme_contentOfTweetsByMonth(start_time, end_time);
        List<HelpThemeMsg> themeMsgs = new ArrayList<>();
        for(String theme: themeList){
            for (HelpThemeData helpThemeData: themeData){
                HelpThemeMsg helpThemeMsg = new HelpThemeMsg();
                helpThemeMsg.setTime(helpThemeData.getTime());
                helpThemeMsg.setTheme(theme);

                String content = helpThemeData.getContent();
                helpThemeMsg.setCount(content.split(theme).length - 1);

                themeMsgs.add(helpThemeMsg);
            }
        }
        HelpThemeMsgFinal helpThemeMsgFinal = new HelpThemeMsgFinal();
        helpThemeMsgFinal.setTopN(themeList);
        helpThemeMsgFinal.setData(themeMsgs);
        return JSON.toJSONString(helpThemeMsgFinal);
    }

    @Override
    public String help_vis_emotion(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        List<HelpEmotionMsg> helpEmotionMsgs = new ArrayList<>();

        /*List<HelpEmotionData> contents = iTweetsDao.contentOfTweetsByDay(start_time, end_time);

        Set keys = jsonRedisTemplate.keys("emotion*");
        for (HelpEmotionData content: contents){
            HelpEmotionMsg helpEmotionMsg = new HelpEmotionMsg();

            String key = "emotion" + ":" + content.getDate();

            if(keys.contains(key)){
                helpEmotionMsg = (HelpEmotionMsg) jsonRedisTemplate.opsForValue().get(key);
            }else {
                String result = Java2Python.doPost(content.getContent(), "HTTP://127.0.0.1:5000/emotion");
                result = result.replace("Sentiment(polarity=", "");
                result = result.replace("subjectivity=", "");
                result = result.replace(")", "");
                String[] results = result.split(",");

                helpEmotionMsg.setSentiment(Float.parseFloat(results[0]));
                helpEmotionMsg.setSubjectivity(Float.parseFloat(results[1]));

                jsonRedisTemplate.opsForValue().set(key, helpEmotionMsg);
            }
            helpEmotionMsgs.add(helpEmotionMsg);
        }*/

        //
        String time = StringDateTransferUtils.date2str_YMD(start_time);
        Date end = StringDateTransferUtils.str2date_YMD(StringDateTransferUtils.date2str_YMD(end_time));
        Date temp;

        for(int i = 0; (temp = StringDateTransferUtils.addDayFromStr(time, i)).compareTo(end) < 0; i+=7){
            HelpEmotionMsg helpEmotionMsg;
            String key = "emotion" + ":" + StringDateTransferUtils.date2str_YMD(temp);
            helpEmotionMsg = (HelpEmotionMsg) jsonRedisTemplate.opsForValue().get(key);
            if(helpEmotionMsg != null) {
                helpEmotionMsgs.add(helpEmotionMsg);
            }
        }

    //

        return JSON.toJSONString(helpEmotionMsgs);
    }

    @Override
    @Cacheable(value = "relation_50")
    public String help_vis_relation(String startTime, String endTime) {
        Date start_time = StringDateTransferUtils.str2date_YM(startTime, 0);
        Date end_time = StringDateTransferUtils.str2date_YM(endTime, 1);

        String jsonKeywords = iTweetsService.help_vis_wordcloud(startTime, endTime);
        List<HelpWordCloudMsg> keywords = (JSON.parseArray(jsonKeywords, HelpWordCloudMsg.class));

        int keywordNums = 50;
        keywordNums = Math.min(keywordNums, keywords.size());
        String[] keywordList = new String[keywordNums];
        for(int i = 0; i < keywordNums; i++){
            keywordList[i] = keywords.get(i).getName();
        }

        List<String> contents = iTweetsDao.pureContentOfTweets(start_time, end_time);
        int docNums = contents.size();

        double pmi[][] = new double[keywordNums][keywordNums];

        List<HelpRelationPoint> points = new ArrayList<>();
        List<HelpRelationLink> links = new ArrayList<>();
        List<HelpRelationCategory> categories = new ArrayList<>();

        int category = 0;
        HashMap<String, Integer> postags = new HashMap<>();
        for(int i = 0; i < keywordNums; i++) {

            HelpRelationPoint point = new HelpRelationPoint();
            point.setId("" + i);
            point.setName(keywordList[i]);
            point.setSymbolSize(keywords.get(i).getValue());

            String postagKey = Java2Python.doPost(keywordList[i], "HTTP://127.0.0.1:5000/postag")
                    .split(",")[1];
            if (postags.containsKey(postagKey)) {
                point.setCategory(postags.get(postagKey));
            }else {
                HelpRelationCategory helpRelationCategory = new HelpRelationCategory();
                helpRelationCategory.setName("" + category);
                categories.add(helpRelationCategory);
                postags.put(postagKey, category);
                point.setCategory(category);
                category++;
            }

            points.add(point);

            for(int j = 0; j < keywordNums; j++){
                double P_all = 0, P_1 = 0, P_2 = 0;
                for (String content : contents) {
                    if (content.indexOf(keywordList[i]) >= 0 && content.indexOf(keywordList[j]) >= 0){
                        P_all++;
                    }
                    if(content.indexOf(keywordList[i]) >= 0){
                        P_1++;
                    }
                    if(content.indexOf(keywordList[j]) >= 0){
                        P_2++;
                    }
                }

                P_all = P_all / docNums;
                P_1 = P_1 / docNums;
                P_2 = P_2 / docNums;

                pmi[i][j] = Math.max(Math.log((P_all) / (P_1 * P_2)), 0);
                if(i == j || P_1 == 0 || P_2 == 0){
                    pmi[i][j] = -1;
                }
            }
        }

        int linkId = 0;
        int topK = 3;
        for (int i = 0; i < keywordNums; i++){
            Double[] sort = new Double[keywordNums];
            for(int j = 0; j < keywordNums; j++){
                sort[j] = pmi[i][j];
            }
            Arrays.sort(sort, Collections.reverseOrder());

            Set<Integer> haveFound = new HashSet<>();
            for(int k = 0; k < topK; k++){
                for(int j = i + 1; j < keywordNums; j++) {
                    if(pmi[i][j] == sort[k] && !haveFound.contains(j)) {
                        haveFound.add(j);
                        HelpRelationLink link = new HelpRelationLink();
                        link.setId("" + linkId++);
                        link.setSource("" + i);
                        link.setTarget("" + j);
                        links.add(link);
                        break;
                    }
                }
            }
            haveFound.clear();
        }
        HelpRelationMsg helpRelationMsg = new HelpRelationMsg();
        helpRelationMsg.setPoints(points);
        helpRelationMsg.setLinks(links);
        helpRelationMsg.setCategories(categories);
        return JSON.toJSONString(helpRelationMsg);
    }
}
