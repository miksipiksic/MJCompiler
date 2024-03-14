package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	protected int formalParamCount, count, actualParamCount;
	
	public int getFormalParamCount() {
		return formalParamCount;
	}
	
	public int getActualParamCount() {
		return actualParamCount;
	}
	
	
	// 2 nove klase
	// za brojanje formalnih parametara
	// i lokalnih param metode
	
	public static class FormParamCounter extends CounterVisitor {

		public void visit(AddFormal formParamDecl) {
			formalParamCount++;
		}
		
	}
	
	public static class ActualParamCounter extends CounterVisitor {
		
		public void visit(ActParDecls actParDecls) {
			actualParamCount = 1;
		}

		public void visit(Exprs actExprs ) {
			actualParamCount++;
		}
		
	}
	
	public static class VarCounter extends CounterVisitor {

		public void visit(AddVarDecl formParamDecl) {
			count++;
		}
		
		public void visit(EndVarDecl f) {
			count++;
		}
		
	}
}
