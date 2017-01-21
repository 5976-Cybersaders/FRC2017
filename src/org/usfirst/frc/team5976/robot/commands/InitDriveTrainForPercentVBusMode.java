package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

public class InitDriveTrainForPercentVBusMode extends InitDriveTrain {

	public InitDriveTrainForPercentVBusMode(DriveTrain driveTrain) {
		super(driveTrain);
	}

	@Override
	protected void initTalons() {
		leftMaster.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
		leftSlave.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
		rightMaster.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
		rightSlave.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
	}

}
