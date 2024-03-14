// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class AddConstDecls extends AddConstList {

    private AddConstList AddConstList;
    private AddConst AddConst;

    public AddConstDecls (AddConstList AddConstList, AddConst AddConst) {
        this.AddConstList=AddConstList;
        if(AddConstList!=null) AddConstList.setParent(this);
        this.AddConst=AddConst;
        if(AddConst!=null) AddConst.setParent(this);
    }

    public AddConstList getAddConstList() {
        return AddConstList;
    }

    public void setAddConstList(AddConstList AddConstList) {
        this.AddConstList=AddConstList;
    }

    public AddConst getAddConst() {
        return AddConst;
    }

    public void setAddConst(AddConst AddConst) {
        this.AddConst=AddConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddConstList!=null) AddConstList.accept(visitor);
        if(AddConst!=null) AddConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddConstList!=null) AddConstList.traverseTopDown(visitor);
        if(AddConst!=null) AddConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddConstList!=null) AddConstList.traverseBottomUp(visitor);
        if(AddConst!=null) AddConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddConstDecls(\n");

        if(AddConstList!=null)
            buffer.append(AddConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddConst!=null)
            buffer.append(AddConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddConstDecls]");
        return buffer.toString();
    }
}
