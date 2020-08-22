package com.scofen.study.util.pdftransform;
import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  20:41 2020/6/12
 * Description:
 * Modified  By:
 */
public class Converter {

    private static final String appId = "20362099";
    private static final String apiKey = "9bfGsQVES3OCRyK4jlsjYwCr";
    private static final String secretKey = "Cd1DT5allOQQpK1BTwEkPD2htKXH6jwj";
    private static AipOcr client;

    private static String pdfPath = "C:\\Users\\GF\\Desktop\\pdf\\MySQL技术内幕  InnoDB存储引擎  第2版.pdf";
    private static String pngPath = "C:\\Users\\GF\\Desktop\\sourceCode\\demo\\study\\util\\png\\mysql";
    private static String logPath = "C:\\Users\\GF\\Desktop\\sourceCode\\demo\\study\\util\\log\\MySQL技术内幕";


    private static void init(){
        // 初始化一个AipOcr
        client = new AipOcr(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

/*        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");*/
    }

    public static void main(String[] args) {
        init();
//        pdf2Png();
        png2Log();
    }

    private static void pdf2Png() {
        File file = new File(pdfPath);
        try {
            PDDocument document = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(document);
            int pages = document.getNumberOfPages();
//            final List<BufferedImage> picList = Lists.newArrayList();
            for (int i = 0; i < pages; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 200f, ImageType.ARGB);
//                picList.add(image);
                File dir = new File(pngPath);
/*                if (!dir.exists())
                    dir.mkdir();*/
                String fileName = dir + "\\" + (i + 1) + ".png";
                File imageFile = new File(fileName);
                if (imageFile.length() < 1024 * 100){
//                    Thread.sleep(900);
                    System.out.println(fileName + imageFile.length());
                    ImageIO.write(image, "PNG", new File(fileName));
                }
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void png2Log(){
        File dir = new File(pngPath);
        BufferedWriter out = null;
        String target = logPath + ".log";
        try {

            List<File> files = Lists.newArrayList(dir.listFiles());
/*            files.stream().sorted((o1, o2) -> {
                int start1 = o1.getName().lastIndexOf("_") + 1;
                int end1 = o1.getName().lastIndexOf(".");
                String sor1 = o1.getName().substring(start1, end1);
                System.out.println(sor1);
                int sort1 = Integer.parseInt(sor1);
                int start2 = o2.getName().lastIndexOf("_") + 1;
                int end2 = o2.getName().lastIndexOf(".");
                String sor2 = o2.getName().substring(start2, end2);
                System.out.println(sor2);
                int sort2 = Integer.parseInt(sor2);
                return sort1 - sort2;
            }).collect(Collectors.toList());*/
            List<File> sortFiles = files.stream()
                    .sorted(Comparator.comparing(file ->
                            Integer.parseInt(file.getName().substring(file.getName().lastIndexOf("_") + 1, file.getName().lastIndexOf(".")))))
                    .collect(Collectors.toList());
            out = new BufferedWriter(new FileWriter(target));
            BufferedWriter finalOut = out;
            sortFiles.forEach(png -> {
                try {
                    Thread.sleep(900);
                    JSONObject jsonObject = client.basicGeneral(png.getPath(), new HashMap<>());
                    List<Word> words = JSON.parseArray(jsonObject.get("words_result").toString(), Word.class);
                    String result = words.stream().map(Word::getWords).collect(Collectors.joining("\n"));
                    System.out.println(result);
                    finalOut.write(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

@Data
class Word{
    private String words;
}