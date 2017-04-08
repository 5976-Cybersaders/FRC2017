package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousEncoderDeliverGear extends CommandGroup {
	public AutonomousEncoderDeliverGear(DriveTrain driveTrain) {
		encoderDeliver(driveTrain);
	}
	
	protected void encoderDeliver(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new AutonomousEncoderDriveStraight(SmartDashboardMap.DRIVE_DISTANCE1, driveTrain));	
		addSequential(new AutonomousEncoderTurn(SmartDashboardMap.ANGLE, driveTrain, SmartDashboardMap.TURN_TIME));
		addSequential(new AutonomousEncoderDriveStraight(SmartDashboardMap.DRIVE_DISTANCE2, driveTrain));
		System.out.println("END INIT MMC Encoder Gear Delivery");
	}
}
