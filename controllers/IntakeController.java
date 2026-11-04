package org.firstinspires.ftc.teamcode.biobuzz.controllers;

 import com.qualcomm.robotcore.hardware.HardwareMap;
 import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.DcMotorEx;
 import com.qualcomm.robotcore.hardware.Gamepad;
 import org.firstinspires.ftc.robotcore.external.Telemetry;
 import com.qualcomm.robotcore.util.ElapsedTime;

 public class IntakeController {

     private DcMotorEx intakeMotor;
     private Gamepad gamepad;
     public double regularPower = 1.0;//POSSIBLE CHNAGES
     public double shootPower = 1.0;
     private Telemetry telemetry;
     private boolean isRunning = false;
     private boolean isIntakeMode = true;
     private ElapsedTime stallTimer =  new ElapsedTime();
     public int MIN_VELOCITY = 250; // 250 ticks
     private final double MIN_WAIT = 1.0; // seconds
     //public double minVel = 0.6;
     //private double velocity,maxVelocity=0,minVelocity=10000;
     public int releaseTime = 500;

     public IntakeController(HardwareMap hardwareMap, Gamepad pad, Telemetry tmetry){
         intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");

         gamepad = pad;
         telemetry = tmetry;
     }

     public void run(){
         /*
         velocity = Math.abs(intakeMotor.getVelocity());
         if(velocity>maxVelocity){
             maxVelocity = velocity;//(intakeMotor.getVelocity() + maxVelocity)/2.0;
         }
         if(velocity<minVelocity){
             minVelocity = velocity;
         }
         telemetry.addData("Velocity","Min = %f, Max = %f, Current=%f", minVelocity, maxVelocity, velocity);
         */
         if (gamepad.startWasPressed()) {
             if(!isRunning){
                 setPower(regularPower);
             }else{
                 if(isIntakeMode){ // intakeMotor.getPower()<0
                     stop();
                 }else{
                     stop();
                     setPower(regularPower);
                 }
             }
             isIntakeMode = true;
         }else if(gamepad.backWasPressed()){
             if(!isRunning){
                 setPower(-regularPower);
             }else{
                 if(isIntakeMode){ // intakeMotor.getPower()>0
                     stop();
                     setPower(-regularPower);
                 }else{
                     stop();
                 }
             }
             isIntakeMode = false;
         }

         // Start the motor for shooting (if stalled before).
         restartIfStalled(gamepad.y);
         //*
         if(gamepad.y){
             setShootPower();
         }else{
             if(intakeMotor.getPower()!=0 && intakeMotor.getPower()!=regularPower){
                 resetPower();
             }
         }//*/

         // Check if the motor stalled for multiple balls.
         //stopIfStalled();
         releaseBallIfStalled();
     }

     private void setPower(double power){
         if(power==intakeMotor.getPower()){
             return;
         }
         intakeMotor.setPower(power);
         isRunning = true;
         stallTimer.reset();
     }

     public void start(){
         if(isIntakeMode){
             setPower(regularPower);
         }else{
             setPower(-regularPower);
         }
     }

     public void stop(){
         intakeMotor.setPower(0.0);
         isRunning = false;
     }

     public void releaseBallIfStop(){
         if(intakeMotor.getPower()==0){
             releaseBall(releaseTime);
         }
     }

     public void stopIfStalled(){
         if(stallTimer.seconds()>MIN_WAIT && intakeMotor.getPower()!=0 &&
            Math.abs(intakeMotor.getVelocity())<MIN_VELOCITY){
             // First, set opposite motion to release the pressure on the exit door.
             if(isIntakeMode){
                 setPower(-regularPower);
                 try{
                     Thread.sleep(100);
                 }catch(InterruptedException e){
                     telemetry.addData("Error: ", e.getMessage());
                 }
             }
             // Next, stop the motion.
             stop();
         }
     }

     public void releaseBallIfStalled(){
         if(stallTimer.seconds()>MIN_WAIT && intakeMotor.getPower()!=0 &&
            Math.abs(intakeMotor.getVelocity())<MIN_VELOCITY){

                stop();
                releaseBall(releaseTime);

         }
     }

     private void releaseBall(int waitTime){
         // Run the motor to reverse direction
         setPower(-regularPower);
         // Wait enough to release one ball
         try{
             Thread.sleep(waitTime);
         }catch(InterruptedException e){
             telemetry.addData("Error: ", e.getMessage());
         }
         // Reset the motor to its regular motion direction.
         setPower(regularPower);
     }

     public void restartIfStalled(boolean youCanRestart){
         if(youCanRestart && intakeMotor.getPower()==0){
             start();
         }
     }

     public void setShootPower(){
         setPower(shootPower);
     }

     public void setShootPower(boolean youCanChange){
         if(youCanChange){
             setPower(shootPower);
         }
     }

     public void resetPower(){
         start();
     }

 }
