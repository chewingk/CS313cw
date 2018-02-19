import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class TurnDirect implements Behavior {
  private boolean suppressed = false;
  DifferentialPilot pilot;
	LightSensor light_left;
  LightSensor light_right;

  public TurnDirect(DifferentialPilot pilot, LightSensor light_left, LightSensor light_right) {
    suppressed = false;
    this.pilot = pilot;
    this.light_left = light_left;
    this.light_right = light_right;
  }

  public boolean takeControl() {
    return (light_left.readNormalizedValue()<474 || light_right.readNormalizedValue()<545);
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    if (light_left.readNormalizedValue() < 474) {
      System.out.println("LEFT");
      pilot.rotate(-45);
    } else if (light_right.readNormalizedValue() < 545) {
      System.out.println("RIGHT");
      pilot.rotate(45);
    }
    pilot.travel(1);
  }
}
