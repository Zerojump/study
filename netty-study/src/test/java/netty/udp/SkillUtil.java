package netty.udp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/8/4
 */
public class SkillUtil {

    //<skillId,skill> 假如001为技能开窗window_on的技能id，则 <001,window_on=1>，
    private ConcurrentHashMap<String, String> skillMap = new ConcurrentHashMap<>();

    {
        skillMap.put("101", "window_on=1");
        skillMap.put("102", "window_on=0");
        skillMap.put("103", "window_off=1");
        skillMap.put("201", "curtain_on=1");
        skillMap.put("202", "curtain_on=0");
        skillMap.put("203", "curtain_off=1");
        skillMap.put("301", "aircondition=1");
        skillMap.put("302", "aircondition=0");
        skillMap.put("303", "wind_low=1");
        skillMap.put("304", "wind_auto=1");
        skillMap.put("305", "wind_middle=1");
        skillMap.put("306", "wind_high=1");
        skillMap.put("307", "cold=1");
        skillMap.put("308", "hot=1");
        skillMap.put("309", "temperature=22");
        skillMap.put("401", "tv=1");
        skillMap.put("402", "tv=0");
        skillMap.put("501", "mirror_light=1");
        skillMap.put("502", "mirror_light=0");
        skillMap.put("601", "night_light=0");
        skillMap.put("701", "aisle_light=1");
        skillMap.put("702", "aisle_light=0");
        skillMap.put("801", "wc_light=1");
        skillMap.put("802", "wc_light=0");
        skillMap.put("901", "pqs=1");
        skillMap.put("902", "pqs=0");
        skillMap.put("1001", "cabinet_light=1");
        skillMap.put("1002", "cabinet_light=0");
        skillMap.put("1101", "lamp_light=1");
        skillMap.put("1102", "lamp_light=0");
        skillMap.put("1201", "belt_light=1");
        skillMap.put("1202", "belt_light=0");
        skillMap.put("1301", "left_light=1");
        skillMap.put("1302", "left_light=0");
        skillMap.put("1401", "right_light=1");
        skillMap.put("1402", "right_light=0");
        skillMap.put("1501", "tuifang=1");
        skillMap.put("1601", "clean=1");
        skillMap.put("1701", "call=1");
    }

    public String buildControlReq(String hotelId, String roomName, String skill) {
        return String.format("yykk/hotel_id=%s/room_name=%s/%s", hotelId, roomName, skill);
    }
}
