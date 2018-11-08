package com.lanxiang.poi.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by lanxiang on 2017/2/10.
 */
public class SplitLine {

    private List<String> headerList = new ArrayList<>();

    private String outputPath = "template.xls";

    private void initHeaderList() {
        headerList.add("id");
        headerList.add("suggest");
        headerList.add("feedback");
        headerList.add("actual");
        headerList.add("effective");
    }

    //生成excel模板
    private void generateExcelTemplate() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("materil-test");
        generateExcelHeader(sheet);
        generateExcelContent(workbook, sheet);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputPath);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            System.out.println("生成excel的目标路径错误");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    private void generateExcelContent(Workbook workbook, Sheet sheet) {
        int rowNum = 1;
        for (int i = 0; i < 10; i++) {
            int startRowNum = rowNum;
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(rowNum);
            row.createCell(1).setCellValue(rowNum * 10);
            row.createCell(2).setCellValue(rowNum * 5);
            row.createCell(3).setCellValue(rowNum * 3);
            Hyperlink link = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_URL);
            link.setAddress("http://vfile.meituan.net/moviebd/d3aea62fb6880695d706b53103ba4523116231.");
            Cell pic1 = row.createCell(4);
            pic1.setCellValue("图片1");
            pic1.setHyperlink(link);

            Hyperlink link2 = workbook.getCreationHelper().createHyperlink(Hyperlink.LINK_URL);
            link2.setAddress("http://vfile.meituan.net/moviebd/1a5b2903076160ddf00d04a96564d5b5494521.");
            Cell pic2 = sheet.createRow(++rowNum).createCell(4);
            pic2.setCellValue("图片2");
            pic2.setHyperlink(link2);

            for (int k = 0; k < 4; k++) {
                sheet.addMergedRegion(new CellRangeAddress(startRowNum, rowNum, k, k));
            }

            for (int k = 0; k < 4; k++) {
                try {
                    sheet.addMergedRegion(new CellRangeAddress(startRowNum, rowNum, k, k));
                } catch (Exception e) {
                    System.out.println(
                            "2333333333"
                    );
                }
            }

            rowNum++;
        }
    }

    public static void main(String[] args) throws Exception {
        SplitLine excel = new SplitLine();
        excel.initHeaderList();
        excel.generateExcelTemplate();
    }
}