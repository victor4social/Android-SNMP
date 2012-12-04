/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.androidsnmp.manager.gui;

import com.androidsnmp.manager.main.AndroidSNMPManager;
import com.androidsnmp.manager.models.ManagedDevice;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author brnunes
 */
public class ManagerFrame extends javax.swing.JFrame {
    private static final String blank = "blank";
    private CardLayout cardLayout;
    private AndroidSNMPManager androidSMNPManager;
    
    /**
     * Creates new form ManagerFrame
     */
    public ManagerFrame(AndroidSNMPManager androidSNMPManager) {
        this.androidSMNPManager = androidSNMPManager;
        
        initComponents();
        
        cardLayout = new CardLayout();
        dummyPhonePanel.setLayout(cardLayout);
        
        cardLayout.addLayoutComponent(new JPanel(), blank);
        
        devicesList.setCellRenderer(new ManagedDeviceCellRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        devicesListScrollPane = new javax.swing.JScrollPane();
        devicesList = new javax.swing.JList(androidSMNPManager.getSampleModel());
        dummyPhonePanel = new javax.swing.JPanel();
        addDeviceButton = new javax.swing.JButton();
        editDeviceButton = new javax.swing.JButton();
        removeDeviceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Android SNMP Manager");
        setResizable(false);

        devicesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                devicesListValueChanged(evt);
            }
        });
        devicesListScrollPane.setViewportView(devicesList);

        dummyPhonePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dummyPhonePanel.setPreferredSize(new java.awt.Dimension(640, 360));
        dummyPhonePanel.setLayout(null);

        addDeviceButton.setText("Add");
        addDeviceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDeviceButtonActionPerformed(evt);
            }
        });

        editDeviceButton.setText("Edit");
        editDeviceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDeviceButtonActionPerformed(evt);
            }
        });

        removeDeviceButton.setText("Remove");
        removeDeviceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDeviceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(devicesListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addDeviceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editDeviceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeDeviceButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(dummyPhonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dummyPhonePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(devicesListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addDeviceButton)
                            .addComponent(removeDeviceButton)
                            .addComponent(editDeviceButton))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addDeviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDeviceButtonActionPerformed
        String ip = "___.___.___.___";
        
        do {
            ip = (String) JOptionPane.showInputDialog(this, "Enter the IP address of the device:",
                    "Add Device", JOptionPane.PLAIN_MESSAGE, null, null, ip);

            if (ip != null) {
                if (ManagedDevice.isIpValid(ip)) {
                    if (androidSMNPManager.hasDevice(ip)) {
                        JOptionPane.showMessageDialog(this, "There is already one device with this IP!");
                    } else {
                        ManagedDevice device = new ManagedDevice(ip);
                        androidSMNPManager.addManagedDevice(device);
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid IP Address!\nMake sure the IP is in the format [0..255].[0..255].[0..255].[0..255]");
                }
            }
        } while (ip != null);
    }//GEN-LAST:event_addDeviceButtonActionPerformed

    private void devicesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_devicesListValueChanged
        if(devicesList.getSelectedIndex() == -1) {
            cardLayout.show(dummyPhonePanel, blank);
        } else {
            ManagedDevice device = androidSMNPManager.getManagedDevice(devicesList.getSelectedIndex());
            cardLayout.show(dummyPhonePanel, device.getIp());
        }
    }//GEN-LAST:event_devicesListValueChanged

    private void editDeviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDeviceButtonActionPerformed
        int selectedIndex = devicesList.getSelectedIndex();
        String ip = androidSMNPManager.getManagedDevice(selectedIndex).getIp();
        
        do {
            ip = (String) JOptionPane.showInputDialog(this, "Enter the IP address of the device:",
                    "Edit Device", JOptionPane.PLAIN_MESSAGE, null, null, ip);

            if (ip != null) {
                if (ManagedDevice.isIpValid(ip)) {
                    if (androidSMNPManager.hasDevice(ip)) {
                        JOptionPane.showMessageDialog(this, "There is already one device with this IP!");
                    } else {
                        androidSMNPManager.removeManagedDevice(selectedIndex);
                        ManagedDevice device = new ManagedDevice(ip);
                        androidSMNPManager.addManagedDevice(device, selectedIndex);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid IP Address!\nMake sure the IP is in the format [0..255].[0..255].[0..255].[0..255]");
                }
            }
        } while (ip != null);
    }//GEN-LAST:event_editDeviceButtonActionPerformed

    private void removeDeviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDeviceButtonActionPerformed
        if(devicesList.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "No device selected!");
        } else {
            if(JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this device?",
                    "Remove '" + androidSMNPManager.getManagedDevice(devicesList.getSelectedIndex()).getIp() + "'?",
                    JOptionPane.YES_NO_OPTION) == 0) {
                androidSMNPManager.removeManagedDevice(devicesList.getSelectedIndex());
            }
        }
    }//GEN-LAST:event_removeDeviceButtonActionPerformed

    public void addPhonePanel(ManagedDevice device, Object constraints) {
        dummyPhonePanel.add(device.getPhonePanel(), constraints);
        devicesList.setSelectedValue(device, true);
    }
    
    public void removePhonePanel(PhonePanel phonePanel) {
        dummyPhonePanel.remove(phonePanel);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDeviceButton;
    private javax.swing.JList devicesList;
    private javax.swing.JScrollPane devicesListScrollPane;
    private javax.swing.JPanel dummyPhonePanel;
    private javax.swing.JButton editDeviceButton;
    private javax.swing.JButton removeDeviceButton;
    // End of variables declaration//GEN-END:variables
}