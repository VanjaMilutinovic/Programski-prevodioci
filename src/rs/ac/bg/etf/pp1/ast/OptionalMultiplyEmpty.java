// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class OptionalMultiplyEmpty extends OptionalMultiply {

    public OptionalMultiplyEmpty () {
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
        buffer.append("OptionalMultiplyEmpty(\n");

        buffer.append(tab);
        buffer.append(") [OptionalMultiplyEmpty]");
        return buffer.toString();
    }
}
