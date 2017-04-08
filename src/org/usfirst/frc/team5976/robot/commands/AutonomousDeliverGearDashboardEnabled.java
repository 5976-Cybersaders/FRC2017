package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGearDashboardEnabled extends CommandGroup {
	public AutonomousDeliverGearDashboardEnabled(DriveTrain driveTrain) {
		timedDeliver(driveTrain);
	}
	
	protected void encoderDeliver(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DriveStraight(SmartDashboardMap.DRIVE1, driveTrain));	
		addSequential(new Turn(SmartDashboardMap.ANGLE, driveTrain, SmartDashboardMap.TURN_TIME));
		addSequential(new DriveStraight(SmartDashboardMap.DRIVE2, driveTrain));
		System.out.println("END INIT MMC Encoder Gear Delivery");
	}
	
	protected void timedDeliver(DriveTrain driveTrain) {
		addSequential(new AutonomousTimedDriveStraight(driveTrain, 9759));
		addSequential(new AutonomousTimedTurn(driveTrain, 1460, SmartDashboardMap.TURN_DIRECTION));
		addSequential(new AutonomousTimedDriveStraight(driveTrain, 1500));
		System.out.println("END INIT MMC Timed Gear Delivery");
	}
}
