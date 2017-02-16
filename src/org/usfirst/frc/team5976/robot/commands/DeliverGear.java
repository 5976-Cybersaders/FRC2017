package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.Command;

public class DeliverGear extends Command {
	long startTime;
	private GearDelivery gearDelivery;
	
	public DeliverGear(GearDelivery gearDelivery) {
		this.gearDelivery = gearDelivery;
		requires(gearDelivery);
	}
	
	protected void initialize() {
		startTime = System.currentTimeMillis();
		gearDelivery.getLeftServo().set(1);
		gearDelivery.getRightServo().set(0);
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - startTime) > 300;
	}

}