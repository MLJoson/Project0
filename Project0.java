//Name: Marco Joson
//Date: 5/06/24
//Project: CS1400 Final - BMI
//Project Description: Write a program that takes a person's height and weight in pounds and returns the body mass index(BMI).  

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.text.DecimalFormat;


public class Project0 extends JFrame implements ActionListener {
  // declare our components or fields
  // a field is a global level variable
  JTextField txtWeight = new JTextField(5);
  JTextField txtHeightFeet = new JTextField(5);
  JTextField txtHeightInches = new JTextField(5);
  // declare textArea
  JTextArea txaBMI = new JTextArea("BMI: " + "\t" + "Average BMI: " + "\t" + "Count: " + "\t" + "Stand. Dev.: " + "Standing: " + "\n",
      20, 50);
  JButton btnAdd = new JButton("Add to List");

  // declare variables to hold information.
  String WeightString;
  String HeightFeetString;
  String HeightInchesString;
  String finalBMI;
  String BMIAverageString;
  String TotalCountString;
  String StandDevString;
  String BMIStanding;

  double Weight;
  double HeightFeet;
  double HeightInches;
  double HeightTotal;
  double BMI;
  double BMITotal;
  double BMIAverage;
  double TotalCount;
  double finalHeight;
  double BMISq;
  double BMISD;

  ArrayList<Double> BMIList = new ArrayList<Double>();
  DecimalFormat df = new DecimalFormat("#.##");

  // main is the first method to run - method means function
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // declare a frame for form
    Project0 frame = new Project0();
    frame.setSize(650, 650);
    frame.setVisible(true);

  }

  // declare constructor for the project
  // The constructor sets everything up.
  public Project0() {

    super("BMI");



    System.out.print("hello world!");



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // set layout manager
    setLayout(new FlowLayout());
    // add our components to the form
    add(new JLabel("Weight (in pounds):    "));
    add(txtWeight);
    add(new JLabel("Height (feet):    "));
    add(txtHeightFeet);
    add(new JLabel("Height (inches):    "));
    add(txtHeightInches);
    add(btnAdd);
    add(txaBMI);
    // *********
    // add listener to the button
    btnAdd.addActionListener(this);
  }// end of constructor

  // when you push the button - the code comes here

  public void actionPerformed(ActionEvent event) {
    Object objSource = event.getSource();
    if (objSource == btnAdd) {

      String outputString = "";
      // get information from textboxes
      WeightString = txtWeight.getText();
      HeightFeetString = txtHeightFeet.getText();
      HeightInchesString = txtHeightInches.getText();

      // converts string into doubles
      Weight = Double.parseDouble(WeightString);
      HeightFeet = Double.parseDouble(HeightFeetString);
      HeightInches = Double.parseDouble(HeightInchesString);

      // math
      TotalCount += 1;
      // calls seperate methods
      finalHeight = MathHeight(HeightFeet, HeightInches);
      BMI = MathBMI(Weight, finalHeight);
      BMIList.add(BMI);
      BMITotal += BMI;
      BMISq += Math.pow(BMITotal, 2);
      BMIAverage = MathAverage(BMITotal, TotalCount);
      BMISD = MathSD(BMITotal, TotalCount, BMIAverage);
      BMIStanding = mathBMIStanding(BMI);

      finalBMI = String.format("%.2f", BMI);
      BMIAverageString = String.format("%.2f", BMIAverage);
      TotalCountString = String.format("%.2f", TotalCount);
      StandDevString = String.format("%.2f", BMISD);;
      // concatenate the text together
      outputString = finalBMI + "\t" + BMIAverageString + "\t" + TotalCountString + "\t" + StandDevString + "\t" + BMIStanding + "\n";
      // output to the text area
      txaBMI.append(outputString);

      // clear text boxes
      txtWeight.setText("");
      txtHeightFeet.setText("");
      txtHeightInches.setText("");
    }
  }
  
  public String mathBMIStanding(double mathBMI)
  {
    String BMIStanding = "";

    if (mathBMI < 18.5)
    {
      BMIStanding = "Underweight";
    }
    else if (mathBMI >= 18.5 && mathBMI <= 24.9)
    {
      BMIStanding = "Normal";
    }
    else if (mathBMI >= 25.0 && mathBMI <= 29.9)
    {
      BMIStanding = "Overweight";
    }
    else if (mathBMI >= 30.0)
    {
      BMIStanding = "Obese";
    }
    return BMIStanding;
  }

  public double MathHeight(double mathFeet, double mathInches) {
    double finalHeight = 0.00;
    finalHeight = (mathFeet * 12) + mathInches;
    finalHeight *= 0.0254;
    return finalHeight;
  }

  public double MathBMI(double mathWeight, double mathHeight) {
    double mathFinalBMI = 0.00;
    mathWeight *= 0.453592;
    mathFinalBMI = mathWeight / (mathHeight * mathHeight);
    mathFinalBMI *= 100;
    mathFinalBMI = Math.round(mathFinalBMI);
    mathFinalBMI /= 100;
    return mathFinalBMI;
  }

  public double MathAverage(double mathTotalBMI, double mathTotalCount) {
    double AverageBMI = 0.00;
    AverageBMI = mathTotalBMI / mathTotalCount;
    return AverageBMI;
  }

  public double MathSD(double mathTotalBMI, double mathTotalCount, double mathBMIAverage) 
  {
    double sumSquaredDiff = 0;
    for (double BMI : BMIList) {
        sumSquaredDiff += Math.pow(BMI - mathBMIAverage, 2);
    }

    double variance = sumSquaredDiff / mathTotalCount;

    double standardDeviation = Math.sqrt(variance);
    df.format(standardDeviation);
    return standardDeviation;
  }
}// end of class