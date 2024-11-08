// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class TypeDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String typeName;

    public TypeDecl (String typeName) {
        this.typeName=typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName=typeName;
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeDecl(\n");

        buffer.append(" "+tab+typeName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeDecl]");
        return buffer.toString();
    }
}
