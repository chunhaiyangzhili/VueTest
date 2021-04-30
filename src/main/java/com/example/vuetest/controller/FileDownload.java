package com.example.vuetest.controller;

import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Description
 *
 * @author zwzhang5
 * @description
 * @date Created on 2021/4/1 14:50
 */
public class FileDownload {
    public static void download(String name, HttpServletResponse response) throws Exception {
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        File file = new File(staticPath + "/img" + File.separator + name);
        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        System.out.println("11211");
        System.out.println("11211");
        System.out.println("11211");
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }
}
