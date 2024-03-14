// generated with ast extension for cup
// version 0.8
// 16/0/2024 23:24:55


package rs.ac.bg.etf.pp1.ast;

public class DesFunctionFactor extends Factor {

    private DesignatorFun DesignatorFun;
    private ActPars ActPars;

    public DesFunctionFactor (DesignatorFun DesignatorFun, ActPars ActPars) {
        this.DesignatorFun=DesignatorFun;
        if(DesignatorFun!=null) DesignatorFun.setParent(this);
        this.ActPars=ActPars;
        if(ActPars!=null) ActPars.setParent(this);
    }

    public DesignatorFun getDesignatorFun() {
        return DesignatorFun;
    }

    public void setDesignatorFun(DesignatorFun DesignatorFun) {
        this.DesignatorFun=DesignatorFun;
    }

    public ActPars getActPars() {
        return ActPars;
    }

    public void setActPars(ActPars ActPars) {
        this.ActPars=ActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorFun!=null) DesignatorFun.accept(visitor);
        if(ActPars!=null) ActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorFun!=null) DesignatorFun.traverseTopDown(visitor);
        if(ActPars!=null) ActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorFun!=null) DesignatorFun.traverseBottomUp(visitor);
        if(ActPars!=null) ActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesFunctionFactor(\n");

        if(DesignatorFun!=null)
            buffer.append(DesignatorFun.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPars!=null)
            buffer.append(ActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesFunctionFactor]");
        return buffer.toString();
    }
}
