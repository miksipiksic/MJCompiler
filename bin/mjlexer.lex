
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"break"   { return new_symbol(sym.BREAK, yytext()); }
"class"   { return new_symbol(sym.CLASS, yytext()); }
"else"   { return new_symbol(sym.ELSE, yytext()); }
"const"   { return new_symbol(sym.CONST, yytext()); }
"if"   { return new_symbol(sym.IF, yytext()); }
"new"   { return new_symbol(sym.NEW, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"   { return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"extends"   { return new_symbol(sym.EXTENDS, yytext()); }
"continue"   { return new_symbol(sym.CONTINUE, yytext()); }
"for"   { return new_symbol(sym.FOR, yytext()); }
"static"   { return new_symbol(sym.STATIC, yytext()); }
"namespace"   { return new_symbol(sym.NAMESPACE, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLIER, yytext()); }
"/" 		{ return new_symbol(sym.DIVIDER, yytext()); }
"%" 		{ return new_symbol(sym.MODULO, yytext()); }
"==" 		{ return new_symbol(sym.EQUALTO, yytext()); }
"!=" 		{ return new_symbol(sym.NOTEQUALTO, yytext()); }
">" 		{ return new_symbol(sym.GREATERTHAN, yytext()); }
">=" 		{ return new_symbol(sym.GREATERTHANEQ, yytext()); }
"<" 		{ return new_symbol(sym.LESSTHAN, yytext()); }
"<=" 		{ return new_symbol(sym.LESSTHANEQ, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"=" 		{ return new_symbol(sym.EQUALS, yytext()); }
"++" 		{ return new_symbol(sym.INCREMENT, yytext()); }
"--" 		{ return new_symbol(sym.DECREMENT, yytext()); }
";" 		{ return new_symbol(sym.SEMICOLON, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LSQUAREB, yytext()); }
"]" 		{ return new_symbol(sym.RSQUAREB, yytext()); }
"{" 		{ return new_symbol(sym.LCURLYB, yytext()); }
"}"			{ return new_symbol(sym.RCURLYB, yytext()); }
"=>" 		{ return new_symbol(sym.ARROW, yytext()); }

"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUMCONST, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{  return new_symbol(sym.IDENT, yytext()); }
['][ -~]['] { return new_symbol( sym.CHARCONST, yytext().charAt(1)); }
true    { return new_symbol(sym.BOOLCONST, Boolean.TRUE); }
false   { return new_symbol(sym.BOOLCONST, Boolean.FALSE); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }










