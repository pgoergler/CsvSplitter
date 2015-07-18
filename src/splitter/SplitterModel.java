package splitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.max;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author paul
 */
public class SplitterModel {

    protected SplitterView view;
    protected Integer defaultSplitSize = 10000;
    protected Integer splitSize = 10000;
    protected boolean includeDate = false;
    protected int nbLines = 0;
    protected File openfile;
    protected StringBuilder content = new StringBuilder();
    //-

    public SplitterModel() {
    }

    public SplitterModel(SplitterView view) {
        this.view = view;
    }

    public SplitterView getView() {
        return view;
    }

    public void setView(SplitterView view) {
        this.view = view;
    }

    public Integer getSplitSize() {
        return this.splitSize;
    }

    public SplitterModel setSplitSize(String size) {
        Integer iSize = Number.castToInteger(size, 0);
        if (iSize == 0 || iSize == null) {
            this.view.alert("Split size must be an integer greater than 0.");
            this.splitSize = iSize;
            this.disableSplitButton();
            return this;
        }
        this.enableSplitButton();

        this.splitSize = iSize;
        this.updateNumberOfLines();
        return this;
    }

    public SplitterModel setSplitSize(Integer size) {
        this.splitSize = size;
        return this;
    }

    public int getNbLines() {
        return nbLines;
    }

    public Integer getNbFiles() {
        if (this.openfile != null && this.nbLines > 0 && this.splitSize > 0) {
            Double d = Math.ceil(this.nbLines / new Double(this.splitSize));
            return d.intValue();
        }
        return 0;
    }

    public String getContent() {
        return this.content.toString();
    }

    public boolean shouldIncludeDate() {
        return this.includeDate;
    }

    public SplitterModel includeDate(boolean include) {
        this.includeDate = include;
        return this;
    }

    public synchronized void enableOpenFileButton() {
        this.view.enableOpenFileButton();
    }

    public synchronized void disableOpenFileButton() {
        this.view.disableOpenFileButton();
    }

    protected boolean canEnableSplitButton() {
        return !(this.openfile == null || this.splitSize <= 0);
    }

    public synchronized void enableSplitButton() {
        if (this.canEnableSplitButton()) {
            this.view.enableSplitButton();
        }
    }

    public synchronized void disableSplitButton() {
        this.view.disableSplitButton();
    }

    public void updateNumberOfLines() {
        this.view.setNumberOfLines(this.nbLines);
        this.view.setNumberOfFilesValue(this.getNbFiles());
    }

    public OpenFileTask getOpenTask(File file) {
        return new OpenFileTask(this, this.view, file);
    }

    public SplitFileTask getSplitTask() {
        return new SplitFileTask(this, this.view, this.openfile, this.getSplitSize(), this.getNbLines());
    }

    public static class OpenFileTask extends SwingWorker<String, Integer> {

        protected SplitterModel model;
        protected SplitterView view;
        //-
        protected JTextArea textArea;
        protected JProgressBar progressBar;
        protected File file;
        //-
        protected int nbFirstRowsPrinted;
        protected int nbLastRowsPrinted;
        protected int nbTotalRows = 0;
        //-

        public OpenFileTask(SplitterModel model, SplitterView view, File file) {
            this(model, view, file, 10, 10);
        }

        public OpenFileTask(SplitterModel model, SplitterView view, File file, int nbFirstRowsPrinted, int nbLastRowsPrinted) {
            this.model = model;
            this.view = view;
            this.textArea = this.view.getContentTextarea();
            this.progressBar = this.view.getProgressBar();
            this.file = file;
            this.nbFirstRowsPrinted = nbFirstRowsPrinted;
            this.nbLastRowsPrinted = nbLastRowsPrinted;

            this.model.disableOpenFileButton();
            this.model.disableSplitButton();
        }

        @Override
        protected String doInBackground() throws Exception {
            long start = System.currentTimeMillis();

            System.out.println("absolutePath=" + file.getAbsolutePath());
            System.out.println("Path=" + file.getPath());
            System.out.println("Parent=" + file.getParent());
            System.out.println("Name=" + file.getName());

            StringBuilder content = new StringBuilder();
            CircularLimitedQueue<String> lastRows = new CircularLimitedQueue<>(this.nbLastRowsPrinted);

            try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String row = line.trim();
                    if ("".equals(row)) {
                        continue;
                    }
                    if (this.nbTotalRows < this.nbFirstRowsPrinted) {
                        content.append(row).append(System.lineSeparator());
                    } else {
                        lastRows.add(row);
                    }
                    this.nbTotalRows++;
                }

                if (this.nbTotalRows > this.nbFirstRowsPrinted) {
                    if (this.nbTotalRows > this.nbFirstRowsPrinted + this.nbLastRowsPrinted) {
                        content.append("... ")
                                .append(this.nbTotalRows - this.nbFirstRowsPrinted - this.nbLastRowsPrinted)
                                .append(" hidden rows ...")
                                .append(System.lineSeparator());
                    }

                    boolean addSeparator = false;
                    for (String row : lastRows) {
                        if (addSeparator) {
                            content.append(System.lineSeparator());
                        }
                        addSeparator = true;
                        content.append(row);
                    }
                }

            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("file not found " + file.getAbsolutePath());
            }

            System.out.println("open in " + (System.currentTimeMillis() - start) + " milliseconds");
            return content.toString();
        }

        @Override
        protected void done() {
            super.done();
            try {
                String content = this.get();

                this.model.openfile = this.file;
                this.model.nbLines = this.nbTotalRows;
                this.model.updateNumberOfLines();
                this.model.view.getContentTextarea().setText(content);

            } catch (Exception e) {
                this.model.view.alert(e.getMessage());
            }
            progressBar.setIndeterminate(false);
            this.model.enableOpenFileButton();
            this.model.enableSplitButton();
        }

    }

    public static class SplitFileTask extends SwingWorker<Void, Integer> {

        protected SplitterModel model;
        protected SplitterView view;
        //-
        protected JProgressBar progressBar;
        protected File file;
        //-
        protected int batchSize;
        protected int nbTotalFiles;
        protected int nbTotalRows;
        //-

        public SplitFileTask(SplitterModel model, SplitterView view, File file, int batchSize, int nbTotalRows) {
            this.model = model;
            this.view = view;
            this.file = file;
            this.progressBar = this.view.getProgressBar();
            this.batchSize = batchSize;
            this.nbTotalRows = nbTotalRows;
            this.nbTotalFiles = nbTotalRows / batchSize;

            this.model.disableOpenFileButton();
            this.model.disableSplitButton();
            this.progressBar.setIndeterminate(false);
            this.progressBar.setMaximum(this.nbTotalRows);
        }
        
        @Override
        protected void process(List<Integer> chunks) {
            int i = chunks.get(chunks.size()-1);
            this.progressBar.setValue(i); // The last value in this array is all we care about.
        }

        @Override
        protected Void doInBackground() throws Exception {
            long start = System.currentTimeMillis();
            
            String parentFolder = this.file.getParent();
            String filename = this.file.getName();
            String[] infos = filename.split("\\.(?=[^\\.]+$)");
            if (infos.length != 2) {
                System.err.println("Your file must ends with .csv extension");
                throw new IOException("Your file must ends with .csv extension");
            }

            int fileCounterLength = ("" + (this.nbTotalFiles - 1)).length();

            String filenameFormat = infos[0] + "_%0" + fileCounterLength + "d." + infos[1];
            int fileIndex = 0;

            String outputFilename = parentFolder + File.separator + String.format(filenameFormat, fileIndex);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename, false)));

            int loopIndex = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(this.file.getAbsolutePath()))) {
                int batch = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    if ("".equals(line.trim())) {
                        continue;
                    }

                    if (batch < this.batchSize) {
                        out.println(line.trim());
                        batch++;
                    } else {
                        // System.out.println("Done: " + (batch * (fileIndex + 1)));
                        fileIndex++;
                        out.flush();
                        out.close();

                        outputFilename = parentFolder + File.separator + String.format(filenameFormat, fileIndex);
                        out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename, true)));

                        out.println(line.trim());
                        batch = 1;
                    }
                    
                    this.publish(loopIndex++);
                }
            } finally {
                out.close();
            }
            this.publish(loopIndex);
            
            System.out.println("split finished " + (System.currentTimeMillis() - start) + " milliseconds");
            return null;
        }

        @Override
        protected void done() {
            try {
                this.get();
                this.model.view.success("Split succesfully, " + this.nbTotalFiles + " files generated.");
            } catch (Exception e) {
                this.model.view.alert(e.getMessage());
            }
            this.model.enableOpenFileButton();
            this.model.enableSplitButton();
        }
    }

}
