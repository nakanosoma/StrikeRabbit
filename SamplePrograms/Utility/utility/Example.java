package utility;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JFrame;
import mvc.Controller;
import mvc.Model;
import mvc.View;

/**
 * ユーティリティの例題プログラム。
 * オブザーバ・デザインパターン(MVC: Model-View-Controller)を用いた典型的(模範的)なプログラム。
 */
public class Example extends Object
{
	/**
	 * 画像をファイルに書き出す際の番号。
	 */
	private static int fileNo = 0;
	
	/**
	 * ウィンドウの表示位置。
	 */
	private static Point displayPoint = new Point(30, 50);
	
	/**
	 * ウィンドウをずらして表示してゆく際の支距。
	 */
	private static Point offsetPoint = new Point(25, 25);
	
	/**
	 * ユーティリティの例題プログラム群を実行する。
	 * @param arguments 引数の文字列の配列
	 */
	public static void main(String[] arguments)
	{
		String aString = "SampleTexts" + File.separator + "PrimeMinisters.csv";
		ArrayList<ArrayList<String>> aCollection = StringUtility.readRowsFromFile(aString);
		
		for (ArrayList<String> aRow : aCollection)
		{
			for (int index = 0; index < aRow.size(); index++)
			{
				if (index > 0) { System.out.print(","); }
				aString = aRow.get(index);
				aString = StringUtility.csvString(aString);
				System.out.print(aString);
			}
			System.out.println();
		}

		aString = "SampleTexts" + File.separator + "PrimeMinisters2.csv";
		StringUtility.writeRows(aCollection, aString);
		
		BufferedImage theImage = ImageUtility.readImage("SampleImages" + File.separator + "SAI.jpg");
		Example.open(theImage, "SAI (Color)");

		BufferedImage anImage = ImageUtility.grayscaleImage(theImage);
		Example.open(anImage, "SAI (Gray Scale)");

		double factor = 0.75d;
		int width = (int)((double)(theImage.getWidth()) * factor);
		int height = (int)((double)(theImage.getHeight()) * factor);
		anImage = ImageUtility.adjustImage(theImage, width, height);
		Example.open(anImage, "SAI (Shrinked)");
		
		factor = 1.25d;
		width = (int)((double)(theImage.getWidth()) * factor);
		height = (int)((double)(theImage.getHeight()) * factor);
		anImage = ImageUtility.adjustImage(theImage, width, height);
		Example.open(anImage, "SAI (Magnified)");
		
		aString = "http://aokilab.kyoto-su.ac.jp/documents/BlackBook/images/BlackBookFrontPage335x432.jpg";
		anImage = ImageUtility.readImageFromURL(aString);
		Example.open(anImage, "Black Book");

		return;
	}

	/**
	 * 描画画像(anImage)とラベル文字列(labelString)を指定してMVCを作り、表示位置(displayPoint)にウィンドウを開く。
	 * @param anImage 描画画像
	 * @param labelString ラベル文字列
	 */
	private static void open(BufferedImage anImage, String labelString)
	{
		Model aModel = new Model();
		aModel.picture(anImage);
		View aView = new View(aModel, new Controller());

		JFrame aWindow = new JFrame(labelString);
		aWindow.getContentPane().add(aView);
		Dimension aDimension = new Dimension(anImage.getWidth(), anImage.getHeight());
		aWindow.setMinimumSize(aDimension);
		aWindow.setMaximumSize(aDimension);
		aWindow.setResizable(false);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(aDimension.width, aDimension.height + titleBarHeight);
		aWindow.setLocation(displayPoint.x, displayPoint.y);
		aWindow.setVisible(true);

		Example.write(anImage);
		displayPoint = new Point(displayPoint.x + offsetPoint.x, displayPoint.y + offsetPoint.y);
		return;
	}

	/**
	 * 描画画像(anImage)をResultImagesというディレクトリの中に連番を付けて書き込む。
	 * @param anImage 描画画像
	 */
	private static void write(BufferedImage anImage)
	{
		File aDirectory = new File("ResultImages");
		if (aDirectory.exists() == false)
		{
			try { aDirectory.mkdir(); }
			catch (SecurityException anException) { anException.printStackTrace(); }
		}
		String aString = Integer.toString(fileNo++);
		while (aString.length() < 2) { aString = "0" + aString; }
		ImageUtility.writeImage(anImage, aDirectory.getName() + File.separator + "Utility" + aString + ".jpg");
		return;
	}
}


