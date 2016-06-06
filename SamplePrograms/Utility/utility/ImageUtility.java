package utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * 画像のユーティリティ。
 */
public class ImageUtility extends Object
{
	/**
	 * 画像(anImage)を指定された幅(width)と高さ(height)に変形した複製を応答する。
	 * @param anImage 画像
	 * @param width 幅(横)
	 * @param height 高さ(縦)
	 * @return 画像
	 */
	public static BufferedImage adjustImage(BufferedImage anImage, int width, int height)
	{
		BufferedImage adjustedImage = new BufferedImage(width, height, anImage.getType());
		Graphics2D aGraphics = adjustedImage.createGraphics();
		aGraphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		aGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		aGraphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		aGraphics.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		aGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		aGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		aGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		aGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		aGraphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		aGraphics.drawImage(anImage, 0, 0, width, height, null);
		return adjustedImage;
	}

	/**
	 * 画像(anImage)をグレースケール画像に変換した複製を応答する。
	 * @param anImage 画像(カラー)
	 * @return 画像(グレースケール)
	 */
	public static BufferedImage grayscaleImage(BufferedImage anImage)
	{
		int width = anImage.getWidth();
		int height = anImage.getHeight();
		BufferedImage grayscaleImage = new BufferedImage(anImage.getWidth(), anImage.getHeight(), anImage.getType());
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int aRGB = anImage.getRGB(x, y);
				double luminance = ColorUtility.luminanceFromRGB(aRGB);
				aRGB = ColorUtility.convertRGBtoINT(luminance, luminance, luminance);
				grayscaleImage.setRGB(x, y, aRGB);
			}
		}
		return grayscaleImage;
	}
	
	/**
	 * 画像(anImage)をコピーして応答する。
	 * @param anImage 画像
	 * @return コピー画像
	 */
	public static BufferedImage copyImage(BufferedImage anImage)
	{
		BufferedImage copiedImage = new BufferedImage(anImage.getWidth(), anImage.getHeight(), anImage.getType());
		Graphics aGraphics = copiedImage.createGraphics();
		aGraphics.setColor(Color.white);
		aGraphics.fillRect(0, 0, copiedImage.getWidth(), copiedImage.getHeight());
		aGraphics.drawImage((Image)anImage, 0, 0, null);
		return copiedImage;
	}

	/**
	 * 画像を輝度マトリックスへ変換して応答する。
	 * @param anImage 画像
	 * @return 輝度（ルミナンス）の二次元配列
	 */
	public static double[][] convertImageToLuminanceMatrix(BufferedImage anImage)
	{
		int width = anImage.getWidth();
		int height = anImage.getHeight();
		double[][] aMatrix = new double[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int aRGB = anImage.getRGB(x, y);
				aMatrix[y][x] = ColorUtility.luminanceFromRGB(aRGB);
			}
		}
		return aMatrix;
	}

	/**
	 * 画像をYUVマトリックスへ変換して応答する。
	 * @param anImage 画像
	 * @return yの二次元配列とuの二次元配列とvの二次元配列の配列
	 */
	public static double[][][] convertImageToYUVMatrixes(BufferedImage anImage)
	{
		int width = anImage.getWidth();
		int height = anImage.getHeight();
		double[][] yMatrix = new double[height][width];
		double[][] uMatrix = new double[height][width];
		double[][] vMatrix = new double[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int aRGB = anImage.getRGB(x, y);
				double[] yuv = ColorUtility.convertRGBtoYUV(aRGB);
				yMatrix[y][x] = yuv[0];
				uMatrix[y][x] = yuv[1];
				vMatrix[y][x] = yuv[2];
			}
		}
		return new double[][][] { yMatrix, uMatrix, vMatrix };
	}

	/**
	 * 輝度マトリックスを画像へ変換して応答する。
	 * @param aMatrix 輝度（ルミナンス）の二次元配列
	 * @return 画像
	 */
	public static BufferedImage convertLuminanceMatrixToImage(double[][] aMatrix)
	{
		int width = aMatrix[0].length;
		int height = aMatrix.length;
		BufferedImage anImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D aGraphics = anImage.createGraphics();
		aGraphics.setColor(Color.white);
		aGraphics.fillRect(0, 0, width, height);
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				double luminance = aMatrix[y][x];
				// Color aColor = ColorUtility.colorFromLuminance(luminance);
				// aGraphics.setColor(aColor);
				// aGraphics.fillRect(x, y, 1, 1);
				double[] rgb = ColorUtility.convertYUVtoRGB(luminance, 0.0d, 0.0d);
				int aRGB = ColorUtility.convertRGBtoINT(rgb);
				anImage.setRGB(x, y, aRGB);
			}
		}
		return anImage;
	}

	/**
	 * YUVマトリックスを画像へ変換して応答する。
	 * @param yuvMatrixes yの二次元配列とuの二次元配列とvの二次元配列の配列
	 * @return 画像
	 */
	public static BufferedImage convertYUVMatrixesToImage(double[][][] yuvMatrixes)
	{
		double[][] yMatrix = yuvMatrixes[0];
		double[][] uMatrix = yuvMatrixes[1];
		double[][] vMatrix = yuvMatrixes[2];
		int width = yMatrix[0].length;
		int height = yMatrix.length;
		BufferedImage anImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D aGraphics = anImage.createGraphics();
		aGraphics.setColor(Color.white);
		aGraphics.fillRect(0, 0, width, height);
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				// double[] yuv = new double[] { yMatrix[y][x], uMatrix[y][x], vMatrix[y][x] };
				// Color aColor = ColorUtility.colorFromYUV(yuv);
				// aGraphics.setColor(aColor);
				// aGraphics.fillRect(x, y, 1, 1);
				double[] rgb = ColorUtility.convertYUVtoRGB(yMatrix[y][x], uMatrix[y][x], vMatrix[y][x]);
				int aRGB = ColorUtility.convertRGBtoINT(rgb);
				anImage.setRGB(x, y, aRGB);
			}
		}
		return anImage;
	}

	/**
	 * ファイル(aFile)から画像を読み込んで応答する。
	 * @param aFile ファイル（ファイルの拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImage(File aFile)
	{
		return ImageUtility.readImageFromFile(aFile);
	}

	/**
	 * ファイル名(aString)で指定されるファイルから画像を読み込んで応答する。
	 * @param aString ファイル名（ファイル名の拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImage(String aString)
	{
		return ImageUtility.readImageFromFile(aString);
	}

	/**
	 * ファイル(aFile)から画像を読み込んで応答する。
	 * @param aFile ファイル（ファイルの拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImageFromFile(File aFile)
	{
		BufferedImage anImage = null;
		try { anImage = ImageIO.read(aFile); }
		catch (IOException anException) { anException.printStackTrace(); }
		return anImage;
	}

	/**
	 * ファイル名(aString)で指定されるファイルから画像を読み込んで応答する。
	 * @param aString ファイル名（ファイル名の拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImageFromFile(String aString)
	{
		File aFile = new File(aString);
		return ImageUtility.readImageFromFile(aFile);
	}

	/**
	 * URL(aURL)から画像を読み込んで応答する。
	 * @param aURL ユニフォームリソースロケータ（最後の拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImageFromURL(URL aURL)
	{
		BufferedImage anImage = null;
		try { anImage = ImageIO.read(aURL); }
		catch (IOException anException) { anException.printStackTrace(); }
		return anImage;
	}

	/**
	 * URL(aString)で指定されるファイルから画像を読み込んで応答する。
	 * @param aString ファイル名（ファイル名の拡張子のフォーマットが重要）
	 * @return 画像
	 */
	public static BufferedImage readImageFromURL(String aString)
	{
		URL aURL = null;
		try { aURL = new URL(aString); }
		catch (MalformedURLException anException) { anException.printStackTrace(); }
		return ImageUtility.readImageFromURL(aURL);
	}

	/**
	 * 画像をファイル(aFile)へ拡張子で指定されたフォーマットで書き込む。
	 * @param anImage 画像
	 * @param aFile ファイル（ファイルの拡張子のフォーマットが重要）
	 */
	public static void writeImage(BufferedImage anImage, File aFile)
	{
		String aString = aFile.getName();
		aString = aString.substring(aString.lastIndexOf(".") + 1);
		try { ImageIO.write(anImage, aString, aFile); }
		catch (IOException anException) { anException.printStackTrace(); }
		return;
	}

	/**
	 * 画像をファイル名(aString)で指定されるファイルへ拡張子で指定されたフォーマットで書き込む。
	 * @param anImage 画像
	 * @param aString ファイル名（ファイル名の拡張子のフォーマットが重要）
	 */
	public static void writeImage(BufferedImage anImage, String aString)
	{
		File aFile = new File(aString);
		ImageUtility.writeImage(anImage, aFile);
		return;
	}
}
