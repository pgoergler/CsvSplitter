package splitter;

import java.io.File;

/**
 *
 * @author paul
 */
public class CsvFileFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".txt" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".txt")
                || file.getAbsolutePath().endsWith(".csv");
    }

    @Override
    public String getDescription() {
        return "CSV documents (*.txt, *.csv)";
    }

}
