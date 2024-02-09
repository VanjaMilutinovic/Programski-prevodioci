// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:59


package rs.ac.bg.etf.pp1.ast;

public class TermAdditionNotEmpty extends TermAddition {

    private TermAddition TermAddition;
    private AddOperand AddOperand;
    private Term Term;

    public TermAdditionNotEmpty (TermAddition TermAddition, AddOperand AddOperand, Term Term) {
        this.TermAddition=TermAddition;
        if(TermAddition!=null) TermAddition.setParent(this);
        this.AddOperand=AddOperand;
        if(AddOperand!=null) AddOperand.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public TermAddition getTermAddition() {
        return TermAddition;
    }

    public void setTermAddition(TermAddition TermAddition) {
        this.TermAddition=TermAddition;
    }

    public AddOperand getAddOperand() {
        return AddOperand;
    }

    public void setAddOperand(AddOperand AddOperand) {
        this.AddOperand=AddOperand;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermAddition!=null) TermAddition.accept(visitor);
        if(AddOperand!=null) AddOperand.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermAddition!=null) TermAddition.traverseTopDown(visitor);
        if(AddOperand!=null) AddOperand.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermAddition!=null) TermAddition.traverseBottomUp(visitor);
        if(AddOperand!=null) AddOperand.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermAdditionNotEmpty(\n");

        if(TermAddition!=null)
            buffer.append(TermAddition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddOperand!=null)
            buffer.append(AddOperand.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermAdditionNotEmpty]");
        return buffer.toString();
    }
}
