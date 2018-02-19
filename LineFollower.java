import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class LineFollower implements Behavior {
  private boolean suppressed;
  DifferentialPilot pilot;
	LightSensor light_left;
  LightSensor light_right;

  public LineFollower (DifferentialPilot pilot, LightSensor light_left, LightSensor light_right) {
    suppressed = false;
    this.pilot = pilot;
    this.light_left = light_left;
    this.light_right = light_right;
  }

  public boolean takeControl() {
    return true;
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    pilot.forward();
    while (!suppressed) {
      Thread.yield();
    }
    pilot.stop();
  }
}
