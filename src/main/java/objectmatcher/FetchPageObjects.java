package objectmatcher;

import common.Constants;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FetchPageObjects {

    private static final Logger LOG = LogManager.getLogger(FetchPageObjects.class);

    private FetchPageObjects() {
        //Utility class
    }

    public static Set<Class<?>> populateListOfPO() {
        Set<Class<?>> pageClassSet = new HashSet<>();
        File directory = null;
        directory = new File(common.Constants.PAGEOBJECTSPATH);

        if (!directory.exists()) {
            File folder = new File(common.Constants.PAGEOBJECTSJAR);
            if (folder.exists()) {
                File[] files = folder.listFiles();
                for (File file : files) {
                    getPageObjectClassNames(pageClassSet, file);
                }
            }
            return pageClassSet;
        }
        List<String> javaClasses = findClassesInPath(common.Constants.PAGEOBJECTSPATH);
        List<PageObject> javaPackageDetails = findJavaFiles(Constants.PAGEOBJECTJAVAPATH);

        javaPackageDetails.stream().filter(a -> javaClasses.contains(a.getJavaClassName())).collect(Collectors.toList()).stream().
                forEach(pageObject -> {
                    try {
                        pageClassSet.add(Class.forName(pageObject.getJavaClassPackage().substring(8, pageObject.getJavaClassPackage().indexOf(';')) + "." + pageObject.getJavaClassName()));
                    } catch (ClassNotFoundException e) {
                        //no action
                    }
                });
        return pageClassSet;
    }

    private static void getPageObjectClassNames(Set<Class<?>> pageClassSet, File file) {
        try (JarInputStream pageObjJarFile = new JarInputStream(new FileInputStream(file))) {
            JarEntry pageObj = pageObjJarFile.getNextJarEntry();
            while (pageObj != null) {
                if ((pageObj.getName().endsWith(".class"))) {
                    String className = pageObj.getName().replace("/", ".");
                    if (className.startsWith("pageobjects")) {
                        String myClass = className.substring(0, className.lastIndexOf('.'));
                        pageClassSet.add(Class.forName(myClass));
                    }
                }
                pageObj = pageObjJarFile.getNextJarEntry();
            }
        } catch (IOException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static List<String> findClassesInPath(String rootDir) {
        Path filePath = Paths.get(rootDir);
        final List<String> fileList = new ArrayList<>();

        try (Stream<Path> files = Files.find(filePath, 5,
                                             (path, attributes) -> {
                                                 File file = path.toFile();
                                                 return !file.isDirectory() &&
                                                        file.getName().endsWith(".class");
                                             })) {

            files.forEach(file -> fileList.add(getFileNameWithoutExtension(file)));
            if (fileList.isEmpty()) {
                LOG.error("No .class file found within path \"{}\"", rootDir);
            } else {
                return fileList;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return fileList;
    }

    public static List<PageObject> findJavaFiles(String rootDir) {
        Path filePath = Paths.get(rootDir);
        final List<PageObject> list = new ArrayList<>();

        try (Stream<Path> files = Files.find(filePath, 5,
                                             (path, attributes) -> {
                                                 File file = path.toFile();
                                                 return !file.isDirectory() &&
                                                        file.getName().endsWith(".java");
                                             })) {

            files.forEach(file -> {
                try {
                    list.add(new PageObject(getFileNameWithoutExtension(file), getJavaClassPackage(file)));
                } catch (IOException e) {
                    LOG.error(e);
                }
            });

            if (list.isEmpty()) {
                LOG.error("No .java file found within path \"{}\"", rootDir);
            } else {
                return list;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return list;
    }

    private static String getJavaClassPackage(Path file) throws IOException {
        try {
            return Files.readAllLines(file).get(0);
        } catch (MalformedInputException e) {
            return Files.readAllLines(file, StandardCharsets.ISO_8859_1).get(0);
        }
    }

    public static String getFileNameWithoutExtension(Path path) {
        String fileName = path.getFileName().toString();
        fileName = FilenameUtils.removeExtension(fileName);

        return fileName;
    }

    static class PageObject {
        String javaClassName;
        String javaClassPackage;

        PageObject(String javaClassName, String javaClassPackage) {
            this.javaClassName = javaClassName;
            this.javaClassPackage = javaClassPackage;
        }

        public String getJavaClassName() {
            return javaClassName;
        }

        public String getJavaClassPackage() {
            return javaClassPackage;
        }
    }
}
