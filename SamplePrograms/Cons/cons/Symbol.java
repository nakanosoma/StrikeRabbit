package cons;

import java.util.Hashtable;

/**
 * 一意性(同一性：単写性)を有するシンボル。正準な文字列とも言われる。
 */
public class Symbol extends Object implements Comparable<Symbol>
{
	/**
	 * シンボルの印字名を保持する私的なフィールド。
	 */
	private final String name;

	/**
	 * シンボルの一意性(同一性：単写性)を保つための表(table)を保持する私的なフィールド。
	 */
	private static final Hashtable<String, Symbol> table = new Hashtable<String, Symbol>();

	/**
	 * 私的なコンストラクタ。
	 */
	private Symbol()
	{
		name = null;
	}

	/**
	 * 印字名から未インターンのシンボルを構築する私的なコンストラクタ。
	 * @param name 印字名
	 */
	private Symbol(String name)
	{
		this.name = name;
	}

	/**
	 * 同じ印字名に対して必ず同一のシンボルを応答する。
	 * @param aString 印字名
	 * @return シンボル（単写性：同じ印字名ならば必ず同一のシンボルを答える）
	 */
	public static Symbol of(String aString)
	{
		synchronized (table)
		{
			Symbol aSymbol = table.get(aString);
			if (aSymbol == null)
			{
				aSymbol = new Symbol(aString);
				table.put(aString, aSymbol);
			}
			return aSymbol;
		}
	}

	/**
	 * 自分を文字列に変換して応答する。
	 */
	public String toString()
	{
		return name;
	}

	/**
	 * 印字名で比較する。
	 */
	public int compareTo(Symbol aSymbol)
	{
		return name.compareTo(aSymbol.name);
	}
}
