// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class SingleTerm implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private UnaryMinus UnaryMinus;
    private Term Term;

    public SingleTerm (UnaryMinus UnaryMinus, Term Term) {
        this.UnaryMinus=UnaryMinus;
        if(UnaryMinus!=null) UnaryMinus.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public UnaryMinus getUnaryMinus() {
        return UnaryMinus;
    }

    public void setUnaryMinus(UnaryMinus UnaryMinus) {
        this.UnaryMinus=UnaryMinus;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
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
        if(UnaryMinus!=null) UnaryMinus.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(UnaryMinus!=null) UnaryMinus.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(UnaryMinus!=null) UnaryMinus.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleTerm(\n");

        if(UnaryMinus!=null)
            buffer.append(UnaryMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleTerm]");
        return buffer.toString();
    }
}
