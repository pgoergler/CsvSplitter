package main;

import splitter.SplitterView;
import splitter.SplitterController;

/**
 *
 * @author paul
 */
public class Splitter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ImageRotator");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SplitterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplitterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplitterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplitterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        SplitterController splitter = new SplitterController();
        splitter.display();
    }
    
}
