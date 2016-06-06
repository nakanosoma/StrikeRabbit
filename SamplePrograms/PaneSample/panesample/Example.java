package panesample;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pane.PaneController;
import pane.PaneModel;
import pane.PaneView;

/**
 * 窓MVCの例題プログラム。
 * オブザーバ・デザインパターン(MVC: Model-View-Controller)を用いた典型的(模範的)なプログラム。
 */
public class Example extends Object
{
	/**
	 * 窓MVCの例題プログラムを実行する。
	 * @param arguments 引数の文字列の配列
	 */
	public static void main(String[] arguments)
	{
		// 窓MVCの例題プログラム(1)を実行する。（フーリエ変換のヒント）
		Example.paneSample1();

		// 窓MVCの例題プログラム(2)を実行する。（ウェーブレット変換のヒント）
		Example.paneSample2();

		return;
	}

	/**
	 * 窓MVCの例題プログラム(1)を実行する。（フーリエ変換のヒント）
	 */
	public static void paneSample1()
	{
		// 2行2列のレイアウトを持つパネルを生成する。
		GridLayout aLayout = new GridLayout(2, 2);
		JPanel aPanel = new JPanel(aLayout);
		
		// サンプル波(いくつかの正弦波と余弦波の合成波)の窓MVCを作ってパネルに入れる。
		double[] sourceData = SampleData.dataSampleWave();
		BufferedImage imageSourceData = SampleData.generateImageForData(sourceData);
		PaneModel aModel = new PaneModel(imageSourceData);
		PaneView aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		// のこぎり波(波形の見た目が鋸の歯のような信号)の窓MVCを作ってパネルに入れる。
		sourceData = SampleData.dataSawtoothWave();
		imageSourceData = SampleData.generateImageForData(sourceData);
		aModel = new PaneModel(imageSourceData);
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		// 矩形波(二つのレベルの間を規則的かつ瞬間的に変化する信号)の窓MVCを作ってパネルに入れる。
		sourceData = SampleData.dataSquareWave();
		imageSourceData = SampleData.generateImageForData(sourceData);
		aModel = new PaneModel(imageSourceData);
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		// 三角波(波形の見た目が三角形のような信号)の窓MVCを作ってパネルに入れる。
		sourceData = SampleData.dataTriangleWave();
		imageSourceData = SampleData.generateImageForData(sourceData);
		aModel = new PaneModel(imageSourceData);
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		// 当該のパネルを入れたウィンドウを作って開く。
		JFrame aWindow = new JFrame("Pane Sample (1)");
		aWindow.getContentPane().add(aPanel);
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setMinimumSize(new Dimension(512, 150 + titleBarHeight));
		aWindow.setResizable(true);
		aWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aWindow.setSize(1024, 300 + titleBarHeight);
		aWindow.setLocation(100, 100);
		aWindow.setVisible(true);
		aWindow.toFront();

		return;
	}

	/**
	 * 窓MVCの例題プログラム(2)を実行する。（ウェーブレット変換のヒント）
	 */
	public static void paneSample2()
	{
		// 2行1列のレイアウトを持つパネルを生成する。
		GridLayout aLayout = new GridLayout(2, 1);
		JPanel aPanel = new JPanel(aLayout);
		
		// 地球の画像で窓MVCを作ってパネルに入れる。
		String aString = "SampleImages/imageEarth512x256.jpg";
		PaneModel aModel = new PaneModel(aString);
		PaneView aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		// Smalltalk気球の画像で窓MVCを作ってパネルに入れる。
		aString = "SampleImages/imageSmalltalkBalloon256x256.jpg";
		aModel = new PaneModel(aString);
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
				
		// 当該のパネルを入れたウィンドウを作って開く。
		JFrame aWindow = new JFrame("Pane Sample (2)");
		aWindow.getContentPane().add(aPanel);
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setMinimumSize(new Dimension(256, 256 + titleBarHeight));
		aWindow.setResizable(true);
		aWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aWindow.setSize(512, 512 + titleBarHeight);
		aWindow.setLocation(150, 150);
		aWindow.setVisible(true);
		aWindow.toFront();

		return;
	}
}