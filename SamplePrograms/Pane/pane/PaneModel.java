package pane;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.BufferedImage;
import utility.ImageUtility;

/**
 * 窓（ペイン）のモデル。
 */
public class PaneModel extends mvc.Model
{
	/**
	 * ペインモデルを作るコンストラクタ。
	 */
	public PaneModel()
	{
		super();
	}
	
	/**
	 * ファイル名(aString)からペインモデルを作るコンストラクタ。
	 * @param aString 画像のファイル名
	 */
	public PaneModel(String aString)
	{
		super();
		BufferedImage anImage = ImageUtility.readImage(aString);
		this.picture(anImage);
	}

	/**
	 * 画像(anImage)からペインモデルを作るコンストラクタ。
	 * @param anImage 画像
	 */
	public PaneModel(BufferedImage anImage)
	{
		super();
		this.picture(anImage);
	}
	
	/**
	 * マウスクリックした位置をピクチャ座標として受け取り、それをただ出力する。
	 * @param aPoint マウスクリックした位置のピクチャ座標
	 * @param aMouseEvent マウスイベント
	 */
	public void mouseClicked(Point aPoint, MouseEvent aMouseEvent)
	{
		System.out.println(aPoint);
		return;
	}
	
	/**
	 * マウスドラッグした位置をピクチャ座標として受け取り、それをただ出力する。
	 * @param aPoint マウスドラッグした位置のピクチャ座標
	 * @param aMouseEvent マウスイベント
	 */
	public void mouseDragged(Point aPoint, MouseEvent aMouseEvent)
	{
		System.out.println(aPoint);
		return;
	}
}
