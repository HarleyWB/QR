import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class test {
    private static final String CHARSET = "utf-8";

    /**
     * 禁止生成实例，生成实例也没有意义。
     */
    public static void main(String[] a) throws IOException {
        File file = new File("C:\\Users\\Harley\\Desktop\\新建文件夹\\Image 001.bmp");
        //System.out.println(test.decode(file));
        FileInputStream f = null;

        f = new FileInputStream(file);


        System.out.println(test.decode(f));


    }

    /**
     * 解码，需要javase包。
     * 文件方式解码
     *
     * @param file
     * @return
     */
    public static String decode(File file) {
        BufferedImage image;
        try {
            if (file == null || file.exists() == false) {
                throw new Exception(" File not found:" + file.getPath());
            }
            image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result;
            // 解码设置编码方式为：utf-8，
            Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();

            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 流方式解码
     *
     * @param input
     * @return
     */
    public static String decode(InputStream input) {

        BufferedImage image;
        try {
            if (input == null) {
                throw new Exception(" input is null");
            }

            image = ImageIO.read(input);

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result;

            // 解码设置编码方式为：utf-8，
            Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

            result = new MultiFormatReader().decode(bitmap, hints);

            return result.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}