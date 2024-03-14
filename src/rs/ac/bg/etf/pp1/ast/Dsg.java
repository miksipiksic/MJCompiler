// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class Dsg extends Designator {

    private DesUsing DesUsing;

    public Dsg (DesUsing DesUsing) {
        this.DesUsing=DesUsing;
        if(DesUsing!=null) DesUsing.setParent(this);
    }

    public DesUsing getDesUsing() {
        return DesUsing;
    }

    public void setDesUsing(DesUsing DesUsing) {
        this.DesUsing=DesUsing;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesUsing!=null) DesUsing.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesUsing!=null) DesUsing.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesUsing!=null) DesUsing.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Dsg(\n");

        if(DesUsing!=null)
            buffer.append(DesUsing.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Dsg]");
        return buffer.toString();
    }
}
