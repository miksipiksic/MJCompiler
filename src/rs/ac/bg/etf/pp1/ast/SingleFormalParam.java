// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class SingleFormalParam extends FormalParamList {

    private AddFormal AddFormal;

    public SingleFormalParam (AddFormal AddFormal) {
        this.AddFormal=AddFormal;
        if(AddFormal!=null) AddFormal.setParent(this);
    }

    public AddFormal getAddFormal() {
        return AddFormal;
    }

    public void setAddFormal(AddFormal AddFormal) {
        this.AddFormal=AddFormal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddFormal!=null) AddFormal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddFormal!=null) AddFormal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddFormal!=null) AddFormal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFormalParam(\n");

        if(AddFormal!=null)
            buffer.append(AddFormal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFormalParam]");
        return buffer.toString();
    }
}
