package cons;

import java.util.Iterator;

/**
 * リスト(二進木)のイテレータ。
 */
public class Loop extends Object implements Iterator<Object>, Iterable<Object>
{
	/**
	 * リストを保持するフィールド。
	 */
	private Cons cons;

	/**
	 * リストの長さを保持するフィールド。
	 */
	private int length;

	/**
	 * リストをアクセスするインデックスを保持するフィールド。
	 */
	private int index;

	/**
	 * リスト(二進木)のイテレータを作るコンストラクタ。
	 * @param aList リスト(二進木)
	 */
	public Loop(Cons aList)
	{
		cons = aList;
		length = aList.length();
		index = 1;
	}

	/**
	 * リストのイテレータを応答する。
	 */
	public Iterator<Object> iterator()
	{
		return this;
	}

	/**
	 * 次の要素があるか否かを応答する。
	 */
	public boolean hasNext()
	{
		return index <= length;
	}

	/**
	 * 次の要素を応答する。
	 */
	public Object next()
	{
		synchronized (cons)
		{
			Object anObject = cons.nth(index);
			this.remove();
			return anObject;
		}
	}

	/**
	 * 次の要素へ移行(削除)する。
	 */
	public void remove()
	{
		index++;
		return;
	}
}
