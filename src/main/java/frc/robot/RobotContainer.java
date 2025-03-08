package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.commands.Intake;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
    private double MaxAngularRate = RotationsPerSecond.of(0.55).in(RadiansPerSecond);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric().withDeadband(0.1).withRotationalDeadband(0.1).withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    /* Driver Buttons */
    // private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    // private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kX.value);

    // private final JoystickButton moveUp = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
    // private final JoystickButton moveDown = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    // private final JoystickButton intake = new JoystickButton(driver, XboxController.Button.kA.value);
    // private final JoystickButton Output = new JoystickButton(driver, XboxController.Button.kB.value);

    /* Subsystems */
    private final CommandSwerveDrivetrain s_Swerve = TunerConstants.createDrivetrain();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            drivetrain.applyRequest(() -> drive.withVelocityX(Constants.driver.getLeftY() * MaxSpeed

            // new TeleopSwerve(
            //     s_Swerve, 
            //     () -> -driver.getRawAxis(translationAxis), 
            //     () -> -driver.getRawAxis(strafeAxis), 
            //     () -> -driver.getRawAxis(rotationAxis), 
            //     () -> robotCentric.getAsBoolean()
            // )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        //moveUp.whileTrue(new MoveArm(MoveArm.Direction.UP));
        //moveDown.whileTrue(new MoveArm(MoveArm.Direction.DOWN));
        intake.whileTrue(new Intake(Intake.Direction.IN));
        Output.whileTrue(new Intake(Intake.Direction.OUT));       
    }
    

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new simpleAuto(s_Swerve);
    }

    public void zeroModuleAngles(){
        s_Swerve.resetAngles();
    }
}
