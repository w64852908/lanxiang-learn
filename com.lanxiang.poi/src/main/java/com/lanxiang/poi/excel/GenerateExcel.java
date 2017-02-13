package com.lanxiang.poi.excel;

import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanxiang on 2017/2/10.
 */
public class GenerateExcel {

    private final static List<String> headerList = new ArrayList<>();

    private final static String excelPath = "/Users/lanxiang/desktop/excel/template.xls";

    private final static int totalRow = 5000;

    private final static String name = "手机";

    private final static String model = "iphone10";

    private final static int quantity = 1;

    private final static String unit = "个";

    private final static int needReturn = 1;

    @Before
    public void init() {
        initHeaderList();
    }

    private void initHeaderList() {
        headerList.add("物品编号");
        headerList.add("物品名称");
        headerList.add("规格型号");
        headerList.add("导入数量");
        headerList.add("数量单位(非必填)");
        headerList.add("是否需归还(非必填)");
    }

    //生成excel模板
    public void generateExcelTemplate() throws Exception {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("微办公-资产管理模板");
        generateExcelHeader(sheet);
        List<Material> materialList = generateMaterialList();
        generateExcelContent(sheet, materialList);
        FileOutputStream fos = new FileOutputStream(excelPath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    //生成表头
    private void generateExcelHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headerList.get(i));
            sheet.autoSizeColumn(i);
        }
    }

    private void generateExcelContent(Sheet sheet, List<Material> materialList) {
        int rowNum = 1;
        for (Material material : materialList) {
            Row row = sheet.createRow(rowNum);
            int snIndex = 0;
            int quantityIndex = 3;
            int nameIndex = 1;
            int modelIndex = 2;
            int unitIndex = 4;
            int needReturnIndex = 5;
            Cell nameCell = row.createCell(nameIndex);
            nameCell.setCellValue(material.getName());
            Cell modelCell = row.createCell(modelIndex);
            modelCell.setCellValue(material.getModel());
            Cell unitCell = row.createCell(unitIndex);
            unitCell.setCellValue(material.getUnit());
            Cell needReturnCell = row.createCell(needReturnIndex);
            if (material.getNeedReturn() == Material.NeedReturn.RIGHT.getValue()) {
                needReturnCell.setCellValue("是");
            } else if (material.getNeedReturn() == Material.NeedReturn.WRONG.getValue()) {
                needReturnCell.setCellValue("否");
            }
            Cell snCell = row.createCell(snIndex);
            snCell.setCellValue(material.getSn());
            Cell quantityCell = row.createCell(quantityIndex);
            quantityCell.setCellValue(material.getQuantity());
            rowNum++;
        }
    }

    private List<Material> generateMaterialList() {
        List<Material> materialList = new ArrayList<>();
        for (int i = 1; i <= totalRow; i++) {
            Material material = new Material();
            String sn = "sn-" + i;
            material.setSn(sn);
            material.setQuantity(quantity);
            material.setName(name);
            material.setNeedReturn(needReturn);
            material.setModel(model);
            material.setUnit(unit);
            materialList.add(material);
        }
        return materialList;
    }

    @Test
    public void run() throws Exception {
        generateExcelTemplate();
    }

    @Data
    static class Material {

        private String sn;

        private Integer quantity;

        private String name;

        private Integer needReturn;

        private String model;

        private String unit;

        public enum NeedReturn {

            RIGHT(1),

            WRONG(2);

            private final int value;

            private NeedReturn(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
    }
}
