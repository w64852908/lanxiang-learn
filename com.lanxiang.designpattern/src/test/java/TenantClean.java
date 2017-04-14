import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lanxiang on 2017/4/12.
 */
public class TenantClean {

    private List<Long> doneList = new ArrayList<>();

    private List<Long> notYetList = new ArrayList<>();

    @Before
    public void init() {
        initDone();
        initNotYet();
    }

    private void initDone() {
        String doneStr = "870355,\n" +
                "884711,\n" +
                "884724,\n" +
                "884728,\n" +
                "884775,\n" +
                "884821";
        for (String str : doneStr.split(",\n")) {
            doneList.add(Long.valueOf(str));
        }
    }

    private void initNotYet() {
        String notYetStr = "8532,\n" +
                "361585,\n" +
                "376565,\n" +
                "376635,\n" +
                "376754,\n" +
                "376819,\n" +
                "376974,\n" +
                "448662,\n" +
                "827103,\n" +
                "827106,\n" +
                "827109,\n" +
                "827116,\n" +
                "827119,\n" +
                "827122,\n" +
                "827170,\n" +
                "827464,\n" +
                "827471,\n" +
                "827479,\n" +
                "827500,\n" +
                "870355,\n" +
                "870606,\n" +
                "884711,\n" +
                "884720,\n" +
                "884724,\n" +
                "884728,\n" +
                "884775,\n" +
                "884789,\n" +
                "884793,\n" +
                "884797,\n" +
                "884801,\n" +
                "884805,\n" +
                "884809,\n" +
                "884813,\n" +
                "884817,\n" +
                "884821,\n" +
                "884825,\n" +
                "884829,\n" +
                "884833,\n" +
                "884837,\n" +
                "884841,\n" +
                "913706,\n" +
                "913742,\n" +
                "913888,\n" +
                "913920,\n" +
                "914158,\n" +
                "915913,\n" +
                "916492,\n" +
                "916501,\n" +
                "916616,\n" +
                "916758,\n" +
                "917136,\n" +
                "917926,\n" +
                "917933,\n" +
                "917956,\n" +
                "917973,\n" +
                "917981,\n" +
                "918005,\n" +
                "918016,\n" +
                "918207,\n" +
                "918215,\n" +
                "918339,\n" +
                "918422,\n" +
                "918428,\n" +
                "918621,\n" +
                "918630,\n" +
                "918636,\n" +
                "918639,\n" +
                "918662,\n" +
                "918820,\n" +
                "919176,\n" +
                "919323,\n" +
                "919418,\n" +
                "919434,\n" +
                "919622,\n" +
                "919625,\n" +
                "919741,\n" +
                "919742,\n" +
                "919852,\n" +
                "920081,\n" +
                "920129,\n" +
                "920133,\n" +
                "920159,\n" +
                "920241,\n" +
                "920546,\n" +
                "920553,\n" +
                "920861,\n" +
                "920868,\n" +
                "920902,\n" +
                "920903,\n" +
                "920904,\n" +
                "920943,\n" +
                "920976,\n" +
                "920983,\n" +
                "921005,\n" +
                "921312,\n" +
                "921383,\n" +
                "921593,\n" +
                "921597,\n" +
                "921681,\n" +
                "921769,\n" +
                "921817,\n" +
                "921822,\n" +
                "921862,\n" +
                "922500,\n" +
                "922607,\n" +
                "922616,\n" +
                "922617,\n" +
                "922725,\n" +
                "922738,\n" +
                "922832,\n" +
                "922838,\n" +
                "923025,\n" +
                "923427,\n" +
                "923468,\n" +
                "923568,\n" +
                "923660,\n" +
                "981076,\n" +
                "981081,\n" +
                "988418,\n" +
                "1167580";
        for (String str : notYetStr.split(",\n")) {
            notYetList.add(Long.valueOf(str));
        }
    }

    @Test
    public void run() {
        Set<Long> merge = new HashSet<>();
        merge.addAll(doneList);
        merge.addAll(notYetList);
        for (Long notYet : merge) {
            System.out.println(notYet + ",");
        }
    }
}
