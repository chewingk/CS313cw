import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Terminate implements Behavior {
  private boolean suppressed;
  DifferentialPilot pilot;
	LightSensor light_left;
  LightSensor light_right;

  public Terminate(DifferentialPilot pilot, LightSensor light_left, LightSensor light_right) {
    suppressed = false;
    this.pilot = pilot;
    this.light_left = light_left;
    this.light_right = light_right;
  }

  public boolean takeControl() {
    return Button.LEFT.isDown();
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    pilot.stop();
    System.exit(0);
  }
}
