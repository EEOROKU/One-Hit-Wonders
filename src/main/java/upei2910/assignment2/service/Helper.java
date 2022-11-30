package upei2910.assignment2.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {

    /**
     * Open one of the files in the resources/upei2910/assignment1 directory
     * i.e., this method can be used to open one of the .csv files
     * precondition: none (static method)
     * postcondition: none (static method)
     * @param fileName the filename (without the path) e.g., sample.csv
     *                 for a file in the resources directory
     * @return The file
     * @throws IOException if the file is not found
     */
    public static File openResourceFile(String fileName)  {
        Path p = Paths.get("src","main", "resources", "upei2910", "assignment2", fileName);
        return new File(p.toAbsolutePath().toString());
    }
}
