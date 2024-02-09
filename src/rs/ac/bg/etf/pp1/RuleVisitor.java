package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import org.apache.log4j.*;
public class RuleVisitor extends VisitorAdaptor {
	
	int printcallcount=0;
    public void visit(StatementPrint statementPrint) { 
		Logger log = Logger.getLogger(RuleVisitor.class);
    	
    	printcallcount++;
    	log.info("Print prepoznat!!!");
    }


}
