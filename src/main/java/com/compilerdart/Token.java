package com.compilerdart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.ElementScanner6;

public class Token {
	public static final int TK_INT       = 0;
	public static final int TK_DOUBLE    = 1;
	public static final int TK_BOOL      = 2;
	public static final int TK_STRING    = 3;
	public static final int TK_VARTYPE   = 4;
	public static final int TK_VARNAME   = 5;
	public static final int TK_FUCNTION  = 6;
	public static final int TK_OP        = 7;
	public static final int TK_COMP      = 8;
	public static final int TK_IF        = 9;
	public static final int TK_ELSE      =10;
	public static final int TK_SFUCNTION =11;
	public static final int TK_ATRB      =12;
	public static final int TK_ENDLINE   =13;
	public static final int TK_WORD      =14;
	public static final int VAR_INT      =15;
	public static final int VAR_DOUBLE   =16;
	public static final int VAR_BOOL     =17;
	public static final int VAR_STRING   =18;
	
	public static final String TK_TEXT[] = {
			"INT", "DOUBLE", "BOOL", "STRING", "VARTYPE", "VARNAME", "FUNCTION", "OP", "COMP", "IF", "ELSE", "SFUNCTION", "ATRIBUICAO", "ENDLINE", "WORD", "VARIAVEL INT", "VARIAVEL DOUBLE" , "VARIAVEL BOOL", "VARIAVEL STRING"
	};

	static List<String> variaveisInt = new ArrayList<>();
	static List<String> variaveisString = new ArrayList<>();
	static List<String> variaveisDouble = new ArrayList<>();
	static List<String> variaveisBoolean = new ArrayList<>();

	static int lastVarType;
	
	private int    type;
	private String text;
	private int    line;
	private int    column;
	
	public Token(int type, String text) {
		super();
		this.type = type;
		this.text = text;
	}

	public Token() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Token [type= " + TK_TEXT[type] + ", text= " + text + "]";
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void updateType() {
		if (isVarType(this.text)) {
			setType(Token.TK_VARTYPE);
			setLastVarType();
		} else if (this.text.equals("true") || this.text.equals("false")) {
			setType(Token.TK_BOOL);
		} else if (this.text.equals("write")) {
			setType(Token.TK_STRING);
		} else if (this.text.equals("if")) {
			setType(Token.TK_IF);
		} else if (this.text.equals("else")) {
			setType(Token.TK_ELSE);
		} else if (isVar(this.text)){
			switch (whichVar()) {
				case TK_BOOL:
					setType(VAR_BOOL);
					break;
				case TK_DOUBLE:
					setType(VAR_DOUBLE);
					break;
				case TK_INT:
					setType(VAR_INT);
					break;
				case TK_STRING:
					setType(VAR_STRING);
				default:
					break;
			}
		} else { 			
			switch (lastVarType) {
				case TK_BOOL:
					variaveisBoolean.add(text);
					break;
				case TK_DOUBLE:
					variaveisDouble.add(text);
					break;
				case TK_INT:
					variaveisInt.add(text);
					break;
				case TK_STRING:
					variaveisString.add(text);
				default:
					break;
			}
			setType(TK_VARNAME);
		}
	}

	private boolean isVarType(String s) {
		return s.equals("int") || s.equals("double") || s.equals("bool")||s.equals("String");
	}

	private void setLastVarType() {
		if (text.equals("int")) {
			lastVarType = TK_INT;
		} else if (text.equals("double")) {
			lastVarType = TK_DOUBLE;
		} else if (text.equals("bool")) {
			lastVarType = TK_BOOL;
		}else if (text.equals("String")){
			lastVarType = TK_STRING;
		}
	}

	private boolean isVar(String s) {
		return variaveisBoolean.contains(s)||variaveisDouble.contains(s)||variaveisInt.contains(s)||variaveisString.contains(s);
	}

	private int whichVar() {
		if (variaveisBoolean.contains(this.text)) {
			return TK_BOOL;
		}else if (variaveisDouble.contains(this.text)){
			return TK_DOUBLE;
		}else if(variaveisInt.contains(this.text)){
			return TK_INT;
		}else if (variaveisString.contains(this.text)){
			return TK_STRING;
		} else { return 100;}
	}

}