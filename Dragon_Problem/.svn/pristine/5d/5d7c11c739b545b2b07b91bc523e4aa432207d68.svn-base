package dragon;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.ArrayList;
import java.util.Iterator;

import mvc4jogl.OpenGLCompound;
import mvc4jogl.OpenGLModel;
import mvc4jogl.OpenGLObject;
import mvc4jogl.OpenGLProjection;
import mvc4jogl.OpenGLTriangle;

import utility.StringUtility;

/**
 * <a href="http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/Java/Dragon/">ドラゴン</a>（描画オブジェクト）である。<br>
 */
public class Dragon extends OpenGLCompound
{
	/**
	 * ドラゴンのコンストラクタである。<br>
	 * <a href="http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/Dragon/dragon.txt">dragon.txt</a>からデータを読み取って立体（描画オブジェクト）を生成する。
	 */
	public Dragon()
	{
		super();

		String urlString = "http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/Dragon/dragon.txt";
		ArrayList<String> aCollection = StringUtility.readTextFromURL(urlString);
		int numberOfVertexes = 0;
		int numberOfTriangles = 0;
		Iterator anIterator = aCollection.iterator();
		while (anIterator.hasNext())
		{
			String aString = (String)anIterator.next();
			ArrayList<String> aList = StringUtility.splitString(aString, " \t\n\r");
			if (aList.size() == 0) { continue; }
			System.out.println(aList);
			/*****
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 * このコメントになっている部分（約40行）をプログラミングするとドラゴンが出現します。
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			 *
			*****/
		}

		return;
	}

	/**
	 * ドラゴンを生成してウィンドウを開く。<br>
	 * @param arguments コマンドの引数列（文字列の配列）<br>
	 */
	public static void main(String[] arguments)
	{
		Dragon.open(150, 150);
	}
	
	/**
	 * ドラゴンを生成してウィンドウを開く。<br>
	 * @param x ウィンドウを開く場所のx座標
	 * @param y ウィンドウを開く場所のy座標
	 */
	public static void open(int x, int y)
	{
		// 描画オブジェクトを生成する。
		OpenGLObject aDragon = new Dragon();

		// モデルに描画オブジェクトを指定してウィンドウを開く。
		OpenGLModel aModel = new OpenGLModel(aDragon);
		aModel.axesScale(1.0d);
		aModel.windowTitle("Dragon");
		OpenGLProjection aProjection = aModel.projection();
		aProjection.eyePoint(-5.5852450791872d, 3.07847342734d, 15.794105252496d);
		aProjection.sightPoint(0.27455347776413d, 0.20096999406815d, -0.11261999607086d);
		aProjection.upVector(0.1018574904194d, 0.98480906061847d, -0.14062775604137d);
		aProjection.fovy(12.642721790235d);
		aModel.open(x, y);

		return;
	}
}