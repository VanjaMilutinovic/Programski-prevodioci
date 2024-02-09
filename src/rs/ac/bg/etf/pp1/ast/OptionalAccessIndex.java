// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class OptionalAccessIndex extends OptionalAccess {

    private OptionalAccess OptionalAccess;
    private Expr Expr;

    public OptionalAccessIndex (OptionalAccess OptionalAccess, Expr Expr) {
        this.OptionalAccess=OptionalAccess;
        if(OptionalAccess!=null) OptionalAccess.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public OptionalAccess getOptionalAccess() {
        return OptionalAccess;
    }

    public void setOptionalAccess(OptionalAccess OptionalAccess) {
        this.OptionalAccess=OptionalAccess;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalAccess!=null) OptionalAccess.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalAccess!=null) OptionalAccess.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalAccess!=null) OptionalAccess.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalAccessIndex(\n");

        if(OptionalAccess!=null)
            buffer.append(OptionalAccess.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalAccessIndex]");
        return buffer.toString();
    }
}
