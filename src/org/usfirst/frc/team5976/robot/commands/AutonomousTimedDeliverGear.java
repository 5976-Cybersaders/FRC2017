package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousTimedDeliverGear extends CommandGroup {
	public AutonomousTimedDeliverGear(DriveTrain driveTrain) {
		timedDeliver(driveTrain);
	}
	
	protected void timedDeliver(DriveTrain driveTrain) {
		addSequential(new AutonomousTimedDriveStraight(driveTrain, SmartDashboardMap.DRIVE_TIME1));
		addSequential(new AutonomousTimedTurn(driveTrain, SmartDashboardMap.TURN_TIME, SmartDashboardMap.TURN_DIRECTION));
		addSequential(new AutonomousTimedDriveStraight(driveTrain, SmartDashboardMap.DRIVE_TIME2));
		System.out.println("END INIT MMC Timed Gear Delivery");
	}
}
