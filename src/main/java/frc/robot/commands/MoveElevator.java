package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;



public class MoveElevator extends Command {
    public static enum Direction {
        UP,
        DOWN
    }
    
    private Direction _dir;
    public MoveElevator(Direction dir){
        _dir = dir; 
    }

    @Override
    public void initialize() {
        switch(_dir) {
            case UP:
                ElevatorSubsystem.getInstance().moveUp();
                break;
            case DOWN:
                ElevatorSubsystem.getInstance().moveDown();
                break;
        }
    }
    
    @Override
    public void end(boolean interrupted){
        ElevatorSubsystem.getInstance().stopArm();
    }

   // @Override
    //public boolean isFinished(){
   //     switch (_dir) {
    //        case UP:
    //            return _timer.get() >= Constants.AUTO_UP_TIME;
    //    }

     //   return false;
   // }
}
