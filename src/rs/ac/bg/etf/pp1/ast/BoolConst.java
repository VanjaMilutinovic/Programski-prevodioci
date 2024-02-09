// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class BoolConst extends Const {

    private Boolean constBoolValue;

    public BoolConst (Boolean constBoolValue) {
        this.constBoolValue=constBoolValue;
    }

    public Boolean getConstBoolValue() {
        return constBoolValue;
    }

    public void setConstBoolValue(Boolean constBoolValue) {
        this.constBoolValue=constBoolValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConst(\n");

        buffer.append(" "+tab+constBoolValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst]");
        return buffer.toString();
    }
}
