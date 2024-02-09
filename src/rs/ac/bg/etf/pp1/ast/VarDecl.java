// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private VarType VarType;
    private String VarName;
    private SquareBraces SquareBraces;
    private Vars Vars;

    public VarDecl (VarType VarType, String VarName, SquareBraces SquareBraces, Vars Vars) {
        this.VarType=VarType;
        if(VarType!=null) VarType.setParent(this);
        this.VarName=VarName;
        this.SquareBraces=SquareBraces;
        if(SquareBraces!=null) SquareBraces.setParent(this);
        this.Vars=Vars;
        if(Vars!=null) Vars.setParent(this);
    }

    public VarType getVarType() {
        return VarType;
    }

    public void setVarType(VarType VarType) {
        this.VarType=VarType;
    }

    public String getVarName() {
        return VarName;
    }

    public void setVarName(String VarName) {
        this.VarName=VarName;
    }

    public SquareBraces getSquareBraces() {
        return SquareBraces;
    }

    public void setSquareBraces(SquareBraces SquareBraces) {
        this.SquareBraces=SquareBraces;
    }

    public Vars getVars() {
        return Vars;
    }

    public void setVars(Vars Vars) {
        this.Vars=Vars;
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
        if(VarType!=null) VarType.accept(visitor);
        if(SquareBraces!=null) SquareBraces.accept(visitor);
        if(Vars!=null) Vars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarType!=null) VarType.traverseTopDown(visitor);
        if(SquareBraces!=null) SquareBraces.traverseTopDown(visitor);
        if(Vars!=null) Vars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarType!=null) VarType.traverseBottomUp(visitor);
        if(SquareBraces!=null) SquareBraces.traverseBottomUp(visitor);
        if(Vars!=null) Vars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(VarType!=null)
            buffer.append(VarType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+VarName);
        buffer.append("\n");

        if(SquareBraces!=null)
            buffer.append(SquareBraces.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Vars!=null)
            buffer.append(Vars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
