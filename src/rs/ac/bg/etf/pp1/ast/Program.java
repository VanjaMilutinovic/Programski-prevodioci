// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:10:58


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgName ProgName;
    private Namespaces Namespaces;
    private Declarations Declarations;
    private MainBody MainBody;

    public Program (ProgName ProgName, Namespaces Namespaces, Declarations Declarations, MainBody MainBody) {
        this.ProgName=ProgName;
        if(ProgName!=null) ProgName.setParent(this);
        this.Namespaces=Namespaces;
        if(Namespaces!=null) Namespaces.setParent(this);
        this.Declarations=Declarations;
        if(Declarations!=null) Declarations.setParent(this);
        this.MainBody=MainBody;
        if(MainBody!=null) MainBody.setParent(this);
    }

    public ProgName getProgName() {
        return ProgName;
    }

    public void setProgName(ProgName ProgName) {
        this.ProgName=ProgName;
    }

    public Namespaces getNamespaces() {
        return Namespaces;
    }

    public void setNamespaces(Namespaces Namespaces) {
        this.Namespaces=Namespaces;
    }

    public Declarations getDeclarations() {
        return Declarations;
    }

    public void setDeclarations(Declarations Declarations) {
        this.Declarations=Declarations;
    }

    public MainBody getMainBody() {
        return MainBody;
    }

    public void setMainBody(MainBody MainBody) {
        this.MainBody=MainBody;
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
        if(ProgName!=null) ProgName.accept(visitor);
        if(Namespaces!=null) Namespaces.accept(visitor);
        if(Declarations!=null) Declarations.accept(visitor);
        if(MainBody!=null) MainBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgName!=null) ProgName.traverseTopDown(visitor);
        if(Namespaces!=null) Namespaces.traverseTopDown(visitor);
        if(Declarations!=null) Declarations.traverseTopDown(visitor);
        if(MainBody!=null) MainBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgName!=null) ProgName.traverseBottomUp(visitor);
        if(Namespaces!=null) Namespaces.traverseBottomUp(visitor);
        if(Declarations!=null) Declarations.traverseBottomUp(visitor);
        if(MainBody!=null) MainBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgName!=null)
            buffer.append(ProgName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Namespaces!=null)
            buffer.append(Namespaces.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Declarations!=null)
            buffer.append(Declarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MainBody!=null)
            buffer.append(MainBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
