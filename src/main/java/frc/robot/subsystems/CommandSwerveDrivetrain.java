package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.TunerConstants.TunerSwerveDrivetrain;
import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import java.util.function.Supplier;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.mechanisms.swerve.LegacySwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CommandSwerveDrivetrain extends TunerSwerveDrivetrain implements Subsystem{
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    public Pigeon2 gyro;

    // public CommandSwerveDrivetrain() {
    //     gyro = new Pigeon2(Constants.Swerve.pigeonID);
    //     gyro.getConfigurator().apply(new Pigeon2Configuration());
    //     zeroGyro();

    //     mSwerveMods = new SwerveModule[] {
    //         new SwerveModule(0, Constants.Swerve.Mod0.constants),
    //         new SwerveModule(1, Constants.Swerve.Mod1.constants),
    //         new SwerveModule(2, Constants.Swerve.Mod2.constants),
    //         new SwerveModule(3, Constants.Swerve.Mod3.constants)
    //     };

    //     swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw(), getModulePositions());
    // }

    public CommandSwerveDrivetrain(
        SwerveDrivetrainConstants drivetrainConstants,
        SwerveModuleConstants<?, ?, ?>...modules){
            super(drivetrainConstants, modules);

        }
    
    public CommandSwerveDrivetrain(
        SwerveDrivetrainConstants drivetrainConstants, 
        double odometryUpdateFrequency,
        SwerveModuleConstants<?, ?, ?>...modules){
            super(drivetrainConstants, odometryUpdateFrequency, modules);
        }

    public CommandSwerveDrivetrain(
            SwerveDrivetrainConstants drivetrainConstants,
            double odometryUpdateFrequency,
            Matrix<N3, N1> odometryStandardDeviation,
            Matrix<N3, N1> visionStandardDeviation,
            SwerveModuleConstants<?, ?, ?>... modules){
                super(drivetrainConstants,odometryUpdateFrequency, odometryStandardDeviation, visionStandardDeviation, modules);
            }

    public final SwerveRequest.FieldCentricFacingAngle fieldCentricFacingAngle = new SwerveRequest.FieldCentricFacingAngle()
    .withDeadband(0.5).withRotationalDeadband(0.5).withDriveRequestType(DriveRequestType.OpenLoopVoltage);

   // public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {

    //     SmartDashboard.putNumber("X Command", translation.getX() /Constants.Swerve.maxSpeed);
    //     SmartDashboard.putNumber("Y Command", translation.getY()/Constants.Swerve.maxSpeed);

    //     SwerveModuleState[] swerveModuleStates =
    //         Constants.Swerve.swerveKinematics.toSwerveModuleStates(
    //             fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
    //                                 translation.getX() * 0.2, 
    //                                 translation.getY() * 0.2, 
    //                                 rotation, 
    //                                 getPose().getRotation()
    //                             )
    //                             : new ChassisSpeeds(
    //                                 translation.getX(), 
    //                                 translation.getY(), 
    //                                 Math.pow(rotation, 2) * 0.15)
    //                             );
    //     SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);

    //     for(SwerveModule mod : mSwerveMods){
    //         mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
    //     }
    // }    

    // /* Used by SwerveControllerCommand in Auto */
    // public void setModuleStates(SwerveModuleState[] desiredStates) {
    //     SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
        
    //     for(SwerveModule mod : mSwerveMods){
    //         mod.setDesiredState(desiredStates[mod.moduleNumber], false);
    //     }
    // }    

    // public Pose2d getPose() {
    //     return swerveOdometry.getPoseMeters();
    // }

    // public void resetOdometry(Pose2d pose) {
    //     swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    // }

    // public void setHeading(Rotation2d rotation) {
    //     resetOdometry(new Pose2d(getPose().getX(), getPose().getY(), rotation));
    // }

    // public SwerveModuleState[] getModuleStates(){
    //     SwerveModuleState[] states = new SwerveModuleState[4];
    //     for(SwerveModule mod : mSwerveMods){
    //         states[mod.moduleNumber] = mod.getState();
    //     }
    //     return states;
    // }

    // public SwerveModulePosition[] getModulePositions(){
    //     SwerveModulePosition[] positions = new SwerveModulePosition[4];
    //     for(SwerveModule mod : mSwerveMods){
    //         positions[mod.moduleNumber] = mod.getPosition();
    //     }
    //     return positions;
    // }

    // public void zeroGyro(){
    //     gyro.setYaw(0);
    // }

    // public Rotation2d getYaw() {
    //     return (Constants.Swerve.invertGyro) ? Rotation2d.fromDegrees(360 - gyro.getYaw().getValueAsDouble()) : Rotation2d.fromDegrees(gyro.getYaw().getValueAsDouble());
    // }

    // public void resetModulesToAbsolute(){
    //     for(SwerveModule mod : mSwerveMods){
    //         mod.resetToAbsolute();
    //     }
    // }

    @Override
    public void periodic(){
       // swerveOdometry.update(getYaw(), getModulePositions());  

        for(SwerveModule mod : mSwerveMods){
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " CANcoder", mod.getCANcoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);    
        }
    }

    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
        return run(() -> this.setControl(requestSupplier.get()));
    }

    public Command c_seedFieldRelative(){
        runOnce(() -> seedFieldCentric());
    }

    public void resetAngles(){
        for(SwerveModule mod : mSwerveMods){
            mod.resetAngle();
        }
    }

    public Command c_cardinalLock(double angle){
        return applyRequest(() -> fieldCentricFacingAngle
        .withVelocityX((Constants.driver.getLeftY())  * .3)
        .withVelocityY((-Constants.driver.getLeftX()) * .3)
        .withTargetDirection(Rotation2d.fromDegrees(angle)));
    }
}