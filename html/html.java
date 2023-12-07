///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "html", mixinStandardHelpOptions = true, version = "html 0.1", description = "html made with jbang")
class html implements Callable<Integer> {

    static final String blankHtml = """
            <!doctype html>
            <html lang=en>
            <head>
            <meta charset=utf-8>
            <title>blah</title>
            </head>
            <body>
            <p>I'm the content</p>
            </body>
            </html>
            """;

    @Parameters(index = "0", description = "The greeting to print", defaultValue = "World!")
    private String greeting;

    public static void main(String... args) {
        int exitCode = new CommandLine(new html()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        java.nio.file.Files.list(java.nio.file.Path.of("")).forEach(System.out::println);
        System.out.println("Hello " + greeting);
        return 0;
    }

    @Command(mixinStandardHelpOptions = true)
    void blankHtml(@Parameters(description = "name of the new html.") String name) throws IOException {
        // if (name == null || name.isBlank()) {
        //     System.err.println("name is required");
        //     return;
        // }
        Files.writeString(Path.of(name), blankHtml);
    }
}
