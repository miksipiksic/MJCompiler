// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class NoAddVarDecls extends AddVarList {

    private EndVar EndVar;

    public NoAddVarDecls (EndVar EndVar) {
        this.EndVar=EndVar;
        if(EndVar!=null) EndVar.setParent(this);
    }

    public EndVar getEndVar() {
        return EndVar;
    }

    public void setEndVar(EndVar EndVar) {
        this.EndVar=EndVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EndVar!=null) EndVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EndVar!=null) EndVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EndVar!=null) EndVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoAddVarDecls(\n");

        if(EndVar!=null)
            buffer.append(EndVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoAddVarDecls]");
        return buffer.toString();
    }
}
