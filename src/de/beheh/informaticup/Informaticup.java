package de.beheh.informaticup;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Benedict Etzel <developer@beheh.de>
 */
public class Informaticup {

	private static final Logger logger = LogManager.getLogger(Informaticup.class.getName());

	public static void main(String[] args) {
		try {
			SpaceUsageRuleParser parser = new SpaceUsageRuleParser(new File("data/Dataset_20102014/Data.txt"));
			parser.parse();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

}
