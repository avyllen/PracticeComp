// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  private CANSparkFlex m_leftElevator;
  private SparkPIDController l_pidController;
  private RelativeEncoder l_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  private ShuffleboardTab tab = Shuffleboard.getTab("Elevator");
  private GenericEntry elevatorEncoder = tab.add("Elevator Encoder", 0).getEntry();
private GenericEntry elevatorVoltage =
      tab.add("Elevator Voltage", 0)
         .getEntry();
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {

    m_leftElevator = new CANSparkFlex(ElevatorConstants.leftElevator, MotorType.kBrushless);
    m_leftElevator.restoreFactoryDefaults();
    m_leftElevator.setIdleMode(IdleMode.kBrake);

    /**
       * In order to use PID functionality for a controller, a SparkPIDController object
       * is constructed by calling the getPIDController() method on an existing
       * CANSparkMax object
    */

    l_pidController = m_leftElevator.getPIDController();

// Encoder object created to display position values
     l_encoder = m_leftElevator.getEncoder();
  
     // PID coefficients
     kP = 0.02; 
     kI = 0;
     kD = 0; 
     kIz = 0; 
     kFF = 0.000015; 
     kMaxOutput = 1; 
     kMinOutput = -1;
     maxRPM = 5700;
  
     // Set PID coefficients
     l_pidController.setP(kP);
     l_pidController.setI(kI);
     l_pidController.setD(kD);
     l_pidController.setIZone(kIz);
     l_pidController.setFF(kFF);
     l_pidController.setOutputRange(kMinOutput, kMaxOutput);
  
     l_encoder.setPosition(0);
  }
public void setVelocity(double setPoint)
{
  m_leftElevator.set(-setPoint);
       SmartDashboard.putNumber("Right Drive Encoder", l_encoder.getPosition());
}

private void setPosition(double setPoint)
{
  l_pidController.setReference(setPoint, CANSparkMax.ControlType.kPosition);
}

public double getEncoder()
{
  return l_encoder.getPosition();
}

public Command withVelocity(double setPoint)
{
  return runOnce(() -> this.setVelocity(setPoint));

}

public Command slowUp()
{
  return run(() -> this.setVelocity(.1));
}


public Command slowDown()
{
  return run(() -> this.setVelocity(-.1));
}


public Command stop()
{
  return run(() -> this.setVelocity(0));
}


public Command withPosition(double setPoint)
{
  return runOnce(() -> this.setPosition(setPoint));
}

public Command holdPosition()
{
  return run(() -> this.setPosition(this.getEncoder()));
}

public Command setHomePosition()
{
  return run(() -> this.setPosition(ElevatorConstants.eHomePos)); // need to find
}

public Command setUpPosition()
{
  return run(() -> this.setPosition(ElevatorConstants.eUpPos)); // need to find
}


public Command setClimbPosition()
{
  return runOnce(() -> this.setPosition(ElevatorConstants.eClimbPos)); // need to find
}

public boolean LimitChecks()
{
return ((l_encoder.getPosition() > -1.5 && m_leftElevator.getAppliedOutput() > 0) || (l_encoder.getPosition() < -50 && m_leftElevator.getAppliedOutput() < 0));
}

@Override
public void periodic() {
  // This method will be called once per scheduler run

  elevatorEncoder.setDouble(l_encoder.getPosition());
  elevatorVoltage.setDouble(m_leftElevator.getAppliedOutput());
SmartDashboard.putNumber("Elevator Encoder", l_encoder.getPosition());
SmartDashboard.putNumber("Elevator Appied Voltage", m_leftElevator.getAppliedOutput() );
}
}