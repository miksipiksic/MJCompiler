// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class AddConst implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstValueType ConstValueType;

    public AddConst (ConstValueType ConstValueType) {
        this.ConstValueType=ConstValueType;
        if(ConstValueType!=null) ConstValueType.setParent(this);
    }

    public ConstValueType getConstValueType() {
        return ConstValueType;
    }

    public void setConstValueType(ConstValueType ConstValueType) {
        this.ConstValueType=ConstValueType;
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
        if(ConstValueType!=null) ConstValueType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstValueType!=null) ConstValueType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstValueType!=null) ConstValueType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddConst(\n");

        if(ConstValueType!=null)
            buffer.append(ConstValueType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddConst]");
        return buffer.toString();
    }
}
