package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;


public class PracticeSubsystem {
    private static PracticeSubsystem _instance;

    private TalonSRX practiceMotor;
    

    // Constructor
    private PracticeSubsystem() {
        practiceMotor = new TalonSRX(Constants.PRACTICE_MOTOR_ID); // Initialize the Talon SRX motor controller
        configureTalonSRX(); // Configure Talon SRX
    }

    // Method to configure Talon SRX parameters
    private void configureTalonSRX() {
        // Configure motor controller feedback device (if using)
        //talonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // Configure limit switch
        practiceMotor.configReverseLimitSwitchSource(
            LimitSwitchSource.FeedbackConnector, // Use limit switch connected to the feedback connector
            LimitSwitchNormal.NormallyOpen,      // Assume the limit switch is normally open
            0);   
        practiceMotor.configForwardLimitSwitchSource(
            LimitSwitchSource.FeedbackConnector, // Use limit switch connected to the feedback connector
            LimitSwitchNormal.NormallyOpen,      // Assume the limit switch is normally open
            0);                                // Timeout in milliseconds (0 for no timeout)
    }

    public static PracticeSubsystem getInstance(){
		if(_instance == null)
			_instance = new PracticeSubsystem();
		
		return _instance;
	}

    // Method to intake game elements
    public void intake() {
        // Check if limit switch is not pressed before starting intake
        
        practiceMotor.set(ControlMode.PercentOutput, 1); // Example: Set motor to 50% output
                
    }
     public void Output() {
        // Check if limit switch is not pressed before starting intake
        
        practiceMotor.set(ControlMode.PercentOutput, -1); // Example: Set motor to -50% output
                
    }
    public void stopArmI(){
        practiceMotor.set(ControlMode.PercentOutput, 0.0); // Example: Set motor to 0% output

    }
    
}
