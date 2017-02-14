package com.lanxiang.poi.excel;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.*;


/**
 * Created by lanxiang on 2016/11/11.
 */
@Slf4j
public class XSSFTest {

    private String excelFolder = "/Users/lanxiang/Downloads/shuzi100";

    private String outputPath = "/Users/lanxiang/Downloads/id2file.txt";

//    private String excelFolder = "/Users/lanxiang/Desktop/excel/forCustomer";

//    private String outputPath = "/Users/lanxiang/Desktop/excel/forCustomer/id2file.txt";

    private Map<String, List<String>> idToFiles = new HashMap<>();

    private List<Map.Entry<String, List<String>>> sortedIdToFiles;

    @Test
    public void readAllExcel() throws Exception {
        List<File> excelList = getAllExcelFiles(excelFolder);
        for (File excel : excelList) {
            System.out.print(excel.getName() + "中共有");
            Workbook wb = WorkbookFactory.create(excel);
            getCellContents(wb);
        }

//        throughMap();
        sortMap();
        outputToFile();
    }

    private List<File> getAllExcelFiles(String excelPath) {
        List<File> result = new ArrayList<>();
        File excelFolder = new File(excelPath);
        File[] excelFiles = excelFolder.listFiles();
        for (File excel : excelFiles) {
            if (!excel.getName().startsWith("~$") && !excel.getName().equals(".DS_Store")) {
                result.add(excel);
            }
        }
        return result;
    }

    private void getCellContents(Workbook wb) {
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(0);

        int recorderNoIndex = -1, addFilesIndex = -1;
        //遍历表头,记录需要字段的下标
        Row title = sheet.getRow(0);
        for (Cell cell : title) {
            String text = formatter.formatCellValue(cell);
            if (text.equals("recorderNO") || text.equals("projid")) {
                recorderNoIndex = cell.getColumnIndex();
            }
            if (text.equals("addfiles")) {
                addFilesIndex = cell.getColumnIndex();
            }
        }
        int num = 0;
        if (recorderNoIndex >= 0 && addFilesIndex >= 0) {
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Cell recordNoCell = row.getCell(recorderNoIndex);

                Cell addFilesCell = row.getCell(addFilesIndex);

                if (addFilesCell != null) {
                    String[] fileStrings = addFilesCell.getRichStringCellValue().getString().split("\\|");
                    List<String> fileStringList = new ArrayList<>();
                    for (String fileString : fileStrings) {
                        if (fileString != null && !fileString.equals("")) {
                            fileStringList.add(fileString);
                        }
                    }
                    put(idToFiles, recordNoCell.getRichStringCellValue().getString(), fileStringList);
                    num++;
                }
            }
        }
        System.out.println(num + "个文件.");
    }

    private void put(Map<String, List<String>> map, String id, List<String> fileStringList) {
        if (map.containsKey(id)) {
            List<String> source = map.get(id);
            for (String fileString : fileStringList) {
                if (!source.contains(fileString)) {
                    source.add(fileString);
                } else {
                    throw new RuntimeException("duplicated file");
                }
            }
        } else {
            map.put(id, fileStringList);
        }
    }

    private void sortMap() {
        sortedIdToFiles = new ArrayList<>(idToFiles.entrySet());
        Collections.sort(sortedIdToFiles, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }

    private void outputToFile() throws Exception {
        StringBuilder sb;
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File(outputPath)));
        for (Map.Entry<String, List<String>> entry : sortedIdToFiles) {
            String id = entry.getKey();
            List<String> fileStringList = idToFiles.get(id);
            for (String fileString : fileStringList) {
                sb = new StringBuilder(id).append(":").append(fileString);
                pw.println(sb.toString());
            }
        }
        pw.flush();
        pw.close();
    }

    private void throughMap() {
        System.out.println("map size : " + idToFiles.size());
        for (String id : idToFiles.keySet()) {
            System.out.println("id : " + id + ", " + idToFiles.get(id));
        }
    }

    @Test
    public void run() throws JsonProcessingException {
        Template template = new Template();
        template.setName("手机");
        template.setOutPutPath("/Users/lanxiang/desktop/excel/template.xls");
        template.setTotalRow(5000);
        template.setModel("iphone7s");
        template.setQuantity(1);
        template.setUnit("个");
        template.setNeedReturn(1);
        System.out.println(new ObjectMapper().writeValueAsString(template));

        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(path);
    }
}
