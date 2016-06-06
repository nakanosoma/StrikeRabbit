package pane;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * 窓（ペイン）のコントローラ。
 */
public class PaneController extends mvc.Controller
{
	/**
	 * 上位コンストラクタを継承するただけのコンストラクタ。
	 */
	public PaneController()
	{
		super();
	}

	/**
	 * 自分のビューを応答する。
	 * @return このコントローラのビュー
	 */
	public PaneView getView()
	{
		return (PaneView)(this.view);
	}
	
	/**
	 * マウスクリックした位置をピクチャ座標にしてモデルに通知する。
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		PaneView aView = this.getView();
		aPoint = aView.convertViewPointToPicturePoint(aPoint);
		if (aPoint == null) { return; }
		aView.getModel().mouseClicked(aPoint, aMouseEvent);
		return;
	}

	/**
	 * マウスドラッグした位置をピクチャ座標にしてモデルに通知する。
	 */
	public void mouseDragged(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		PaneView aView = this.getView();
		aPoint = aView.convertViewPointToPicturePoint(aPoint);
		if (aPoint == null) { return; }
		aView.getModel().mouseDragged(aPoint, aMouseEvent);
		return;
	}
}
