package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGearDashboardEnabled extends CommandGroup {

	public AutonomousDeliverGearDashboardEnabled(DriveTrain driveTrain, GearDelivery gearDelivery) {
		//double revs = SmartDashboard.getNumber("Revs", 20.0);
		
		Command forward = new DriveStraight("drive1", 12, driveTrain);
		Command turn = new Turn("angle", 45, driveTrain);
		Command forward2 = new DriveStraight("drive2", 6, driveTrain);
		Command deliverGear = new DeliverGear(gearDelivery);
		Command backward = new DriveStraight("drive3", -4, driveTrain);

		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(forward);
		addSequential(turn);
		addSequential(forward2);
		addSequential(deliverGear);
		addSequential(backward);
		System.out.println("END INIT MMC");
	}
}
