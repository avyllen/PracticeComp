// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.PFSI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkMax;
import java.util.function.BooleanSupplier;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants.LimeLightConstants;
import frc.robot.Constants.OperatorConstants.PivotConstants;
import frc.robot.Constants.OperatorConstants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotSubsystem extends SubsystemBase {

private CANSparkMax m_pivot;
private SparkPIDController m_pidController;
private RelativeEncoder m_encoder;
public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  /** Creates a new PivotSubsystem. */
  public PivotSubsystem() {
    m_pivot = new CANSparkMax(PivotConstants.pivot, MotorType.kBrushless);
    m_pivot.restoreFactoryDefaults();
    m_pidController = m_pivot.getPIDController();
    m_encoder = m_pivot.getEncoder();
  
     // PID coefficients
     kP = 0.2; 
     kI = 1e-4;
     kD = 1; 
     kIz = 0; 
     kFF = 0; 
     kMaxOutput = 1; 
     kMinOutput = -1;
  
     // set PID coefficients
     m_pidController.setP(kP);
     m_pidController.setI(kI);
     m_pidController.setD(kD);
     m_pidController.setIZone(kIz);
     m_pidController.setFF(kFF);
     m_pidController.setOutputRange(kMinOutput, kMaxOutput);
  
     m_encoder.setPosition(0);
  }

public void setVelocity(double setPoint)
{
m_pivot.set(setPoint);
}

public void setPosition(double setPoint)
{
  m_pidController.setReference(setPoint, CANSparkMax.ControlType.kPosition);
}

public void intakePosition()
{
  m_pidController.setReference(9.95, CANSparkMax.ControlType.kPosition);
}

public void subwooferPosition()
{
  m_pidController.setReference(12.5, CANSparkMax.ControlType.kPosition);
}

public void otherPositions()
{
  m_pidController.setReference(12.5, CANSparkMax.ControlType.kPosition);
}

public double getDistance(double ty)
{
    return (LimeLightConstants.limelightLensHeightInches - LimeLightConstants.goalHeightInches) / 
    Math.abs(Math.toRadians(LimeLightConstants.limelightMountAngledegrees) + Math.toRadians(ty));

}

public Command withPosition(double setPoint)
{
  return run(() -> this.setPosition(setPoint));
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

public Command holdPosition()
{
  return run(() -> this.setPosition(this.m_encoder.getPosition()));
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  SmartDashboard.putNumber("Shooter Pivot Encoder", m_encoder.getPosition());
  SmartDashboard.putNumber("Shooter Pivot Appied Voltage", m_pivot.getAppliedOutput() );
}

}
