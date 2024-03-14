package rs.ac.bg.etf.pp1;

import rs.etf.pp1.mj.runtime.Code;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.Add;
import rs.ac.bg.etf.pp1.ast.AddTerm;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.DStmt;
import rs.ac.bg.etf.pp1.ast.DesFactor;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorDecrement;
import rs.ac.bg.etf.pp1.ast.DesignatorFun;
import rs.ac.bg.etf.pp1.ast.DesignatorIncrement;
import rs.ac.bg.etf.pp1.ast.Divide;
import rs.ac.bg.etf.pp1.ast.Dsg;
import rs.ac.bg.etf.pp1.ast.DsqB;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulFactors;
import rs.ac.bg.etf.pp1.ast.Multiply;
import rs.ac.bg.etf.pp1.ast.NewFactorArray;
import rs.ac.bg.etf.pp1.ast.NoDesUsing;
import rs.ac.bg.etf.pp1.ast.NoUnaryMinus;
import rs.ac.bg.etf.pp1.ast.NumFactor;
import rs.ac.bg.etf.pp1.ast.OneDesUsing;
import rs.ac.bg.etf.pp1.ast.OneUnaryMinus;
import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.ReadStmt;
import rs.ac.bg.etf.pp1.ast.ReturnStmt;
import rs.ac.bg.etf.pp1.ast.SimplePrintStmt;
import rs.ac.bg.etf.pp1.ast.SingleTerm;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.UnaryMinus;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	
	public void visit(DesignatorAssign assign) {
		Code.store(assign.getDesignator().obj);
	}
	
	public void visit(DesignatorIncrement desInc) {
		if(desInc.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(desInc.getDesignator().obj);
		} else if (desInc.getDesignator().obj.getKind() == Obj.Elem) {
	//		Code.put(Code.dup2);
	//		Code.load(desInc.getDesignator().obj);
		}
		
		Code.loadConst(1);
		Code.put(Code.add);
		
		Code.store(desInc.getDesignator().obj);
			
	}
	
	public void visit(DesignatorDecrement desInc) {
		if(desInc.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(desInc.getDesignator().obj);
		} else if (desInc.getDesignator().obj.getKind() == Obj.Elem) {
			//element niza
			Code.put(Code.dup2);
			Code.load(desInc.getDesignator().obj);
		}
		
		Code.loadConst(1);
		Code.put(Code.sub);
		
		Code.store(desInc.getDesignator().obj);
			
	}
	
	public void visit(DStmt designatorStmt) {
		
	}
	
	public void visit(OneDesUsing dsg) {
		if (dsg.getParent() instanceof DsqB) {

			Code.load(dsg.obj);
		}
	}
	

	public void visit(NoDesUsing dsg) {
		if (dsg.getParent() instanceof DsqB) {

		Code.load(dsg.obj);
	}
		if (dsg.getParent() instanceof ReadStmt) {
			Code.load(dsg.obj);
		}
	}
	
	public void visit(DsqB dsgArr) {
	}
	
	public void visit(ReadStmt readStmt) {
		Code.put(Code.read);
		Code.store(readStmt.getDesignator().obj);
		Code.load(readStmt.getDesignator().obj);
		
	}
	
	public void visit(ReturnStmt rs) {
		Code.put(Code.return_);
	}
	
	
	
	public void visit(SimplePrintStmt printStmt) {
		// format koji se ocekuje na steku
		// vrednost i sirina
		
		//print ima expr u sebi
		// ako ima semanticku gresku, uopste nece ni doci do ovoga
		if (printStmt.getBExprB().getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if (printStmt.getBExprB().getExpr().struct == SemanticAnalyzator.boolType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1); // 1 za sirinu ako je char
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintStmt printStmt) {
		
		Code.loadConst(printStmt.getPrintNumber());
		
		if (printStmt.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if (printStmt.getExpr().struct == SemanticAnalyzator.boolType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1); // 1 za sirinu ako je char
			Code.put(Code.bprint);
		}
	}
	

	boolean unaryMinus = false;
	public void visit(OneUnaryMinus um) {
		unaryMinus = true;
	}
	public void visit(NoUnaryMinus um) {
		unaryMinus = false;
	}
	
	public void visit(SingleTerm st) {
		if (unaryMinus) {
			Code.put(Code.neg);
		}
	}
	
	public void visit(AddTerm at) {
		if (at.getAddop() instanceof Add) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(MulFactors mf) {
		if (mf.getMulop() instanceof Multiply) {
			Code.put(Code.mul);
		} else if (mf.getMulop() instanceof Divide) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}
	
	// factor smena - number - interger - const
	// u adr polje se pise vr konstante
	
	public void visit(NumFactor numFactorConst) {
		Obj con = Tab.insert(Obj.Con, "$", numFactorConst.struct);
		con.setLevel(0);
		con.setAdr(numFactorConst.getNumFactor());
		
		Code.load(con);
	}
	
	public void visit(CharFactor charFactor) {
		Obj ch = Tab.insert(Obj.Con, "$", charFactor.struct);
		ch.setLevel(0);
		ch.setAdr(charFactor.getCharFactor());
		
		Code.load(ch);
	}
	
	public void visit(BoolFactor boolF) {
		Obj b = Tab.insert(Obj.Con, "$",SemanticAnalyzator.boolType);
		b.setLevel(0);
		if (boolF.getBoolFactor() == true)
			b.setAdr(1);
		else b.setAdr(0);
		
		Code.load(b);
	}
	
	public void visit (DesFactor df) {
		Code.load(df.getDesignator().obj);
	}
	
	public void visit (NewFactorArray nfa) {
		Code.put(Code.newarray);
		
		if (nfa.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1); // int/bool
		}
	}
	
	
}


