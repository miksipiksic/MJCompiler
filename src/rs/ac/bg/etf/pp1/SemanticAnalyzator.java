package rs.ac.bg.etf.pp1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.DesFactor;
import rs.ac.bg.etf.pp1.ast.SingleFactor;

public class SemanticAnalyzator extends VisitorAdaptor {

	boolean errorDetected = false;
	String currentRelOp = null;
	String currentAddOp = null;
	String currentMulOp = null;
	List<String> namespaces = new ArrayList<String>();
	boolean mainMethodFound = false;

	int nVars;

	public static final Struct boolType = new Struct(Struct.Bool);
	Logger log = Logger.getLogger(getClass());
	
	public SemanticAnalyzator() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", SemanticAnalyzator.boolType));
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {

		nVars = Tab.currentScope.getnVars();
		if(mainMethodFound == false){
			report_error("void main() nije pronadjen!", null);
		}
		// ulancavanje lokalnih simbola
		Tab.chainLocalSymbols(program.getProgName().obj);

		Tab.closeScope();
		
	}
	
	String currentNamespace = null;
	boolean inNamespaceDecl = false;
	public void visit(EntryNamespace es) {
		inNamespaceDecl = true;

		currentNamespace = es.getNamespaceName();
		namespaces.add(currentNamespace);
	}

	
	
	public void visit(EndOfNamespace endOf) {
		inNamespaceDecl = false;
	}

	Struct currentType = null;
	
	

	public void visit(TypeDecl typeDecl) {
		// prvo provera da li se radi o tipu
		Obj typeNode = Tab.find(typeDecl.getTypeName());
		if (typeNode == Tab.noObj) {
			// nije nadjen u tabeli
			report_error("Nije pronadjen tip " + typeDecl.getTypeName(), typeDecl);
			typeDecl.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				typeDecl.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + typeDecl.getTypeName() + " ne predstavlja tip", typeDecl);
				typeDecl.struct = Tab.noType;
			}
		}
		if(inNamespaceDecl) {
			Obj nmType = Tab.insert(Obj.Type, currentNamespace+"::"+typeDecl.getTypeName(), typeDecl.struct);
		}
		currentType = typeDecl.struct;
	}

	public void visit(UsingTypeDecl usingType) {
		boolean namespaceFound = false;
		String namespace = usingType.getNamespaceName();
		for (String string : namespaces) {
			if (namespace.equals(string)) {
				namespaceFound = true;
			}
		}
		if(!namespaceFound) {
			report_error("Prostor imena " + namespace + " nije pronadjen", usingType);
		}
		Obj typeNode = Tab.find(namespace + "::" + usingType.getTypeDecl().getTypeName());
		if (typeNode == Tab.noObj) {
			// nije nadjen u tabeli
			report_error("Nije pronadjen tip " + usingType.getTypeDecl().getTypeName(),usingType);
			usingType.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				usingType.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + usingType.getTypeDecl().getTypeName() + " ne predstavlja tip", usingType);
				usingType.struct = Tab.noType;
			}
		}
		currentType = usingType.struct;;

	}

	public void visit(NoUsingType usingType) {
		Obj typeNode = Tab.find(usingType.getTypeDecl().getTypeName());
		if (typeNode == Tab.noObj) {
			// nije nadjen u tabeli
			report_error("Nije pronadjen tip " + usingType.getTypeDecl().getTypeName(), null);
			usingType.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				usingType.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + usingType.getTypeDecl().getTypeName() + " ne predstavlja tip", usingType);
				usingType.struct = Tab.noType;
			}
		}
		
		currentType = usingType.struct;
		

	}
	
	

	public void visit(NumType numConst) {
		if(inNamespaceDecl) {

			Obj constName = Tab.find(numConst.getConstName());
			if (constName != Tab.noObj) {
				report_error(currentNamespace+ "::" + numConst.getConstName() + " je vec deklarisano.", numConst);
			}
			// provera tipa
			if (!currentType.equals(Tab.intType)) {
				report_error( numConst.getConstName()+ " nije dobrog tipa", numConst);
			}
			// dodavanje u tabelu simbola
			Obj constObj = Tab.insert(Obj.Con, currentNamespace+ "::" + numConst.getConstName(), currentType);
			constObj.setAdr(numConst.getNumConstValue());

			report_info("Deklarisana konstanta: const " + currentNamespace+ "::" + numConst.getConstName() + " = "
					+ numConst.getNumConstValue(), numConst);
			return;
		}
		Obj constName = Tab.find(numConst.getConstName());
		// da li postoji u tabeli vec, za konstante ne bi smelo
		if (constName != Tab.noObj) {
			report_error(numConst.getConstName() + " je vec deklarisano.",
					numConst);
		}
		// provera tipa
		if (!currentType.equals(Tab.intType)) {
			report_error(numConst.getConstName()
					+ " nije dobrog tipa", numConst);
		}
		// dodavanje u tabelu simbola
		Obj constObj = Tab.insert(Obj.Con, numConst.getConstName(), currentType);
		constObj.setAdr(numConst.getNumConstValue());

		report_info("Deklarisana konstanta: const " + numConst.getConstName() + " = "
				+ numConst.getNumConstValue(), numConst);
		
		
	}

	public void visit(CharType charConst) {
		if (inNamespaceDecl) {
			Obj constName = Tab.find(charConst.getConstName());
			// da li postoji u tabeli vec, za konstante ne bi smelo
			if (constName != Tab.noObj) {
				report_error(currentNamespace+ "::" +  charConst.getConstName()
						+ " je vec deklarisano.", charConst);
			}
			// provera tipa
			if (!currentType.equals(Tab.charType)) {
				report_error(currentNamespace+ "::" + charConst.getConstName()
						+ " nije dobrog tipa", charConst);
			}
			// dodavanje u tabelu simbola
			Obj constObj = Tab.insert(Obj.Con, currentNamespace+ "::" + charConst.getConstName(), currentType);
			constObj.setAdr(charConst.getCharConstValue());

			report_info("Deklarisana konstanta: const " + currentNamespace+ "::" + charConst.getConstName() + " = "
					+ charConst.getCharConstValue(), charConst);
			return;
		}
		Obj constName = Tab.find(charConst.getConstName());
		// da li postoji u tabeli vec, za konstante ne bi smelo
		if (constName != Tab.noObj) {
			report_error( charConst.getConstName()
			+ " je vec deklarisano.", charConst);
		}
		// provera tipa
		if (!currentType.equals(Tab.charType)) {
			report_error( charConst.getConstName()
			+ " nije dobrog tipa", charConst);
		}
		// dodavanje u tabelu simbola
		Obj constObj = Tab.insert(Obj.Con, charConst.getConstName(), currentType);
		constObj.setAdr(charConst.getCharConstValue());

		report_info("Deklarisana konstanta: const " +  charConst.getConstName() + " = "
				+ charConst.getCharConstValue(), charConst);
	}


	public void visit(BoolType boolConst) {
		if (inNamespaceDecl) {
			Obj constName = Tab.find(boolConst.getConstName());
			// da li postoji u tabeli vec, za konstante ne bi smelo
			if (constName != Tab.noObj) {
				report_error(currentNamespace + "::" + boolConst.getConstName()
						+ " je vec deklarisano.", boolConst);
			}
			// provera tipa
			if (!currentType.equals(boolType)) {
				report_error(currentNamespace + "::" + boolConst.getConstName()
				+" nije dobrog tipa", boolConst);
			}
			// dodavanje u tabelu simbola
			Obj constObj = Tab.insert(Obj.Con,
					currentNamespace+ "::" + boolConst.getConstName(), currentType);
			if (boolConst.getBoolConstValue() == true) {
				constObj.setAdr(1);
			} else {
				constObj.setAdr(0);
			}
			report_info(currentNamespace + "::" + boolConst.getConstName() + " = "
					+ boolConst.getBoolConstValue(), boolConst);
			
			return;
		}
		Obj constName = Tab.find(boolConst.getConstName());
		// da li postoji u tabeli vec, za konstante ne bi smelo
		if (constName != Tab.noObj) {
			report_error( boolConst.getConstName()
			+ " je vec deklarisano.", boolConst);
		}
		// provera tipa
		if (!currentType.equals(boolType)) {

			report_error(boolConst.getConstName()
			+" nije dobrog tipa", boolConst);
		}
		// dodavanje u tabelu simbola
		Obj constObj = Tab.insert(Obj.Con, boolConst.getConstName(), currentType);
		if (boolConst.getBoolConstValue() == true) {
			constObj.setAdr(1);
		} else {
			constObj.setAdr(0);
		}
		report_info("Deklarisana konstanta: const " +  boolConst.getConstName() + " = "
				+ boolConst.getBoolConstValue(), boolConst);
	}

	// varijable
	boolean array = false;

	public void visit(AddSqBrackets sq) {
		array = true;
	}

	public void visit(AddVarDecl addVar) {
		if (inNamespaceDecl) {
			Obj varName = Tab.find(addVar.getVarName());
			// da li postoji u tabeli vec, za konstante ne bi smelo
			if (varName != Tab.noObj) {
				report_error(currentNamespace + "::"+ addVar.getVarName() + " je vec deklarisano.",
						addVar);
			}
			Struct varType = currentType;
			// dodavanje u tabelu simbola
			if (array) {
				varType = new Struct(Struct.Array, currentType);
			}
			Obj constObj = Tab.insert(Obj.Var, currentNamespace + "::" + addVar.getVarName(), varType);

			report_info("Deklarisana promenljiva: " + currentNamespace + "::"+ addVar.getVarName(), addVar);
			array = false;
			return;
		}
		Obj varName = Tab.find(addVar.getVarName());
		// da li postoji u tabeli vec, za konstante ne bi smelo
		if (varName != Tab.noObj) {
			report_error(addVar.getVarName() + " je vec deklarisano.",
					addVar);
		}
		Struct varType = currentType;
		// dodavanje u tabelu simbola
		if (array) {
			varType = new Struct(Struct.Array, currentType);
		}
		Obj constObj = Tab.insert(Obj.Var, addVar.getVarName(), varType);

		report_info("Deklarisana promenljiva: " + addVar.getVarName(), addVar);
		array = false;
	}

	public void visit(EndVarDecl addVar) {
		if (inNamespaceDecl) {
			Obj varName = Tab.find(addVar.getVarName());
			// da li postoji u tabeli vec, za konstante ne bi smelo
			if (varName != Tab.noObj) {
				report_error(currentNamespace + "::"+ addVar.getVarName() + " je vec deklarisano.",
						addVar);
			}
			Struct varType = currentType;
			// dodavanje u tabelu simbola
			if (array) {
				varType = new Struct(Struct.Array, currentType);
			}
			Obj constObj = Tab.insert(Obj.Var, currentNamespace + "::" + addVar.getVarName(), varType);

			report_info("Deklarisana promenljiva: " + currentNamespace + "::"+ addVar.getVarName(), addVar);
			array = false;
			return;
		}
		Obj varName = Tab.find(addVar.getVarName());
		// da li postoji u tabeli vec, za konstante ne bi smelo
		if (varName != Tab.noObj) {
			report_error(addVar.getVarName() + " je vec deklarisano.",
					addVar);
		}
		Struct varType = currentType;
		// dodavanje u tabelu simbola
		if (array) {
			varType = new Struct(Struct.Array, currentType);
		}
		Obj constObj = Tab.insert(Obj.Var, addVar.getVarName(), varType);

		report_info("Deklarisana promenljiva: " + addVar.getVarName(), addVar);
		array = false;
	}

	Obj currentMethod = null;
	boolean returnFound = false;

	public void visit(MethodTypeName methodTypeName) {
		if (inNamespaceDecl) {
			currentMethod = Tab.insert(Obj.Meth, currentNamespace + "::" +  methodTypeName.getMethName(), methodTypeName.getRetType().struct);
		}
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getRetType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		if (inNamespaceDecl) {
		report_info("Obradjuje se funkcija " + currentNamespace+ "::" + methodTypeName.getMethName(), methodTypeName);
		} else {
			report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
		}
	}

	CounterVisitor cntV = new CounterVisitor();

	public void visit(MethodDecl methodDecl) {

		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Funkcija " + currentMethod.getName()
					+ " nema return", methodDecl);
		}
		currentMethod.setLevel(cntV.getFormalParamCount());
		
		if("main".equals(currentMethod.getName()) && (currentMethod.getLevel() == 0)){
			mainMethodFound = true;
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		returnFound = false;
		currentMethod = null;
	}

	public void visit(SimpleRetType simpleRetType) {
		simpleRetType.struct = currentType;
	}

	public void visit(RetVoid retVoid) {
		retVoid.struct = Tab.noType;
	}

	public void visit(AddFormal addFormal) {
		Obj formalParam = Tab.find(addFormal.getParamName());

		if (formalParam != Tab.noObj) {

			if (Tab.currentScope.findSymbol(addFormal.getParamName()) != null) {
				report_error("Formalni parametat " + addFormal.getParamName()
						+ " je vec deklarisan", addFormal);

			}
		}

		Struct parameterType = currentType;
		if (array == true) {
			parameterType = new Struct(Struct.Array, currentType);
		}

		Tab.insert(Obj.Var, addFormal.getParamName(), parameterType);

		array = false;

	}

	public void visit(ReturnExprStmt returnExpr) {
		returnFound = true;
		if (currentMethod.getType() == Tab.noType) {
			// error da ne moze da vraca tip koji ima expr
		}
		if (currentMethod.getType() != returnExpr.getExpr().struct) {
			// drugaciji tipovi error
		}
	}
	
	public void visit(ReturnStmt returnNoExpr) {
		returnFound = true;
		if (currentMethod.getType() != Tab.noType) {
			// error metoda mora vracati tip
		}
	}
	
	public void visit(Dsg dsg) {
		// single designator -> Designator: ident
		dsg.obj = dsg.getDesUsing().obj;
	}
	
	public void visit(OneDesUsing desUsing) {
		boolean namespaceFound = false;
		String namespace = desUsing.getDesNamespace();
		for (String string : namespaces) {
			if (namespace.equals(string)) {
				namespaceFound = true;
			}
		}
		if(!namespaceFound) {
			// error nije definisan taj prostor imena
			report_error("Nije def prostor imena " + namespace, desUsing);
		}
		Obj desNode = Tab.find(namespace+ "::" + desUsing.getDesName());
		if (desNode == Tab.noObj) {
			// report error promenljiva nije deklarisana
			report_error("Promenljiva: " +namespace + "::" + desUsing.getDesName()
			+" nije deklarisana.", desUsing);
		}
		desUsing.obj = desNode;
		
		if(desUsing.obj.getKind() != Obj.Con
				&& desUsing.obj.getKind() != Obj.Var
				&& desUsing.obj.getKind() != Obj.Meth
				&& desUsing.obj.getKind() != Obj.Type) {

			//promenljivu, konstantu, 
		//	metodu ili tip definisan u okviru datog prostora imena
			report_error("Designator :" + desUsing.getDesName() 
					+ " mora biti promenljiva, konstanta, metod ili tip u okviru datog prostora imena.", desUsing);
			
		}
		

		
	}
	
	public void visit(DesName desName) {
		Obj desNameNode = Tab.find(desName.getDesName());
		if(desNameNode == Tab.noObj) {
			
		}
		desName.obj = desNameNode;
	}
	
	
	public void visit(NoDesUsing desUsing) {
		String name = desUsing.getDesName();

		Obj desNode = Tab.find(desUsing.getDesName());
		if (desNode == Tab.noObj) {
			report_error("Promenljiva: " + desUsing.getDesName()
			+" nije deklarisana.", desUsing);
		}
		desUsing.obj = desNode;
		
		
		if(desUsing.obj.getKind() != Obj.Con
				&& desUsing.obj.getKind() != Obj.Var
				&& desUsing.obj.getKind() != Obj.Meth
				&& desUsing.obj.getKind() != Obj.Type) {

			report_error("Designator :" + desUsing.getDesName() 
			+ " mora biti promenljiva, konstanta, metod ili tip.", desUsing);
	
		}
		

		
		

	}


	public void visit(DesignatorAssign designatorAssign) {

		// provera da li je elem, niz
		if ((designatorAssign.getDesignator().obj.getKind() != Obj.Var
				&& designatorAssign.getDesignator().obj.getKind() != Obj.Elem)) {
			report_error("Sa leve strane mora biti promenljiva ili element niza", designatorAssign);
		}

		// kompatabilnost tipova
		if (!designatorAssign.getExpr().struct.assignableTo(designatorAssign.getDesignator().obj.getType()))
			report_error("Tipovi pri dodeli vrednosti nisu komptabilni.", designatorAssign);
	}

	public void visit(DesignatorIncrement designatorInc) {

		// provera da li je elem, niz, ili field
		if (designatorInc.getDesignator().obj.getKind() != Obj.Var
				&& designatorInc.getDesignator().obj.getKind() != Obj.Fld
				&& designatorInc.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Sa leve strane mora biti promenljiva, element niza ili polje klase", designatorInc);
		}

		// kompatabilnost tipova - designator mora biti int
		if (designatorInc.getDesignator().obj.getType() != Tab.intType)
			report_error("Tipovi pri dodeli vrednosti nisu komptabilni. Moze se inkrementirati int tip.", designatorInc);
		
	}
	
	
	
	public void visit(DesignatorDecrement designatorInc) {

		// provera da li je elem, niz, ili field
		if (designatorInc.getDesignator().obj.getKind() != Obj.Var
				&& designatorInc.getDesignator().obj.getKind() != Obj.Fld
				&& designatorInc.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Sa leve strane mora biti promenljiva, element niza ili polje klase", designatorInc);
		}

		// kompatabilnost tipova - designator mora biti int
		if (designatorInc.getDesignator().obj.getType() != Tab.intType)
			report_error("Tipovi pri dodeli vrednosti nisu komptabilni. Moze se dekrementirati int tip.", designatorInc);

	}
	
	public void visit(ReadStmt readstmt) {
		if (readstmt.getDesignator().obj.getKind() != Obj.Var
				&& readstmt.getDesignator().obj.getKind() != Obj.Fld
				&& readstmt.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Sa leve strane mora biti promenljiva, element niza ili polje klase", readstmt);
		}
		if (readstmt.getDesignator().obj.getType() != Tab.intType
				&& readstmt.getDesignator().obj.getType() != Tab.charType
				&& readstmt.getDesignator().obj.getType() != boolType) {
			report_error("Tip mora biti int, char ili bool", readstmt);
		}

		System.out.println(readstmt.getDesignator().obj.getName());
		
	}
	
	public void visit(SingleCondFact condFact) {
		// mora biti bool
		if(condFact.getExpr().struct != boolType) {
			report_error("Uslov mora biti tipa bool.", condFact);
		}
	}
	
	public void visit(ActParDecls actualPars) {
		if (cntV.getActualParamCount() != cntV.getFormalParamCount()) {
			report_error("Broj formalnih i stvarih argumenata nije isti.", actualPars);
		}
	}
	
	public void visit(CondFacts condFacts) {
		Struct expr1 = condFacts.getExpr().struct;
		Struct expr2 = condFacts.getExpr1().struct;
		if (!expr1.compatibleWith(expr2)) {
			report_error("Tipovi nisu kompatabilni.", condFacts);
		}
		if(expr1.getKind() == Struct.Array || expr2.getKind() == Struct.Array) {
			if(!currentRelOp.equals("equalto") && !currentRelOp.equals("notequalto")) {
				report_error("Moze se vrsiti uporedjivanje samo na jednakost (==) ili nejednakost (!=).", condFacts);
			}
		}
		
	}
	
	boolean unaryMinus = false;
	
	public void visit(OneUnaryMinus uMinus) {
		unaryMinus = true;
	}
	
	Struct currentExprType = null;
	
	public void visit(NoUnaryMinus nm) {
		unaryMinus = false;
	}
	
	public void visit(SingleTerm singleTerm) {
		if (unaryMinus) {
			if (singleTerm.getTerm().struct != Tab.intType) {
				report_error("Tip mora biti int.", singleTerm);
				singleTerm.struct = Tab.noType;
			}

			singleTerm.struct = Tab.intType;
		} else {
			singleTerm.struct = singleTerm.getTerm().struct;
		}
		
	}
	
	public void visit(Expr expr) {
		expr.struct = expr.getSingleTerm().struct;
	}
	
	public void visit(Term term) {
		term.struct = term.getFactorList().struct;
	}
	
	public void visit(AddTerm addTerm) {
		if (!addTerm.getTerm().struct.compatibleWith(addTerm.getAddTerms().struct)) {

			addTerm.struct = Tab.noType;
		}
		if (addTerm.getTerm().struct != Tab.intType) {
			report_error("Tip mora biti int pri koriscenju operacije +.", addTerm);

			addTerm.struct = Tab.noType;
		}


    	addTerm.struct = Tab.intType;
	}
	
	public void visit(MulFactors mulFactor) {
		if (mulFactor.getFactor().struct != Tab.intType) {
			report_error("Tip mora biti int pri koriscenju operacije *.", mulFactor);
			mulFactor.struct = Tab.noType;
		}

    	mulFactor.struct = Tab.intType;
	}
	
	public void visit(NewFactorArray newArray) {
		if (newArray.getExpr().struct != Tab.intType) {
			report_error("Da bi se alocirao niz, treba zadati njegovu velicinu u int.", newArray);
		}
		newArray.struct = new Struct(Struct.Array, newArray.getType().struct);
	}
	
	public void visit(DsqB desArray) {
		if (desArray.getExpr().struct != Tab.intType) {
			report_error("Da bi se pristupilo nizu, indeksi su tipa int", desArray);
			desArray.obj = Tab.noObj;
		}
		if (desArray.getDesUsing().obj.getType().getKind() != Struct.Array) {
			report_error("Promenljiva mora biti niz.", desArray);

			desArray.obj = Tab.noObj;
		}
	//	System.out.println( desArray.getDesUsing().obj.getName());
		desArray.obj = new Obj(Obj.Elem, desArray.getDesUsing().obj.getName(), desArray.getDesUsing().obj.getType().getElemType());
	}
	
	
	
	public void visit(EqualOp EqualOp) {
    	currentRelOp = "equalop";
    }
    
    public void visit(NotEqualOp NotEqualOp) {
    	currentRelOp = "notequalop";
    }
    
    public void visit(GreaterOp GreaterOp) {
    	currentRelOp = "greaterop";
    }
    
    public void visit(GreaterOpEq GreaterOpEq) {
    	currentRelOp = "greaterOpEq";
    }

    public void visit(LessOp LessOp) {
    	currentRelOp = "lessop";
    }
    
    public void visit(LessEq LessEq) {
    	currentRelOp = "lesseq";
    }
    
    public void visit(Add add) {
    	currentAddOp = "plus";
    }
    
    public void visit(Sub sub) {
    	currentAddOp = "minus";
    }
    
    public void visit(Multiply m) {
    	currentMulOp = "multiplier";
    }
    
    public void visit(Divide m) {
    	currentMulOp = "divider";
    }
    
    public void visit(Modulo m) {
    	currentMulOp = "modulo";
    }
    
    public void visit(PrintStmt ps) {
    	Struct printExprType = ps.getExpr().struct;
    	if(printExprType != Tab.intType &&
    			printExprType != Tab.charType &&
    			printExprType != boolType) {
    		report_error("Argument fje print mora biti tipa int, char ili bool.", ps);
    	}
    		
    }
    
    public void visit(SimplePrintStmt sps) {
    	Struct printExprType = sps.getBExprB().getExpr().struct;
    	if(printExprType != Tab.intType &&
    			printExprType != Tab.charType &&
    			printExprType != boolType) {
    		report_error("Argument fje print mora biti tipa int, char ili bool.", sps);
    	}
    }
    
   // prosledjivanje tipova
    
    public void visit(SingleFactor sf) {
    	sf.struct = sf.getFactor().struct;
    }
    
    public void visit(DesFactor df) {
    	df.struct = df.getDesignator().obj.getType();
    }
    
    public void visit(ExprFactor ef) {
    	ef.struct = ef.getExpr().struct;
        
    }
    
    public void visit(NumFactor nf) {
    	nf.struct = Tab.intType;
    }
    
    public void visit(CharFactor cf) {
    	cf.struct = Tab.charType;
    }
    
    public void visit(BoolFactor bf) {
    	bf.struct = boolType;
    }
	public boolean passed() {
    	return !errorDetected;
    }
	
	

}
