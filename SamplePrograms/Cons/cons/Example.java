package cons;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * リスト(二進木)の例題(テスト)プログラム。
 */
public class Example extends Object
{
	/**
	 * リテラルやリスト(二進木)に対して数々のテストを施すプログラム。
	 * そして、リスト(二進木)を入力して出力するだけの例題プログラム。
	 * @param arguments コマンドの引数列（文字列の配列）
	 */
	public static void main(String[] arguments)
	{
		Object anObject;
		String aString;
		Cons aList;

		// nil(ニル)のテスト。
		anObject = Cons.parse("nil");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// 整数のテスト。
		anObject = Cons.parse("123");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// 実数のテスト。
		anObject = Cons.parse("4.5678e-9");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// 文字のテスト。
		anObject = Cons.parse("'x'");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// 文字列のテスト。
		anObject = Cons.parse("\"xyz\"");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// シンボルのテスト。
		anObject = Cons.parse("abc");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// cons(コンス：セル：リスト：二進木)のテスト。
		anObject = Cons.parse("(abc . def)");
		aString = Cons.toString(anObject);
		System.out.println(aString);
		System.out.println("isAtom(" + aString + ") ==> " + Cons.isAtom(anObject));
		System.out.println("isCharacter(" + aString + ") ==> " + Cons.isCharacter(anObject));
		System.out.println("isCons(" + aString + ") ==> " + Cons.isCons(anObject));
		System.out.println("isDouble(" + aString + ") ==> " + Cons.isDouble(anObject));
		System.out.println("isInteger(" + aString + ") ==> " + Cons.isInteger(anObject));
		System.out.println("isList(" + aString + ") ==> " + Cons.isList(anObject));
		System.out.println("isNumber(" + aString + ") ==> " + Cons.isNumber(anObject));
		System.out.println("isNil(" + aString + ") ==> " + Cons.isNil(anObject));
		System.out.println("isNotCons(" + aString + ") ==> " + Cons.isNotCons(anObject));
		System.out.println("isNotNil(" + aString + ") ==> " + Cons.isNotNil(anObject));
		System.out.println("isString(" + aString + ") ==> " + Cons.isString(anObject));
		System.out.println("isSymbol(" + aString + ") ==> " + Cons.isSymbol(anObject));

		// 自力で複雑なリスト(二進木)を作って書き出してみる。
		// 以下のようなリスト(二進木)が出力されるはず。
		// (((nil . symbol) aoki . atsushi) 'z' ("abc" . 3.141592653589793) (-123 . 4.5678E-9))
		aList = new Cons(-123, 4.5678e-9);
		aList = new Cons(aList, Cons.nil);
		aList = new Cons(new Cons("abc", Math.PI), aList);
		aList = new Cons('z', aList);
		aList = new Cons(new Cons(new Cons(Cons.nil, Symbol.of("symbol")), new Cons(Symbol.of("aoki"), Symbol.of("atsushi"))), aList);
		System.out.println("this ==> " + aList);

		// 今度はリスト(二進木)を文字列に変換して、その文字列をパーズ（解析）してリスト(二進木)を作ってみる。
		// 以下のように、さっきとまったく同じリスト(二進木)が出力されるはず。
		// (((nil . symbol) aoki . atsushi) 'z' ("abc" . 3.141592653589793) (-123 . 4.5678E-9))
		aString = aList.toString();
		aList = (Cons)Cons.parse(aString);
		System.out.println("list ==> " + aList);

		// プリミティブ関数(原始関数)のテスト。
		System.out.println("list.length() ==> " + aList.length());
		System.out.println("list.nth(4) ==> " + aList.nth(4));
		System.out.println("list.last() ==> " + aList.last());
		System.out.println("list.last().add(new Cons(100, 200)) ==> " + aList.last().add(new Cons(100, 200)));
		System.out.println("list.ppString() ==> ");
		System.out.println(aList.ppString());

		// イテレータのテスト。
		System.out.println("list ==> " + aList);
		int index = 1;
		Iterator anIterator = aList.iterator();
		while (anIterator.hasNext())
		{
			anObject = anIterator.next();
			aString = "th of list ==> ";
			if (index == 1) { aString = "st of list ==> "; }
			if (index == 2) { aString = "nd of list ==> "; }
			if (index == 3) { aString = "rd of list ==> "; }
			System.out.println("iterator: " + index + aString + Cons.toString(anObject));
			index++;
		}

		// for-each文のテスト。
		System.out.println("list ==> " + aList);
		index = 1;
		for (Object each : aList.loop()) 
		{
			aString = "th of list  ==> ";
			if (index == 1) { aString = "st of list ==> "; }
			if (index == 2) { aString = "nd of list ==> "; }
			if (index == 3) { aString = "rd of list ==> "; }
			System.out.println("for-each: " + index + aString + Cons.toString(each));
			index++;
		}

		// コメントのテスト。
		aList = (Cons)Cons.parse("# comment\n(0 1 2 3 4 5 6 7 8 9)");
		System.out.println("this ==> " + aList);
		aList = (Cons)Cons.parse("% COMMENT\r\n(a b c d e f g h i j k l m n o p q r s t u v w x y z)");
		System.out.println("this ==> " + aList);
		aList = (Cons)Cons.parse("# SpiroDesign at 2011/01/15 16:55:12\n\n(spiro.SpiroModel (spur spiro.SpurGear (center (x . 0.0) (y . 0.0)) (radius . 200.0)) (pinion spiro.PinionGear (center (x . 150.0) (y . 0.0)) (radius . 50.0) (pen (x . 175.0) (y . 0.0)) (color (r . 255) (g . 200) (b . 0)) (nib . 2) (degrees . 0.0)) (inscribe . true) (tick . 100) (locus spiro.DesignLocus (spur spiro.SpurGear (center (x . 0.0) (y . 0.0)) (radius . 200.0)) (pinions)) (loci))");
		System.out.println("this ==> " + aList);
		System.out.println("this.ppString() ==> ");
		System.out.println(aList.ppString());

		// ファイルからリスト(二進木)表現を読み込んで書き出してみる。
		JFileChooser fileChooser = new JFileChooser();
		FileFilter fileFilter = new FileNameExtensionFilter("リスト表現ファイル (*.txt)", "txt");
		fileChooser.addChoosableFileFilter(fileFilter);
		int answer = fileChooser.showOpenDialog(null);
		if (answer != JFileChooser.APPROVE_OPTION) { return; }
		File aFile = fileChooser.getSelectedFile();
		StringBuffer aBuffer = new StringBuffer();
		aBuffer.append("(");
		try
		{
			InputStreamReader aStream = new InputStreamReader(new FileInputStream(aFile), "UTF-8");
			int aCharacter;
			while ((aCharacter = aStream.read()) != -1)
			{
				aBuffer.append((char)aCharacter);
			}
			aStream.close();
		}
		catch (IOException anException)
		{
			throw new RuntimeException(anException.toString());
		}
		aBuffer.append(")");
		aString = aBuffer.toString();
		System.out.println("file(contents) ==> ");
		System.out.println(aString);
		// ずらずらとリスト(二進木)が出力されるはず。
		aList = (Cons)Cons.parse(aString);
		System.out.println("this ==> " + aList);
		// ずらずらとリスト(二進木)が出力されるはず。
		System.out.println("this.ppString() ==> ");
		System.out.println(aList.ppString());
		// プリティプリントされるはず。

		return;
	}
}
