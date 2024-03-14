// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class NamespaceDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private EntryNamespace EntryNamespace;
    private Lists Lists;
    private MethodDeclList MethodDeclList;
    private EndOfNamespace EndOfNamespace;

    public NamespaceDecl (EntryNamespace EntryNamespace, Lists Lists, MethodDeclList MethodDeclList, EndOfNamespace EndOfNamespace) {
        this.EntryNamespace=EntryNamespace;
        if(EntryNamespace!=null) EntryNamespace.setParent(this);
        this.Lists=Lists;
        if(Lists!=null) Lists.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.EndOfNamespace=EndOfNamespace;
        if(EndOfNamespace!=null) EndOfNamespace.setParent(this);
    }

    public EntryNamespace getEntryNamespace() {
        return EntryNamespace;
    }

    public void setEntryNamespace(EntryNamespace EntryNamespace) {
        this.EntryNamespace=EntryNamespace;
    }

    public Lists getLists() {
        return Lists;
    }

    public void setLists(Lists Lists) {
        this.Lists=Lists;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public EndOfNamespace getEndOfNamespace() {
        return EndOfNamespace;
    }

    public void setEndOfNamespace(EndOfNamespace EndOfNamespace) {
        this.EndOfNamespace=EndOfNamespace;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EntryNamespace!=null) EntryNamespace.accept(visitor);
        if(Lists!=null) Lists.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
        if(EndOfNamespace!=null) EndOfNamespace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EntryNamespace!=null) EntryNamespace.traverseTopDown(visitor);
        if(Lists!=null) Lists.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(EndOfNamespace!=null) EndOfNamespace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EntryNamespace!=null) EntryNamespace.traverseBottomUp(visitor);
        if(Lists!=null) Lists.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(EndOfNamespace!=null) EndOfNamespace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NamespaceDecl(\n");

        if(EntryNamespace!=null)
            buffer.append(EntryNamespace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Lists!=null)
            buffer.append(Lists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndOfNamespace!=null)
            buffer.append(EndOfNamespace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NamespaceDecl]");
        return buffer.toString();
    }
}
