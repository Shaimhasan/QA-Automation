package objectmatcher;

import common.Constants;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FetchFeatureFiles {

    private FetchFeatureFiles() {
        //Utility class
    }

    public static Set<File> populateListOfFeatureFiles() {
        return recursiveListFiles(new File(Constants.FEATUREFILEPATH), new HashSet<>());
    }

    private static Set<File> recursiveListFiles(File directory, Set<File> listFiles) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    return recursiveListFiles(file, listFiles);
                } else listFiles.add(file);

            }
            return listFiles;
        } else {
            return Collections.emptySet();
        }

    }
}