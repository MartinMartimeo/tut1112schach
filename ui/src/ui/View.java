/*
 * View.java
 */

package ui;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class View extends FrameView {

    public View(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        Handling.doInit(this);

        Handling.setField(1, 1, button_a1);
        Handling.setField(1, 2, button_a2);
        Handling.setField(1, 3, button_a3);
        Handling.setField(1, 4, button_a4);
        Handling.setField(1, 5, button_a5);
        Handling.setField(1, 6, button_a6);
        Handling.setField(1, 7, button_a7);
        Handling.setField(1, 8, button_a8);
        Handling.setField(2, 1, button_b1);
        Handling.setField(2, 2, button_b2);
        Handling.setField(2, 3, button_b3);
        Handling.setField(2, 4, button_b4);
        Handling.setField(2, 5, button_b5);
        Handling.setField(2, 6, button_b6);
        Handling.setField(2, 7, button_b7);
        Handling.setField(2, 8, button_b8);
        Handling.setField(3, 1, button_c1);
        Handling.setField(3, 2, button_c2);
        Handling.setField(3, 3, button_c3);
        Handling.setField(3, 4, button_c4);
        Handling.setField(3, 5, button_c5);
        Handling.setField(3, 6, button_c6);
        Handling.setField(3, 7, button_c7);
        Handling.setField(3, 8, button_c8);
        Handling.setField(4, 1, button_d1);
        Handling.setField(4, 2, button_d2);
        Handling.setField(4, 3, button_d3);
        Handling.setField(4, 4, button_d4);
        Handling.setField(4, 5, button_d5);
        Handling.setField(4, 6, button_d6);
        Handling.setField(4, 7, button_d7);
        Handling.setField(4, 8, button_d8);
        Handling.setField(5, 1, button_e1);
        Handling.setField(5, 2, button_e2);
        Handling.setField(5, 3, button_e3);
        Handling.setField(5, 4, button_e4);
        Handling.setField(5, 5, button_e5);
        Handling.setField(5, 6, button_e6);
        Handling.setField(5, 7, button_e7);
        Handling.setField(5, 8, button_e8);
        Handling.setField(6, 1, button_f1);
        Handling.setField(6, 2, button_f2);
        Handling.setField(6, 3, button_f3);
        Handling.setField(6, 4, button_f4);
        Handling.setField(6, 5, button_f5);
        Handling.setField(6, 6, button_f6);
        Handling.setField(6, 7, button_f7);
        Handling.setField(6, 8, button_f8);
        Handling.setField(7, 1, button_g1);
        Handling.setField(7, 2, button_g2);
        Handling.setField(7, 3, button_g3);
        Handling.setField(7, 4, button_g4);
        Handling.setField(7, 5, button_g5);
        Handling.setField(7, 6, button_g6);
        Handling.setField(7, 7, button_g7);
        Handling.setField(7, 8, button_g8);
        Handling.setField(8, 1, button_h1);
        Handling.setField(8, 2, button_h2);
        Handling.setField(8, 3, button_h3);
        Handling.setField(8, 4, button_h4);
        Handling.setField(8, 5, button_h5);
        Handling.setField(8, 6, button_h6);
        Handling.setField(8, 7, button_h7);
        Handling.setField(8, 8, button_h8);

    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = App.getApplication().getMainFrame();
            aboutBox = new AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        button_a8 = new javax.swing.JButton();
        button_a7 = new javax.swing.JButton();
        button_a6 = new javax.swing.JButton();
        button_a5 = new javax.swing.JButton();
        button_b5 = new javax.swing.JButton();
        button_b6 = new javax.swing.JButton();
        button_b7 = new javax.swing.JButton();
        button_b8 = new javax.swing.JButton();
        button_c5 = new javax.swing.JButton();
        button_c6 = new javax.swing.JButton();
        button_c7 = new javax.swing.JButton();
        button_c8 = new javax.swing.JButton();
        button_d5 = new javax.swing.JButton();
        button_d6 = new javax.swing.JButton();
        button_d7 = new javax.swing.JButton();
        button_d8 = new javax.swing.JButton();
        button_e2 = new javax.swing.JButton();
        button_e3 = new javax.swing.JButton();
        button_e4 = new javax.swing.JButton();
        button_e5 = new javax.swing.JButton();
        button_e6 = new javax.swing.JButton();
        button_e7 = new javax.swing.JButton();
        button_f2 = new javax.swing.JButton();
        button_f3 = new javax.swing.JButton();
        button_f4 = new javax.swing.JButton();
        button_f5 = new javax.swing.JButton();
        button_f6 = new javax.swing.JButton();
        button_f7 = new javax.swing.JButton();
        button_g1 = new javax.swing.JButton();
        button_g2 = new javax.swing.JButton();
        button_g3 = new javax.swing.JButton();
        button_g4 = new javax.swing.JButton();
        button_g5 = new javax.swing.JButton();
        button_g6 = new javax.swing.JButton();
        button_g7 = new javax.swing.JButton();
        button_h1 = new javax.swing.JButton();
        button_h2 = new javax.swing.JButton();
        button_h3 = new javax.swing.JButton();
        button_h4 = new javax.swing.JButton();
        button_h5 = new javax.swing.JButton();
        button_h6 = new javax.swing.JButton();
        button_h7 = new javax.swing.JButton();
        button_e8 = new javax.swing.JButton();
        button_f8 = new javax.swing.JButton();
        button_g8 = new javax.swing.JButton();
        button_h8 = new javax.swing.JButton();
        button_f1 = new javax.swing.JButton();
        button_e1 = new javax.swing.JButton();
        button_d1 = new javax.swing.JButton();
        button_d2 = new javax.swing.JButton();
        button_d3 = new javax.swing.JButton();
        button_d4 = new javax.swing.JButton();
        button_c1 = new javax.swing.JButton();
        button_c2 = new javax.swing.JButton();
        button_c3 = new javax.swing.JButton();
        button_c4 = new javax.swing.JButton();
        button_b1 = new javax.swing.JButton();
        button_b2 = new javax.swing.JButton();
        button_b3 = new javax.swing.JButton();
        button_b4 = new javax.swing.JButton();
        button_a1 = new javax.swing.JButton();
        button_a2 = new javax.swing.JButton();
        button_a3 = new javax.swing.JButton();
        button_a4 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        mainPanel.setLayout(null);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ui.App.class).getContext().getResourceMap(View.class);
        button_a8.setText(resourceMap.getString("button_a8.text")); // NOI18N
        button_a8.setName("button_a8"); // NOI18N
        button_a8.setPreferredSize(new java.awt.Dimension(50, 50));
        button_a8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a8);
        button_a8.setBounds(12, 0, 50, 50);

        button_a7.setAction(button_a8.getAction());
        button_a7.setText(resourceMap.getString("button_a7.text")); // NOI18N
        button_a7.setName("button_a7"); // NOI18N
        button_a7.setPreferredSize(button_a8.getPreferredSize());
        button_a7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a7);
        button_a7.setBounds(12, 56, 50, 50);

        button_a6.setAction(button_a8.getAction());
        button_a6.setText(resourceMap.getString("button_a6.text")); // NOI18N
        button_a6.setName("button_a6"); // NOI18N
        button_a6.setPreferredSize(button_a8.getPreferredSize());
        button_a6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a6);
        button_a6.setBounds(12, 112, 50, 50);

        button_a5.setAction(button_a8.getAction());
        button_a5.setText(resourceMap.getString("button_a5.text")); // NOI18N
        button_a5.setName("button_a5"); // NOI18N
        button_a5.setPreferredSize(button_a8.getPreferredSize());
        button_a5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a5);
        button_a5.setBounds(12, 168, 50, 50);

        button_b5.setAction(button_a8.getAction());
        button_b5.setText(resourceMap.getString("button_b5.text")); // NOI18N
        button_b5.setName("button_b5"); // NOI18N
        button_b5.setPreferredSize(button_a8.getPreferredSize());
        button_b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b5);
        button_b5.setBounds(68, 168, 50, 50);

        button_b6.setAction(button_a8.getAction());
        button_b6.setText(resourceMap.getString("button_b6.text")); // NOI18N
        button_b6.setName("button_b6"); // NOI18N
        button_b6.setPreferredSize(button_a8.getPreferredSize());
        button_b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b6);
        button_b6.setBounds(68, 112, 50, 50);

        button_b7.setAction(button_a8.getAction());
        button_b7.setText(resourceMap.getString("button_b7.text")); // NOI18N
        button_b7.setName("button_b7"); // NOI18N
        button_b7.setPreferredSize(button_a8.getPreferredSize());
        button_b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b7);
        button_b7.setBounds(68, 56, 50, 50);

        button_b8.setAction(button_a8.getAction());
        button_b8.setText(resourceMap.getString("button_b8.text")); // NOI18N
        button_b8.setName("button_b8"); // NOI18N
        button_b8.setPreferredSize(button_a8.getPreferredSize());
        button_b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b8);
        button_b8.setBounds(68, 0, 50, 50);

        button_c5.setAction(button_a8.getAction());
        button_c5.setText(resourceMap.getString("button_c5.text")); // NOI18N
        button_c5.setName("button_c5"); // NOI18N
        button_c5.setPreferredSize(button_a8.getPreferredSize());
        button_c5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c5);
        button_c5.setBounds(124, 168, 50, 50);

        button_c6.setAction(button_a8.getAction());
        button_c6.setText(resourceMap.getString("button_c6.text")); // NOI18N
        button_c6.setName("button_c6"); // NOI18N
        button_c6.setPreferredSize(button_a8.getPreferredSize());
        button_c6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c6);
        button_c6.setBounds(124, 112, 50, 50);

        button_c7.setAction(button_a8.getAction());
        button_c7.setText(resourceMap.getString("button_c7.text")); // NOI18N
        button_c7.setName("button_c7"); // NOI18N
        button_c7.setPreferredSize(button_a8.getPreferredSize());
        button_c7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c7);
        button_c7.setBounds(124, 56, 50, 50);

        button_c8.setAction(button_a8.getAction());
        button_c8.setText(resourceMap.getString("button_c8.text")); // NOI18N
        button_c8.setName("button_c8"); // NOI18N
        button_c8.setPreferredSize(button_a8.getPreferredSize());
        button_c8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c8);
        button_c8.setBounds(124, 0, 50, 50);

        button_d5.setAction(button_a8.getAction());
        button_d5.setText(resourceMap.getString("button_d5.text")); // NOI18N
        button_d5.setName("button_d5"); // NOI18N
        button_d5.setPreferredSize(button_a8.getPreferredSize());
        button_d5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d5);
        button_d5.setBounds(180, 168, 50, 50);

        button_d6.setAction(button_a8.getAction());
        button_d6.setText(resourceMap.getString("button_d6.text")); // NOI18N
        button_d6.setName("button_d6"); // NOI18N
        button_d6.setPreferredSize(button_a8.getPreferredSize());
        button_d6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d6);
        button_d6.setBounds(180, 112, 50, 50);

        button_d7.setAction(button_a8.getAction());
        button_d7.setText(resourceMap.getString("button_d7.text")); // NOI18N
        button_d7.setName("button_d7"); // NOI18N
        button_d7.setPreferredSize(button_a8.getPreferredSize());
        button_d7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d7);
        button_d7.setBounds(180, 56, 50, 50);

        button_d8.setAction(button_a8.getAction());
        button_d8.setText(resourceMap.getString("button_d8.text")); // NOI18N
        button_d8.setName("button_d8"); // NOI18N
        button_d8.setPreferredSize(button_a8.getPreferredSize());
        button_d8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d8);
        button_d8.setBounds(180, 0, 50, 50);

        button_e2.setAction(button_a8.getAction());
        button_e2.setText(resourceMap.getString("button_e2.text")); // NOI18N
        button_e2.setName("button_e2"); // NOI18N
        button_e2.setPreferredSize(button_a8.getPreferredSize());
        button_e2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e2);
        button_e2.setBounds(236, 336, 50, 50);

        button_e3.setAction(button_a8.getAction());
        button_e3.setText(resourceMap.getString("button_e3.text")); // NOI18N
        button_e3.setName("button_e3"); // NOI18N
        button_e3.setPreferredSize(button_a8.getPreferredSize());
        button_e3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e3);
        button_e3.setBounds(236, 280, 50, 50);

        button_e4.setAction(button_a8.getAction());
        button_e4.setText(resourceMap.getString("button_e4.text")); // NOI18N
        button_e4.setName("button_e4"); // NOI18N
        button_e4.setPreferredSize(button_a8.getPreferredSize());
        button_e4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e4);
        button_e4.setBounds(236, 224, 50, 50);

        button_e5.setAction(button_a8.getAction());
        button_e5.setText(resourceMap.getString("button_e5.text")); // NOI18N
        button_e5.setName("button_e5"); // NOI18N
        button_e5.setPreferredSize(button_a8.getPreferredSize());
        button_e5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e5);
        button_e5.setBounds(236, 168, 50, 50);

        button_e6.setAction(button_a8.getAction());
        button_e6.setText(resourceMap.getString("button_e6.text")); // NOI18N
        button_e6.setName("button_e6"); // NOI18N
        button_e6.setPreferredSize(button_a8.getPreferredSize());
        button_e6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e6);
        button_e6.setBounds(236, 112, 50, 50);

        button_e7.setAction(button_a8.getAction());
        button_e7.setText(resourceMap.getString("button_e7.text")); // NOI18N
        button_e7.setName("button_e7"); // NOI18N
        button_e7.setPreferredSize(button_a8.getPreferredSize());
        button_e7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e7);
        button_e7.setBounds(236, 56, 50, 50);

        button_f2.setAction(button_a8.getAction());
        button_f2.setText(resourceMap.getString("button_f2.text")); // NOI18N
        button_f2.setName("button_f2"); // NOI18N
        button_f2.setPreferredSize(button_a8.getPreferredSize());
        button_f2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f2);
        button_f2.setBounds(292, 336, 50, 50);

        button_f3.setAction(button_a8.getAction());
        button_f3.setText(resourceMap.getString("button_f3.text")); // NOI18N
        button_f3.setName("button_f3"); // NOI18N
        button_f3.setPreferredSize(button_a8.getPreferredSize());
        button_f3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f3);
        button_f3.setBounds(292, 280, 50, 50);

        button_f4.setAction(button_a8.getAction());
        button_f4.setText(resourceMap.getString("button_f4.text")); // NOI18N
        button_f4.setName("button_f4"); // NOI18N
        button_f4.setPreferredSize(button_a8.getPreferredSize());
        button_f4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f4);
        button_f4.setBounds(292, 224, 50, 50);

        button_f5.setAction(button_a8.getAction());
        button_f5.setText(resourceMap.getString("button_f5.text")); // NOI18N
        button_f5.setName("button_f5"); // NOI18N
        button_f5.setPreferredSize(button_a8.getPreferredSize());
        button_f5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f5);
        button_f5.setBounds(292, 168, 50, 50);

        button_f6.setAction(button_a8.getAction());
        button_f6.setText(resourceMap.getString("button_f6.text")); // NOI18N
        button_f6.setName("button_f6"); // NOI18N
        button_f6.setPreferredSize(button_a8.getPreferredSize());
        button_f6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f6);
        button_f6.setBounds(292, 112, 50, 50);

        button_f7.setAction(button_a8.getAction());
        button_f7.setText(resourceMap.getString("button_f7.text")); // NOI18N
        button_f7.setName("button_f7"); // NOI18N
        button_f7.setPreferredSize(button_a8.getPreferredSize());
        button_f7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f7);
        button_f7.setBounds(292, 56, 50, 50);

        button_g1.setAction(button_a8.getAction());
        button_g1.setText(resourceMap.getString("button_g1.text")); // NOI18N
        button_g1.setName("button_g1"); // NOI18N
        button_g1.setPreferredSize(button_a8.getPreferredSize());
        button_g1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g1);
        button_g1.setBounds(348, 392, 50, 50);

        button_g2.setAction(button_a8.getAction());
        button_g2.setText(resourceMap.getString("button_g2.text")); // NOI18N
        button_g2.setName("button_g2"); // NOI18N
        button_g2.setPreferredSize(button_a8.getPreferredSize());
        button_g2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g2);
        button_g2.setBounds(348, 336, 50, 50);

        button_g3.setAction(button_a8.getAction());
        button_g3.setText(resourceMap.getString("button_g3.text")); // NOI18N
        button_g3.setName("button_g3"); // NOI18N
        button_g3.setPreferredSize(button_a8.getPreferredSize());
        button_g3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g3);
        button_g3.setBounds(348, 280, 50, 50);

        button_g4.setAction(button_a8.getAction());
        button_g4.setText(resourceMap.getString("button_g4.text")); // NOI18N
        button_g4.setName("button_g4"); // NOI18N
        button_g4.setPreferredSize(button_a8.getPreferredSize());
        button_g4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g4);
        button_g4.setBounds(348, 224, 50, 50);

        button_g5.setAction(button_a8.getAction());
        button_g5.setText(resourceMap.getString("button_g5.text")); // NOI18N
        button_g5.setName("button_g5"); // NOI18N
        button_g5.setPreferredSize(button_a8.getPreferredSize());
        button_g5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g5);
        button_g5.setBounds(348, 168, 50, 50);

        button_g6.setAction(button_a8.getAction());
        button_g6.setText(resourceMap.getString("button_g6.text")); // NOI18N
        button_g6.setName("button_g6"); // NOI18N
        button_g6.setPreferredSize(button_a8.getPreferredSize());
        button_g6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g6);
        button_g6.setBounds(348, 112, 50, 50);

        button_g7.setAction(button_a8.getAction());
        button_g7.setText(resourceMap.getString("button_g7.text")); // NOI18N
        button_g7.setName("button_g7"); // NOI18N
        button_g7.setPreferredSize(button_a8.getPreferredSize());
        button_g7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g7);
        button_g7.setBounds(348, 56, 50, 50);

        button_h1.setAction(button_a8.getAction());
        button_h1.setText(resourceMap.getString("button_h1.text")); // NOI18N
        button_h1.setName("button_h1"); // NOI18N
        button_h1.setPreferredSize(button_a8.getPreferredSize());
        button_h1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h1);
        button_h1.setBounds(404, 392, 50, 50);

        button_h2.setAction(button_a8.getAction());
        button_h2.setText(resourceMap.getString("button_h2.text")); // NOI18N
        button_h2.setName("button_h2"); // NOI18N
        button_h2.setPreferredSize(button_a8.getPreferredSize());
        button_h2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h2);
        button_h2.setBounds(404, 336, 50, 50);

        button_h3.setAction(button_a8.getAction());
        button_h3.setText(resourceMap.getString("button_h3.text")); // NOI18N
        button_h3.setName("button_h3"); // NOI18N
        button_h3.setPreferredSize(button_a8.getPreferredSize());
        button_h3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h3);
        button_h3.setBounds(404, 280, 50, 50);

        button_h4.setAction(button_a8.getAction());
        button_h4.setText(resourceMap.getString("button_h4.text")); // NOI18N
        button_h4.setName("button_h4"); // NOI18N
        button_h4.setPreferredSize(button_a8.getPreferredSize());
        button_h4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h4);
        button_h4.setBounds(404, 224, 50, 50);

        button_h5.setAction(button_a8.getAction());
        button_h5.setText(resourceMap.getString("button_h5.text")); // NOI18N
        button_h5.setName("button_h5"); // NOI18N
        button_h5.setPreferredSize(button_a8.getPreferredSize());
        button_h5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h5button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h5);
        button_h5.setBounds(404, 168, 50, 50);

        button_h6.setAction(button_a8.getAction());
        button_h6.setText(resourceMap.getString("button_h6.text")); // NOI18N
        button_h6.setName("button_h6"); // NOI18N
        button_h6.setPreferredSize(button_a8.getPreferredSize());
        button_h6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h6button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h6);
        button_h6.setBounds(404, 112, 50, 50);

        button_h7.setAction(button_a8.getAction());
        button_h7.setText(resourceMap.getString("button_h7.text")); // NOI18N
        button_h7.setName("button_h7"); // NOI18N
        button_h7.setPreferredSize(button_a8.getPreferredSize());
        button_h7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h7button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h7);
        button_h7.setBounds(404, 56, 50, 50);

        button_e8.setAction(button_a8.getAction());
        button_e8.setText(resourceMap.getString("button_e8.text")); // NOI18N
        button_e8.setName("button_e8"); // NOI18N
        button_e8.setPreferredSize(button_a8.getPreferredSize());
        button_e8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e8);
        button_e8.setBounds(236, 0, 50, 50);

        button_f8.setAction(button_a8.getAction());
        button_f8.setText(resourceMap.getString("button_f8.text")); // NOI18N
        button_f8.setName("button_f8"); // NOI18N
        button_f8.setPreferredSize(button_a8.getPreferredSize());
        button_f8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f8);
        button_f8.setBounds(292, 0, 50, 50);

        button_g8.setAction(button_a8.getAction());
        button_g8.setText(resourceMap.getString("button_g8.text")); // NOI18N
        button_g8.setName("button_g8"); // NOI18N
        button_g8.setPreferredSize(button_a8.getPreferredSize());
        button_g8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_g8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_g8);
        button_g8.setBounds(348, 0, 50, 50);

        button_h8.setAction(button_a8.getAction());
        button_h8.setText(resourceMap.getString("button_h8.text")); // NOI18N
        button_h8.setName("button_h8"); // NOI18N
        button_h8.setPreferredSize(button_a8.getPreferredSize());
        button_h8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_h8button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_h8);
        button_h8.setBounds(404, 0, 50, 50);

        button_f1.setAction(button_a8.getAction());
        button_f1.setText(resourceMap.getString("button_f1.text")); // NOI18N
        button_f1.setName("button_f1"); // NOI18N
        button_f1.setPreferredSize(button_a8.getPreferredSize());
        button_f1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_f1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_f1);
        button_f1.setBounds(292, 392, 50, 50);

        button_e1.setAction(button_a8.getAction());
        button_e1.setText(resourceMap.getString("button_e1.text")); // NOI18N
        button_e1.setName("button_e1"); // NOI18N
        button_e1.setPreferredSize(button_a8.getPreferredSize());
        button_e1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_e1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_e1);
        button_e1.setBounds(236, 392, 50, 50);

        button_d1.setAction(button_a8.getAction());
        button_d1.setText(resourceMap.getString("button_d1.text")); // NOI18N
        button_d1.setName("button_d1"); // NOI18N
        button_d1.setPreferredSize(button_a8.getPreferredSize());
        button_d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d1);
        button_d1.setBounds(180, 392, 50, 50);

        button_d2.setAction(button_a8.getAction());
        button_d2.setText(resourceMap.getString("button_d2.text")); // NOI18N
        button_d2.setName("button_d2"); // NOI18N
        button_d2.setPreferredSize(button_a8.getPreferredSize());
        button_d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d2);
        button_d2.setBounds(180, 336, 50, 50);

        button_d3.setAction(button_a8.getAction());
        button_d3.setText(resourceMap.getString("button_d3.text")); // NOI18N
        button_d3.setName("button_d3"); // NOI18N
        button_d3.setPreferredSize(button_a8.getPreferredSize());
        button_d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d3);
        button_d3.setBounds(180, 280, 50, 50);

        button_d4.setAction(button_a8.getAction());
        button_d4.setText(resourceMap.getString("button_d4.text")); // NOI18N
        button_d4.setName("button_d4"); // NOI18N
        button_d4.setPreferredSize(button_a8.getPreferredSize());
        button_d4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_d4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_d4);
        button_d4.setBounds(180, 224, 50, 50);

        button_c1.setAction(button_a8.getAction());
        button_c1.setText(resourceMap.getString("button_c1.text")); // NOI18N
        button_c1.setName("button_c1"); // NOI18N
        button_c1.setPreferredSize(button_a8.getPreferredSize());
        button_c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c1);
        button_c1.setBounds(124, 392, 50, 50);

        button_c2.setAction(button_a8.getAction());
        button_c2.setText(resourceMap.getString("button_c2.text")); // NOI18N
        button_c2.setName("button_c2"); // NOI18N
        button_c2.setPreferredSize(button_a8.getPreferredSize());
        button_c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c2);
        button_c2.setBounds(124, 336, 50, 50);

        button_c3.setAction(button_a8.getAction());
        button_c3.setText(resourceMap.getString("button_c3.text")); // NOI18N
        button_c3.setName("button_c3"); // NOI18N
        button_c3.setPreferredSize(button_a8.getPreferredSize());
        button_c3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c3);
        button_c3.setBounds(124, 280, 50, 50);

        button_c4.setAction(button_a8.getAction());
        button_c4.setText(resourceMap.getString("button_c4.text")); // NOI18N
        button_c4.setName("button_c4"); // NOI18N
        button_c4.setPreferredSize(button_a8.getPreferredSize());
        button_c4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_c4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_c4);
        button_c4.setBounds(124, 224, 50, 50);

        button_b1.setAction(button_a8.getAction());
        button_b1.setText(resourceMap.getString("button_b1.text")); // NOI18N
        button_b1.setName("button_b1"); // NOI18N
        button_b1.setPreferredSize(button_a8.getPreferredSize());
        button_b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b1);
        button_b1.setBounds(68, 392, 50, 50);

        button_b2.setAction(button_a8.getAction());
        button_b2.setText(resourceMap.getString("button_b2.text")); // NOI18N
        button_b2.setName("button_b2"); // NOI18N
        button_b2.setPreferredSize(button_a8.getPreferredSize());
        button_b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b2);
        button_b2.setBounds(68, 336, 50, 50);

        button_b3.setAction(button_a8.getAction());
        button_b3.setText(resourceMap.getString("button_b3.text")); // NOI18N
        button_b3.setName("button_b3"); // NOI18N
        button_b3.setPreferredSize(button_a8.getPreferredSize());
        button_b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b3);
        button_b3.setBounds(68, 280, 50, 50);

        button_b4.setAction(button_a8.getAction());
        button_b4.setText(resourceMap.getString("button_b4.text")); // NOI18N
        button_b4.setName("button_b4"); // NOI18N
        button_b4.setPreferredSize(button_a8.getPreferredSize());
        button_b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_b4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_b4);
        button_b4.setBounds(68, 224, 50, 50);

        button_a1.setAction(button_a8.getAction());
        button_a1.setText(resourceMap.getString("button_a1.text")); // NOI18N
        button_a1.setName("button_a1"); // NOI18N
        button_a1.setPreferredSize(button_a8.getPreferredSize());
        button_a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a1button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a1);
        button_a1.setBounds(12, 392, 50, 50);

        button_a2.setAction(button_a8.getAction());
        button_a2.setText(resourceMap.getString("button_a2.text")); // NOI18N
        button_a2.setName("button_a2"); // NOI18N
        button_a2.setPreferredSize(button_a8.getPreferredSize());
        button_a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a2button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a2);
        button_a2.setBounds(12, 336, 50, 50);

        button_a3.setAction(button_a8.getAction());
        button_a3.setText(resourceMap.getString("button_a3.text")); // NOI18N
        button_a3.setName("button_a3"); // NOI18N
        button_a3.setPreferredSize(button_a8.getPreferredSize());
        button_a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a3button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a3);
        button_a3.setBounds(12, 280, 50, 50);

        button_a4.setAction(button_a8.getAction());
        button_a4.setText(resourceMap.getString("button_a4.text")); // NOI18N
        button_a4.setName("button_a4"); // NOI18N
        button_a4.setPreferredSize(button_a8.getPreferredSize());
        button_a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_a4button_a8ActionPerformed(evt);
            }
        });
        mainPanel.add(button_a4);
        button_a4.setBounds(12, 224, 50, 50);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ui.App.class).getContext().getActionMap(View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1821, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1637, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a8ActionPerformed

    private void button_a6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a6button_a8ActionPerformed

    private void button_a5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a5button_a8ActionPerformed

    private void button_b5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b5button_a8ActionPerformed

    private void button_b6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b6button_a8ActionPerformed

    private void button_b7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b7button_a8ActionPerformed

    private void button_b8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b8button_a8ActionPerformed

    private void button_c5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c5button_a8ActionPerformed

    private void button_c6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c6button_a8ActionPerformed

    private void button_c7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c7button_a8ActionPerformed

    private void button_c8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c8button_a8ActionPerformed

    private void button_d5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d5button_a8ActionPerformed

    private void button_d6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d6button_a8ActionPerformed

    private void button_d7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d7button_a8ActionPerformed

    private void button_d8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d8button_a8ActionPerformed

    private void button_e2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e2button_a8ActionPerformed

    private void button_e3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e3button_a8ActionPerformed

    private void button_e4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e4button_a8ActionPerformed

    private void button_e5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e5button_a8ActionPerformed

    private void button_e6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e6button_a8ActionPerformed

    private void button_e7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e7button_a8ActionPerformed

    private void button_f2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f2button_a8ActionPerformed

    private void button_f3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f3button_a8ActionPerformed

    private void button_f4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f4button_a8ActionPerformed

    private void button_f5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f5button_a8ActionPerformed

    private void button_f6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f6button_a8ActionPerformed

    private void button_f7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f7button_a8ActionPerformed

    private void button_g1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g1button_a8ActionPerformed

    private void button_g2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g2button_a8ActionPerformed

    private void button_g3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g3button_a8ActionPerformed

    private void button_g4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g4button_a8ActionPerformed

    private void button_g5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g5button_a8ActionPerformed

    private void button_g6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g6button_a8ActionPerformed

    private void button_g7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g7button_a8ActionPerformed

    private void button_h1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h1button_a8ActionPerformed

    private void button_h2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h2button_a8ActionPerformed

    private void button_h3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h3button_a8ActionPerformed

    private void button_h4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h4button_a8ActionPerformed

    private void button_h5button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h5button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h5button_a8ActionPerformed

    private void button_h6button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h6button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h6button_a8ActionPerformed

    private void button_h7button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h7button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h7button_a8ActionPerformed

    private void button_e8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e8button_a8ActionPerformed

    private void button_f8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f8button_a8ActionPerformed

    private void button_g8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_g8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_g8button_a8ActionPerformed

    private void button_h8button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_h8button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_h8button_a8ActionPerformed

    private void button_f1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_f1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_f1button_a8ActionPerformed

    private void button_e1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_e1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_e1button_a8ActionPerformed

    private void button_d1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d1button_a8ActionPerformed

    private void button_d2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d2button_a8ActionPerformed

    private void button_d3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d3button_a8ActionPerformed

    private void button_d4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_d4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_d4button_a8ActionPerformed

    private void button_c1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c1button_a8ActionPerformed

    private void button_c2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c2button_a8ActionPerformed

    private void button_c3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c3button_a8ActionPerformed

    private void button_c4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_c4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_c4button_a8ActionPerformed

    private void button_b1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b1button_a8ActionPerformed

    private void button_b2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b2button_a8ActionPerformed

    private void button_b3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b3button_a8ActionPerformed

    private void button_b4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_b4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_b4button_a8ActionPerformed

    private void button_a1button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a1button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a1button_a8ActionPerformed

    private void button_a2button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a2button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a2button_a8ActionPerformed

    private void button_a3button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a3button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a3button_a8ActionPerformed

    private void button_a4button_a8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_a4button_a8ActionPerformed
        Handling.doClick( ((javax.swing.JButton) evt.getSource()) );
    }//GEN-LAST:event_button_a4button_a8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_a1;
    private javax.swing.JButton button_a2;
    private javax.swing.JButton button_a3;
    private javax.swing.JButton button_a4;
    private javax.swing.JButton button_a5;
    private javax.swing.JButton button_a6;
    private javax.swing.JButton button_a7;
    private javax.swing.JButton button_a8;
    private javax.swing.JButton button_b1;
    private javax.swing.JButton button_b2;
    private javax.swing.JButton button_b3;
    private javax.swing.JButton button_b4;
    private javax.swing.JButton button_b5;
    private javax.swing.JButton button_b6;
    private javax.swing.JButton button_b7;
    private javax.swing.JButton button_b8;
    private javax.swing.JButton button_c1;
    private javax.swing.JButton button_c2;
    private javax.swing.JButton button_c3;
    private javax.swing.JButton button_c4;
    private javax.swing.JButton button_c5;
    private javax.swing.JButton button_c6;
    private javax.swing.JButton button_c7;
    private javax.swing.JButton button_c8;
    private javax.swing.JButton button_d1;
    private javax.swing.JButton button_d2;
    private javax.swing.JButton button_d3;
    private javax.swing.JButton button_d4;
    private javax.swing.JButton button_d5;
    private javax.swing.JButton button_d6;
    private javax.swing.JButton button_d7;
    private javax.swing.JButton button_d8;
    private javax.swing.JButton button_e1;
    private javax.swing.JButton button_e2;
    private javax.swing.JButton button_e3;
    private javax.swing.JButton button_e4;
    private javax.swing.JButton button_e5;
    private javax.swing.JButton button_e6;
    private javax.swing.JButton button_e7;
    private javax.swing.JButton button_e8;
    private javax.swing.JButton button_f1;
    private javax.swing.JButton button_f2;
    private javax.swing.JButton button_f3;
    private javax.swing.JButton button_f4;
    private javax.swing.JButton button_f5;
    private javax.swing.JButton button_f6;
    private javax.swing.JButton button_f7;
    private javax.swing.JButton button_f8;
    private javax.swing.JButton button_g1;
    private javax.swing.JButton button_g2;
    private javax.swing.JButton button_g3;
    private javax.swing.JButton button_g4;
    private javax.swing.JButton button_g5;
    private javax.swing.JButton button_g6;
    private javax.swing.JButton button_g7;
    private javax.swing.JButton button_g8;
    private javax.swing.JButton button_h1;
    private javax.swing.JButton button_h2;
    private javax.swing.JButton button_h3;
    private javax.swing.JButton button_h4;
    private javax.swing.JButton button_h5;
    private javax.swing.JButton button_h6;
    private javax.swing.JButton button_h7;
    private javax.swing.JButton button_h8;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
