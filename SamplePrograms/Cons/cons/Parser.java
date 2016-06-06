package cons;

import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/**
 * リスト(二進木)のパーザ。
 */
public class Parser extends Object
{
	/**
	 * ソースである文字列を文字列ストリームにして保持するフィールド。
	 */
	private StringReader stream;

	/**
	 * 字句(トークン)を保持するフィールド。
	 */
	private String token = new String();

	/**
	 * 字句(トークン)の種別（シンボル）を保持するフィールド。
	 */
	private Symbol type = Symbol.of("xBinary");

	/**
	 * 文字(キャラクタ)から種別(タイプシンボル)を引くための表(テープル)を保持する私的なフィールド。
	 */
	private static final Hashtable<Character, Symbol> types = new Hashtable<Character, Symbol>();

	/**
	 * リスト(二進木)のパーザを作るコンストラクタ。文字列は文字列ストリームとして保持される。
	 * @param aString リスト(二進木)の文字列
	 */
	public Parser(String aString)
	{
		stream = new StringReader(aString);
		for (char c = 0; c <= 127; c++) types.put(Character.valueOf(c), Symbol.of("xBinary"));
		for (char c = 9; c <= 13; c++) types.put(Character.valueOf(c), Symbol.of("delimiter"));
		types.put(Character.valueOf((char)32), Symbol.of("delimiter"));
		types.put(Character.valueOf('!'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('"'), Symbol.of("xDoubleQuote"));
		types.put(Character.valueOf('#'), Symbol.of("xComment"));
		types.put(Character.valueOf('$'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('%'), Symbol.of("xComment"));
		types.put(Character.valueOf('&'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('\''), Symbol.of("xSingleQuote"));
		types.put(Character.valueOf('('), Symbol.of("leftParenthesis"));
		types.put(Character.valueOf(')'), Symbol.of("rightParenthesis"));
		types.put(Character.valueOf('*'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('+'), Symbol.of("xSign"));
		types.put(Character.valueOf(','), Symbol.of("xSymbol"));
		types.put(Character.valueOf('-'), Symbol.of("xSign"));
		types.put(Character.valueOf('.'), Symbol.of("period"));
		types.put(Character.valueOf('/'), Symbol.of("xSymbol"));
		for (char c = '0'; c <= '9'; c++) types.put(Character.valueOf(c), Symbol.of("xDigit"));
		types.put(Character.valueOf(':'), Symbol.of("xSymbol"));
		types.put(Character.valueOf(';'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('<'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('='), Symbol.of("xSymbol"));
		types.put(Character.valueOf('>'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('?'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('@'), Symbol.of("xSymbol"));
		for (char c = 'A'; c <= 'Z'; c++) types.put(Character.valueOf(c), Symbol.of("xSymbol"));
		types.put(Character.valueOf('['), Symbol.of("leftBracket"));
		types.put(Character.valueOf('\\'), Symbol.of("xSymbol"));
		types.put(Character.valueOf(']'), Symbol.of("rightBracket"));
		types.put(Character.valueOf('^'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('_'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('`'), Symbol.of("quote"));
		for (char c = 'a'; c <= 'z'; c++) types.put(Character.valueOf(c), Symbol.of("xSymbol"));
		types.put(Character.valueOf('{'), Symbol.of("leftBrace"));
		types.put(Character.valueOf('|'), Symbol.of("xSymbol"));
		types.put(Character.valueOf('}'), Symbol.of("rightBrace"));
		types.put(Character.valueOf('~'), Symbol.of("xSymbol"));
	}

	/**
	 * 文字列ストリームが終わりに達しているか否かを応答する。
	 * @return 終わりかどうか
	 */
	private boolean atEnd()
	{
		Character aCharacter = peekChar();
		return aCharacter == null;
	}

	/**
	 * 警告ダイアログを出して終了(ランタイム例外のスローを)する。
	 * @param aString エラー文字列
	 */
	private void fatal(String aString)
	{
		StringBuffer aBuffer = new StringBuffer();
		for (int count = 1; count <= 32; count++)
		{
			if (this.atEnd()) { break; }
			aBuffer.append(this.nextChar());
		}
		String aMessage = aString + "\n  nearby '" + aBuffer.toString() + "'";
		JOptionPane.showMessageDialog(null, aMessage, "Fatal mistake", JOptionPane.PLAIN_MESSAGE);
		throw new RuntimeException(aString);
	}

	/**
	 * 指定された文字(キャラクタ)の種別(タイプシンボル)を応答する。
	 * @param aCharacter ある文字
	 * @return 種別(タイプシンボル)
	 */
	private Symbol getType(Character aCharacter)
	{
		Symbol aSymbol = null;
		if (aCharacter == null)
		{
			aSymbol = Symbol.of("xBinary");
		}
		else
		{
			if ((int)aCharacter > 127)
			{
				if (Character.isLetter(aCharacter))
				{
					aSymbol = Symbol.of("xDelimiter");
				}
				else
				{
					aSymbol = Symbol.of("xBinary");
				}
			}
			else
			{
				aSymbol = types.get(aCharacter);
			}
		}
		return aSymbol;
	}

	/**
	 * 文字列ストリームの現在位置をマークする。
	 */
	private void mark()
	{
		try
		{
			stream.mark(Integer.MAX_VALUE);
		}
		catch (IOException anException)
		{
			anException.printStackTrace();
		}
		return;
	}

	/**
	 * 文字列ストリームから1文字を読んで応答する。
	 * @return 1文字
	 */
	private Character nextChar()
	{
		Character aCharacter = null;
		try
		{
			int c = stream.read();
			if (c >= 0) { aCharacter = Character.valueOf((char)c); }
		}
		catch (IOException anException)
		{
			anException.printStackTrace();
		}
		return aCharacter;
	}

	/**
	 * 文字列ストリームから字句(トークン)を読んで応答する。
	 * @return 字句(トークン)
	 */
	private String nextToken()
	{
		this.mark();
		Character aCharacter = null;
		Symbol aType = null;
		while (this.atEnd() == false)
		{
			aCharacter = this.peekChar();
			aType = this.getType(aCharacter);
			if (aType != Symbol.of("delimiter")) { break; }
			this.nextChar();
		}
		if (this.atEnd()) {
			token = new String();
			type = Symbol.of("eof");
		} else {
			if (aType.toString().charAt(0) == 'x')
			{
				if (aType == Symbol.of("xComment")) { this.xComment(); return this.nextToken(); }
				else if (aType == Symbol.of("xDigit")) { this.xDigit(); }
				else if (aType == Symbol.of("xDoubleQuote")) { this.xDoubleQuote(); }
				else if (aType == Symbol.of("xSign")) { this.xSign(); }
				else if (aType == Symbol.of("xSingleQuote")) { this.xSingleQuote(); }
				else if (aType == Symbol.of("xSymbol")) { this.xSymbol(); }
				else this.xBinary();
			}
			else
			{
				aCharacter = this.nextChar();
				token = Character.toString(aCharacter);
				type = aType;
			}
		}
		// System.out.println(type + ": " + token);
		return token;
	}

	/**
	 * 文字列ストリームをパーズして作成したリスト(二進木)を応答する。
	 * @return 文字列をパーズして作成したリスト(二進木)
	 */
	public Object parse()
	{
		if (this.atEnd()) { return Cons.nil; }
		return this.parseList();
	}

	/**
	 * 指定された文字列をパーズして作成したリスト(二進木)を応答する。
	 * @param aString リスト(二進木)の文字列
	 * @return 文字列をパーズして作成したリスト(二進木)
	 */
	public static Object parse(String aString)
	{
		Parser aParser = new Parser(aString);
		return aParser.parse();
	}

	/**
	 * 文字列ストリームをパーズしてリストを応答する。
	 * @return リスト
	 */
	private Object parseList()
	{
		this.nextToken();
		if (type == Symbol.of("eof")) { return Cons.nil; }
		if (type == Symbol.of("character")) { return this.tokenToCharacter(); }
		if (type == Symbol.of("leftParenthesis")) { return this.parseListAux(); }
		if (type == Symbol.of("number")) { return this.tokenToNumber(); }
		if (type == Symbol.of("quote"))
		{
			Cons expression = new Cons(this.parseList(), Cons.nil);
			return new Cons(Symbol.of("quote"), expression);
		}
		if (type == Symbol.of("string")) { return tokenToString(); }
		if (type == Symbol.of("symbol")) { return tokenToSymbol(); }
		this.fatal("Syntax error '" + token + "'");
		return Cons.nil;
	}

	/**
	 * 文字列ストリームをパーズしてリストを応答する。
	 * @return リスト
	 */
	private Object parseListAux()
	{
		this.nextToken();
		if (type == Symbol.of("eof")) { return Cons.nil; }
		if (type == Symbol.of("rightParenthesis")) { return Cons.nil; }
		if (type == Symbol.of("leftParenthesis")) { return new Cons(this.parseListAux(), this.parseListAux()); }
		if (type == Symbol.of("character")) { return new Cons(this.tokenToCharacter(), this.parseListAux()); }
		if (type == Symbol.of("number")) { return new Cons(this.tokenToNumber(), this.parseListAux()); }
		if (type == Symbol.of("period"))
		{
			Object cdr = this.parseList();
			this.nextToken();
			if (type == Symbol.of("rightParenthesis")) { return cdr; }
			this.fatal("Syntax error '" + token + "'");
		}
		if (type == Symbol.of("quote"))
		{
			Cons expression = new Cons(this.parseList(), Cons.nil);
			expression = new Cons(Symbol.of("quote"), expression);
			return new Cons(expression, this.parseListAux());
		}
		if (type == Symbol.of("string")) { return new Cons(tokenToString(), this.parseListAux()); }
		if (type == Symbol.of("symbol")) { return new Cons(tokenToSymbol(), this.parseListAux()); }
		this.fatal("Syntax error '" + token + "'");
		return Cons.nil;
	}

	/**
	 * 文字列ストリームから1文字を盗み見て応答する。
	 * @return 1文字
	 */
	private Character peekChar()
	{
		Character aCharacter = null;
		try
		{
			this.mark();
			int c = stream.read();
			this.reset();
			if (c >= 0) { aCharacter = Character.valueOf((char)c); }
		}
		catch (IOException anException) { anException.printStackTrace(); }
		return aCharacter;
	}

	/**
	 * 直前のマーク位置にストリームをリセットする。
	 */
	private void reset()
	{
		try { stream.reset(); }
		catch (IOException anException) { anException.printStackTrace(); }
		return;
	}

	/**
	 * 現在のトークンを文字に変換して応答する。
	 * @return 文字
	 */
	private Object tokenToCharacter()
	{
		Character aCharacter = new Character(token.charAt(0));
		return aCharacter;
	}

	/**
	 * 現在のトークンを数値に変換して応答する。
	 * @return 数値
	 */
	private Object tokenToNumber()
	{
		boolean isInteger = true;
		for (int index = 0; index < token.length(); index++)
		{
			Character aCharacter = token.charAt(index);
			if ((aCharacter == '.') || (aCharacter == 'E') || (aCharacter == 'e')) { isInteger = false; }
		}
		Object aNumber;
		if (isInteger) { aNumber = (Object)(Integer.parseInt(token)); }
		else { aNumber = (Object)(Double.parseDouble(token)); }
		return aNumber;
	}

	/**
	 * 現在のトークンを文字列に変換して応答する。
	 * @return 文字列
	 */
	private Object tokenToString()
	{
		String aString = new String(token);
		return aString;
	}

	/**
	 * 現在のトークンをシンボルに変換して応答する。
	 * @return シンボル
	 */
	private Object tokenToSymbol()
	{
		Symbol aSymbol = Symbol.of(token);
		if (aSymbol == Symbol.of("nil")) { return Cons.nil; }
		return aSymbol;
	}

	/**
	 * 文字列ストリームから1文字を盗み見て応答する。
	 */
	private void unNextChar()
	{
		try { stream.skip((long)-1); }
		catch (IOException anException) { anException.printStackTrace(); }
		return;
	}

	/**
	 * 予期しない文字が現れたので、文法エラー。
	 */
	private void xBinary()
	{
		Character aCharacter = this.peekChar();
		String aString = "eof";
		if (aCharacter != null) { aString = Character.toString(aCharacter); }
		token = aString;
		type = Symbol.of("error");
		this.fatal("Syntax error '" + aString + "'");
		return;
	}

	/**
	 * コメントを切り出す。
	 */
	private void xComment()
	{
		if (this.atEnd()) { return; }
		StringBuffer aBuffer = new StringBuffer();
		while (this.atEnd() == false)
		{
			Character aCharacter = this.peekChar();
			if (aCharacter == Character.valueOf((char)13) /* CR */)
			{
				if (this.atEnd() == false)
				{
					aCharacter = this.peekChar();
					if (aCharacter != Character.valueOf((char)10) /* LF */)
					{
						aCharacter = this.nextChar();
						aBuffer.append(aCharacter);
					}
				}
				break;
			}
			if (aCharacter == Character.valueOf((char)10) /* LF */)
			{
				aCharacter = this.nextChar();
				aBuffer.append(aCharacter);
				break;
			}
			aCharacter = this.nextChar();
			aBuffer.append(aCharacter);
		}
		token = aBuffer.toString();
		type = Symbol.of("comment");
		return;
	}

	/**
	 * 数を切り出す。
	 */
	private void xDigit()
	{
		if (this.atEnd()) { return; }
		StringBuffer aBuffer = new StringBuffer();
		Character aCharacter;
		Symbol aType;
		boolean isInteger = true;
		while (this.atEnd() == false)
		{
			aCharacter = this.peekChar();
			aType = this.getType(aCharacter);
			if (((aType == Symbol.of("xDigit")) || (aType == Symbol.of("xSign")) || (aType == Symbol.of("period")) || (aCharacter == 'E') || (aCharacter == 'e')) == false) { break; }
			if ((aType == Symbol.of("period")) || (aCharacter == 'E') || (aCharacter == 'e')) { isInteger = false; }
			aCharacter = this.nextChar();
			aBuffer.append(aCharacter);
		}
		token = aBuffer.toString();
		type = Symbol.of("number");
		return;
	}

	/**
	 * 文字列を切り出す。
	 */
	private void xDoubleQuote()
	{
		if (this.atEnd()) { return; }
		StringBuffer aBuffer = new StringBuffer();
		Character aCharacter = this.nextChar();
		Symbol aType = this.getType(aCharacter);
		boolean ok = true;
		while (this.atEnd() == false)
		{
			aCharacter = this.peekChar();
			aType = this.getType(aCharacter);
			if (aType == Symbol.of("xDoubleQuote")) { this.nextChar(); break; }
			if (this.atEnd()) { ok = false; break; }
			if (aCharacter == '\\') { this.nextChar(); }
			if (this.atEnd()) { ok = false; break; }
			aCharacter = this.nextChar();
			aBuffer.append(aCharacter);
		}
		if (ok)
		{
			token = aBuffer.toString();
			type = Symbol.of("string");
		}
		else
		{
			token = aBuffer.toString();
			type = Symbol.of("error");
			String aString = token;
			if (token.length() > 32) { aString = token.substring(0, 32); }
			this.fatal("Syntax error unmatched double quote \"" + aString);
		}
		return;
	}

	/**
	 * 数またはシンボルを切り出す。
	 */
	private void xSign()
	{
		if (this.atEnd()) { return; }
		Character aSign = this.nextChar();
		if (this.atEnd() == false)
		{
			Character aCharacter = this.peekChar();
			Symbol aType = this.getType(aCharacter);
			if (aType == Symbol.of("xDigit"))
			{
				this.xDigit();
				if (aSign == '-') { token = Character.toString(aSign) + token; }
			}
			else
			{
				this.unNextChar();
				this.xSymbol();
			}
			return;
		}
		token = Character.toString(aSign);
		type = Symbol.of("error");
		this.fatal("Syntax error '" + token + "'");
		return;
	}

	/**
	 * 文字または引用を切り出す。
	 */
	private void xSingleQuote()
	{
		if (this.atEnd()) { return; }
		Character aCharacter = this.nextChar(); int howMany = 0;
		if (this.atEnd() == false)
		{
			Character theCharacter = this.nextChar(); howMany++;
			if (this.atEnd() == false)
			{
				if (theCharacter == '\\') { theCharacter = this.nextChar(); howMany++; }
				if (this.atEnd() == false)
				{
					aCharacter = this.nextChar(); howMany++;
					Symbol aType = this.getType(aCharacter);
					if (aType == Symbol.of("xSingleQuote"))
					{
						token = Character.toString(theCharacter);
						type = Symbol.of("character");
					}
					else
					{
						for (int count = 0; count < howMany; count++) { this.unNextChar();}
						token = new String("'");
						type = Symbol.of("quote");
					}
					return;
				}
			}
		}
		token = Character.toString(aCharacter);
		type = Symbol.of("error");
		this.fatal("Syntax error '" + token + "'");
		return;
	}

	/**
	 * シンボルを切り出す。
	 */
	private void xSymbol()
	{
		if (this.atEnd()) { return; }
		StringBuffer aBuffer = new StringBuffer();
		Character aCharacter;
		Symbol aType;
		while (this.atEnd() == false)
		{
			aCharacter = this.peekChar();
			aType = this.getType(aCharacter);
			if (((aType == Symbol.of("xSymbol")) || (aType == Symbol.of("xDigit")) || (aType == Symbol.of("xSign")) || (aType == Symbol.of("period"))) == false) { break; }
			aCharacter = this.nextChar();
			aBuffer.append(aCharacter);
		}
		token = aBuffer.toString();
		type = Symbol.of("symbol");
		return;
	}
}
