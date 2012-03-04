import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Generating dragon fractal image
 * User: Tsykin
 * Date: 04.03.12
 * Time: 20:01
 *
 * @author Tsykin V.A. (nektoDev)
 * @version 0.3
 */
public class Dragon {
    public static void st(int x1, int y1, int x2, int y2, int k, Graphics2D g) {
        if (k > 0) {
            int xn = (x1 + x2) / 2 + (y2 - y1) / 2;
            int yn = (y1 + y2) / 2 - (x2 - x1) / 2;
            st(x2, y2, xn, yn, k - 1, g);
            st(x1, y1, xn, yn, k - 1, g);
        } else {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        for (int i = 1; i < 30; i++) {
            System.out.print(i);
            long time = System.currentTimeMillis();
            int k = i;
            int height = 2100;
            int width = 1800;
            BufferedImage scaledBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = scaledBI.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.blue);
            st(400, 800, 1500, 1700, k, g);
            g.dispose();
            String num = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
            File file = new File("d:\\test" + num + ".bmp");
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            ImageIO.write(scaledBI, "bmp", out);
            out.close();
            time = System.currentTimeMillis() - time;
            System.out.println(" - " + time + "ms");
        }
    }
}
