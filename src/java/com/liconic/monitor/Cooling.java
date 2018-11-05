/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liconic.monitor;

import java.util.TimerTask;

/**
 *
 * @author Rucha Deshpande
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
/**
 *
 * @author Rucha Deshpande
 */
class Cooling extends TimerTask {
     private Monitor monitor;
     int count = 0;

    double temp,dischargeSecond, suctionSecond, chilledWater, dischargeFirst, dryer, stepper, pressureFirst, pressureSecond, oil;    
    double hightemp, highdischargeSecond, highsuctionSecond, highchilledWater, highdischargeFirst, highdryer, highstepper, highpressureFirst, highpressureSecond; 
    public Cooling(Monitor monitor) {
        this.monitor = monitor;
    }
    
      @Override
    public void run() {
     try{
        File file = new File(monitor.getDBLocation());
        FileInputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null, tmp;
        while ((tmp = br.readLine()) != null)
        {
            line = tmp;
        }
        String[] lastRow = line.split("	  ");
//
//        for(int i = 0;i<lastRow.length;i++)
//        {
//            System.out.println(i + "  "  + lastRow[i]);
//        }

        temp = Double.parseDouble(lastRow[1]);//row.getCell(1).getNumericCellValue();
        dischargeSecond = Double.parseDouble(lastRow[2]);//row.getCell(2).getNumericCellValue();
        suctionSecond = Double.parseDouble(lastRow[4]);//row.getCell(4).getNumericCellValue();
        chilledWater = Double.parseDouble(lastRow[7]);//row.getCell(7).getNumericCellValue();
        dischargeFirst = Double.parseDouble(lastRow[9]);//row.getCell(9).getNumericCellValue();
        dryer = Double.parseDouble(lastRow[14]);//row.getCell(14).getNumericCellValue();
        stepper = Double.parseDouble(lastRow[16]);//row.getCell(16).getNumericCellValue();
        pressureFirst =Double.parseDouble(lastRow[21]);//row.getCell(21).getNumericCellValue();
        pressureSecond  = Double.parseDouble(lastRow[23]);//row.getCell(23).getNumericCellValue();
        //oil  = row.getCell(23).getNumericCellValue();
        
        System.out.println("Current parameter values are: " + temp + " " + dischargeSecond + " " +  suctionSecond + " " + chilledWater + " " +  dischargeFirst + " " +  dryer + " " + stepper + " " + pressureFirst + " " + pressureSecond + " " + oil);
        if(temp < monitor.getTempMin() || temp > monitor.getTempMax())
        {
            hightemp = temp;
            count ++;
            System.out.println("Temperature is out of bound");
        }
        else if(dischargeSecond < monitor.getDischargeSecondMin() || dischargeSecond > monitor.getDischargeSecondMax())
        {
            highdischargeSecond = dischargeSecond;
            count ++; 
            System.out.println("dischargeSecond is out of bound");
        }
        else if(suctionSecond < monitor.getSuctionSecondMin() || suctionSecond > monitor.getSuctionSecondMax())
        {
            highsuctionSecond = suctionSecond;
            count ++; 
             System.out.println("suctionSecond is out of bound");
        }
        else if(chilledWater < monitor.getChilledWaterMin() || chilledWater > monitor.getChilledWaterMax())
        {
            highchilledWater = chilledWater;
             count ++;
              System.out.println("chilledWater is out of bound");
        }
        else if(dischargeFirst < monitor.getDischargeFirstMin() || dischargeFirst > monitor.getDischargeFirstMax())
        {
            highdischargeFirst = dischargeFirst;
             count ++;
              System.out.println("dischargeFirst is out of bound");
        }
        else if(dryer < monitor.getDryerMin() || dryer > monitor.getDryerMax())
        {
            highdryer = dryer;
             count ++;
              System.out.println("dryer is out of bound");
        }
        else if(stepper < monitor.getStepperMin() || stepper > monitor.getStepperMax())
        {
            highstepper = stepper;
             count ++;
              System.out.println("stepper is out of bound");
        }
        else if(pressureFirst < monitor.getPressureFirstMin() || pressureFirst > monitor.getPressureFirstMax())
        {
            highpressureFirst = pressureFirst;
             count ++;
              System.out.println("pressureFirst is out of bound");
        }
        else if(pressureSecond < monitor.getPressureSecondMin() || pressureSecond > monitor.getPressureSecondMax())
        {
            highpressureSecond = pressureSecond;
             count ++;
              System.out.println("pressureSecond is out of bound");
        }
        else if(oil == 1)
        {
             count ++;
             System.out.println("oil is low");
        }
        else
        {
            count = 0;
        }
        
        if(count >= 3)
        {
            monitor.SendEmail(temp,dischargeSecond, suctionSecond, chilledWater, dischargeFirst, dryer, stepper, pressureFirst, pressureSecond,oil);
        }
        
        System.out.println("Monitor Count : "+ count);
        }
        catch(Exception e)
        {
            System.out.println("Error in accesing monitor excel: " +e.getMessage());
        }
    }
}
    

