package splitter;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingWorker;

/**
 *
 * @author paul
 */
public class SplitterController {

    public final String name = "Splitter";
    public final String VERSION = "0.1";

    private SplitterModel model = null;
    private SplitterView view = null;

    /*
     public Action actionOpenFile = getAction("onOpenFile", "Open file");
     public Action actionSplit = getAction("onSplit", "Split");

     private Action getAction(final String command, final String name) {
     return new TrampolineAction(command, name, null, this);
     }
     */
    public SplitterController() {
        this.view = new SplitterView(this);
        this.model = new SplitterModel(view);

        this.view.setSplitSize("" + this.model.getSplitSize());
    }

    public void display() {
        this.view.setVisible(true);
        this.view.pack();
    }

    public void hide() {
        this.view.setVisible(false);
    }

    public void onOpenFile(File file) {
        // this.model.openFile(file);
        // this.view.getContentTextarea().setText(this.model.getContent());
        
        this.model.getOpenTask(file).execute();
        
        
        /*try {
            //this.view.getContentTextarea().read(new FileReader(file.getAbsolutePath()), null);
        } catch (IOException e) {
            System.out.println("problem accessing file" + file.getAbsolutePath());
        }*/
    }
    
    public Integer getNbLines() {
        return this.model.getNbLines();
    }
    
    public Integer getNbFiles() {
        return this.model.getNbFiles();
    }
    
    public void onSplit() {
        try {
            this.model.getSplitTask().execute();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public SplitterController setSplitSize(String size) {
        this.model.setSplitSize(size);
        return this;
    }

    public Integer getSplitSize() {
        return this.model.getSplitSize();
    }

}
