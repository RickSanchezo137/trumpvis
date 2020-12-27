package team.noname.trumpvis.utils;

import team.noname.trumpvis.pojo.help.HelpWordCloudMsg;

import java.util.ArrayList;
import java.util.List;

public class KeyWordResultProcess {
    public static List<HelpWordCloudMsg> getResult(String text){
        List<HelpWordCloudMsg> helpWordCloudMsgs = new ArrayList<>();
        text = text.replace("[(", "");
        text = text.replace(")]", "");
        text = text.replace("'", "");
        String[] text2 = text.split("\\), \\(");

        for (String e: text2){
            String[] data = e.split(",");
            HelpWordCloudMsg helpWordCloudMsg = new HelpWordCloudMsg();
            helpWordCloudMsg.setName(data[0].trim());
            helpWordCloudMsg.setValue((int)(Double.parseDouble(data[1].trim()) * 10000));
            helpWordCloudMsgs.add(helpWordCloudMsg);
        }
        return helpWordCloudMsgs;
    }
}
