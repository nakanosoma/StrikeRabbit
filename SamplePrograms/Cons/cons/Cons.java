package cons;

import java.util.Iterator;

/**
 * リスト(二進木)のセル(cons)とニル(nil)。
 */
public class Cons extends Object
{
	/**
	 * ニル(nil)をシングルトンとして記憶する公的なフィールド。
	 */
	public static final Cons nil = new Cons();

	/**
	 * プリティプリントする際の字下げ文字列を保持するフィールド。
	 */
	private static String indent = "\t";

	/**
	 * car(左)を表すフィールド。
	 */
	private Object car;

	/**
	 * cdr(右)を表すフィールド。
	 */
	private Object cdr;

	/**
	 * 私的なコンストラクタ。
	 */
	private Cons()
	{
		car = nil;
		cdr = nil;
	}

	/**
	 * car(左：head)とcdr(右：tail)を受け取って、リスト(二進木：セル)を作るコンストラクタ。
	 * @param head car(左：head)
	 * @param tail cdr(右：tail)
	 */
	public Cons(Object head, Object tail)
	{
		car = head;
		cdr = tail;
	}

	/**
	 * リストの最後に指定された要素(anObject)を加える。
	 * @param anObject リストやアトム
	 * @return 自分
	 */
	public Cons add(Object anObject)
	{
		Cons aList = new Cons(anObject, Cons.nil);
		return this.nconc(aList);
	}

	/**
	 * car(左)を応答する。
	 * @return car(左)
	 */
	public Object car()
	{
		return car;
	}

	/**
	 * car(左)を設定する。
	 * @param anObject リストやアトム
	 */
	public void car(Object anObject)
	{
		car = anObject;
		return;
	}

	/**
	 * cdr(右)を応答する。
	 * @return cdr(右)
	 */
	public Object cdr()
	{
		return cdr;
	}

	/**
	 * cdr(右)を設定する。
	 * @param anObject リストやアトム
	 */
	public void cdr(Object anObject)
	{
		cdr = anObject;
		return;
	}

	/**
	 * アトムであるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return アトムであるか否か
	 */
	public static boolean isAtom(Object anObject)
	{
		return !(Cons.isNil(anObject) || Cons.isCons(anObject));
	}

	/**
	 * 文字であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return 文字であるか否か
	 */
	public static boolean isCharacter(Object anObject)
	{
		return Character.class.isInstance(anObject);
	}

	/**
	 * リスト(二進木：セル)であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return リスト(二進木：セル)であるか否か
	 */
	public static boolean isCons(Object anObject)
	{
		return (anObject != nil) && (Cons.class.isInstance(anObject));
	}

	/**
	 * 倍精度浮動小数点数であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return 倍精度浮動小数点数であるか否か
	 */
	public static boolean isDouble(Object anObject)
	{
		return Double.class.isInstance(anObject);
	}

	/**
	 * 整数であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return 整数であるか否か
	 */
	public static boolean isInteger(Object anObject)
	{
		return Integer.class.isInstance(anObject);
	}

	/**
	 * アトムであるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return アトムであるか否か
	 */
	public static boolean isList(Object anObject)
	{
		return Cons.isNil(anObject) || Cons.isCons(anObject);
	}

	/**
	 * 数であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return 数であるか否か
	 */
	public static boolean isNumber(Object anObject)
	{
		return (Cons.isInteger(anObject) || Cons.isDouble(anObject));
	}

	/**
	 * ニル(nil)であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return ニル(nil)であるか否か
	 */
	public static boolean isNil(Object anObject)
	{
		return anObject == nil;
	}

	/**
	 * リスト(二進木：セル)でないか否かを応答する。
	 * @param anObject リストやアトム
	 * @return リスト(二進木：セル)でないか否か
	 */
	public static boolean isNotCons(Object anObject)
	{
		return !(Cons.isCons(anObject));
	}

	/**
	 * ニル(nil)でないか否かを応答する。
	 * @param anObject リストやアトム
	 * @return ニル(nil)でないか否か
	 */
	public static boolean isNotNil(Object anObject)
	{
		return !(Cons.isNil(anObject));
	}

	/**
	 * 文字列であるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return 文字列であるか否か
	 */
	public static boolean isString(Object anObject)
	{
		return String.class.isInstance(anObject);
	}

	/**
	 * シンボルであるか否かを応答する。
	 * @param anObject リストやアトム
	 * @return シンボルであるか否か
	 */
	public static boolean isSymbol(Object anObject)
	{
		return Symbol.class.isInstance(anObject);
	}

	/**
	 * リストのイテレータを応答する。
	 * @return リストのイテレータ
	 */
	public Iterator<Object> iterator()
	{
		return new Loop(this);
	}

	/**
	 * リストの最後のセルを応答する。
	 * @return リストの最後のセル
	 */
	public Cons last()
	{
		Cons theList = new Cons(Cons.nil, this);
		Cons aList = this;
		while (Cons.isCons(aList)) {
			theList = (Cons)(theList.cdr());
			aList = (Cons)(aList.cdr());
		}
		return theList;
	}

	/**
	 * リストのイテレータを応答する。
	 * @return リストのイテレータ
	 */
	public Loop loop()
	{
		return new Loop(this);
	}

	/**
	 * リストの長さを応答する。
	 * @return リストの長さ
	 */
	public int length()
	{
		int count = 0;
		for (Cons aList = this; Cons.isCons(aList); aList = (Cons)(aList.cdr())) count++;
		return count;
	}

	/**
	 * リストの最後のセルのcdrを指定されたリスト(aList)にして応答する。つまり、結合するということ。
	 * @param aList 自分の最後のセルのcdrに入れるもの
	 * @return 自分
	 */
	public Cons nconc(Cons aList)
	{
		this.last().cdr(aList);
		return this;
	}

	/**
	 * リストのn番目の要素を応答する。
	 * @param n n番目
	 * @return リストやアトム
	 */
	public Object nth(int n)
	{
		if (n <= 0) { return Cons.nil; }
		int count = 1;
		for (Cons aList = this; Cons.isCons(aList); aList = (Cons)(aList.cdr()))
		{
			if (count >= n) { return aList.car(); }
			count++;
		}
		return Cons.nil;
	}

	/**
	 * 指定された文字列(aString)をパーズ(解析)してリスト(二進木)を作り上げて応答する。
	 * @param aString 文字列
	 * @return リスト(二進木)
	 */
	public static Object parse(String aString)
	{
		return Parser.parse(aString);
	}

	/**
	 * プリティプリント(pp)の私的な補助メソッド。
	 * @param buffer ストリングバッファ
	 * @param list リストやアトム
	 * @param level 字下げレベル
	 */
	private void ppList(StringBuffer buffer, Object list, int level)
	{
		if (Cons.isNotCons(list))
		{
			buffer.append(Cons.toString(list));
			return;
		}
		Object head = ((Cons)list).car();
		Object tail = ((Cons)list).cdr();
		buffer.append("(");
		this.ppList(buffer, head, level);
		if (Cons.isList(tail))
		{
			if (Cons.isCons(tail))
			{
				buffer.append("\n");
				this.ppSpace(buffer, level + 1);
				this.ppListTail(buffer, tail, level + 1);
			}
		}
		else
		{
			buffer.append(" . ");
			buffer.append(Cons.toString(tail));
		}
		buffer.append(")");
		return;
	}

	/**
	 * プリティプリント(pp)の私的な補助メソッド。
	 * @param buffer ストリングバッファ
	 * @param list リストやアトム
	 * @param level 字下げレベル
	 */
	private void ppListTail(StringBuffer buffer, Object list, int level)
	{
		if (Cons.isNotCons(list)) { return; }
		Object head = ((Cons)list).car();
		Object tail = ((Cons)list).cdr();
		if (Cons.isList(tail))
		{
			if (Cons.isCons(tail))
			{
				this.ppList(buffer, head, level);
				buffer.append("\n");
				this.ppSpace(buffer, level);
				this.ppListTail(buffer, tail, level);
			}
			else
			{
				this.ppList(buffer, head, level);
			}
		}
		else
		{
			this.ppList(buffer, head, level);
			buffer.append(" . ");
			buffer.append(Cons.toString(tail));
		}
		return;
	}

	/**
	 * プリティプリント(pp)の私的な補助メソッド。
	 * @param buffer ストリングバッファ
	 * @param level 字下げレベル
	 */
	private void ppSpace(StringBuffer buffer, int level)
	{
		for (int count = 1; count <= level; count++) { buffer.append(indent); }
		return;
	}

	/**
	 * 自分をリスト表現のプリティプリント文字列に変換して応答する。
	 * @return プリティプリント文字列
	 */
	public String ppString()
	{
		return this.ppString("\t");
	}

	/**
	 * 字下げ文字列を指定して、自分をリスト表現のプリティプリント文字列に変換して応答する。
	 * @param aString 字下げ文字列
	 * @return プリティプリント文字列
	 */
	public String ppString(String aString)
	{
		synchronized (indent)
		{
			indent = aString;
			StringBuffer aBuffer = new StringBuffer();
			this.ppList(aBuffer, this, 0);
			return aBuffer.toString();
		}
	}

	/**
	 * 自分をリスト表現の文字列に変換して応答する。
	 */
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		if (this == nil)
		{
			aBuffer.append(Cons.toString(nil));
		}
		else
		{
			aBuffer.append("(");
			aBuffer.append(Cons.toString(car));
			if (cdr == nil)
			{
				aBuffer.append(")");
			}
			else if (Cons.class.isInstance(cdr) == false)
			{
				aBuffer.append(" . ");
				aBuffer.append(Cons.toString(cdr));
				aBuffer.append(")");
			}
			else
			{
				Cons cons = (Cons)cdr;
				while (true)
				{
					Object head = cons.car();
					Object tail = cons.cdr();
					aBuffer.append(" ");
					aBuffer.append(Cons.toString(head));
					if (tail == nil)
					{
						aBuffer.append(")");
						break;
					}
					else if (Cons.class.isInstance(tail) == false)
					{
						aBuffer.append(" . ");
						aBuffer.append(Cons.toString(tail));
						aBuffer.append(")");
						break;
					}
					cons = (Cons)tail;
				}
			}
		}
		return aBuffer.toString();
	}

	/**
	 * 指定されたオブジェクトをリスト表現の文字列に変換して応答する。
	 * @param anObject あるオブジェクト
	 * @return リスト表現の文字列
	 */
	public static String toString(Object anObject)
	{
		StringBuffer aBuffer = new StringBuffer();
		if (anObject == nil)
		{
			aBuffer.append("nil");
		}
		else if (java.lang.String.class.isInstance(anObject))
		{
			aBuffer.append("\"");
			aBuffer.append(anObject.toString());
			aBuffer.append("\"");
		}
		else if (java.lang.Character.class.isInstance(anObject))
		{
			aBuffer.append("'");
			aBuffer.append(anObject.toString());
			aBuffer.append("'");
		}
		else
		{
			aBuffer.append(anObject.toString());
		}
		return aBuffer.toString();
	}
}
