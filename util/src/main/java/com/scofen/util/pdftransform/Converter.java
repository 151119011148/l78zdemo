package com.scofen.util.pdftransform;
import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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

    private static String pdfPath = "E:\\gaofeng\\worker\\project_relation\\disa\\需求工程基础、原理和技术_13070058.pdf";
    private static String pngPath = "E:\\gaofeng\\worker\\project_relation\\disa\\需求工程基础\\png";
    private static String logPath = "E:\\gaofeng\\worker\\project_relation\\disa\\需求工程基础\\log\\";


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
        String target = logPath + "需求工程基础.log";
        try {

            List<File> files = Lists.newArrayList(dir.listFiles());
            List<File> sortFiles = files.stream()
                    .sorted(Comparator.comparing(file ->
                            Integer.parseInt(file.getName().substring(file.getName().lastIndexOf("_") + 1, file.getName().lastIndexOf(".")))))
                    .collect(Collectors.toList());
            out = new BufferedWriter(new FileWriter(target));
            BufferedWriter finalOut = out;
            int index = 0;
            while (index < sortFiles.size()) {
                File png = sortFiles.get(index);
                try {
                    Thread.sleep(1500);
                    JSONObject jsonObject = client.basicGeneral(png.getPath(), new HashMap<>());
                    List<Word> words = JSON.parseArray(jsonObject.get("words_result").toString(), Word.class);
                    String result = null;
                    while (StringUtils.isEmpty(result)){
                        try {
                            result = words.stream().map(Word::getWords).collect(Collectors.joining("\n"));
                        }catch (Exception e){
                            result = null;
                        }
                    }
                    System.out.println(result);
                    finalOut.write(result);
                    index++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

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