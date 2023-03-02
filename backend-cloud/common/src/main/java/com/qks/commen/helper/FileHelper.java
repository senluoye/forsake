package com.qks.commen.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;


public class FileHelper {

    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        String wholePath = filePath + fileName;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(wholePath);
        out.write(file);
        out.flush();
        out.close();
        resize(wholePath, wholePath, 215, 215);
        return "/stc/images/" + fileName;
    }

    public static boolean resize(String src, String to, int newWidth, int newHeight) {
        try {
            File srcFile = new File(src);
            File toFile = new File(to);
            BufferedImage img = ImageIO.read(srcFile);
            int w = img.getWidth();
            int h = img.getHeight();
            BufferedImage dimg = new BufferedImage(newWidth, newHeight, img.getType());
            Graphics2D g = dimg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, w, h, null);
            g.dispose();
            ImageIO.write(dimg, "jpg", toFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}