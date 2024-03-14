// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class Expr implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private SingleTerm SingleTerm;
    private AddTerms AddTerms;

    public Expr (SingleTerm SingleTerm, AddTerms AddTerms) {
        this.SingleTerm=SingleTerm;
        if(SingleTerm!=null) SingleTerm.setParent(this);
        this.AddTerms=AddTerms;
        if(AddTerms!=null) AddTerms.setParent(this);
    }

    public SingleTerm getSingleTerm() {
        return SingleTerm;
    }

    public void setSingleTerm(SingleTerm SingleTerm) {
        this.SingleTerm=SingleTerm;
    }

    public AddTerms getAddTerms() {
        return AddTerms;
    }

    public void setAddTerms(AddTerms AddTerms) {
        this.AddTerms=AddTerms;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleTerm!=null) SingleTerm.accept(visitor);
        if(AddTerms!=null) AddTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleTerm!=null) SingleTerm.traverseTopDown(visitor);
        if(AddTerms!=null) AddTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleTerm!=null) SingleTerm.traverseBottomUp(visitor);
        if(AddTerms!=null) AddTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr(\n");

        if(SingleTerm!=null)
            buffer.append(SingleTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddTerms!=null)
            buffer.append(AddTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr]");
        return buffer.toString();
    }
}
