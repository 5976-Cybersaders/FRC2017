package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOpTankDrive extends Command {
	RobotDrive robotDrive;
	DriveTrain driveTrain;
	XboxController driveController;
	private static double expoFactor = 0.2;
	//private PowerDistributionPanel pdp;
	private boolean speedReduced;
	
	public TeleOpTankDrive(XboxController driveController, DriveTrain driveTrain) {
		robotDrive = driveTrain.getRobotDrive();
		this.driveTrain = driveTrain;
		this.driveController = driveController;
		//pdp = driveTrain.getPDP();
		speedReduced = false;
	}
	
	public double adjustSpeed(double d){
    	double speed = Math.signum(d) * Math.pow(Math.abs(d), Math.pow(4, expoFactor));
    	//System.out.println("in: " + d + " out: " + speed);
    	if (speedReduced) return speed * 0.5;
    	return speed;
    }
	
	@Override
	protected void execute() {
		
		//System.out.println("Left Master Encoder Position " + driveTrain.getLeftMaster().getEncPosition() + " Right Master Encoder Position " + driveTrain.getRightMaster().getEncPosition());
		//System.out.println("Current Left: " + ((pdp.getCurrent(2) + pdp.getCurrent(3))) + " Current Right: " + ((pdp.getCurrent(12) + pdp.getCurrent(13))) + "  Rio: "+pdp.getCurrent(8) + " Total Current:  " +pdp.getTotalCurrent());
		//reportCurrent("Left Master Current", RobotMap.LEFT_MASTER_PDP);
		//reportCurrent("Left Slave Current", RobotMap.LEFT_SLAVE_PDP);
		//reportCurrent("Right Master Current", RobotMap.RIGHT_MASTER_PDP);
		//reportCurrent("Right Slave Current", RobotMap.RIGHT_SLAVE_PDP);
		if (driveController.getBumper(Hand.kLeft)) {
			speedReduced = true;
		}
		else speedReduced = false;
		if (driveController.getBumper(Hand.kRight))
			robotDrive.tankDrive(adjustSpeed(driveController.getY(Hand.kLeft)), adjustSpeed(driveController.getY(Hand.kLeft)));
		else robotDrive.tankDrive(adjustSpeed(driveController.getY(Hand.kLeft)), adjustSpeed(driveController.getY(Hand.kRight)));
		SmartDashboard.putNumber("Closed Loop Ramp Rate:", driveTrain.getLeftMaster().getCloseLoopRampRate());
	}
	
	//private void reportCurrent(String label, int channel) {
	//	SmartDashboard.putNumber(label, pdp.getCurrent(channel));
	//}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		robotDrive.tankDrive(0, 0);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
