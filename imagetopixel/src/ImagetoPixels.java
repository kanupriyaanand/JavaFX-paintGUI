import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagetoPixels {

    private static final String IMAGE_EXT_JPG = "jpg";
    private static final String IMAGE_EXT_JPEG = "jpeg";
    private static final String IMAGE_EXT_PNG = "png";
    private static final String IMAGE_EXT_GIF = "gif";
    /**
     * Exception message to be thrown when allowed image types are not read
     */
    public static final String IMAGE_ALLOW_TYPES = "Image types allowed - " + IMAGE_EXT_JPG + IMAGE_EXT_JPEG
            + IMAGE_EXT_PNG + IMAGE_EXT_GIF;

    /**
     * @param args
     */
    public static void main(String[] args) {

        BufferedImage bufferedImage = getImage("C:\\Users\\Anuranjan Anand\\IdeaProjects\\paintGUI\\paint.png");


        int[][] pixels = getImageToPixels(bufferedImage);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (pixels[i][j]!=-1)
                {
                    int color = pixels[i][j];

// Components will be in the range of 0..255:
                    int blue = color & 0xff;
                    int green = (color & 0xff00) >> 8;
                    int red = (color & 0xff0000) >> 16;
                    int grey =(blue+red+green)/3;
                    pixels[i][j]=red;
                }
                else pixels[i][j]=0;
                System.out.print(pixels[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Read image and return BufferedImage imageFullPath - full path of the image
     * with image file name
     */
    public static BufferedImage getImage(String imageFullPath) {
        BufferedImage bufferedImage = null;
        try {
            if (imageFullPath == null) {
                throw new NullPointerException("Image full path cannot be null or empty");
            }


            boolean isImage = isFileAnImage(imageFullPath);

            if (!isImage) {
                throw new ImagingOpException(IMAGE_ALLOW_TYPES);
            }

            String imagePath = imageFullPath;


            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * Get pixels in two dimensional array bufferedImage - get the BufferedImage
     * instance from the image
     */
    public static int[][] getImageToPixels(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            throw new IllegalArgumentException();
        }
        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();
        int[][] pixels = new int[h][w];
        for (int i = 0; i < h; i++) {

            bufferedImage.getRGB(0, i, w, 1, pixels[i], 0, w);
        }
        return pixels;
    }

    /**
     * Check a file is an image imageName - image file name with extension
     */
    private static boolean isFileAnImage(String imageName) {
        if (imageName == null) {
            throw new NullPointerException("Image full path cannot be null or empty");
        }
        File imageFile = new File(imageName);
        String ext = getFileExtension(imageFile);
        return IMAGE_EXT_GIF.equalsIgnoreCase(ext) || IMAGE_EXT_JPEG.equalsIgnoreCase(ext)
                || IMAGE_EXT_JPG.equalsIgnoreCase(ext) || IMAGE_EXT_PNG.equalsIgnoreCase(ext);
    }

    /**
     * Get file extension from the file
     *
     * @param file - file
     */
    public static String getFileExtension(File file) {
        if (file == null) {
            throw new NullPointerException("Image file cannot be null");
        }
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf(".");
        if (lastDotIndex > 0 && lastDotIndex < (name.length() - 1)) {
            return name.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }

}
