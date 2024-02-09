// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class VarsNotEmpty extends Vars {

    private Vars Vars;
    private String varsName;
    private SquareBracesVars SquareBracesVars;

    public VarsNotEmpty (Vars Vars, String varsName, SquareBracesVars SquareBracesVars) {
        this.Vars=Vars;
        if(Vars!=null) Vars.setParent(this);
        this.varsName=varsName;
        this.SquareBracesVars=SquareBracesVars;
        if(SquareBracesVars!=null) SquareBracesVars.setParent(this);
    }

    public Vars getVars() {
        return Vars;
    }

    public void setVars(Vars Vars) {
        this.Vars=Vars;
    }

    public String getVarsName() {
        return varsName;
    }

    public void setVarsName(String varsName) {
        this.varsName=varsName;
    }

    public SquareBracesVars getSquareBracesVars() {
        return SquareBracesVars;
    }

    public void setSquareBracesVars(SquareBracesVars SquareBracesVars) {
        this.SquareBracesVars=SquareBracesVars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Vars!=null) Vars.accept(visitor);
        if(SquareBracesVars!=null) SquareBracesVars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Vars!=null) Vars.traverseTopDown(visitor);
        if(SquareBracesVars!=null) SquareBracesVars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Vars!=null) Vars.traverseBottomUp(visitor);
        if(SquareBracesVars!=null) SquareBracesVars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarsNotEmpty(\n");

        if(Vars!=null)
            buffer.append(Vars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varsName);
        buffer.append("\n");

        if(SquareBracesVars!=null)
            buffer.append(SquareBracesVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarsNotEmpty]");
        return buffer.toString();
    }
}
