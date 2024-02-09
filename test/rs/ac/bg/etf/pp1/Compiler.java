package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.tab.ExtendedTab;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	public static void tsdump() {
		ExtendedTab.dump();
	}
	public static void main(String[] args) throws Exception {
		
		if (args.length == 2) {
            
			FileOutputStream sout = new FileOutputStream(args[0]);
		    FileOutputStream serror = new FileOutputStream(args[1]);

		    System.setOut(new PrintStream(sout));
		    System.setErr(new PrintStream(serror));
		}
		
		
		Logger log = Logger.getLogger(Compiler.class);
		
		try (BufferedReader br = new BufferedReader(new FileReader("test/program.mj"))) {
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        SyntaxNode prog = (SyntaxNode)(s.value);
	        
			ExtendedTab.init(); // Universe scope
			SemanticPass semanticCheck = new SemanticPass();
			prog.traverseBottomUp(semanticCheck);
			
	        
	        if (!p.errorDetected && semanticCheck.passed()) {
	        	File objFile = new File("test/program.obj");
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	// Code generation...
	        	CodeGenerator codeGenerator = new CodeGenerator();
	        	prog.traverseBottomUp(codeGenerator);
	        	Code.dataSize = semanticCheck.nVars;
	        	Code.mainPc = codeGenerator.getMainPc();
	        	Code.write(new FileOutputStream(objFile));
	        	log.info("Parsiranje uspesno zavrseno!");
	        }
	        else {
	        	log.error("Parsiranje NIJE uspesno zavrseno!");
	        }
	        tsdump();
		}
	}
		
		
		
	
	
}