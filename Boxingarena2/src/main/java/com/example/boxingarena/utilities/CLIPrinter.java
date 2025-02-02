package com.example.boxingarena.utilities;

import java.util.logging.Logger;

public class CLIPrinter {
    private CLIPrinter() {
        throw new IllegalStateException("Utility class");
    }

    public static void printMessage(String s) {
        Logger logger = Logger.getLogger(CLIPrinter.class.getName());
        logger.info(s);
    }
}
