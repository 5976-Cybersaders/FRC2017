package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothingCommand extends Command{
	private DriveTrain driveTrain;
	private CANTalon leftMaster, rightMaster, leftSlave, rightSlave;
	private int printCounter = 20, printInterval = 20;
	
	public DoNothingCommand(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
		leftMaster = driveTrain.getLeftMaster();
		rightMaster = driveTrain.getRightMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightSlave = driveTrain.getRightSlave();
	}
	
	protected void initialize() {
		super.initialize();
		leftMaster.setSafetyEnabled(false);
		rightMaster.setSafetyEnabled(false);
		reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
		//reportExecute(leftSlave, "Left Slave", RobotMap.LEFT_SLAVE_PDP);
		reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
		//reportExecute(rightSlave, "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
		System.out.println("INITIALIZE OUTPUT");
	}
	
	protected void execute() {
		leftSlave.set(leftMaster.getDeviceID());
		rightSlave.set(rightMaster.getDeviceID());
		if (printCounter == printInterval) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			//reportExecute(leftSlave, "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			//reportExecute(rightSlave, "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			System.out.println();
			printCounter = 0;
		}
		else {
			printCounter++;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		leftMaster.setSafetyEnabled(true);
		rightMaster.setSafetyEnabled(true);
	}
	
	protected void reportExecute(CANTalon talon, String description, int pdpPort) {
		ReportHelper.reportExecute(talon, description, driveTrain.getPDP(), pdpPort, 0);
	}
}
