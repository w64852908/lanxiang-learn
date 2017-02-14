package com.lanxiang.poi.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2017/2/10.
 */
public class GenerateExcel {

    private List<String> headerList = new ArrayList<>();

    private String outputPath = "/Users/lanxiang/desktop/excel/template.xls";

    private int totalRow = 5000;

    private String name = "手机";

    private String model = "iphone7";

    private int quantity = 1;

    private String unit = "个";

    private int needReturn = 1;

    private void init(String configFile) {
        initHeaderList();
        initConfig(new File(configFile));
    }

    private void initHeaderList() {
        headerList.add("物品编号");
        headerList.add("物品名称");
        headerList.add("规格型号");
        headerList.add("导入数量");
        headerList.add("数量单位(非必填)");
        headerList.add("是否需归还(非必填)");
    }

    private void initConfig(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        Template template = null;
        try {
            template = objectMapper.readValue(file, Template.class);
        } catch (IOException e) {
            System.out.println("找不到配置文件");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("配置文件错误");
            e.printStackTrace();
        }
        if (template.getOutPutPath() != null) {
            this.outputPath = template.getOutPutPath();
        }
        if (template.getTotalRow() != null) {
            this.totalRow = template.getTotalRow();
        }
        if (template.getName() != null) {
            this.name = template.getName();
        }
        if (template.getModel() != null) {
            this.model = template.getModel();
        }
        if (template.getQuantity() != null) {
            this.quantity = template.getQuantity();
        }
        if (template.getUnit() != null) {
            this.unit = template.getUnit();
        }
        if (template.getNeedReturn() != null) {
            this.needReturn = template.getNeedReturn();
        }
    }

    //生成excel模板
    public void generateExcelTemplate() throws Exception {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("微办公-资产管理模板");
        generateExcelHeader(sheet);
        List<Material> materialList = generateMaterialList();
        generateExcelContent(sheet, materialList);
        FileOutputStream fos = new FileOutputStream(outputPath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        System.out.println("done.");
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

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            System.out.println("没有配置文件,生成默认的配置文件");
        }
        System.out.println(args[0]);
//        GenerateExcel excel = new GenerateExcel();
//        excel.init(args[0]);
//        excel.generateExcelTemplate();
    }

    static class Material {

        private String sn;

        private Integer quantity;

        private String name;

        private Integer needReturn;

        private String model;

        private String unit;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNeedReturn() {
            return needReturn;
        }

        public void setNeedReturn(Integer needReturn) {
            this.needReturn = needReturn;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

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
