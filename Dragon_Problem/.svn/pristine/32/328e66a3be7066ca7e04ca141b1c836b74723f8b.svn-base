package dragon;

/**
 * ドラゴンをOpenGLでレンダリング（描画）するプログラムである。<br>
 * JOGL（Java bindings for OpenGL）の例題プログラムである。<br>
 * OpenGLを使ったJavaによる三次元グラフィックスの初歩的な練習になる。<br>
 * オブザーバ・デザインパターン（MVC: Model-View-Controller）を用いた典型的（模範的）なプログラムである。<br>
 */
public class Example extends Object
{
	/**
	 * OpenGLオブジェクトからOpenGLモデルを生成してウィンドウを開く。<br>
	 * @param arguments コマンドの引数列（文字列の配列）<br>
	 */
	public static void main(String[] arguments)
	{
		int x = 100;
		int y = 100;

		// ドラゴンを生成してウィンドウを開く。
		Dragon.open(x += 25, y += 25);

		return;
	}
}

/**
 * jogampのサイト「http://jogamp.org/deployment/」からJOGLの最新のバージョンをダウンロードできる。
 * 2014年8月8日現在、v2.2.0が最新である。http://jogamp.org/deployment/v2.2.0/archive/
 * 上記のサイトから「jogamp-all-platforms.7z 07-Aug-2014 02:44 29M」をダウンロード。
 * 参考となるサイトは「http://schabby.de/jogl-example-hello-world/」で、
 * 最新版に対応したセットアップ法が丁寧に説明されている。
 * それを要約すると、以下のjarファイル群にclasspathを通せばいいことがわかる。
 * １）gluegen-rt-natives-macosx-universal.jar
 * ２）gluegen-rt.jar
 * ３）jogl-all-natives-macosx-universal.jar
 * ４）jogl-all.jar
 * ネイティブなjarを選ぶ際には、使用環境のOSのものにすること。
 */
