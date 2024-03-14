// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class DsgDot extends Designator {

    private DesUsing DesUsing;
    private String classField;

    public DsgDot (DesUsing DesUsing, String classField) {
        this.DesUsing=DesUsing;
        if(DesUsing!=null) DesUsing.setParent(this);
        this.classField=classField;
    }

    public DesUsing getDesUsing() {
        return DesUsing;
    }

    public void setDesUsing(DesUsing DesUsing) {
        this.DesUsing=DesUsing;
    }

    public String getClassField() {
        return classField;
    }

    public void setClassField(String classField) {
        this.classField=classField;
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
        buffer.append("DsgDot(\n");

        if(DesUsing!=null)
            buffer.append(DesUsing.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+classField);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DsgDot]");
        return buffer.toString();
    }
}
