// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstType ConstType;
    private AddConst AddConst;
    private AddConstList AddConstList;

    public ConstDecl (ConstType ConstType, AddConst AddConst, AddConstList AddConstList) {
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
        this.AddConst=AddConst;
        if(AddConst!=null) AddConst.setParent(this);
        this.AddConstList=AddConstList;
        if(AddConstList!=null) AddConstList.setParent(this);
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public AddConst getAddConst() {
        return AddConst;
    }

    public void setAddConst(AddConst AddConst) {
        this.AddConst=AddConst;
    }

    public AddConstList getAddConstList() {
        return AddConstList;
    }

    public void setAddConstList(AddConstList AddConstList) {
        this.AddConstList=AddConstList;
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
        if(ConstType!=null) ConstType.accept(visitor);
        if(AddConst!=null) AddConst.accept(visitor);
        if(AddConstList!=null) AddConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
        if(AddConst!=null) AddConst.traverseTopDown(visitor);
        if(AddConstList!=null) AddConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        if(AddConst!=null) AddConst.traverseBottomUp(visitor);
        if(AddConstList!=null) AddConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddConst!=null)
            buffer.append(AddConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddConstList!=null)
            buffer.append(AddConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
