package com.compilerdart;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Scanner {
	
	private char[] content;
	private int    estado;
	private int    pos;
	private int    line;
	private int    column;
	
	public Scanner(String filename) {
		try {
			line = 1;
			column = 0;
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
//			System.out.println("DEBUG --------");
//			System.out.println(txtConteudo);
//			System.out.println("--------------");
			content = txtConteudo.toCharArray();
			pos=0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	
	public Token le_Token() {
		char currentChar;
		Token token;
		String term="";
		if (isEOF()) {
			return null;
		}
		estado = 0;
		while (true) {
			currentChar = nextChar();
			column++;
			
			switch(estado) {
			case 0:
				if (isChar(currentChar)) {
					term += currentChar;
					estado = 17;
				}
				else if (isDigit(currentChar)) {
					estado = 1;
					term += currentChar;
				}
				else if (isSpace(currentChar)) {
					estado = 0;
				}
				else if (isOperator(currentChar)) {
					term += currentChar;
					token = new Token();
					token.setType(Token.TK_OP);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				else if (currentChar == '"'	){
					term += currentChar;
					estado = 7;
				}
				else if(isGSThan(currentChar)){
					term+=currentChar;
					estado=12;
				}
				else if(currentChar == '='){
					term+=currentChar;
					estado=15;
				}
				else if(currentChar == '('){
					term += currentChar;
					token = new Token();
					token.setType(Token.L_BRACKET);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				else if(currentChar == ')'){
					term += currentChar;
					token = new Token();
					token.setType(Token.R_BRACKET);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				else if(currentChar == '{'){
					term += currentChar;
					token = new Token();
					token.setType(Token.L_CBRACKET);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				else if(currentChar == '}'){
					term += currentChar;
					token = new Token();
					token.setType(Token.R_CBRACKET);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				else {
					//throw new IsiLexicalException("Unrecognized SYMBOL");
				}
				break;
			case 1:
				if (isDigit(currentChar)) {
					term+=currentChar;
					estado=1;
				} 
				else if (currentChar == '.') {
					term+=currentChar;
					estado = 3;
				} 
				else{
					back();
					token = new Token();
					token.setType(Token.TK_INT);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				break;
			case 3:
				if (isDigit(currentChar)) {
					term+=currentChar;
					estado = 4;
				}
				break;
			case 4:
				if (isDigit(currentChar)) {
					term+=currentChar;
					estado=4;
				}
				else {
					back();
					token = new Token();
					token.setType(Token.TK_DOUBLE);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				break;
			case 7:
				if (isChar(currentChar) || isDigit( currentChar)) {
					term+=currentChar;
					estado = 7;
				}
				else if( currentChar == '\\'){
					term+=currentChar;
					estado = 8;
				}
				else if(currentChar == '"'){
					term+=currentChar;
					token = new Token();
					token.setType(Token.TK_STRING);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
				break;
			case 8:
				if (isChar(currentChar) || isDigit(currentChar)) {
					term+=currentChar;
					estado=7;
				}
				break;
			case 12:
				if (currentChar == '=') {
					term+=currentChar;
					token = new Token();
					token.setType(Token.TK_COMP);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				} else {
					back();
					term+=currentChar;
					token = new Token();
					token.setType(Token.TK_COMP);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
			case 15:
				if (currentChar == '=') {
					term+=currentChar;
					token = new Token();
					token.setType(Token.TK_COMP);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				} else {
					
					back();
					token = new Token();
					token.setType(Token.TK_ATRB);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				}
			case 17:
				if (isChar(currentChar) || isDigit(currentChar)) {
					term+=currentChar;
					estado = 17;
				} else {
					back();
					token = new Token();
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					token.updateType();
					return token;
				}
				break;
			}
		}
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c>='A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private boolean isGSThan(char c){
		return c == '>' || c == '<';
	}

	private boolean isSpace(char c) {
		if (c == '\n' || c== '\r') {
			line++;
			column=0;
		}
		return c == ' ' || c == '\t' || c == '\n' || c == '\r'; 
	}
	
	private char nextChar() {
		if (isEOF()) {
			return '\0';
		}
		return content[pos++];
	}
	private boolean isEOF() {
		return pos >= content.length;
	}
	
    private void back() {
    	pos--;
    	column--;
    }
    
   /*private boolean isEOF(char c) {
    	return c == '\0';
    }*/
	
	
	
	
	
}