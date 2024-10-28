package com.scofen.util.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {


    public static void main(String[] args) throws IOException {
//        String filePath = "E:\\gaofeng\\worker\\source_code\\csmimport\\src\\main\\java\\com\\huawang\\xmlImport\\xmi\\xsd\\ModelExtension.java";
//        addAnnotations(filePath);

        String directoryPath = "E:\\gaofeng\\worker\\source_code\\csmimport\\src\\main\\java";
        List<String> javaPaths = traverseFiles(directoryPath);
        javaPaths.forEach(filePath -> addAnnotations(filePath));

    }




    private static List<String> MUST_CONTAINS = Stream.of("(", ")", "{").collect(Collectors.toList());
    private static List<String> MAY_CONTAINS = Stream.of("public", "protected", "private", "<init>").collect(Collectors.toList());
    private static List<String> NOT_CONTAINS = Stream.of("void", "class").collect(Collectors.toList());

    public static boolean evaluate(String str) {
        boolean hasAllMust = MUST_CONTAINS.stream().allMatch(str::contains);

        // 检查是否为构造方法
        boolean isConstructor;
        Matcher matcher = Pattern.compile("\\s*<init>\\s*\\(|\\s*public\\s+[A-Za-z0-9_]+\\s*\\(|\\s*protected\\s+[A-Za-z0-9_]+\\s*\\(|\\s*private\\s+[A-Za-z0-9_]+\\s*\\(").matcher(str);
        if (matcher.find()) {
            isConstructor = true;
        } else {
            isConstructor = false;
        }

        boolean hasOneMay = MAY_CONTAINS.stream().anyMatch(s -> (s.equals("<init>") && isConstructor) || str.contains(s));
        boolean hasNoneNot = NOT_CONTAINS.stream().noneMatch(str::contains);

        return hasAllMust && hasOneMay && hasNoneNot && !isConstructor;
    }
    public static void addAnnotations(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("package com") || line.startsWith("package org")) {
                    fileContent.append(line)
                            .append("\n")
                            .append("import org.jetbrains.annotations.Nullable;")
                            .append("\n");
                } else if (evaluate(line)) {
                    // 假设要在方法声明前添加注解
                    line = line.replaceAll("(public|private)", "$1 @Nullable");
                    fileContent.append(line).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
            }
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(fileContent.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<String> traverseFiles(String directoryPath) {
        List<String> javaFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        traverse(directory, javaFiles);
        return javaFiles;
    }

    private static void traverse(File file, List<String> javaFiles) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files!= null) {
                for (File subFile : files) {
                    traverse(subFile, javaFiles);
                }
            }
        } else if (file.getName().endsWith(".java")) {
            javaFiles.add(file.getAbsolutePath());
        }
    }
}
