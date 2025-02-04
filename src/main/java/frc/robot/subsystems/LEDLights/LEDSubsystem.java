// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.LEDLights;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class LEDSubsystem implements Subsystem{
private AddressableLED m_led;
  private AddressableLEDBuffer m_ledBuffer;
  private int bluePulseBrightness = 0;
    public LEDSubsystem(int PWMPort)
    {
        m_led = new AddressableLED(PWMPort);
        m_ledBuffer = new AddressableLEDBuffer(60);
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public void setRGB(int r, int g, int b)
    {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, r, g, b);
         }
         
         m_led.setData(m_ledBuffer);
    }

    public void setHUE(int h, int s, int v)
    {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the HSV values for red
            m_ledBuffer.setHSV(i, h, s, v);
         }
         
         m_led.setData(m_ledBuffer);
    }
    
    private void rainbow(int m_rainbowFirstPixelHue) {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
          // Set the value
          m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;

        m_led.setData(m_ledBuffer);
        
      }

      public void setRED() {
        this.setRGB(255,0,0);

      }

      public void setGREEN() {
        this.setRGB(0,255,0);

      }


      public void redPulse(){
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Sets the specified LED to the RGB values for blue
          m_ledBuffer.setRGB(i, 0, 0, bluePulseBrightness);
           }
        
           //increase brightness
           bluePulseBrightness += 5;
        
           //Check bounds
           bluePulseBrightness %= 255;
        
           m_led.setData(m_ledBuffer);
        
          }

      public Command setREDCommand() {
        return runOnce(() -> this.setRED());
    }

      public Command setREDPulse() {
        return run(() -> this.setREDPulse());
    }

      public Command setInit() {
        return runOnce(() -> this.setRGB(255, 192, 203));
    }

    public Command setBLUE() {
        return runOnce(() -> this.setRGB(0, 0, 255));
    }

     public Command setGreenCommand() {
        return runOnce(() -> this.setGREEN());
    }

     public Command setRainbow() {
        return runOnce(() -> this.rainbow(0));
    }

    public Command setRainbowAni(double d) {
        return runOnce(() -> this.rainbow((int) d));
    }
}
  


