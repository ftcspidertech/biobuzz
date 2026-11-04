package org.firstinspires.ftc.teamcode.biobuzz.tests;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.biobuzz.controllers.IntakeController;


@TeleOp(name = "Intake Test")
public class TestIntakeMotor extends OpMode {

    private IntakeController controller = null;

    @Override
    public void init() {
        controller = new IntakeController(hardwareMap, gamepad1, telemetry);
    }

    @Override
    public void loop() {
        controller.run();
    }

    @Override
    public void stop() {
        controller.stop();
    }
}
