// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class NumberConst extends Const {

    private Integer constNumberValue;

    public NumberConst (Integer constNumberValue) {
        this.constNumberValue=constNumberValue;
    }

    public Integer getConstNumberValue() {
        return constNumberValue;
    }

    public void setConstNumberValue(Integer constNumberValue) {
        this.constNumberValue=constNumberValue;
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
        buffer.append("NumberConst(\n");

        buffer.append(" "+tab+constNumberValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberConst]");
        return buffer.toString();
    }
}
