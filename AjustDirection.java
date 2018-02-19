import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class AjustDirection implements Behavior {
  private boolean suppressed = false;
  DifferentialPilot pilot;
	LightSensor light_left;
  LightSensor light_right;
  int left_val;
  int right_val;

  public AjustDirection(DifferentialPilot pilot, LightSensor light_left, LightSensor light_right, int left_val, int right_val) {
    suppressed = false;
    this.pilot = pilot;
    this.light_left = light_left;
    this.light_right = light_right;
    this.left_val = left_val;
    System.out.println(left_val);
    this.right_val = right_val;
  }

  public boolean takeControl() {
    //System.out.println(left_val - light_left.readNormalizedValue());
    return ((left_val - light_left.readNormalizedValue()) > 10 || (right_val - light_right.readNormalizedValue()) > 10);
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    if ((left_val - light_left.readNormalizedValue()) > 10) {
      pilot.rotate(-5);
    } else if ((right_val - light_right.readNormalizedValue()) > 10) {
      pilot.rotate(5);
    }
    left_val = light_left.readNormalizedValue();
    right_val = light_right.readNormalizedValue();
  }
}
