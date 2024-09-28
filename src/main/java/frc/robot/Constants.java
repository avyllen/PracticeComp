// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

    public static final class ShooterConstants {
    
      public static final int topShooter = 1;
      public static final int botShooter = 2;
      public static final int shootSpeed = 100;
      
    }
    public static final class IntakeConstants {
      public static final int intakeID = 3;
    public static final double IntakeSPEED = 50;
    }

    public static final class FeederConstants {
      public static final int feederID = 4;
      public static final int feederEncoderA = 5;
      public static final int feederEncoderB = 6;
      public static final double IntakeSPEED = 50;
    }

    public static final class PivotConstants {
        
      public static final int pivot = 37;
      public static final int pivotspeed = 80;
    }

    public static final class ElevatorConstants {
        
      public static final int leftElevator = 7;
      public static final double eHomePos = 0;
      public static final double eUpPos = 100;
      public static final double eClimbPos = 25;
    }

    public static final class ClimberConstants
    {
      public static final int climber = 8;
      public static final int cHomePos = 0;
      public static final int cUpPose = 61;
      public static final int cClimbPos = 100; 
      
    }

    public static final class LimeLightConstants {
      public static final double limelightMountAngledegrees = Math.toRadians(28);
      public static final double limelightLensHeightInches = 31.0625;
      public static final double goalHeightInches = 49.3125;
      public static final double heightOfShooter = 0;           
      public static final double distanceFromShooter = 0;
    }
  }


}
