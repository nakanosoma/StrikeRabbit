package panesample;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 窓MVCの例題プログラムのためのサンプルデータを提供する。
 */
public class SampleData extends Object
{
	/**
	 * 離散フーリエ1次元変換のための元データ(いくつかの正弦波と余弦波の合成波)。
	 * @return いくつかの正弦波と余弦波の合成波データ
	 */
	public static double[] dataSampleWave()
	{
		double pi = Math.PI;
		int sourceSize = 1024;
		double[] sourceData = new double[sourceSize];
		for (int i = 0; i < sourceSize; i++)
		{
			double cos1 = 6.0d * Math.cos(12.0d * 2.0d * pi * (double)i / (double)sourceSize);
			double sin1 = 4.0d * Math.sin( 5.0d * 2.0d * pi * (double)i / (double)sourceSize);
			double cos2 = 3.0d * Math.cos(24.0d * 2.0d * pi * (double)i / (double)sourceSize);
			double sin2 = 2.0d * Math.sin(10.0d * 2.0d * pi * (double)i / (double)sourceSize);
			double value = cos1 + sin1 + cos2 + sin2;
			sourceData[i] = value;
		}
		return sourceData;
	}

	/**
	 * 離散フーリエ1次元変換のための元データ(のこぎり波:波形の見た目が鋸の歯のような信号)。
	 * @return のこぎり波:波形の見た目が鋸の歯のような信号データ
	 */
	public static double[] dataSawtoothWave()
	{
		int sourceSize = 1024;
		double[] sourceData = new double[sourceSize];
		for (int i = 0; i < sourceSize; i++)
		{
			double value = 12.0d * ((double)(i % 50) / 25.0d - 1.0d);
			sourceData[i] = value;
		}
		return sourceData;
	}

	/**
	 * 離散フーリエ1次元変換のための元データ(矩形波:二つのレベルの間を規則的かつ瞬間的に変化する信号)。
	 * @return 矩形波:二つのレベルの間を規則的かつ瞬間的に変化する信号データ
	 */
	public static double[] dataSquareWave()
	{
		double pi = Math.PI;
		int sourceSize = 1024;
		double[] sourceData = new double[sourceSize];
		for (int i = 0; i < sourceSize; i++)
		{
			double cos = Math.cos(10.0d * 2.0d * pi * ((double)i / (double)sourceSize));
			double value = 12.0d * (cos >= 0.0d ? 1.0d : -1.0d);
			sourceData[i] = value;
		}
		return sourceData;
	}

	/**
	 * 離散フーリエ1次元変換のための元データ(三角波:波形の見た目が三角形のような信号)。
	 * @return 三角波:波形の見た目が三角形のような信号データ
	 */
	public static double[] dataTriangleWave()
	{
		boolean flag = false;
		int sourceSize = 1024;
		double[] sourceData = new double[sourceSize];
		for (int i = 0; i < sourceSize; i++)
		{
			if (i % 50 == 0) { flag = flag ? false : true ; }
			double value = 12.0d * ((double)(i % 50) / 25.0d - 1.0d);
			if (flag) { value = 0.0d - value;}
			sourceData[i] = value;
		}
		return sourceData;
	}

	/**
	 * 離散フーリエ1次元変換のデータを画像に変換して応答する。
	 * @param valueCollection 離散フーリエ1次元変換のデータ
	 * @return 画像
	 */
	public static BufferedImage generateImageForData(double[] valueCollection)
	{
		double[] normalizedValues = SampleData.normalize(valueCollection);
		int size = normalizedValues.length;
		int width = 1024;
		int height = 300;
		int halfHeight = height / 2;
		BufferedImage anImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D aGraphics = anImage.createGraphics();
		aGraphics.setColor(Color.white);
		aGraphics.fillRect(0, 0, width, height);
		aGraphics.setColor(Color.gray);
		aGraphics.setStroke(new BasicStroke(2));
		aGraphics.drawLine(0, halfHeight, width, halfHeight);
		int[] xValues = new int[size];
		int[] yValues = new int[size];
		for (int index = 0; index < size; index++)
		{
			double value = normalizedValues[index];
			int x = (int)Math.round((double)index * ((double)width / (double)size));
			int y = (int)Math.round((0.0d - (value * 0.95d)) * (double)halfHeight) + halfHeight;
			xValues[index] = x;
			yValues[index] = y;
		}
		aGraphics.setColor(Color.gray);
		aGraphics.setStroke(new BasicStroke(3));
		aGraphics.drawPolyline(xValues, yValues, size);
		aGraphics.setColor(Color.black);
		for (int index = 0; index < size; index++)
		{
			int x = xValues[index];
			int y = yValues[index];
			Rectangle box = new Rectangle(x, y, 1, 1);
			box.grow(1, 1);
			aGraphics.fill(box);
		}
		return anImage;
	}

	/**
	 * 元データや逆変換データを正規化して応答する。
	 * @param valueCollection 元データや逆変換データ
	 * @return 元データや逆変換データを正規化したデータ
	 */
	public static double[] normalize(double[] valueCollection)
	{
		double maximumValue = 0.0d;
		int size = valueCollection.length;
		for (int index = 0; index < size; index++)
		{
			maximumValue = Math.max(Math.abs(valueCollection[index]), maximumValue);
		}
		if (maximumValue < 1.0E-5d) { maximumValue = 1.0d; }
		double[] normalizedValues = new double[size];
		for (int index = 0; index < size; index++)
		{
			normalizedValues[index] = valueCollection[index] / maximumValue;
		}
		return normalizedValues;
	}
}