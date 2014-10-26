package de.beheh.informaticup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Benedict Etzel <developer@beheh.de>
 */
public class SpaceUsageRuleParser {

	private static final Logger logger = LogManager.getLogger(SpaceUsageRuleParser.class.getName());

	private BufferedReader reader;
	private final Pattern pattern;

	public SpaceUsageRuleParser(InputStream stream) throws IOException {
		this.reader = new BufferedReader(new InputStreamReader(stream));
		this.pattern = compilePattern();
	}

	public SpaceUsageRuleParser(BufferedReader reader) throws IOException {
		this.reader = reader;
		this.pattern = compilePattern();
	}

	public SpaceUsageRuleParser(File file) throws IOException {
		this.reader = new BufferedReader(new FileReader(file));
		this.pattern = compilePattern();
	}

	/**
	 * Compile the pattern to match lines the space usage rule format.
	 *
	 * @return
	 */
	private Pattern compilePattern() {
		StringBuilder builder = new StringBuilder();
		String separator = ",\\s?";
		builder.append("(\\w*)").append(separator); // id
		builder.append("([0-1]+\\.[0-9]+)").append(separator); // latitude
		builder.append("([0-9]+\\.[0-9]+)").append(separator); // longitude
		builder.append("(.+)=\"(.*)\""); // type and value
		Pattern compiledPattern = Pattern.compile("^" + builder.toString() + "$");
		logger.debug("Compiled pattern is " + compiledPattern.toString());
		return compiledPattern;
	}

	public void parse() throws IOException {

		String line = reader.readLine();
		int ruleCount = new Integer(line);
		logger.info(ruleCount + " rule(s)");

		int lineCount = 0;
		while ((line = reader.readLine()) != null) {
			Matcher lineMatcher = pattern.matcher(line);
			lineCount++;
			if (!lineMatcher.matches()) {
				logger.warn("Line does not match: " + line);
				continue;
			}
			String id = lineMatcher.group(1);
			String latitude = lineMatcher.group(2);
			String longitude = lineMatcher.group(3);
			String type = lineMatcher.group(4);
			String value = lineMatcher.group(5);
			logger.info(id + ": " + latitude + "/" + longitude + " " + type + "=" + value);
		}

		if (lineCount != ruleCount) {
			logger.warn("Rule count (" + ruleCount + ") does not match line count (" + lineCount + ")");
		}

	}

}
