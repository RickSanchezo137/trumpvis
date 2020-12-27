package team.noname.trumpvis.service;

public interface ITweetsService {
    /**
     *  main_home
     */
    String home_vis(String startTime, String endTime);

    /**
     *  main_trend
     */
    String trend_vis(String startTime, String endTime);

    /**
     *  help_calendar
     */
    String help_vis_calendar();

    /**
     *  help_calendar
     */
    String help_vis_wordcloud(String startTime, String endTime);

    /**
     *  help_theme
     */
    String help_vis_theme(String startTime, String endTime);

    /**
     *  help_emotion
     */
    String help_vis_emotion(String startTime, String endTime);

    /**
     *  help_relation
     */
    String help_vis_relation(String startTime, String endTime);
}
