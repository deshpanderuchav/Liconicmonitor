


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rucha Deshpande
*/


package com.liconic.monitor;

import com.liconic.binding.conffiles.Parameter;
import com.liconic.binding.conffiles.ParameterGroup;
import com.liconic.binding.conffiles.Parameters;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class AppListener implements ServletContextListener {
 

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext context = sce.getServletContext();
               
        String ParamFile = "";
        String SMTPAddress = "";
        String SMTPPort = "";
        String SMTPUser = "";
        String EMMailFrom = "";
        String SMTPPassword = "";
        String DBLocation = "";
        String EMailTo = "";
        
        int tempMax = 0, tempMin = 0;
        int dischargeSecondMax = 0, dischargeSecondMin = 0;
        int suctionSecondMax = 0, suctionSecondMin = 0;
        int chilledWaterMax = 0, chilledWaterMin = 0;
        int dischargeFirstMax = 0, dischargeFirstMin = 0;
        int dryerMax = 0, dryerMin = 0;
        int stepperMax = 0, stepperMin = 0;
        int pressureFirstMax = 0, pressureFirstMin = 0;
        int pressureSecondMax = 0, pressureSecondMin = 0;
        
        ParamFile = context.getInitParameter("ConfigFile");
        
        System.out.println("Reading Monitor Config File:"+ParamFile);
        
       try 
       {
            JAXBContext jaxbContent = JAXBContext.newInstance(com.liconic.binding.conffiles.ObjectFactory.class);

            Unmarshaller um = jaxbContent.createUnmarshaller();

            FileInputStream fis = new FileInputStream(ParamFile);

            Parameters params = (Parameters) um.unmarshal(fis);

            for(int i=0; i<params.getParameterGroup().size(); i++){
                                
                ParameterGroup paramGroup = (ParameterGroup)params.getParameterGroup().get(i);
                
                if (paramGroup.getName().equals("StoreTemperature")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            tempMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            tempMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else if (paramGroup.getName().equals("DischargeSecondStage")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            dischargeSecondMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            dischargeSecondMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("SuctionSecondStage")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            suctionSecondMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            suctionSecondMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("ChilledWaterIn")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            chilledWaterMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            chilledWaterMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("DischargeFirstStage")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            dischargeFirstMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            dischargeFirstMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("DryerOut")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            dryerMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            dryerMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("StepperValve")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            stepperMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            stepperMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("HighPressureFirstStage")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            pressureFirstMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            pressureFirstMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("HighPressureSecondStage")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("MaxValue"))
                        {
                            pressureSecondMax = Integer.parseInt(param.getValue());
                        }   
                        
                        else if (param.getName().equals("MinValue"))
                        {
                            pressureSecondMin = Integer.parseInt(param.getValue());
                        }
                     
                    }
                }
                
                else  if (paramGroup.getName().equals("E-mail")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                            if (param.getName().equals("SMTPAddress")){
                                 
                                SMTPAddress = param.getValue();
                                System.out.println("SMTPAddress " + SMTPAddress);
                            }
                            
                            else if (param.getName().equals("SMTPPort")){
                                SMTPPort = param.getValue();    
                            }
                            
                            else if (param.getName().equals("SMTPUser")){
                                SMTPUser = param.getValue();
                            }
                            
                            else if (param.getName().equals("E-mailFrom")){
                               
            
                                EMMailFrom = param.getValue(); 
                                System.out.println("From " + EMMailFrom);
                            }
                            
                            else if (param.getName().equals("E-mailTo")){
                               
                                EMailTo = param.getValue(); 
                                System.out.println("To " + EMailTo);
                            }
                            
                            else if (param.getName().equals("SMTPPassword")){
                                SMTPPassword = param.getValue();
                            }
                            
                        }
                    }
                
                
                else if (paramGroup.getName().equals("DBLocation")){
                    
                    for(int j=0; j<paramGroup.getParameter().size(); j++)
                    {
                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
                        
                        if (param.getName().equals("PathtoFile"))
                        {
                            DBLocation = param.getValue();
                               System.out.println("DB lll: " + DBLocation);     
                        }   
                
                
            }
       }
                
//                  else if (paramGroup.getName().equals("DBLocation")){
//                    
//                    for(int j=0; j<paramGroup.getParameter().size(); j++)
//                    {
//                        Parameter param = (Parameter)paramGroup.getParameter().get(j);
//                        
//                        if (param.getName().equals("MaxValue"))
//                        {
//                            DBLocation = param.getValue();
//                        }   
//                        
//                        else if (param.getName().equals("MinValue"))
//                        {
//                            DBLocation = param.getValue();
//                        }
//                     
//                    }
//                }
                
            }
       }
        catch (Exception ex)
        {
            System.out.println("BACKUP read config file error: "+ex.getMessage());
            ex.printStackTrace();
        }
       
        // STX DataBase
        System.out.println("");       
        System.out.println("");                                       
       
        System.out.println("Cooling Monitor settings: ");                
        System.out.println("Store Temprature: " + tempMax + " ," + tempMin);
        System.out.println("Discharge Second Stage: " + dischargeSecondMax + " ," + dischargeSecondMin);
        System.out.println("Suction Second Stage: " + suctionSecondMax + " ," + suctionSecondMin);
        System.out.println("ChilledWaterIn: " + chilledWaterMax + " ," + chilledWaterMin);
        System.out.println("Discharge First Stage: " + dischargeFirstMax + " ," + dischargeFirstMin);
        System.out.println("Dryer Out: " + dryerMax + " ," + dryerMin);
        System.out.println("Stepper valve: " + stepperMax + " ," + stepperMin);
        System.out.println("High Pressure First Stage: " + pressureFirstMax + " ," + pressureFirstMin);
        System.out.println("DB Location: " + DBLocation);          
        
        System.out.println("");       
        System.out.println("");                                       
               
        Monitor monitor = Monitor.getInstance();
        
        context.setAttribute("LiconicMonitor", monitor);
        
        monitor.setDBLocation(DBLocation);
        
        monitor.setTempMax(tempMax);
        monitor.setTempMin(tempMin);
        
        monitor.setDischargeSecondMax(dischargeSecondMax);
        monitor.setDischargeSecondMin(dischargeSecondMin);
        
        monitor.setDischargeFirstMax(dischargeFirstMax);
        monitor.setDischargeFirstMin(dischargeFirstMin);
        
        monitor.setChilledWaterMax(chilledWaterMax);
        monitor.setChilledWaterMin(chilledWaterMin);
        
        monitor.setDryerMax(dryerMax);
        monitor.setDryerMin(dryerMin);
        
        monitor.setSuctionSecondMax(suctionSecondMax);
        monitor.setSuctionSecondMin(suctionSecondMin);
        
        monitor.setStepperMax(stepperMax);
        monitor.setStepperMin(stepperMin);
        
        monitor.setPressureSecondMax(pressureSecondMax);
        monitor.setPressureSecondMin(dischargeSecondMin);
      
        monitor.setPressureFirstMax(pressureFirstMax);
        monitor.setPressureFirstMin(pressureFirstMin);
        
        monitor.setSMTPAddress(SMTPAddress);
        monitor.setSMTPPort(SMTPPort);
        monitor.setSMTPUser(SMTPUser);
        monitor.setSMTPPassword(SMTPPassword);
        monitor.setEMMailFrom(EMMailFrom);
        monitor.setEMailTo(EMailTo);
        
       System.out.println("SMTPServer: " + SMTPAddress );
       System.out.println("SMTP Port: " +  SMTPPort);
       System.out.println("Emaill From:" + SMTPUser );
       System.out.println("Email To: " + EMailTo );
       System.out.println("Password: " + SMTPPassword);
       
        monitor.RunMonitor();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
