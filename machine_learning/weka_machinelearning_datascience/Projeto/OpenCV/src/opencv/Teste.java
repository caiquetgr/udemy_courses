/*
 * To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.text.SimpleDateFormat;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;


/**
 *
 * @author cborges
 */
public class Teste {
    public static void main(String[] args) throws Exception {
        
        DataSource ds = new DataSource("caracteristicas.arff");
        
        int folds = 10;
        int rodadas = 30;
        
        MultilayerPerceptron multi = new MultilayerPerceptron();
        multi.setTrainingTime(2200);
        multi.setHiddenLayers("a, i");
        multi.setLearningRate(0.2);
        multi.setMomentum(0.3);
        multi.setSeed(1);
        
        Classifier classifier = multi;
        Instances dataSet = ds.getDataSet();

        dataSet.setClassIndex( dataSet.numAttributes() - 1 );
        
        long inicio = System.currentTimeMillis();
        
        for(int i = 1; i <= rodadas; i++){
            
            Evaluation avaliador = new Evaluation(dataSet);
            
            avaliador.crossValidateModel(classifier, dataSet, folds, new Random(i));
            
            System.out.println(String.valueOf(avaliador.pctCorrect()).replace('.', ','));
        }
        
        long fim = System.currentTimeMillis();
        
        System.out.println(fim - inicio);
        
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        
        System.out.println(sdf.format(fim - inicio));
    }
}
