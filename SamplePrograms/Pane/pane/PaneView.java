package pane;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.ImageUtility;

/**
 * 窓（ペイン）のビュー。
 */
public class PaneView extends mvc.View
{
	/**
	 * 内容物の表示位置(原点)を保持するフィールド。
	 */
	private Point2D.Double originPoint;

	/**
	 * 内容物の拡大縮小率を保持するフィールド。
	 */
	private Point2D.Double scaleFactor;
	
	/**
	 * 指定されたモデルとコントローラと自分（ビュー）とでMVCを構築するコンストラクタ。
	 * @param aModel このビューのモデル
	 * @param aController このビューのコントローラ
	 */
	public PaneView(PaneModel aModel, PaneController aController)
	{
		super(aModel, aController);
		this.intialize();
	}

	/**
	 * 初期化を行う。
	 */
	public void intialize()
	{
		originPoint = new Point2D.Double(0.0d, 0.0d);
		scaleFactor = new Point2D.Double(1.0d, 1.0d);
	}
	
	/**
	 * 自分のモデルを応答する。
	 * @return このビューのモデル
	 */
	public PaneModel getModel()
	{
		return (PaneModel)(this.model);
	}
	
	/**
	 * ビュー座標をモデル座標に変換して応答する。
	 * @param aViewPoint ビュー座標
	 * @return モデル座標
	 */
	public Point convertViewPointToModelPoint(Point aViewPoint)
	{
		Point scrollAmount = this.scrollAmount();
		Point aPoint = new Point(aViewPoint.x + scrollAmount.x, aViewPoint.y + scrollAmount.y);
		return aPoint;
	}
	
	/**
	 * ビュー座標をピクチャ座標に変換して応答する。
	 * @param aViewPoint ビュー座標
	 * @return ピクチャ座標
	 */
	public Point convertViewPointToPicturePoint(Point aViewPoint)
	{
		Point aModelPoint = this.convertViewPointToModelPoint(aViewPoint);
		Point aPoint = this.convertModelPointToPicturePoint(aModelPoint);
		return aPoint;
	}
	
	/**
	 * モデル座標をピクチャ座標に変換して応答する。
	 * @param aModelPoint モデル座標
	 * @return ピクチャ座標
	 */
	public Point convertModelPointToPicturePoint(Point aModelPoint)
	{
		PaneModel aModel = this.getModel();
		BufferedImage anImage = aModel.picture();
		if (anImage == null) { return null; }

		double xValue = ((double)(aModelPoint.x) - originPoint.x) / scaleFactor.x;
		double yValue = ((double)(aModelPoint.y) - originPoint.y) / scaleFactor.y;
		int x = (int)xValue;
		int y = (int)yValue;

		if (x < 0) { return null; }
		if (y < 0) { return null; }
		int width = (int)(anImage.getWidth());
		int height = (int)(anImage.getHeight());
		if (x > width) { return null; }
		if (y > height) { return null; }

		Point aPoint = new Point(x, y);
		return aPoint;
	}
	
	/**
	 * 描画を行う。
	 */
	public void paintComponent(Graphics aGraphics)
	{
		int width;
		int height;
		BufferedImage picture;
		
		width = this.getWidth();
		height = this.getHeight();
		aGraphics.setColor(Color.white);
		aGraphics.fillRect(0, 0, width, height);

		if (model == null) { return; }
		picture = model.picture();
		if (picture == null) { return; }

		int w = picture.getWidth();
		int h = picture.getHeight();
		double x = (double)width / (double)w;
		double y = (double)height / (double)h;
		if (x > y) { x = y; } else { y = x; }
		scaleFactor = new Point2D.Double(x, y);

		w = (int)((double)w * x);
		h = (int)((double)h * y);
		picture = ImageUtility.adjustImage(picture, w, h);
		x = (double)(width - w) / 2.0d;
		y = (double)(height - h) / 2.0d;
		originPoint = new Point2D.Double(x, y);

		aGraphics.drawImage(picture, (int)x, (int)y, null);
		return;
	}
	 
	/**
	 * スクロールを抑制する。スコープMVCでは必要ないため、スクロール量を常にゼロに保つ。
	 * @return スクロール量を常にゼロ座標を応答する。
	 */
	public Point scrollAmount()
	{
		return (new Point(0, 0));
	}
	
	/**
	 * 相対スクロールをしないようにする。スコープMVCでは必要ないため、何もしないことにする。
	 * @param aPoint 無視
	 */
	public void scrollBy(Point aPoint)
	{
		return;
	}
	
	/**
	 * 絶対スクロールをしないようにする。スコープMVCでは必要ないため、何もしないことにする。
	 * @param aPoint 無視
	 */
	public void scrollTo(Point aPoint)
	{
		return;
	}	
}
