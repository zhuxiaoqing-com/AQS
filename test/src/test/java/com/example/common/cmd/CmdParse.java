package com.example.common.cmd;

import org.apache.commons.cli.*;
import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/7/24 19:47
 * @Description:
 */
public class CmdParse {
	private static Options options;
	HelpFormatter helpFormatter = new HelpFormatter();
	static {
		options = new Options();
		options.addOption(Option.builder().longOpt("project_path").hasArg().desc("project path").required().build());
	}
	@Test
	public void test21() {
		String[] args = new String[]{};
		CommandLine cmd = null;
		try {
			cmd = (new DefaultParser()).parse(options, args);
		} catch (ParseException e) {
			helpFormatter.printHelp("scp -help", options);
			System.exit(0);
		}
		String project_path = cmd.getOptionValue("project_path");
	}
}
