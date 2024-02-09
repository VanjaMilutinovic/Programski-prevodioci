// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class OptionalAccessParam extends OptionalAccess {

    private OptionalAccess OptionalAccess;
    private String I2;

    public OptionalAccessParam (OptionalAccess OptionalAccess, String I2) {
        this.OptionalAccess=OptionalAccess;
        if(OptionalAccess!=null) OptionalAccess.setParent(this);
        this.I2=I2;
    }

    public OptionalAccess getOptionalAccess() {
        return OptionalAccess;
    }

    public void setOptionalAccess(OptionalAccess OptionalAccess) {
        this.OptionalAccess=OptionalAccess;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalAccess!=null) OptionalAccess.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalAccess!=null) OptionalAccess.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalAccess!=null) OptionalAccess.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalAccessParam(\n");

        if(OptionalAccess!=null)
            buffer.append(OptionalAccess.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalAccessParam]");
        return buffer.toString();
    }
}
