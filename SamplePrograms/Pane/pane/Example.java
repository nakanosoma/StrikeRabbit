package pane;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 窓（ペイン）の例題プログラム。
 * オブザーバ・デザインパターン（MVC: Model-View-Controller）を用いた典型的（模範的）なプログラム。
 */
public class Example extends Object
{
	/**
	 * 窓（ペイン）の例題プログラムを実行する。
	 * @param arguments 引数の文字列の配列
	 */
	public static void main(String[] arguments)
	{
		GridLayout aLayout = new GridLayout(2, 3);
		JPanel aPanel = new JPanel(aLayout);

		PaneModel aModel = new PaneModel("SampleImages/BernhardRiemann.jpg");
		PaneView aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);

		aModel = new PaneModel("SampleImages/GeorgeBoole.jpg");
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		aModel = new PaneModel("SampleImages/JosephFourier.jpg");
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		aModel = new PaneModel("SampleImages/LeonardoFibonacci.jpg");
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		aModel = new PaneModel("SampleImages/LeonhardEuler.jpg");
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		aModel = new PaneModel("SampleImages/RichardDedekind.jpg");
		aView = new PaneView(aModel, new PaneController());
		aPanel.add(aView);
		
		JFrame aWindow = new JFrame("Pane");
		aWindow.getContentPane().add(aPanel);
		aWindow.setMinimumSize(new Dimension(100, 100));
		aWindow.setResizable(true);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(400, 300 + titleBarHeight);
		aWindow.setLocation(230, 250);
		aWindow.setVisible(true);
		aWindow.toFront();
		return;
	}
}
