package team.noname.trumpvis.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    public static String objectToJson(Object o){
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
