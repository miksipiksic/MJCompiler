// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class AddVarDecl extends AddVar {

    private String varName;
    private SqBrackets SqBrackets;

    public AddVarDecl (String varName, SqBrackets SqBrackets) {
        this.varName=varName;
        this.SqBrackets=SqBrackets;
        if(SqBrackets!=null) SqBrackets.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public SqBrackets getSqBrackets() {
        return SqBrackets;
    }

    public void setSqBrackets(SqBrackets SqBrackets) {
        this.SqBrackets=SqBrackets;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SqBrackets!=null) SqBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SqBrackets!=null) SqBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SqBrackets!=null) SqBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddVarDecl(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SqBrackets!=null)
            buffer.append(SqBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddVarDecl]");
        return buffer.toString();
    }
}
