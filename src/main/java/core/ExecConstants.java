package core;

import common.Property;

public class ExecConstants extends Constants {
    public static final String SELENIUMSTACKSPATH = BASEPATH + "config/selenium/stacks/" + Property.getVariable("cukes.techstack") + ".json";
    public static final String TESTCASEPATH = BASEPATH + "scripts/testcases.xlsx";
}
