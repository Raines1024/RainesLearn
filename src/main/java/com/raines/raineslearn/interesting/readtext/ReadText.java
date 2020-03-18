package com.raines.raineslearn.interesting.readtext;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 来自无聊的图书格式整理
 */
public class ReadText {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
        Charset charset = Charset.forName("utf-8");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("/Users/raines/IdeaProjects/JavaPro/raines-learn/src/main/resources/1.txt"), charset)) {
            String line = null;
            //按行读取
            while ((line = reader.readLine()) != null) {
                if (line.contains(".epub")){
                    for (String s : line.split(".epub")) {
                        if (s.contains(" │  │  │  │")){
                            for (String str : s.split("│")) {
                                if (!(str.trim().equals(""))){
                                    if (!(str.trim().length() < 2)){
                                        System.out.println(str.trim()+"   ");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
