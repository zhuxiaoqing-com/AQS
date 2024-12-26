package com.youxi.genexcel.com;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    public static void main(String[] args) throws Exception {
        String excelFilePath = "H:\\mh\\game_server-develop\\rpg-server\\测试.xlsx";
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // 获取第一个页签

        List<String> headers = new ArrayList<>();
        List<String> types = new ArrayList<>();

        Row headerRow = sheet.getRow(0);
        Row typeRow = sheet.getRow(1);

        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }

        for (Cell cell : typeRow) {
            types.add(cell.getStringCellValue());
        }

        // 打印表头和类型（调试用）
        System.out.println("Headers: " + headers);
        System.out.println("Types: " + types);

        // 生成JavaBean类代码（假设输出到控制台）
        generateJavaBeanClass(headers, types);

        // 转换为CSV（假设输出到控制台，实际应用中可以输出到文件）
        convertToCSV(sheet, headers);

        workbook.close();
        fis.close();
    }

    private static void generateJavaBeanClass(List<String> headers, List<String> types) throws Exception {
        String className = "MyJavaBean";
        StringBuilder sb = new StringBuilder();

        sb.append("import java.util.List;").append(" \n");
        sb.append("import java.util.Map;").append(" \n");
        sb.append("import java.util.*;").append(" \n");
        sb.append("import java.util.function.Function;").append(" \n");
        sb.append("import java.util.stream.Collectors;").append(" \n");
        sb.append(" \n");
        sb.append(" \n");
        sb.append("public class ").append(className).append(" {\n");

        for (int i = 0; i < headers.size(); i++) {
            String s1 = headers.get(i);
            if(s1 == null || s1.trim().isEmpty()) {
                continue;
            }
            String fieldName = toCamelCase(s1);
            String fieldType = mapTypeToJavaType(types.get(i));
            if (fieldType == null) {
                String a = "Type %s not supported i %s";
                throw new RuntimeException(String.format(a, types.get(i), i + ""));
            }
            sb.append("    private ").append(fieldType).append(" ").append(fieldName).append(";\n");
            String capitalize = capitalize(fieldName);
            sb.append("    public ").append(fieldType).append(" get").append(capitalize).append("() {\n");
            sb.append("        return ").append(fieldName).append(";\n");
            sb.append("    }\n");
            sb.append("    public void set").append(capitalize).append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
            sb.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            sb.append("    }\n");

            // 将怎么解析这个csv 的代码也写上
            sb.append("    public void parse").append(capitalize).append("(").append("String value").append(") {\n");
            String s = mapTypeParse(types.get(i), "value");
            if (s == null) {
                String a = "mapTypeParse %s not supported i %s";
                throw new RuntimeException(String.format(a, types.get(i), i + ""));
            }
            sb.append("        this.").append(fieldName).append(" = ").append(s).append(";\n");
            sb.append("    }\n");
        }

        sb.append(parseStringListMap());
        sb.append("}\n");
        File file = new File(className+".java");
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileUtils.writeStringToFile(file, sb.toString(), "UTF-8");
    }

    private static String toCamelCase(String s) {
        String[] parts = s.split("\\s+");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(capitalize(parts[i]));
        }
        return camelCaseString.toString();
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private static String mapTypeToJavaType(String type) {
        String lowerCase = type.toLowerCase();
        String s = mapTypeToJavaBaseType(lowerCase, false);
        if (s != null) {
            return s;
        }

        // 解析不了 如果是list list:int ...
        if (lowerCase.startsWith("list")) {
            String[] split = lowerCase.split(SplitSymbolConst.MaoHao);
            String listValueStr = split[1];
            String listValue = mapTypeToJavaBaseType(listValueStr, true);
            if (listValue == null) {
                return null;
            }
            return "List<" + listValue + ">";
        }

        // 解析不了 如果是map map:int:int
        if (lowerCase.startsWith("map")) {
            String[] split = lowerCase.split(SplitSymbolConst.MaoHao);
            String mapKeyStr = split[1];
            String listValueStr = split[2];
            String mapKey = mapTypeToJavaBaseType(mapKeyStr, true);
            if (mapKey == null) {
                return null;
            }
            String mapValue = mapTypeToJavaBaseType(listValueStr, true);
            if (mapValue == null) {
                return null;
            }
            return "Map<" + mapKey + "," + mapValue + ">";
        }
        return null;
    }

    private static String mapTypeParse(String type, String value) {
        String lowerCase = type.toLowerCase();
        String s = mapBaseTypeParse(lowerCase, value);
        if (s != null) {
            return s;
        }

        // 解析不了 如果是list list:int ...
        if (lowerCase.startsWith("list")) {
            String[] split = lowerCase.split(SplitSymbolConst.MaoHao);
            String listValueStr = split[1];
            String parseListValue = mapBaseTypeParseFunction(listValueStr);
            if (parseListValue == null) {
                return null;
            }
            String returnStr="parseStrList(%s, SplitSymbolConst.FenHao, %s)";
            return String.format(returnStr, value, parseListValue);
        }

        // 解析不了 如果是map map:int:int
        if (lowerCase.startsWith("map")) {
            String[] split = lowerCase.split(SplitSymbolConst.MaoHao);
            String mapKeyStr = split[1];
            String listValueStr = split[2];
            String mapKey = mapBaseTypeParseFunction(mapKeyStr);
            if (mapKey == null) {
                return null;
            }
            String mapValue = mapBaseTypeParseFunction(listValueStr);
            if (mapValue == null) {
                return null;
            }

           String returnStr =  "parseRewardStrKV(%s,%s,%s)";
            return String.format(returnStr, value, mapKey, mapValue);
        }

       return null;
    }


    private static String mapTypeToJavaBaseType(String type, boolean box) {
        switch (type.toLowerCase()) {
            case "string":
                return "String";
            case "int":
                return box ? "Integer" : "int";
            case "double":
                return box ? "Double" : "double";
            default:
                return null; // 处理不了就返回null;
        }
    }

    private static String mapBaseTypeParse(String type, String value) {
        String s = null;
        switch (type.toLowerCase()) {
            case "string":
                s = " %s";
                break;
            case "int":
                s = " Integer.parseInt(%s) ";
                break;
            case "double":
                s = " Double.parseDouble(%s) ";
                break;
            default:
                break;
        }

        if(s!= null) {
            return String.format(s, value);
        }
       return s; // 解析不了
    }

    private static String mapBaseTypeParseFunction(String type) {
        String s = null;
        switch (type.toLowerCase()) {
            case "string":
                s = "  String::valueOf";
                break;
            case "int":
                s = "  Integer::parseInt";
                break;
            case "double":
                s = "  Double::parseDouble";
                break;
            default:
                break;
        }

        return s; // 解析不了
    }

    private static String parseStringListMap(){
        return "\n" +
                "    public static <K> List<K> parseStrList(String rewardGroupStr, String split, Function<String, K> function) {\n" +
                "        if (rewardGroupStr == null || rewardGroupStr.isEmpty()) {\n" +
                "            return Collections.emptyList();\n" +
                "        }\n" +
                "        String[] param = rewardGroupStr.split(split);\n" +
                "        return Arrays.stream(param).map(function).collect(Collectors.toList());\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public static <K, V> Map<K, V> parseRewardStrKV(String rewardGroupStr, Function<String, K> k, Function<String, V> v) {\n" +
                "        if (rewardGroupStr == null || rewardGroupStr.trim().isEmpty()) {\n" +
                "            return Collections.emptyMap();\n" +
                "        }\n" +
                "        String[] rewardStrArray = rewardGroupStr.split(SplitSymbolConst.JingHao);\n" +
                "        HashMap<K, V> rewardMap = new HashMap<>();\n" +
                "\n" +
                "        for (String rewardStr : rewardStrArray) {\n" +
                "            String[] param = rewardStr.split(SplitSymbolConst.FenHao);\n" +
                "            if (param.length < 2) {\n" +
                "                continue;\n" +
                "            }\n" +
                "            K itemId = k.apply(param[0]);\n" +
                "            V itemNum = v.apply(param[1]);\n" +
                "            rewardMap.put(itemId, itemNum);\n" +
                "        }\n" +
                "        return rewardMap;\n" +
                "    }\n";
    }


    private static void convertToCSV(Sheet sheet, List<String> headers) throws IOException {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(String.join(",", headers)).append("\n");

        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            rowData.add(cell.getDateCellValue().toString());
                        } else {
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    default:
                        rowData.add("");
                }
            }
            csvContent.append(String.join(",", rowData)).append("\n");
        }

        // 打印CSV内容（调试用）
        System.out.println(csvContent.toString());
    }
}