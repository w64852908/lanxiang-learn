package com.lanxiang.poi.excel;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2017/2/10.
 */
public class GenerateExcel {

    private final static List<String> headerList = new ArrayList<>();


    @Before
    public void init(){
    }

    private void initHeaderList() {
        headerList.add("物品编号");
        headerList.add("物品名称");
        headerList.add("规格型号");
        headerList.add("导入数量");
        headerList.add("数量单位(非必填)");
        headerList.add("是否需归还(非必填)");
    }
}
