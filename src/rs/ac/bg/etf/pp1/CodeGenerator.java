package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.tab.ExtendedTab;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private int varCount;
	
	private int paramCnt;
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	Logger log = Logger.getLogger(SemanticPass.class);
	@Override
	public void visit(MainLabel MethodTypeName) {
		mainPc = Code.pc;
		
		MethodTypeName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.
		SyntaxNode methodNode = MethodTypeName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		log.info("VarCount: " + varCnt.getCount());
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(0);
		Code.put(varCnt.getCount());
	}
	
	@Override
	public void visit(VarDecl VarDecl) {
		varCount++;
	}

	@Override
	public void visit(MainBody ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(StatementPrint StatementPrint) {
		if(StatementPrint.getExpr().struct.equals(Tab.intType)) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	public void visit(StatementRead read) {
		Code.put(Code.read);
		Code.store(read.getDesignator().obj);
	}

	
 	public void visit(FactorDesignator factor) {
 		Code.load(factor.getDesignator().obj);
 	}
 	public void visit(FactorNum factor) {
 		Code.loadConst(factor.getN1());
 	}
 	/*	@Override
	public void visit(Const Const) {
		Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getN1(), 0));
	}*/
 	public void visit(FactorChar factor) {
 		// Ako bude problema sa Char pogledaj  gore
 		Code.loadConst(factor.getC1());
 	}
 	public void visit(FactorBool factor) {
 		int b = factor.getB1()?1:0;
 		Code.loadConst(b);
 	} 	
 	public void visit(FactorNewArray factor) {
 		if (factor.getType().struct.equals(Tab.intType)) {
 			Code.put(Code.newarray);
 			Code.put(1);
 		}
 		else {
 			Code.put(Code.newarray);
 			Code.put(0);
 		}
 			
 	}

 	
 	public void visit(DesigantorStatementsIncrement inc) {
 		 // Designator je vec na steku
 		Code.put(Code.const_1);
 		Code.put(Code.add);
 		Code.store(inc.getDesignator().obj);
 		
 	}
 	public void visit(DesigantorStatementsDecrement dec) {
 		// Designator je vec na steku
 		Code.put(Code.const_m1);
 		Code.put(Code.add);
 		Code.store(dec.getDesignator().obj);

 	}
 	

 	public void visit(OptionalMinusNotEmpty neg) {
 		Code.put(Code.neg);
 	}

 	public void visit(OptionalMultiplyNotEmpty mul) {
 		if (mul.getMultiplyOperand() instanceof MultiplyOperandMulptipy)
 			Code.put(Code.mul);
 		else if (mul.getMultiplyOperand() instanceof MultiplyOperandDivide)
 			Code.put(Code.div);
 		else
 			Code.put(Code.rem);
 	}
 	public void visit(TermAdditionNotEmpty plus) {
 		if (plus.getAddOperand() instanceof  AddOperandPlus)
 			Code.put(Code.add);
 		else
 			Code.put(Code.sub);
 	}

 	
 	public void visit(DesignatorStatementAssign assign) {
 		//Expr je na steku
 		Code.store(assign.getDesignator().obj);
 	}
 	
 	public void desIndex(Obj des) {
 			Code.load(new Obj(Obj.Var, des.getName(), des.getType(), 
 	 	 								des.getAdr(), des.getLevel()));
 			Code.put(Code.dup_x1);
 			Code.put(Code.pop); 			
 	}
 	public void visit(DesignatorIdent des) {
 		if (des.obj.getKind()==Obj.Elem) {		
 			desIndex(des.obj);		
 		}
 		else {
 			if (des.getParent() instanceof DesigantorStatementsDecrement || des.getParent() instanceof DesigantorStatementsIncrement) {
 	 			Code.load(des.obj);
 	 		}
 			
 		}
 	}
 	public void visit(DesignatorNoIdent des) {
 		if (des.obj.getKind()==Obj.Elem) {		
 			desIndex(des.obj);		
 		}
 		else {
 			if (des.getParent() instanceof DesigantorStatementsDecrement || des.getParent() instanceof DesigantorStatementsIncrement) {
 	 			Code.load(des.obj);
 	 		}
 			
 		}
 	}

}
