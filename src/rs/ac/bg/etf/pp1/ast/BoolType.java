// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class BoolType extends ConstValueType {

    private String constName;
    private Boolean boolConstValue;

    public BoolType (String constName, Boolean boolConstValue) {
        this.constName=constName;
        this.boolConstValue=boolConstValue;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Boolean getBoolConstValue() {
        return boolConstValue;
    }

    public void setBoolConstValue(Boolean boolConstValue) {
        this.boolConstValue=boolConstValue;
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
        buffer.append("BoolType(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+boolConstValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolType]");
        return buffer.toString();
    }
}
