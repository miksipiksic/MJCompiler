// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class SimplePrintStmt extends Statement {

    private BExprB BExprB;

    public SimplePrintStmt (BExprB BExprB) {
        this.BExprB=BExprB;
        if(BExprB!=null) BExprB.setParent(this);
    }

    public BExprB getBExprB() {
        return BExprB;
    }

    public void setBExprB(BExprB BExprB) {
        this.BExprB=BExprB;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(BExprB!=null) BExprB.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BExprB!=null) BExprB.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BExprB!=null) BExprB.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimplePrintStmt(\n");

        if(BExprB!=null)
            buffer.append(BExprB.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimplePrintStmt]");
        return buffer.toString();
    }
}
