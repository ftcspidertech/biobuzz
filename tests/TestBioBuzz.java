package org.firstinspires.ftc.teamcode.biobuzz.tests;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.biobuzz.navigation.DrivetrainKinematics;
import org.firstinspires.ftc.teamcode.biobuzz.navigation.MecanumRobotController;
import org.firstinspires.ftc.teamcode.biobuzz.navigation.TeleMecanumRobotController;

@Autonomous(name = "BioBuzz Autonomous Robot")
//@TeleOp(name = "BioBuzz Tele Robot")
public class TestBioBuzz extends OpMode{
     private MecanumRobotController robotController = null;
     private ElapsedTime autoLauncherTimer = new ElapsedTime();
     private double movePower = 0.075;
     private boolean testComplete = false;

//     DcMotor intakeMotor = null;
     private TeleMecanumRobotController teleRobot = null;
     private DrivetrainKinematics kinematics = null;
     private Pose2d robotPose = null;

    @Override
    public void init() {
         initControllers();
     }
 
     private void initControllers(){
         robotController = new MecanumRobotController(hardwareMap,telemetry);
         robotController.tickTolerance = 0;
         teleRobot = new TeleMecanumRobotController(hardwareMap,gamepad1,telemetry);
//         kinematics = new DrivetrainKinematics(robotController.frontLeftMotor, robotController.frontRightMotor,
//                 robotController.backLeftMotor, robotController.backRightMotor, robotController.imu, DistanceUnit.INCH);

//         intakeMotor = hardwareMap.get(DcMotor.class, "intake");
//         // Set direction
//         DcMotor.Direction reverseDirection = DcMotorSimple.Direction.REVERSE;
//         DcMotor.Direction forwardDirection = DcMotorSimple.Direction.FORWARD;
//         intakeMotor.setDirection(reverseDirection);
//
//         // Run mode encoder settings.
//         intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//         // Stop mode break settings.
//         DcMotor.ZeroPowerBehavior stopMode = DcMotor.ZeroPowerBehavior.BRAKE;
//         intakeMotor.setZeroPowerBehavior(stopMode);

     }
     @Override
     public void loop() {
         telemetry.addData("Running ", "test loop");
         testLoop();
/*
         teleRobot.run();

         robotPose = kinematics.updateRobotPose();
         telemetry.addData("X", robotPose.getX());
         telemetry.addData("Y", robotPose.getY());
         telemetry.addData("Heading (Deg)", Math.toDegrees(robotPose.getHeading()));
         telemetry.update();
*/

//         intakeMotor.setPower(1.0);
     }
     
     @Override
     public void stop() {
//         robotController.stop();
         teleRobot.stop();
//         intakeMotor.setPower(0);
     }
     
     private void wait(int ms){
         autoLauncherTimer.reset();
         while(autoLauncherTimer.milliseconds()<ms);
     }
    
     private void testLoop(){
         telemetry.addLine("Inside test loop:");
         telemetry.addData("testComplete",testComplete);
         if(!testComplete) {

/*
             robotController.goForward(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);
             robotController.goBackward(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);
*/

/*
             robotController.goForward(movePower,12,DistanceUnit.INCH);
             wait(1000);
             robotController.goBackward(movePower,12,DistanceUnit.INCH);
             wait(1000);
*/

/*
             robotController.slideTopRight(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);
             robotController.slideBottomLeft(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);

             robotController.slideTopLeft(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);
             robotController.slideBottomRight(movePower,1250,TimeUnit.MILLISECONDS);
             wait(1000);
*/

/*
             robotController.slideLeft(movePower,1500,TimeUnit.MILLISECONDS);
             wait(1000);
             robotController.slideRight(movePower,1500,TimeUnit.MILLISECONDS);
             wait(1000);
*/

/*
             robotController.slideLeft(movePower,12);
             wait(1000);
             robotController.slideRight(movePower,12);
             wait(1000);
*/


/*
             robotController.turnLeft(movePower,1500,TimeUnit.MILLISECONDS);
             wait(1000);
             robotController.turnRight(movePower,1500,TimeUnit.MILLISECONDS);
             wait(1000);
*/

/*
            robotController.turnLeft(0.05,90);
            wait(1000);
            robotController.turnRight(0.05,90);
*/
//            robotController.leftCalibrationFactor = 1.0;
            /*robotController.slideRight(movePower,18);
            wait(1000);
            robotController.slideLeft(movePower,18);
*/

/*
             robotController.splitStickArcadeDrive(0.1,0,0); // forward
             wait(500);
             robotController.stop();
             wait(1000);

             robotController.splitStickArcadeDrive(-0.1,0,0); // backward
             wait(500);
             robotController.stop();
             wait(1000);

             robotController.splitStickArcadeDrive(0,0.1,0); // slide right
             wait(1000);
             robotController.stop();
             wait(1000);

             robotController.splitStickArcadeDrive(0,-0.1,0); // slide left
             wait(1000);
             robotController.stop();
             wait(1000);

             robotController.splitStickArcadeDrive(0,0,0.1); // turn right
             wait(1000);
             robotController.stop();
             wait(1000);

             robotController.splitStickArcadeDrive(0,0,-0.1); // turn left
             wait(1000);
*/

//             robotController.gotoTarget(10, 45, 0.1, 0.1);
//
             robotController.goToTarget(10, 30, 0, 0.25, 0.1);

//             robotController.goToTarget(-24, 5, -90, 0.5, 0.08);

//             robotController.gotoTarget(12, 90, 0.1);

//             robotController.slideAndGo(10, 45, 0.1);

//             robotController.slideAndGo(10, -135, 0.3);


             testComplete = true;
         }else{
             stop();
         }
         double[] robotPose = robotController.getRobotPose();
         telemetry.addData("RobotX: ", robotPose[0]);
         telemetry.addData("RobotY: ", robotPose[1]);
         telemetry.addData("RobotTheta: ", Math.toDegrees(robotPose[2]));
         telemetry.update();
         telemetry.update();
        
     }
 }
