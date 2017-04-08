package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

public class InitDriveTrainForPercentVBusAllMasters extends AbstractInitDriveTrain {

	public InitDriveTrainForPercentVBusAllMasters(DriveTrain driveTrain) {
		super(driveTrain);
	}

	@Override
	protected void initTalons() {
		/*System.out.println("Ramp Rate: " + leftMaster.getCloseLoopRampRate() + 
				" " + leftSlave.getCloseLoopRampRate() + 
				" " + rightMaster.getCloseLoopRampRate() + 
				" " + rightSlave.getCloseLoopRampRate() + 
				" " + leftMaster.getControlMode());
	    */
		initMaster(leftMaster);
		initMaster(leftSlave);
		initMaster(rightMaster);
		initMaster(rightSlave);
		
		
		/*System.out.println("Ramp Rate: " + leftMaster.getCloseLoopRampRate() + 
				" " + leftSlave.getCloseLoopRampRate() + 
				" " + rightMaster.getCloseLoopRampRate() + 
				" " + rightSlave.getCloseLoopRampRate() + 
				" " + leftMaster.getControlMode());
		*/
	}
	
	protected void initCommon(CANTalon talon) {
		//super.initCommon(talon);
		talon.setPID(0, 0, 0);
		talon.configPeakOutputVoltage(+getMaxVoltage(), -getMaxVoltage());
		//***RUN TeleOp BEFORE THE FIRST PRACTICE MATCH***//
		//Change back to 6 if AutonomousTimedDriveStraight goes TOO fast
		//and affects our ability to go straight
	}
	
	protected void initMaster(CANTalon talon) {
		//super.initMaster(talon);
		talon.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
		initCommon(talon);
	}
	
	protected double getMaxVoltage() {
		return RobotMap.PEAK_VOLTAGE_TELEOP;
	}

	@Override
	protected void initSlaves() {
		
	}
}
