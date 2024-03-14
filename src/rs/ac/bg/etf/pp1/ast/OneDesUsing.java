// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class OneDesUsing extends DesUsing {

    private String desNamespace;
    private String desName;

    public OneDesUsing (String desNamespace, String desName) {
        this.desNamespace=desNamespace;
        this.desName=desName;
    }

    public String getDesNamespace() {
        return desNamespace;
    }

    public void setDesNamespace(String desNamespace) {
        this.desNamespace=desNamespace;
    }

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName=desName;
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
        buffer.append("OneDesUsing(\n");

        buffer.append(" "+tab+desNamespace);
        buffer.append("\n");

        buffer.append(" "+tab+desName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneDesUsing]");
        return buffer.toString();
    }
}
