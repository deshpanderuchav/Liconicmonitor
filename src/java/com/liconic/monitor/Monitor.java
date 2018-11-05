package com.liconic.monitor;

import javax.mail.Authenticator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Timer;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Monitor {
    
    
    private String SMTPAddress = "";
    private String SMTPPort = "";
    private String SMTPUser = "";
    private String EMMailFrom = "";
    private String EMailTo;
    private String DBLocation = "";
    String SMTPPassword = "";

    private int tempMax = 0, tempMin = 0;
    private int dischargeSecondMax = 0, dischargeSecondMin = 0;
    private int suctionSecondMax = 0, suctionSecondMin = 0;
    private int chilledWaterMax = 0, chilledWaterMin = 0;
    private int dischargeFirstMax = 0, dischargeFirstMin = 0;
    private int dryerMax = 0, dryerMin = 0;
    private int stepperMax = 0, stepperMin = 0;
    private int pressureFirstMax = 0, pressureFirstMin = 0;
    private int pressureSecondMax = 0, pressureSecondMin = 0;
    
    private static Monitor instance;    

    public String getSMTPPassword() {
        return SMTPPassword;
    }

    public void setSMTPPassword(String SMTPPassword) {
        this.SMTPPassword = SMTPPassword;
    }
    
      public String getDBLocation() {
        return DBLocation;
    }

    public void setDBLocation(String DBLocation) {
        this.DBLocation = DBLocation;
    }
  
    
    public String getSMTPAddress() {
        return SMTPAddress;
    }

    public void setSMTPAddress(String SMTPAddress) {
        this.SMTPAddress = SMTPAddress;
    }

    public String getSMTPPort() {
        return SMTPPort;
    }

    public void setSMTPPort(String SMTPPort) {
        this.SMTPPort = SMTPPort;
    }

    public String getSMTPUser() {
        return SMTPUser;
    }

    public void setSMTPUser(String SMTPUser) {
        this.SMTPUser = SMTPUser;
    }

    public String getEMMailFrom() {
        return EMMailFrom;
    }

    public void setEMMailFrom(String EMMailFrom) {
        this.EMMailFrom = EMMailFrom;
    }
    
    
    public void setEMailTo(String EMailTo) {
        this.EMailTo = EMailTo;
    }
    
     
    public String getEMailTo() {
        return EMailTo;
            }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getDischargeSecondMax() {
        return dischargeSecondMax;
    }

    public void setDischargeSecondMax(int dischargeSecondMax) {
        this.dischargeSecondMax = dischargeSecondMax;
    }

    public int getDischargeSecondMin() {
        return dischargeSecondMin;
    }

    public void setDischargeSecondMin(int dischargeSecondMin) {
        this.dischargeSecondMin = dischargeSecondMin;
    }

    public int getSuctionSecondMax() {
        return suctionSecondMax;
    }

    public void setSuctionSecondMax(int suctionSecondMax) {
        this.suctionSecondMax = suctionSecondMax;
    }

    public int getSuctionSecondMin() {
        return suctionSecondMin;
    }

    public void setSuctionSecondMin(int suctionSecondMin) {
        this.suctionSecondMin = suctionSecondMin;
    }

    public int getChilledWaterMax() {
        return chilledWaterMax;
    }

    public void setChilledWaterMax(int chilledWaterMax) {
        this.chilledWaterMax = chilledWaterMax;
    }

    public int getChilledWaterMin() {
        return chilledWaterMin;
    }

    public void setChilledWaterMin(int chilledWaterMin) {
        this.chilledWaterMin = chilledWaterMin;
    }

    public int getDischargeFirstMax() {
        return dischargeFirstMax;
    }

    public void setDischargeFirstMax(int dischargeFirstMax) {
        this.dischargeFirstMax = dischargeFirstMax;
    }

    public int getDischargeFirstMin() {
        return dischargeFirstMin;
    }

    public void setDischargeFirstMin(int dischargeFirstMin) {
        this.dischargeFirstMin = dischargeFirstMin;
    }

    public int getDryerMax() {
        return dryerMax;
    }

    public void setDryerMax(int dryerMax) {
        this.dryerMax = dryerMax;
    }

    public int getDryerMin() {
        return dryerMin;
    }

    public void setDryerMin(int dryerMin) {
        this.dryerMin = dryerMin;
    }

    public int getStepperMax() {
        return stepperMax;
    }

    public void setStepperMax(int stepperMax) {
        this.stepperMax = stepperMax;
    }

    public int getStepperMin() {
        return stepperMin;
    }

    public void setStepperMin(int stepperMin) {
        this.stepperMin = stepperMin;
    }

    public int getPressureFirstMax() {
        return pressureFirstMax;
    }

    public void setPressureFirstMax(int pressureFirstMax) {
        this.pressureFirstMax = pressureFirstMax;
    }

    public int getPressureFirstMin() {
        return pressureFirstMin;
    }

    public void setPressureFirstMin(int pressureFirstMin) {
        this.pressureFirstMin = pressureFirstMin;
    }

    public int getPressureSecondMax() {
        return pressureSecondMax;
    }

    public void setPressureSecondMax(int pressureSecondMax) {
        this.pressureSecondMax = pressureSecondMax;
    }

    public int getPressureSecondMin() {
        return pressureSecondMin;
    }

    public void setPressureSecondMin(int pressureSecondMin) {
        this.pressureSecondMin = pressureSecondMin;
    }
  
   
    
    public static Monitor getInstance(){
        
        if(instance == null)
            instance = new Monitor();
        return instance;
    }

     
    public void RunMonitor(){
        
        if (tempMax == 0 || tempMin == 0){            
            System.out.println("Monitor ERROR: One or both of temprature thresholds value are missing.");
            return;
        }
        if (dischargeSecondMax == 0 || dischargeSecondMin == 0){            
            System.out.println("Monitor ERROR: One or both of Discharge Second Stage value are missing.");
            return;
        }
        
        if (suctionSecondMax == 0 || suctionSecondMin == 0){            
           System.out.println("Monitor ERROR: One or both of Suction Second Stage value are missing.");
           return;
        }
        
        if (chilledWaterMax == 0 || chilledWaterMin == 0){        
            System.out.println("Monitor ERROR: One or both of Chilled Water In value are missing.");
            return;
        }
        
        if (dischargeFirstMax == 0 || dischargeFirstMin == 0){    
            System.out.println("Monitor ERROR: One or both of Discharge First Stage value are missing.");
            return;
        }
        
        if (dryerMax == 0 || dryerMin == 0){            
            System.out.println("Monitor ERROR: One or both of Dryer out value are missing.");
            return;
        }
        
        if (stepperMax == 0 || stepperMin == 0){            
            System.out.println("Monitor ERROR: One or both of Stepper Valve value are missing.");
            return;
        }
        
        if (pressureFirstMax == 0 || pressureFirstMin == 0){        
            System.out.println("Monitor ERROR: One or both of Pressure First Stage value are missing.");
            return;
        }
        
        if (pressureSecondMax == 0 || pressureSecondMin == 0){     
            System.out.println("Monitor ERROR: One or both of Pressure Second Stage value are missing.");
            return;
        }
        
        
        Calendar cal = Calendar.getInstance();        
        try{
            Timer timer = new Timer();
            Cooling cooling = new Cooling(this);
            timer.schedule(cooling, cal.getTime(),1000*10*60);
        }
        catch(Exception e){
            System.out.println("Monitor timer error: " + e.getMessage());
        }
    }

    public void SendEmail(double temp, double dischargeSecond, double suctionSecond, double chilledWater, double dischargeFirst, double dryer, double stepper, double pressureFirst, double pressureSecond, double oil){
     System.out.println("sending email " );
        
          StringBuilder sb = new StringBuilder("Aux Compressor is in risk status: one or more of the parameters are out of range. Current paramter values are:\n ");
            sb.append("Chamber 301 :" + temp);
            sb.append("\n2nd Disc 201 : " + dischargeSecond);
            sb.append("\n2nd suct 205 : " + suctionSecond);
            sb.append("\nWater In 108 :" + chilledWater);
            sb.append("\n1st Disc 101 :" + dischargeFirst);
            sb.append("\nDryr Out 402 :" + dryer);
            sb.append("\nStp Valve 209 :" + stepper);
            sb.append("\nPrss 1st H 111 :" + pressureFirst);
            sb.append("\nPrss 2nd H 211 :" + pressureSecond);
            sb.append("\nThank you! \n Liconic Team");
           
             String content = sb.toString();
            String subject = "Cooling compressor Alerts - RIMUHC";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host",SMTPAddress );
            props.put("mail.smtp.port", SMTPPort);
            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SMTPUser, SMTPPassword);
            }
            });
            
            System.out.println("Content: " + content);
            try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTPUser));
             StringTokenizer st = new StringTokenizer(EMailTo,";");
                        while(st.hasMoreTokens()) {
                        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(st.nextToken(),false));
                        }
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Cooling email Sent");
            
            } catch (MessagingException e) {
           System.out.println("Error sending email:"+ e.getMessage());
            }
    }
}

            
            

