// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class AddFormals extends AddFormalList {

    private AddFormalList AddFormalList;
    private AddFormal AddFormal;

    public AddFormals (AddFormalList AddFormalList, AddFormal AddFormal) {
        this.AddFormalList=AddFormalList;
        if(AddFormalList!=null) AddFormalList.setParent(this);
        this.AddFormal=AddFormal;
        if(AddFormal!=null) AddFormal.setParent(this);
    }

    public AddFormalList getAddFormalList() {
        return AddFormalList;
    }

    public void setAddFormalList(AddFormalList AddFormalList) {
        this.AddFormalList=AddFormalList;
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
        if(AddFormalList!=null) AddFormalList.accept(visitor);
        if(AddFormal!=null) AddFormal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddFormalList!=null) AddFormalList.traverseTopDown(visitor);
        if(AddFormal!=null) AddFormal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddFormalList!=null) AddFormalList.traverseBottomUp(visitor);
        if(AddFormal!=null) AddFormal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddFormals(\n");

        if(AddFormalList!=null)
            buffer.append(AddFormalList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddFormal!=null)
            buffer.append(AddFormal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddFormals]");
        return buffer.toString();
    }
}
