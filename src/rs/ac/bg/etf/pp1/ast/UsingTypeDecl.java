// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class UsingTypeDecl extends Type {

    private String namespaceName;
    private TypeDecl TypeDecl;

    public UsingTypeDecl (String namespaceName, TypeDecl TypeDecl) {
        this.namespaceName=namespaceName;
        this.TypeDecl=TypeDecl;
        if(TypeDecl!=null) TypeDecl.setParent(this);
    }

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName=namespaceName;
    }

    public TypeDecl getTypeDecl() {
        return TypeDecl;
    }

    public void setTypeDecl(TypeDecl TypeDecl) {
        this.TypeDecl=TypeDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeDecl!=null) TypeDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeDecl!=null) TypeDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeDecl!=null) TypeDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UsingTypeDecl(\n");

        buffer.append(" "+tab+namespaceName);
        buffer.append("\n");

        if(TypeDecl!=null)
            buffer.append(TypeDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UsingTypeDecl]");
        return buffer.toString();
    }
}
