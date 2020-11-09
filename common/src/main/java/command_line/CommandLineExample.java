package command_line;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineExample {

    public static void main(String[] args) {
        args = new String[] { "-cg_ip", "192.168.100.204", "-help", "-cg_port", " 17782" };

        // 定义参数
        Options options = new Options();
        options.addOption("help", "print example usage");
        options.addRequiredOption("c", "cg_ip", true, "calEng ip");
        Option option = new Option("cg_port", true, "cg port");
        option.setRequired(true);
        options.addOption(option);

        // 解析
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar xxxx.jar", options, true);
                return;
            }
        } catch (ParseException e) {
            // TODO: handle exception
        }

        String ip = line.getOptionValue("cg_ip", "192.168.1.1");
        System.out.println("input arguments, ip=" + ip);
    }

}
