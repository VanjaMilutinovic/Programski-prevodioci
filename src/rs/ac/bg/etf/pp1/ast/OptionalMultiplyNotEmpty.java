// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class OptionalMultiplyNotEmpty extends OptionalMultiply {

    private OptionalMultiply OptionalMultiply;
    private MultiplyOperand MultiplyOperand;
    private Factor Factor;

    public OptionalMultiplyNotEmpty (OptionalMultiply OptionalMultiply, MultiplyOperand MultiplyOperand, Factor Factor) {
        this.OptionalMultiply=OptionalMultiply;
        if(OptionalMultiply!=null) OptionalMultiply.setParent(this);
        this.MultiplyOperand=MultiplyOperand;
        if(MultiplyOperand!=null) MultiplyOperand.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public OptionalMultiply getOptionalMultiply() {
        return OptionalMultiply;
    }

    public void setOptionalMultiply(OptionalMultiply OptionalMultiply) {
        this.OptionalMultiply=OptionalMultiply;
    }

    public MultiplyOperand getMultiplyOperand() {
        return MultiplyOperand;
    }

    public void setMultiplyOperand(MultiplyOperand MultiplyOperand) {
        this.MultiplyOperand=MultiplyOperand;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalMultiply!=null) OptionalMultiply.accept(visitor);
        if(MultiplyOperand!=null) MultiplyOperand.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalMultiply!=null) OptionalMultiply.traverseTopDown(visitor);
        if(MultiplyOperand!=null) MultiplyOperand.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalMultiply!=null) OptionalMultiply.traverseBottomUp(visitor);
        if(MultiplyOperand!=null) MultiplyOperand.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalMultiplyNotEmpty(\n");

        if(OptionalMultiply!=null)
            buffer.append(OptionalMultiply.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultiplyOperand!=null)
            buffer.append(MultiplyOperand.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalMultiplyNotEmpty]");
        return buffer.toString();
    }
}
