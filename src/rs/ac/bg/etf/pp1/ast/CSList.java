// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class CSList extends Lists {

    private Lists Lists;
    private ClassDecl ClassDecl;

    public CSList (Lists Lists, ClassDecl ClassDecl) {
        this.Lists=Lists;
        if(Lists!=null) Lists.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public Lists getLists() {
        return Lists;
    }

    public void setLists(Lists Lists) {
        this.Lists=Lists;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Lists!=null) Lists.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Lists!=null) Lists.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Lists!=null) Lists.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CSList(\n");

        if(Lists!=null)
            buffer.append(Lists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CSList]");
        return buffer.toString();
    }
}
