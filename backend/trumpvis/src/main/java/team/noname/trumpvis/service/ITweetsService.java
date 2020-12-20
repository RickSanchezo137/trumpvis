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
     *  help
     */
    String help_vis_calendar();
}
