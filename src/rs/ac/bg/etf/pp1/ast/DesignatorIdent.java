// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIdent extends Designator {

    private String namespaceName;
    private String designatorName;
    private OptionalAccess OptionalAccess;

    public DesignatorIdent (String namespaceName, String designatorName, OptionalAccess OptionalAccess) {
        this.namespaceName=namespaceName;
        this.designatorName=designatorName;
        this.OptionalAccess=OptionalAccess;
        if(OptionalAccess!=null) OptionalAccess.setParent(this);
    }

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName=namespaceName;
    }

    public String getDesignatorName() {
        return designatorName;
    }

    public void setDesignatorName(String designatorName) {
        this.designatorName=designatorName;
    }

    public OptionalAccess getOptionalAccess() {
        return OptionalAccess;
    }

    public void setOptionalAccess(OptionalAccess OptionalAccess) {
        this.OptionalAccess=OptionalAccess;
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
        buffer.append("DesignatorIdent(\n");

        buffer.append(" "+tab+namespaceName);
        buffer.append("\n");

        buffer.append(" "+tab+designatorName);
        buffer.append("\n");

        if(OptionalAccess!=null)
            buffer.append(OptionalAccess.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIdent]");
        return buffer.toString();
    }
}
