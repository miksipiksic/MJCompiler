// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class AddVarDecls extends AddVarList {

    private AddVar AddVar;
    private AddVarList AddVarList;

    public AddVarDecls (AddVar AddVar, AddVarList AddVarList) {
        this.AddVar=AddVar;
        if(AddVar!=null) AddVar.setParent(this);
        this.AddVarList=AddVarList;
        if(AddVarList!=null) AddVarList.setParent(this);
    }

    public AddVar getAddVar() {
        return AddVar;
    }

    public void setAddVar(AddVar AddVar) {
        this.AddVar=AddVar;
    }

    public AddVarList getAddVarList() {
        return AddVarList;
    }

    public void setAddVarList(AddVarList AddVarList) {
        this.AddVarList=AddVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddVar!=null) AddVar.accept(visitor);
        if(AddVarList!=null) AddVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddVar!=null) AddVar.traverseTopDown(visitor);
        if(AddVarList!=null) AddVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddVar!=null) AddVar.traverseBottomUp(visitor);
        if(AddVarList!=null) AddVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddVarDecls(\n");

        if(AddVar!=null)
            buffer.append(AddVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddVarList!=null)
            buffer.append(AddVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddVarDecls]");
        return buffer.toString();
    }
}
