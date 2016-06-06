package utility;

import java.awt.Color;

/**
 * 色のユーティリティである。
 */
public class ColorUtility extends Object
{
	/**
	 * ルミナンス(輝度)から色のインスタンスを生成して応答する。
	 * @param luminance 輝度(ルミナンス)
	 * @return 色(RGB)
	 */
	public static Color colorFromLuminance(double luminance)
	{
		int aRGB = ColorUtility.convertRGBtoINT(luminance, luminance, luminance);
		Color aColor = new Color(aRGB);
		return aColor;
	}
	
	/**
	 * 実数RGB配列(赤成分・緑成分・青成分)から色のインスタンスを生成して応答する。
	 * @param rgb 実数RGB配列(赤成分・緑成分・青成分)
	 * @return 色(RGB)
	 */
	public static Color colorFromRGB(double[] rgb)
	{
		double r = rgb[0];
		double g = rgb[1];
		double b = rgb[2];
		return ColorUtility.colorFromRGB(r, g, b);
	}
	
	/**
	 * 実数RGB(赤成分・緑成分・青成分)から色のインスタンスを生成して応答する。
	 * @param r 赤成分
	 * @param g 緑成分
	 * @param b 青成分
	 * @return 色(RGB)
	 */
	public static Color colorFromRGB(double r, double g, double b)
	{
		int aRGB = ColorUtility.convertRGBtoINT(r, g, b);
		Color aColor = new Color(aRGB);
		return aColor;
	}
	
	/**
	 * 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)から色のインスタンスを生成して応答する。
	 * @param yuv 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 * @return 色(RGB)
	 */
	public static Color colorFromYUV(double[] yuv)
	{
		double[] rgb = ColorUtility.convertYUVtoRGB(yuv);
		Color aColor = ColorUtility.colorFromRGB(rgb);
		return aColor;
	}
	
	/**
	 * 実数YUV(輝度・輝度と青成分の差・輝度と赤成分の差)から色のインスタンスを生成して応答する。
	 * @param y 輝度
	 * @param u 輝度と青成分の差
	 * @param v 輝度と赤成分の差
	 * @return 色(RGB)
	 */
	public static Color colorFromYUV(double y, double u, double v)
	{
		double[] yuv = new double[] { y, u, v };
		return ColorUtility.colorFromYUV(yuv);
	}
	
	/**
	 * 整数RGB(赤成分・緑成分・青成分)から実数RGB配列(赤成分・緑成分・青成分)を計算して応答する。
	 * @param aRGB 整数RGB(赤成分・緑成分・青成分)
	 * @return 実数RGB配列(赤成分・緑成分・青成分)
	 */
	public static double[] convertINTtoRGB(int aRGB)
	{
		double r = (double)((aRGB >> 16) & 0xff) / 255.0d;
		double g = (double)((aRGB >>  8) & 0xff) / 255.0d;
		double b = (double)((aRGB      ) & 0xff) / 255.0d;
		double[] rgb = new double[] { r, g, b };
		return rgb;
	}
	
	/**
	 * 実数RGB配列(赤成分・緑成分・青成分)から整数RGB(赤成分・緑成分・青成分)を計算して応答する。
	 * @param rgb 実数RGB配列(赤成分・緑成分・青成分)
	 * @return 整数RGB(赤成分・緑成分・青成分)
	 */
	public static int convertRGBtoINT(double[] rgb)
	{
		double r = rgb[0];
		double g = rgb[1];
		double b = rgb[2];
		return ColorUtility.convertRGBtoINT(r, g, b);
	}
	
	/**
	 * 実数RGB(赤成分・緑成分・青成分)から整数RGB(赤成分・緑成分・青成分)を計算して応答する。
	 * @param r 赤成分
	 * @param g 緑成分
	 * @param b 青成分
	 * @return 整数RGB(赤成分・緑成分・青成分)
	 */
	public static int convertRGBtoINT(double r, double g, double b)
	{
		int red   = (int)Math.round(r * 255.0d);
		int green = (int)Math.round(g * 255.0d);
		int blue  = (int)Math.round(b * 255.0d);
		red   = (red   << 16) & 0xff0000;
		green = (green <<  8) &   0xff00;
		blue  = (blue       ) &     0xff;
		int aRGB = red + green + blue;
		return aRGB;
	}
	
	/**
	 * 実数RGB配列(赤成分・緑成分・青成分)から実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)を計算して応答する。
	 * @param rgb 実数RGB配列(赤成分・緑成分・青成分)
	 * @return 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 */
	public static double[] convertRGBtoYUV(double[] rgb)
	{
		double r = rgb[0];
		double g = rgb[1];
		double b = rgb[2];
		return ColorUtility.convertRGBtoYUV(r, g, b);
	}
	
	/**
	 * 実数RGB(赤成分・緑成分・青成分)から実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)を計算して応答する。
	 * @param r 赤成分
	 * @param g 緑成分
	 * @param b 青成分
	 * @return 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 */
	public static double[] convertRGBtoYUV(double r, double g, double b)
	{
		double y = ( 0.299d * r) + ( 0.587d * g) + ( 0.114d * b);
		double u = (-0.169d * r) + (-0.331d * g) + ( 0.500d * b);
		double v = ( 0.500d * r) + (-0.419d * g) + (-0.081d * b);
		return new double[] { y, u, v };
	}
	
	/**
	 * 整数RGB(赤成分・緑成分・青成分)から実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)を計算して応答する。
	 * @param aRGB 整数RGB(赤成分・緑成分・青成分)
	 * @return 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 */
	public static double[] convertRGBtoYUV(int aRGB)
	{
		double[] rgb = ColorUtility.convertINTtoRGB(aRGB); 
		return ColorUtility.convertRGBtoYUV(rgb);
	}
	
	/**
	 * 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)から実数RGB配列(赤成分・緑成分・青成分)を計算して応答する。
	 * @param yuv 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 * @return 実数RGB配列(赤成分・緑成分・青成分)
	 */
	public static double[] convertYUVtoRGB(double[] yuv)
	{
		double y = yuv[0];
		double u = yuv[1];
		double v = yuv[2];
		return ColorUtility.convertYUVtoRGB(y, u, v);
	}
	
	/**
	 * 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)から実数RGB配列(赤成分・緑成分・青成分)を計算して応答する。
	 * @param y 輝度
	 * @param u 輝度と青成分の差
	 * @param v 輝度と赤成分の差
	 * @return 実数RGB配列(赤成分・緑成分・青成分)
	 */
	public static double[] convertYUVtoRGB(double y, double u, double v)
	{
		double r = ( 1.000d * y)                 + ( 1.402d * v);
		double g = ( 1.000d * y) + (-0.344d * u) + (-0.714d * v);
		double b = ( 1.000d * y) + ( 1.772d * u)                ;
		return new double[] { r, g, b };
	}
	
	/**
	 * 実数RGB配列(赤成分・緑成分・青成分)からルミナンス(輝度)を計算して応答する。
	 * @param rgb 実数RGB配列(赤成分・緑成分・青成分)
	 * @return ルミナンス(輝度)
	 */
	public static double luminanceFromRGB(double[] rgb)
	{
		double[] yuv = ColorUtility.convertRGBtoYUV(rgb);
		return ColorUtility.luminanceFromYUV(yuv);
	}
	
	/**
	 * 整数RGB(赤成分・緑成分・青成分)からルミナンス(輝度)を計算して応答する。
	 * @param aRGB 整数RGB(赤成分・緑成分・青成分)
	 * @return ルミナンス(輝度)
	 */
	public static double luminanceFromRGB(int aRGB)
	{
		double[] yuv = ColorUtility.convertRGBtoYUV(aRGB);
		return ColorUtility.luminanceFromYUV(yuv);
	}
	
	/**
	 * 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)からルミナンス(輝度)を計算して応答する。
	 * @param yuv 実数YUV配列(輝度・輝度と青成分の差・輝度と赤成分の差)
	 * @return ルミナンス(輝度)
	 */
	public static double luminanceFromYUV(double[] yuv)
	{
		return yuv[0];
	}
}
