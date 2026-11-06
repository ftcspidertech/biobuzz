package org.firstinspires.ftc.teamcode.biobuzz.navigation;

import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DrivetrainKinematics {

    // Bare motor TPR (28) * Actual 5:1 Cartridge Reduction (5.23) = 146.44
    public static final double TICKS_PER_REV = 146.44;

    // GoBuilda 104mm Mecanum wheel converted exactly to inches
    public static final double WHEEL_DIAMETER_INCHES = 104.0 / 25.4;

    public final double WHEEL_DIAMETER_M = 0.104;

    // The scale factor: Linear distance (in inches) traveled per singular encoder count
    public static final double INCH_PER_TICK =
            (Math.PI * WHEEL_DIAMETER_INCHES) / TICKS_PER_REV;

    public final double M_PER_TICK =
            (Math.PI * WHEEL_DIAMETER_M) / TICKS_PER_REV;

    private ElapsedTime timer = null;
    private DcMotorEx frontLeftMotor =null, frontRightMotor =null, backLeftMotor =null, backRightMotor =null;
    private double robotX, robotY, robotTheta;
    private IMU imu = null;
    private double lastTime;
    private DistanceUnit distanceUnit = null;

    public DrivetrainKinematics(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, IMU imuUnit, DistanceUnit distUnit){
        frontLeftMotor = (DcMotorEx) fl;
        frontRightMotor = (DcMotorEx) fr;
        backLeftMotor = (DcMotorEx) bl;
        backRightMotor = (DcMotorEx) br;
        imu = imuUnit;
        distanceUnit = distUnit;

        timer = new ElapsedTime();

        reset();
    }

    public void reset(){
        robotX = 0;
        robotY = 0;
        robotTheta = 0;
        imu.resetYaw();
        timer.reset();
    }

    /**
     * Converts motor encoder ticks-per-second into linear surface velocity (inches/sec).
     */
    private double getLinearVelocity(DcMotorEx motor) {
        double ticksPerSecond = motor.getVelocity();
        double velocity;
        if(distanceUnit==DistanceUnit.INCH){
            velocity = ticksPerSecond * INCH_PER_TICK;
        }else{
            velocity = ticksPerSecond * M_PER_TICK;
        }
        return velocity;
    }

    public Pose2d updateRobotPose(){
        // Keep track of time between loops
        double currentTime = timer.seconds();
        double dt = currentTime - lastTime;
        lastTime = currentTime;

        // 1. Get IMU angle
        double theta = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // 2. Convert motor RPM/ticks to linear surface velocity (m/s)
        double vFL = getLinearVelocity(frontLeftMotor);
        double vFR = getLinearVelocity(frontRightMotor);
        double vBL = getLinearVelocity(backLeftMotor);
        double vBR = getLinearVelocity(backRightMotor);

        // 3. Forward Kinematics (Chassis relative velocities)
        double robotVx = (vFL + vFR + vBL + vBR) / 4.0;
        double robotVy = (-vFL + vFR + vBL - vBR) / 4.0;

        // 4. Rotate to Field Frame using the IMU angle
        double fieldVx = robotVx * Math.cos(theta) - robotVy * Math.sin(theta);
        double fieldVy = robotVx * Math.sin(theta) + robotVy * Math.cos(theta);

        // 5. Integrate to update the global position coordinates
        robotX += fieldVx * dt;
        robotY += fieldVy * dt;
        robotTheta = theta; // Driven directly by the precise IMU sensor

        return new Pose2d(robotX, robotY, new Rotation2d(robotTheta));
    }

    public double averageWheelSpeed(){
        double avg = (Math.abs(((DcMotorEx)frontLeftMotor).getVelocity()) +
                Math.abs(((DcMotorEx)frontRightMotor).getVelocity()) +
                Math.abs(((DcMotorEx)backLeftMotor).getVelocity()) +
                Math.abs(((DcMotorEx)backRightMotor).getVelocity()))/4.0;
        avg = avg*INCH_PER_TICK;

        return avg;
    }

}
