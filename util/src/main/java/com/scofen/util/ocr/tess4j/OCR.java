package com.scofen.util.ocr.tess4j;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.Arrays;

public class OCR {

    static Tesseract tesseract;

    static {
        tesseract = new Tesseract();
        // 设置训练数据文件夹路径
        tesseract.setDatapath("E:\\gaofeng\\worker\\project_relation\\disa\\需求工程基础\\log");
        // 设置为中文简体
        tesseract.setLanguage("chi_sim");
    }

    public static void convert() {
        // 替换为你要识别的图片的实际路径
        String imageFolder = "E:\\gaofeng\\worker\\project_relation\\disa\\需求工程基础\\png";
        File folder = new File(imageFolder);
        Arrays.stream(folder.listFiles())
                .sorted()
                .forEach(imageFile -> {
                    try {
                        String result = tesseract.doOCR(imageFile);
                        System.out.println("识别结果: " + result);
                    } catch (TesseractException e) {
                        e.printStackTrace();
                    }
                });
    }
}
