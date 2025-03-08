package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class ElevatorSubsystem {
    private static ElevatorSubsystem _instance;

    private TalonSRX elevatorMotor;
    

    // Constructor
    private ElevatorSubsystem() {
        elevatorMotor = new TalonSRX(Constants.ELEVATOR_MOTOR_ID); // Initialize the Talon SRX motor controller
        configureTalonSRX(); // Configure Talon SRX
    }

    // Method to configure Talon SRX parameters
    private void configureTalonSRX() {
        // Configure motor controller feedback device (if using)
        //talonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // Configure limit switch
        elevatorMotor.configReverseLimitSwitchSource(
            LimitSwitchSource.FeedbackConnector, // Use limit switch connected to the feedback connector
            LimitSwitchNormal.NormallyOpen,      // Assume the limit switch is normally open
            0);   
        elevatorMotor.configForwardLimitSwitchSource(
            LimitSwitchSource.FeedbackConnector, // Use limit switch connected to the feedback connector
            LimitSwitchNormal.NormallyOpen,      // Assume the limit switch is normally open
            0);                                // Timeout in milliseconds (0 for no timeout)
    }

    public static ElevatorSubsystem getInstance(){
		if(_instance == null)
			_instance = new ElevatorSubsystem();
		
		return _instance;
	}

    // Method to move the arm up for 2 seconds
    public void moveUp() {
            elevatorMotor.set(ControlMode.PercentOutput, 1); // Example: Set motor to 100% output
        }

        

    // Method to move the arm down until the limit switch is triggered
    public void moveDown() {
            elevatorMotor.set(ControlMode.PercentOutput, -1); // Example: Set motor to -100% output
        }  
    public void stopArm(){
        elevatorMotor.set(ControlMode.PercentOutput, 0.0);
    }
    
}