package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	int printCallCount = 0;
	int varDeclCount = 0;
	Struct globalVarType;
	Obj currentMethod = null;
	Struct currentMethodType = null;
	 
	boolean errorDetected = false;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}

	
	public void visit(PrintStmt print){
		printCallCount++;
	}
	
	public void visit(SimplePrintStmt print){
		printCallCount++;
	}
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program) {
		//ulancavanje lokalnih simbola
		
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(VarDecl vardecl){
		varDeclCount++;
	}
	
	public void visit(AddVarDecl addVar) {
		report_info("Deklarisana promenljiva " + addVar.getVarName(), addVar);
		// provera da li je promenljiva vec deklarisana
		// nalazi varName u tabeli simbola
		
	}
	
	public void visit(TypeDecl typeDecl) {
		// prvo provera da li se radi o tipu
		Obj typeNode = Tab.find(typeDecl.getTypeName());
		if(typeNode == Tab.noObj) {
			//nije nadjen u tabeli 
			report_error("Nije pronadjen tip " + typeDecl.getTypeName(), null);
			typeDecl.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				typeDecl.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + typeDecl.getTypeName() + " ne predstavlja tip", typeDecl);
				typeDecl.struct = Tab.noType;
			}
		}
	}
	
	public void visit(RetType retType) {
		//currentMethodType = retType.get
	}
	
	public void visit(MethodTypeName methodTypeName) {
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se fja "+methodTypeName.getMethName(), methodTypeName);
	}
	
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		currentMethod = null;
		//ulancavanje lok simbola, zatvaranje opsega
		//vracanje currentMethod na null
	}
	
	public void visit(DesName designatorName) {
		Obj obj = Tab.find(designatorName.getDesName());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + designatorName.getLine() + " : ime " + designatorName.getDesName() + " nije deklarisano! ", null);
		}
		((Dsg)(designatorName.getParent().getParent())).obj = obj;
	}
	
	//Dsg - Factor - Term - Expr
	// moraju i Factor i Term i E po tipu da budu struct
	// jer se tako doslo do DsgFun
	
	public void visit(DesignatorFun designatorFun) {
		// akcija za poziv fje na liniji
		Obj func = designatorFun.getDesignator().obj;
		// da li je po tipu 
		if (Obj.Meth == func.getKind()) {
			//designatorFun.struct = designatorFun.getType();
		} else {
			report_error("Greska na liniji ", designatorFun);
			//designatorFun.struct = Tab.noType;
		}
	}
	
	public void visit(DesignatorAssign desAssign) {
		if(!desAssign.getExpr().struct.assignableTo(desAssign.getDesignator().obj.getType())) {
			report_error("Greska na liniji " +desAssign.getLine() + " nekompatabilni tipovi u dodeli vrednosti.", null);
		}
	}
	
	enum RelopEnum { EQUALTO, NOTEQUALTO, GREATERTHAN, GREATERTHANEQ, LESSTHAN, LESSTHANEQ }
	private RelopEnum relop = null;
	
	
	public void visit(NotEqualOp notEqOp) {
		relop = RelopEnum.NOTEQUALTO;
	}
	
	public void visit(GreaterOp grOp) {
		relop = RelopEnum.GREATERTHAN;
	}
	
	public void visit(GreaterOpEq grEqOp) {
		relop = RelopEnum.GREATERTHANEQ;
	}
	
	public void visit(LessOp lsOp) {
		relop = RelopEnum.LESSTHAN;
	}
	
	public void visit(LessEq lsEqOp) {
		relop = RelopEnum.LESSTHANEQ;
	}
	
	
	
	
}
