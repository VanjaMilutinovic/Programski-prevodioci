package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.tab.ExtendedTab;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	Boolean errorDetected = false;
	Logger log = Logger.getLogger(SemanticAnalyzer.class);
	ArrayList<String> namespaceNameList = new ArrayList<>();
	Boolean enteredNamespace = false;
	String currentNamespace = "";
	Struct varsType = Tab.noType;
	Struct constsType = Tab.noType;
	int constValue = -1;
	Boolean isArray = false;
	Boolean isArray1 = false;
	int nVars;
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	private Obj findInScope(String name) {
		Obj resultObj = null;
		if (Tab.currentScope.getLocals() != null) {
			resultObj = Tab.currentScope.getLocals().searchKey(name);
		}
		return (resultObj != null) ? resultObj : Tab.noObj;
	}
	public boolean passed() {
		return !errorDetected;
	}
	
	/*Program::= (Program) PROG ProgName Namespaces  Declarations MainBody;
	  ProgName::= (ProgName) IDENT:progName;*/
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	
	public void visit(NamespaceName namespaceName) {
		enteredNamespace = true;
		String name = namespaceName.getNamespaceName();
		currentNamespace = name;
		if (namespaceNameList.contains(name)) {
			report_error("Namespace sa imenom "+name+" vec postoji", null);
		}
		else {
			namespaceNameList.add(name);
		}
	}
	public void visit(Namespace namespace) {
		enteredNamespace = false;
		currentNamespace = "";
	}
	
	
	public void visit(VarDecl varDecl) {
		if(enteredNamespace) {
			String fullName = currentNamespace + "::" + varDecl.getVarName();
			if(Tab.find(fullName) != Tab.noObj) {
				report_error("Promenljiva sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				if (isArray) {
					Tab.insert(Obj.Var, fullName, new Struct(Struct.Array, varsType));
				}
				else{
					Tab.insert(Obj.Var, fullName, varsType);
				}
			}
		}
		else {
			String fullName = varDecl.getVarName();
			if(findInScope(fullName) != Tab.noObj) {
				report_error("Promenljiva sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				if (isArray) {
					Tab.insert(Obj.Var, fullName, new Struct(Struct.Array, varsType));
				}
				else {
					Tab.insert(Obj.Var, fullName, varsType);
				}
			}
		}
		isArray = false;
	}

	public void visit(VarsNotEmpty varsDecl) {
		if(enteredNamespace) {
			String fullName = currentNamespace + "::" + varsDecl.getVarsName();
			if(Tab.find(fullName) != Tab.noObj) {
				report_error("Promenljiva sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				if (isArray1) {
					Tab.insert(Obj.Var, fullName, new Struct(Struct.Array, varsType));
				}
				else{
					Tab.insert(Obj.Var, fullName, varsType);
				}
			}
		}
		else {
			String fullName = varsDecl.getVarsName();
			if(findInScope(fullName) != Tab.noObj) {
				report_error("Promenljiva sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				if (isArray1) {
					Tab.insert(Obj.Var, fullName, new Struct(Struct.Array, varsType));
					
				}
				else {
					Tab.insert(Obj.Var, fullName, varsType);
				}
			}
		}
		isArray1=false;
	}
	
	public void visit(VarType varType) {
		varsType = varType.getType().struct;
	}
	public void visit(SquareVarsNotEmpty s) {
		isArray1=true;
		
	}
	
	public void visit(Square s) {
		isArray = true;
		
	}
	/*(ConstDecl) CONST ConstType IDENT:constName ASSIGMNET Const Consts SEMI;*/
	public void visit(ConstDecl constDecl) {
		String fullName;
		if(enteredNamespace) {
			fullName = currentNamespace + "::" + constDecl.getConstName();
			if(Tab.find(fullName) != Tab.noObj) {
				report_error("Konstanta sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				constDecl.obj = Tab.insert(Obj.Con, fullName, constsType);
				constDecl.obj.setAdr(constValue);
			}
		}
		else {
			fullName = constDecl.getConstName();	
			if(findInScope(fullName) != Tab.noObj) {
				report_error("Konstanta sa imenom "+fullName+" vec postoji", null);	
			}
			else {
				constDecl.obj = Tab.insert(Obj.Con, fullName, constsType);
				constDecl.obj.setAdr(constValue);
			}
		}

	}
	public void visit(ConstType constType) {
		constsType = constType.getType().struct;
	}
	public void visit(NumberConst numberConst) {
		constValue = numberConst.getConstNumberValue();
	}
	public void visit(CharConst charConst) {
		constValue = charConst.getConstCharValue();
	}
	public void visit(BoolConst boolConst) {
		constValue = boolConst.getConstBoolValue()?1:0;
	}
	
	
 	public void visit(TypeOne type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}  
	}
 	
 	
 	public void visit(MainLabel mainLabel) {
 		mainLabel.obj = Tab.insert(Obj.Meth, "main", Tab.noType);
		Tab.openScope();
 	}
 	public void visit(MainBody mainBody) {
 		Tab.chainLocalSymbols(mainBody.getMainLabel().obj);
		Tab.closeScope();
 	}

 	/*(DesignatorNoIdent) IDENT:designatorName OptionalAccess:opAccess
			|
	  (DesignatorIdent) IDENT:namespaceName COLON COLON IDENT:designatorName OptionalAccess;*/
 	public void visit(DesignatorNoIdent designator) {
 		designator.obj = Tab.find(designator.getDesignatorName());
 		// proveri da li posotoji ime
 		if(designator.obj == Tab.noObj) {
 			report_error("Ime "+designator.getDesignatorName()+" nije definisano.", null);
 			designator.obj = new Obj(Obj.Var, designator.getDesignatorName(),Tab.nullType);
 		}
 		else {
 		// proveri da li je niz
 	 		if (designator.obj.getType().getKind()==Struct.Array) {
 	 			if(designator.getOptionalAccess().struct.equals(Tab.intType)) {
	 	 			// kind = elem
	 	 	 		// designator <- novi obj tipa elem of array
 	 				designator.obj = 
 	 						new Obj(Obj.Elem, designator.getDesignatorName(), designator.obj.getType().getElemType(), 
 	 								designator.obj.getAdr(), designator.obj.getLevel());
 	 			}
 	 			else if (designator.getOptionalAccess().struct.equals(Tab.noType)) {
 	 				// nema indeksiranja
 	 			}
 	 			else if (designator.getOptionalAccess().struct.equals(Tab.nullType)) {
 	 				// izraz za indeksiranje je neispravan
 	 				report_error("Izraz za indeksiranje je neispravan. " + designator.getLine(), null);
 	 			}
 	 			else {
 	 				// izraz za indeksiranje nije tipa int
 	 				report_error("Tip "+designator.getOptionalAccess().struct+" se ne moze koristiti za indeksiranje. " + designator.getLine(), null);
 	 			}
 	 		}
 	 		
 		}
 	}
 	public void visit(DesignatorIdent designator) {
 		String fullName = designator.getNamespaceName()+"::"+ designator.getDesignatorName();
 		designator.obj = Tab.find(fullName);
// 		log.info("DesignatorIdent::FullName " + fullName);
// 		log.info("DesignatorIdent::Designator " + designator.obj.getType().getKind());
 		// proveri da li posotoji ime
 		if(designator.obj == Tab.noObj) {
 			report_error("Ime "+fullName+" nije definisano.", null);
 		}
 		else {
 	 		// proveri da li je niz
 	 	 		if (designator.obj.getType().getKind()==(Struct.Array)) {
 	 	 			if(designator.getOptionalAccess().struct.equals(Tab.intType)) {
 		 	 			// kind = elem
 		 	 	 		// designator <- novi obj tipa elem of array
 	 	 				designator.obj = 
 	 	 						new Obj(Obj.Elem, designator.getDesignatorName(), designator.obj.getType().getElemType(), 
 	 	 								designator.obj.getAdr(), designator.obj.getLevel());
 	 	 			}
 	 	 			else if (designator.getOptionalAccess().struct.equals(Tab.noType)) {
 	 	 				// nema indeksiranja
 	 	 			}
 	 	 			else if (designator.getOptionalAccess().struct.equals(Tab.nullType)) {
 	 	 				// izraz za indeksiranje je neispravan
 	 	 				report_error("Izraz za indeksiranje je neispravan. " + designator.getLine(), null);
 	 	 			}
 	 	 			else {
 	 	 				// izraz za indeksiranje nije tipa int
 	 	 				report_error("Tip "+designator.getOptionalAccess().struct+" se ne moze koristiti za indeksiranje. " + designator.getLine(), null);
 	 	 			}
 	 	 		}
 	 	 		
 	 		}
 	}
 	
 	/*(OptionalAccessEmpty)
			|
	  (OptionalAccessIndex) OptionalAccess LSQUAREBRACE Expr RSQUAREBRACE
	  		|
	  (OptionalAccessParam) OptionalAccess FULLSTOP IDENT*/
 	public void visit(OptionalAccessEmpty opAccess) {
 		opAccess.struct = Tab.noType;
 	}
 	public void visit(OptionalAccessIndex opAccess) {
 		if(opAccess.getOptionalAccess().struct.equals(Tab.nullType))
 			opAccess.struct = Tab.nullType;
 		else {
 			if (opAccess.getExpr().struct.equals(Tab.intType))
 	 			opAccess.struct = Tab.intType;
 	 		else
 	 			opAccess.struct = Tab.nullType;
 		}
 	}

 	/*Factor::= (FactorDesignator) Designator
			|
	  (FactorNum) NUMBER
	  		|
	  (FactorChar) CHAR
	  		|
	  (FactorBool) BOOL
	  		|
	  (FactorNewArray) NEW Type LSQUAREBRACE Expr RSQUAREBRACE
	  		|
	  (FactorBraceExpr) LPAREN Expr RPAREN; */
 	public void visit(FactorDesignator factor) {
 		factor.struct = factor.getDesignator().obj.getType();
//		log.info("FactorDesignator::Factor " + factor.getDesignator().obj.getKind());
 	}
 	public void visit(FactorNum factor) {
 		factor.struct = Tab.intType;
//		log.info("FactorNum::Factor " + Struct.Int);
 	}
 	public void visit(FactorChar factor) {
 		factor.struct = Tab.charType;
//		log.info("FactorChar::Factor " + Struct.Char);
 	}
 	public void visit(FactorBool factor) {
 		factor.struct = ExtendedTab.boolType;
//		log.info("FactorBool::Factor " + Struct.Bool);
 	} 	
 	public void visit(FactorNewArray factor) {
 		if (factor.getExpr().struct.equals(Tab.intType)) {
 			factor.struct = factor.getType().struct;
 		}
 		else {
 			factor.struct = Tab.nullType;
 		}
//		log.info("FactorNewArray::Factor " + factor.struct.getKind());
 	}
 	public void visit(FactorBraceExpr factor) {
 		factor.struct=factor.getExpr().struct;
//		log.info("FactorBraceExpr::Factor " + factor.struct.getKind());
 	}
 	
 	
 	
 	
 	
 	/*(Expr) OptionalMinus Term TermAddition*/
 	public void visit(Expr expr) {
 		if (expr.getTermAddition().struct.equals(Tab.noType)) {
 			if (expr.getOptionalMinus() instanceof OptionalMinusNotEmpty) {
 	 			// izraz sa vodecim minusom, obavezno svi elementi tipa int
 	 			expr.struct = Tab.intType;
 	 			if (!((OptionalMinusEmpty)expr.getOptionalMinus()).getTerm().struct.equals(Tab.intType)) {
 	 				report_error("Svi elementi u izrazu sa znakom '-' moraju da budu tipa int. Linija: " + expr.getLine(), null);
// 	 				log.info("Expr::Term " + expr.getTerm().struct.getKind());
 	 	 			expr.struct = Tab.nullType;
 	 			}
 			}
 			else {
 	 			expr.struct = ((OptionalMinusEmpty)expr.getOptionalMinus()).getTerm().struct;
 			}
 		}
 		else {

 	 		if (expr.getOptionalMinus() instanceof OptionalMinusNotEmpty) {
 	 			// izraz sa vodecim minusom, obavezno svi elementi tipa int
 	 			expr.struct = Tab.intType;
 	 			if (!((OptionalMinusNotEmpty)expr.getOptionalMinus()).getTerm().struct.equals(Tab.intType) || !expr.getTermAddition().struct.equals(Tab.intType)) {
 	 				report_error("Svi elementi u izrazu sa znakom '-' moraju da budu tipa int.", null);
// 	 				log.info("1Expr::Term " + expr.getTerm().struct.getKind());
// 	 				log.info("1Expr::TermAdd " + expr.getTermAddition().struct.getKind());
 	 	 			expr.struct = Tab.nullType;
 	 			}
 			}
 	 		else {
 	 			if(!expr.getTermAddition().struct.equals(((OptionalMinusEmpty)expr.getOptionalMinus()).getTerm().struct)) {
 					// svi delovimoraju biti ISTOG tipa
 	 				report_error("Svi elementi u izrazu moraju biti istog tipa.", null);
// 	 				log.info("2Expr::Term " + expr.getTerm().struct.getKind());
// 	 				log.info("2Expr::TermAdd " + expr.getTermAddition().struct.getKind());
 	 				expr.struct = Tab.nullType;
 				}
 				else {
 					// Ako se pojavi minus u izrazu, svi moraju biti int
 					if (lastSymbolMinus) {
 						if (!expr.getTermAddition().struct.equals(Tab.intType)) {
 							report_error("Svi elementi u izrazu sa znakom '-' moraju da budu tipa int. Linija: " + expr.getLine(), null);
// 							log.info("3Expr::Term " + expr.getTerm().struct.getKind());
// 		 	 				log.info("3Expr::TermAdd " + expr.getTermAddition().struct.getKind());
 							expr.struct = Tab.nullType;
 						}
 					}
 					expr.struct = expr.getTermAddition().struct;
 				}
 	 		}
 		}
 		lastSymbolMinus = false; 		
 	}

 	Boolean lastSymbolMinus = false;
 	/*(TermAdditionEmpty)
			|
	  (TermAdditionNotEmpty) TermAddition AddOperand Term;*/
 	public void visit(TermAdditionEmpty termAddition) {
 		termAddition.struct = Tab.noType;
 	}
 	public void visit(TermAdditionNotEmpty termAddition) {
 		if (termAddition.getTermAddition() instanceof TermAdditionEmpty) {
 			if (termAddition.getAddOperand() instanceof AddOperandMinus) {
 				lastSymbolMinus = true;
 				if (!termAddition.getTerm().struct.equals(Tab.intType)) 
 					termAddition.struct = Tab.nullType;
 				else
 					termAddition.struct = Tab.intType;
 			}
 			else
 				termAddition.struct = termAddition.getTerm().struct;
 		}
 		else {
 			if (termAddition.getAddOperand() instanceof AddOperandMinus) {
 				lastSymbolMinus = true;
 				if (!termAddition.getTerm().struct.equals(Tab.intType)) 
 					termAddition.struct = Tab.nullType;
 				if (!termAddition.getTermAddition().struct.equals(Tab.intType)) 
 					termAddition.struct = Tab.nullType;
 				else
 					termAddition.struct = Tab.intType;
 			}
 			else {
 				if (termAddition.getTerm().struct.equals(termAddition.getTermAddition().struct)) 
 					termAddition.struct = termAddition.getTerm().struct;
 				else
 					termAddition.struct = Tab.nullType;
 			}
 		}
 	}

 	/*(Term) Factor OptionalMultiply*/
 	public void visit(Term term) {
 		if (term.getOptionalMultiply() instanceof OptionalMultiplyEmpty) {
 			// Factor moze da bude bilo sta
 			term.struct = term.getFactor().struct;
 		}
 		else {
 			// Ceo izraz mora da bude tipa int
 			if (!term.getFactor().struct.equals(Tab.intType)) {
 				term.struct = Tab.nullType;
// 				log.info("Term::Factor " + term.getFactor().struct.getKind());
 			}
 			else if (!term.getOptionalMultiply().struct.equals(Tab.intType)) {
 				term.struct = Tab.nullType;
// 				log.info("Term::OptionalMultiply " + term.getOptionalMultiply().struct.getKind());
 			}
 			else
 				term.struct = Tab.intType;
 		}
 		
 	}
 	/*(OptionalMultiplyEmpty)
			|
	 (OptionalMultiplyNotEmpty) OptionalMultiply MultiplyOperand Factor*/
 	public void visit(OptionalMultiplyEmpty optionalMultiply) {
 		optionalMultiply.struct = Tab.noType;
 	}
 	public void visit(OptionalMultiplyNotEmpty optionalMultiply) {
 		if (optionalMultiply.getOptionalMultiply() instanceof OptionalMultiplyEmpty) {
 			optionalMultiply.struct = optionalMultiply.getFactor().struct;
 			if (!optionalMultiply.struct.equals(Tab.intType))
 				optionalMultiply.struct = Tab.nullType;
// 			log.info("OptionalMultiplyNotEmpty::Factor " + optionalMultiply.getFactor().struct.getKind());
 		}
 		else {
 			if (!optionalMultiply.getFactor().struct.equals(Tab.intType)) {
 				optionalMultiply.struct = Tab.nullType;
// 	 			log.info("OptionalMultiplyNotEmpty::Factor " + optionalMultiply.getFactor().struct.getKind());
 			}
 			else if (!optionalMultiply.getOptionalMultiply().struct.equals(Tab.intType)) {
 				optionalMultiply.struct = Tab.nullType;
// 	 			log.info("OptionalMultiplyNotEmpty::OptionalMultipy " + optionalMultiply.getOptionalMultiply().struct.getKind());
 			}
 			else
 				optionalMultiply.struct = Tab.intType;
 		}
 	}
 		 
}
