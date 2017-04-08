package org.usfirst.frc.team5976.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportHelper {
	public static void reportExecute(CANTalon talon, String side, PowerDistributionPanel pdp, int pdpPort, int stableCount) {
		System.out.println(side + " Set: " + talon.getSetpoint() + 
				//Gets the current status of the Talon (usually a sensor value).
				" Pos: " + talon.get() +
				//Get the current difference between the setpoint and the sensor value.
				" CLErr: " + talon.getClosedLoopError() +
				//Get the current encoder position, regardless of whether it is the current feedback device.
				" Enc Pos: " + talon.getEncPosition() + 
				//Returns the difference between the setpoint and the current position.
				" Err: " + talon.getError() +
				//When using analog sensors, 0 units corresponds to 0V, 1023 units corresponds to 3.3V When using an analog encoder 
				//(wrapping around 1023 to 0 is possible) the units are still 3.3V per 1023 units.
				" Pos(in revs): " + talon.getPosition() + 
				" Count: " + stableCount);
		SmartDashboard.putNumber(side + " Current", pdp.getCurrent(pdpPort));
	}
	
	public static void report(CANTalon talon, Command command) {
		System.out.println("INIT " + command);
		System.out.println("Control Mode: " + talon.getControlMode());
		System.out.println("Device ID" + talon.getDeviceID());
		System.out.println("Closed Loop Ramp Rate" + talon.getCloseLoopRampRate());
		System.out.println("Source Type " + talon.getPIDSourceType());
		System.out.println("Inverted: " + talon.getInverted());
		System.out.println("PID Values: " + talon.getP() + " " + talon.getI() + " " + talon.getD());
		System.out.println("Position: " + talon.getPosition());
		System.out.println();
	}
}
