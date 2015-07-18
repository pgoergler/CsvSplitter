/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitter;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author paul
 */
public class SplitterView extends javax.swing.JFrame {

    protected SplitterController controller;
    //protected ImageIcon icon;
    /**
     * Creates new form SplitterUI
     *
     * @param controller
     */
    public SplitterView(SplitterController controller) {
        this.controller = controller;
        initComponents();
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new CsvFileFilter();
        this.fileChooser.setFileFilter(filter);
     
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/coffee.png"));
        Image dimg = icon.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH);

        logoLabel.setIcon(new ImageIcon(dimg));
        this.disableSplitButton();
    }

    public String getSplitSize() {
        return this.splitSizeInput.getText();
    }

    public SplitterView setSplitSize(String size) {
        this.splitSizeInput.setText(size);
        // this.setNumberOfFilesValue(this.controller.getNbFiles());
        return this;
    }

    public void setNumberOfLines(Integer number) {
        this.numberOfLinesValueLabel.setText("" + number);
        // this.setNumberOfFilesValue(this.controller.getNbFiles());
    }

    public void setNumberOfFilesValue(Integer nbFiles) {
        if (nbFiles != null && nbFiles > 0) {
            this.numberOfFilesValueLabel.setText("" + nbFiles);
        } else {
            this.numberOfFilesValueLabel.setText("xx");
        }
    }

    /*
     public boolean shouldIncludeDate() {
     return this.includeDateCheckbox.isSelected();
     }

     public SplitterView includeDate(boolean include) {
     this.includeDateCheckbox.setSelected(include);
     return this;
     }
     */
    public JTextArea getContentTextarea() {
        return contentTextarea;
    }

    public void setContentTextarea(JTextArea contentTextarea) {
        this.contentTextarea = contentTextarea;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JButton getOpenFileButton() {
        return openFileButton;
    }

    public JButton getSplitButton() {
        return splitButton;
    }
    
    public synchronized void enableSplitButton() {
        this.getSplitButton().setBackground(new Color(51,122,183));
        this.getSplitButton().setBorder(BorderFactory.createLineBorder(new Color(46, 109, 164), 2));
        this.getSplitButton().setEnabled(true);
    }

    public synchronized void disableSplitButton() {
        this.getSplitButton().setBackground(new Color(238,238,238));
        this.getSplitButton().setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        this.getSplitButton().setEnabled(false);
    }
    
    public synchronized void enableOpenFileButton() {
        this.getOpenFileButton().setBackground(new Color(51,122,183));
        this.getOpenFileButton().setBorder(BorderFactory.createLineBorder(new Color(46, 109, 164), 2));
        this.getOpenFileButton().setEnabled(true);
    }

    public synchronized void disableOpenFileButton() {
        this.getOpenFileButton().setBackground(new Color(238,238,238));
        this.getOpenFileButton().setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        this.getOpenFileButton().setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextarea = new javax.swing.JTextArea();
        splitSizeLabel = new javax.swing.JLabel();
        splitSizeInput = new javax.swing.JTextField();
        splitButton = new javax.swing.JButton();
        openFileButton = new javax.swing.JButton();
        numberOfLinesLabel = new javax.swing.JLabel();
        numberOfLinesValueLabel = new javax.swing.JLabel();
        numberOfFilesLabel = new javax.swing.JLabel();
        numberOfFilesValueLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CSV Splitter");
        setName("mainFrame"); // NOI18N
        setResizable(false);

        contentTextarea.setEditable(false);
        contentTextarea.setColumns(20);
        contentTextarea.setRows(21);
        jScrollPane1.setViewportView(contentTextarea);

        splitSizeLabel.setText(" Lines per file :");

        splitSizeInput.setText("10000");
        splitSizeInput.setName("splitSizeInput"); // NOI18N
        splitSizeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitSizeInputActionPerformed(evt);
            }
        });
        splitSizeInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                splitSizeInputKeyReleased(evt);
            }
        });

        splitButton.setBackground(new java.awt.Color(51, 122, 183));
        splitButton.setForeground(new java.awt.Color(255, 255, 255));
        splitButton.setText("Split");
        splitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 109, 164), 2));
        splitButton.setEnabled(false);
        splitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitButtonActionPerformed(evt);
            }
        });

        openFileButton.setBackground(new java.awt.Color(51, 122, 183));
        openFileButton.setForeground(new java.awt.Color(255, 255, 255));
        openFileButton.setText("Open File");
        openFileButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 109, 164), 2));
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        numberOfLinesLabel.setText("Number of lines :");

        numberOfFilesLabel.setText(" Total files :");

        numberOfFilesValueLabel.setText(" ");

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/coffee.png"))); // NOI18N

        progressBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(openFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numberOfLinesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberOfLinesValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(splitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(splitSizeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(splitSizeInput, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(numberOfFilesLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numberOfFilesValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoLabel)
                                .addGap(72, 72, 72)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(logoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(splitSizeLabel)
                            .addComponent(splitSizeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(splitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numberOfLinesLabel)
                    .addComponent(numberOfLinesValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numberOfFilesLabel)
                        .addComponent(numberOfFilesValueLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void splitSizeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitSizeInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_splitSizeInputActionPerformed

    private void splitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitButtonActionPerformed
        this.controller.onSplit();
    }//GEN-LAST:event_splitButtonActionPerformed

    private void splitSizeInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_splitSizeInputKeyReleased
        try {
            Integer num = Number.castToInteger(this.getSplitSize(), null);
            if (num == null) {
                throw new ClassCastException();
            } else {
                this.controller.setSplitSize(this.getSplitSize());
                // this.setSplitSize(this.getSplitSize());
            }
        } catch (Exception e) {
            this.setSplitSize("" + this.controller.getSplitSize());
        }
    }//GEN-LAST:event_splitSizeInputKeyReleased

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        int returnVal = this.fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            progressBar.setIndeterminate(true);
            this.controller.onOpenFile(fileChooser.getSelectedFile());
        } else {
            System.out.println("returnVal=" + returnVal);
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    public void popup(String message, String title, int type) {
        JOptionPane.showMessageDialog(contentTextarea.getParent(), message, title, type);
    }
    
    public void alert(String message) {
        this.popup(message, "Alert", JOptionPane.ERROR_MESSAGE);
    }
    
    public void info(String message) {
        this.popup(message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void success(String message) {
        this.popup(message, "Success", JOptionPane.PLAIN_MESSAGE);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea contentTextarea;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel numberOfFilesLabel;
    private javax.swing.JLabel numberOfFilesValueLabel;
    private javax.swing.JLabel numberOfLinesLabel;
    private javax.swing.JLabel numberOfLinesValueLabel;
    private javax.swing.JButton openFileButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton splitButton;
    private javax.swing.JTextField splitSizeInput;
    private javax.swing.JLabel splitSizeLabel;
    // End of variables declaration//GEN-END:variables
}