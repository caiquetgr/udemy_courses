/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intefacegrafica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import opencv.ExtratorImagem;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author cborges
 */
public class Preditor extends javax.swing.JFrame {

    private Instances instances;
    private int quantidadeHomer = 0, quantidadeBart = 0;
    
    /**
     * Creates new form Preditor
     */
    public Preditor() {
        initComponents();
    }
    
    public void classifica() throws Exception{
        classificaNaiveBayes();
        classificaJ48();
        classificaRegras();
        classificaIbk();
        classificaSVM();
        classificaMultilayer();
    }
    
    public void classificaNaiveBayes() throws Exception{
        
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(instances);
        
        Instance novaInstance = new DenseInstance(instances.numAttributes());

        novaInstance.setDataset(instances);
        novaInstance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novaInstance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
        novaInstance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
        novaInstance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novaInstance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novaInstance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
 
        double[] resultado = nb.distributionForInstance(novaInstance);
        
        DecimalFormat df = new DecimalFormat("###,##0.00##");
        
        lblNaiveBart.setText(df.format(resultado[0] * 100) + "%");
        lblNaiveHomer.setText(df.format(resultado[1] * 100) + "%");
                
    }
    
    public void classificaJ48() throws Exception{
        
        J48 j48 = new J48();
        
        j48.buildClassifier(instances);
        
        Instance novaInstance = new DenseInstance(instances.numAttributes());
        
        novaInstance.setDataset(instances);
        novaInstance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novaInstance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
        novaInstance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
        novaInstance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novaInstance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novaInstance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
        
        double[] resultado = j48.distributionForInstance(novaInstance);
        
        DecimalFormat df = new DecimalFormat("###,##0.00##");
        
        lblJ48Bart.setText(df.format(resultado[0] * 100) + "%");
        lblJ48Homer.setText(df.format(resultado[1] * 100) + "%");
                
    }
    
    public void classificaRegras() throws Exception{
        
        OneR oneR = new OneR();
        JRip jRip = new JRip();
               
        oneR.buildClassifier(instances);
        jRip.buildClassifier(instances);
        
        Instance instance = new DenseInstance(instances.numAttributes());
        instance.setDataset(instances);
        instance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        instance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
        instance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
        instance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        instance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        instance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
        
        double[] resultadoOneR = oneR.distributionForInstance(instance);
        double[] resultadoJRip = jRip.distributionForInstance(instance);
        
        DecimalFormat df = new DecimalFormat("###,##0.00##");
        
        lblOneRBart.setText(df.format(resultadoOneR[0] * 100) + "%");
        lblOneRHomer.setText(df.format(resultadoOneR[1] * 100) + "%");
        
        lblJRipBart.setText(df.format(resultadoJRip[0] * 100) + "%");
        lblJRipHomer.setText(df.format(resultadoJRip[1] * 100) + "%");
        
    }
    
    public void classificaIbk() throws Exception{
        
        IBk ibk = new IBk(3);
        
        ibk.buildClassifier(instances);
        
        Instance instance = new DenseInstance(instances.numAttributes());
        instance.setDataset(instances);
        instance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        instance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
        instance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
        instance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        instance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        instance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
        
        double[] resultado = ibk.distributionForInstance(instance);
        
        DecimalFormat df = new DecimalFormat("###,##0.00##");
        
        lblIbkBart.setText( df.format(resultado[0] * 100) + "%" );
        lblIbkHomer.setText( df.format(resultado[1] * 100) + "%" );
    }
    
    public void classificaSVM() throws Exception{
        
        LibSVM svm = new LibSVM();

        svm.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_LINEAR,
            LibSVM.TAGS_KERNELTYPE));
        
        svm.buildClassifier(instances);
        
        Instance instance = new DenseInstance(instances.numAttributes());
        instance.setDataset(instances);
        instance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        instance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
        instance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
        instance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        instance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        instance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
        
        double[] resultado = svm.distributionForInstance(instance);
        
        DecimalFormat df = new DecimalFormat("###,##0.00##");
        
        lblSvmBart.setText( df.format(resultado[0] * 100) + "%" );
        lblSvmHomer.setText( df.format(resultado[1] * 100) + "%" );
        
    }
    
    private void classificaMultilayer() {
        
        MultilayerPerceptron multi = new MultilayerPerceptron();
        Instance instance = null;
        
        try {
            
            multi.buildClassifier(instances);
            instance = new DenseInstance(instances.numAttributes());
            
            instance.setDataset(instances);
            instance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
            instance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
            instance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
            instance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
            instance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
            instance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
            
            DecimalFormat df = new DecimalFormat("###,##0.00##");
            
            double[] resultado = multi.distributionForInstance(instance);
            lblMultilayerBart.setText( df.format(resultado[0] * 100) + "%" );
            lblMultilayerHomer.setText( df.format(resultado[1] * 100) + "%");
            
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    public void carregaBaseWeka(){

        DataSource ds = null;
        
        try {
            ds = new DataSource("caracteristicas.arff");
            instances = ds.getDataSet();
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        instances.setClassIndex( instances.numAttributes() - 1 );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelecionarImagem = new javax.swing.JButton();
        txtCaminhoImagem = new javax.swing.JTextField();
        lblImagem = new javax.swing.JLabel();
        btnExtrairCaracteristicas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLaranjaBart = new javax.swing.JLabel();
        lblAzulCalcao = new javax.swing.JLabel();
        lblAzulSapato = new javax.swing.JLabel();
        lblAzulHomer = new javax.swing.JLabel();
        lblMarromHomer = new javax.swing.JLabel();
        lblSapatoHomer = new javax.swing.JLabel();
        btnClassificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblNaiveBart = new javax.swing.JLabel();
        lblNaiveHomer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblJ48Homer = new javax.swing.JLabel();
        lblJ48Bart = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblOneRBart = new javax.swing.JLabel();
        lblOneRHomer = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblJRipBart = new javax.swing.JLabel();
        lblJRipHomer = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblIbkBart = new javax.swing.JLabel();
        lblIbkHomer = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSvmBart = new javax.swing.JLabel();
        lblSvmHomer = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblMultilayerBart = new javax.swing.JLabel();
        lblMultilayerHomer = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSelecionarImagem.setText("Selecionar imagem");
        btnSelecionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarImagemActionPerformed(evt);
            }
        });

        lblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExtrairCaracteristicas.setText("Extrair caracteristicas");
        btnExtrairCaracteristicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrairCaracteristicasActionPerformed(evt);
            }
        });

        jLabel1.setText("Caracteristicas do Bart");

        jLabel2.setText("Caracteristicas do Homer");

        lblLaranjaBart.setText("jLabel3");

        lblAzulCalcao.setText("jLabel4");

        lblAzulSapato.setText("jLabel5");

        lblAzulHomer.setText("jLabel6");

        lblMarromHomer.setText("jLabel7");

        lblSapatoHomer.setText("jLabel8");

        btnClassificar.setText("Classificar");
        btnClassificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassificarActionPerformed(evt);
            }
        });

        jLabel3.setText("Naive Bayes");

        lblNaiveBart.setText("jLabel4");

        lblNaiveHomer.setText("jLabel4");

        jLabel4.setText("J48");

        lblJ48Homer.setText("jLabel5");

        lblJ48Bart.setText("jLabel5");

        jLabel5.setText("Homer:");

        jLabel6.setText("Bart:");

        jLabel7.setText("OneR");

        lblOneRBart.setText("jLabel8");

        lblOneRHomer.setText("jLabel8");

        jLabel8.setText("JRip");

        lblJRipBart.setText("jLabel9");

        lblJRipHomer.setText("jLabel9");

        jLabel9.setText("IBk");

        lblIbkBart.setText("jLabel10");

        lblIbkHomer.setText("jLabel10");

        jLabel10.setText("SVM");

        lblSvmBart.setText("jLabel11");

        lblSvmHomer.setText("jLabel11");

        jLabel11.setText("Multilayer");

        lblMultilayerBart.setText("jLabel12");

        lblMultilayerHomer.setText("jLabel12");

        jButton2.setText("Treinar / Gerar Modelo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Classificar usando modelo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblLaranjaBart)
                                    .addComponent(lblAzulCalcao)
                                    .addComponent(lblAzulSapato)
                                    .addComponent(btnExtrairCaracteristicas))
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClassificar)
                                    .addComponent(lblSapatoHomer)
                                    .addComponent(lblMarromHomer)
                                    .addComponent(lblAzulHomer)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(lblNaiveBart)
                                    .addComponent(lblNaiveHomer)
                                    .addComponent(jLabel10)
                                    .addComponent(lblSvmBart)
                                    .addComponent(lblSvmHomer))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel4)
                                                .addGap(41, 41, 41)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblJ48Bart)
                                                    .addComponent(lblJ48Homer))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblOneRHomer)
                                                    .addComponent(lblOneRBart))))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblJRipBart)
                                            .addComponent(lblJRipHomer)
                                            .addComponent(jLabel8))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(lblIbkBart)
                                            .addComponent(lblIbkHomer)))
                                    .addComponent(jLabel11)
                                    .addComponent(lblMultilayerBart)
                                    .addComponent(lblMultilayerHomer))))
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelecionarImagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCaminhoImagem)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionarImagem)
                    .addComponent(txtCaminhoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExtrairCaracteristicas)
                            .addComponent(btnClassificar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLaranjaBart)
                            .addComponent(lblAzulHomer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAzulCalcao)
                            .addComponent(lblMarromHomer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAzulSapato)
                            .addComponent(lblSapatoHomer))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNaiveBart)
                            .addComponent(jLabel6)
                            .addComponent(lblJ48Bart)
                            .addComponent(lblOneRBart)
                            .addComponent(lblJRipBart)
                            .addComponent(lblIbkBart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNaiveHomer)
                            .addComponent(jLabel5)
                            .addComponent(lblJ48Homer)
                            .addComponent(lblOneRHomer)
                            .addComponent(lblJRipHomer)
                            .addComponent(lblIbkHomer))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSvmBart)
                            .addComponent(lblMultilayerBart))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSvmHomer)
                            .addComponent(lblMultilayerHomer)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarImagemActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        int retorno = chooser.showDialog(this, "Selecione a imagem");
        
        if(retorno == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            txtCaminhoImagem.setText(file.getAbsolutePath());
            
            BufferedImage imgBmp = null;
            
            try {
                imgBmp = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ImageIcon image = new ImageIcon(imgBmp);
            
            lblImagem.setIcon(new ImageIcon(image.getImage().getScaledInstance(lblImagem.getWidth(),
                    lblImagem.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_btnSelecionarImagemActionPerformed

    private void btnExtrairCaracteristicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrairCaracteristicasActionPerformed
        ExtratorImagem extrator = new ExtratorImagem();
        
        float[] caracteristicas = extrator.extrairCaracteristicasImagem(txtCaminhoImagem.getText());
        
        lblLaranjaBart.setText(String.valueOf(caracteristicas[0]));
        lblAzulCalcao.setText(String.valueOf(caracteristicas[1]));
        lblAzulSapato.setText(String.valueOf(caracteristicas[2]));
        lblMarromHomer.setText(String.valueOf(caracteristicas[3]));
        lblAzulHomer.setText(String.valueOf(caracteristicas[4]));
        lblSapatoHomer.setText(String.valueOf(caracteristicas[5]));
        
    }//GEN-LAST:event_btnExtrairCaracteristicasActionPerformed

    private void btnClassificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassificarActionPerformed
        try {
            carregaBaseWeka();
            classifica();
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClassificarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        carregaBaseWeka();
        
        J48 j48 = new J48();
        try {
            j48.buildClassifier(instances);
            ObjectOutputStream classificador = new ObjectOutputStream(new FileOutputStream("src/opencv/arvore_treinada.model"));
            
            classificador.writeObject(j48);
            classificador.flush();
            classificador.close();
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("modelos/j48unpruned.model"));
            J48 j48 = (J48)ois.readObject();
            ois.close();
            
            ois = new ObjectInputStream(new FileInputStream("modelos/ibk3.model"));
            IBk ibk = (IBk)ois.readObject();
            ois.close();
            
            ois = new ObjectInputStream(new FileInputStream("modelos/multilayerPerceptron.model"));
            MultilayerPerceptron neural = (MultilayerPerceptron) ois.readObject();
            ois.close();
             
            
            carregaBaseWeka();
            
            Instance instance = new DenseInstance(instances.numAttributes());
            instance.setDataset(instances);
            
            instance.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
            instance.setValue(1, Float.parseFloat(lblAzulCalcao.getText()));
            instance.setValue(2, Float.parseFloat(lblAzulSapato.getText()));
            instance.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
            instance.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
            instance.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));
            
            DecimalFormat df = new DecimalFormat("###,##0.00##");
            
            double[] resultado = j48.distributionForInstance(instance);
            System.out.println("J48");
            System.out.println("Bart: " + df.format(resultado[0] * 100) + "%" );
            System.out.println("Homer: " + df.format(resultado[1] * 100) + "%");
            
            verificaResultado(resultado);
            
            resultado = ibk.distributionForInstance(instance);
            System.out.println("IBk");
            System.out.println("Bart: " + df.format(resultado[0] * 100) + "%" );
            System.out.println("Homer: " + df.format(resultado[1] * 100) + "%");
            
            verificaResultado(resultado);
            
            resultado = neural.distributionForInstance(instance);
            System.out.println("Neural Network");
            System.out.println("Bart: " + df.format(resultado[0] * 100) + "%" );
            System.out.println("Homer: " + df.format(resultado[1] * 100) + "%");
            
            verificaResultado(resultado);
            
            System.out.println("Quantidade Bart: " + quantidadeBart);
            System.out.println("Quantidade Homer: " + quantidadeHomer);
            
            if(quantidadeBart > quantidadeHomer){
                System.out.println("A classe é Bart");
            } else {
                System.out.println("A classe é Homer");
            }
            
            quantidadeBart = 0;
            quantidadeHomer = 0;
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void verificaResultado(double[] resultado){
        
        final double rejeicao = 0.8;
        
        if(resultado[0] > resultado[1]){
            
            if(resultado[0] > rejeicao)
                quantidadeBart++;
            
        } else if(resultado[1] > resultado[0]) {
            
            if(resultado[1] > rejeicao)
                quantidadeHomer++;
            
        } else {
            if(resultado[0] > rejeicao && resultado[1] > rejeicao){
                quantidadeBart++;
                quantidadeHomer++;
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Preditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClassificar;
    private javax.swing.JButton btnExtrairCaracteristicas;
    private javax.swing.JButton btnSelecionarImagem;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAzulCalcao;
    private javax.swing.JLabel lblAzulHomer;
    private javax.swing.JLabel lblAzulSapato;
    private javax.swing.JLabel lblIbkBart;
    private javax.swing.JLabel lblIbkHomer;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblJ48Bart;
    private javax.swing.JLabel lblJ48Homer;
    private javax.swing.JLabel lblJRipBart;
    private javax.swing.JLabel lblJRipHomer;
    private javax.swing.JLabel lblLaranjaBart;
    private javax.swing.JLabel lblMarromHomer;
    private javax.swing.JLabel lblMultilayerBart;
    private javax.swing.JLabel lblMultilayerHomer;
    private javax.swing.JLabel lblNaiveBart;
    private javax.swing.JLabel lblNaiveHomer;
    private javax.swing.JLabel lblOneRBart;
    private javax.swing.JLabel lblOneRHomer;
    private javax.swing.JLabel lblSapatoHomer;
    private javax.swing.JLabel lblSvmBart;
    private javax.swing.JLabel lblSvmHomer;
    private javax.swing.JTextField txtCaminhoImagem;
    // End of variables declaration//GEN-END:variables


}
