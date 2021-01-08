//package team.noname.trumpvis;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import team.noname.trumpvis.dao.ITweetsDao;
//import team.noname.trumpvis.pojo.Tweets;
//import team.noname.trumpvis.service.ITweetsService;
//import team.noname.trumpvis.utils.*;
//
//import java.util.*;
//
//
//@SpringBootTest
//class TrumpvisApplicationTests {
//
//    @Autowired
//    ITweetsDao iTweetsDao;
//
//    @Autowired
//    ITweetsService iTweetsService;
//
//    @Qualifier("emotionRedisTemplate")
//    @Autowired
//    private RedisTemplate jsonRedisTemplate;
//
//    @Test
//    void contextLoads(){
//        System.out.println(iTweetsService.help_vis_emotion("2009-01", "2020-02"));
//    }
//
//    @Test
//    void save2Redis(){
//        String time = "2009-05";
//        Date end = StringDateTransferUtils.str2date_YM("2005-06", 1);
//        Date temp;
//        while(StringDateTransferUtils.str2date_YM(time, 0).compareTo(end) != 0){
//            for(int i = 0; (temp = StringDateTransferUtils.addMonthFromStr(time, i)).compareTo(end) != 1; i++){
//                iTweetsService.help_vis_emotion(time, StringDateTransferUtils.date2str_YM(temp));
//                System.out.println("success! " + time + "  " + StringDateTransferUtils.date2str_YM(temp));
//            }
//            Date t = StringDateTransferUtils.addMonthFromStr(time, 1);
//            time = StringDateTransferUtils.date2str_YM(t);
//        }
//    }
//
//    @Test
//    public void clean(){
//        Date start_time = StringDateTransferUtils.str2date_YM("2009-05", 0);
//        Date end_time = StringDateTransferUtils.str2date_YM("2020-01", 1);
//        List<Tweets> list = iTweetsDao.tweetsById(start_time, end_time);
//        int i = 0;
//        for(Tweets tweet: list){
//            tweet.setContent(Java2Python.doPost(tweet.getContent(), "HTTP://127.0.0.1:5000/clean").toLowerCase());
//            iTweetsDao.insert(tweet);
//            System.out.println("success" + " " + ++i);
//        }
//    }
//}
