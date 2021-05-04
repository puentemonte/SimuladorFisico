package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	private JLabel currTime;
	private JLabel currLaws;
	private JLabel numOfBodies;
	private ForceLaws fl;
	private Controller ctrl;
	
	public StatusBar(Controller ctrl) {
		this.ctrl = ctrl;
		initGUI();
		ctrl.addObserver(this);
		this.fl = ctrl.getForceLaws();
	}
	
	private void initGUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 20)); 
		setBorder(BorderFactory.createBevelBorder(1));
		
		// Creamos las etiquetas
		currTime = new JLabel("Time: ");
		numOfBodies = new JLabel("Bodies: ");
		currLaws = new JLabel("Laws: ");
		
		// A�adimos las etiquetas al panel
		add(currTime);
		add(numOfBodies);
		add(currLaws);
	}

	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		fl = ctrl.getForceLaws();
		currLaws.setText("Laws: " + fl);
		currTime.setText("Time: " + time);
		numOfBodies.setText("Bodies: " + bodies.size());
	}

	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		fl = ctrl.getForceLaws();
		currLaws.setText("Laws: " + fl);
		currTime.setText("Time: " + time);
		numOfBodies.setText("Bodies: " + bodies.size());
	}

	public void onBodyAdded(List<Body> bodies, Body b) {
		numOfBodies.setText("Bodies: " + bodies.size());
	}

	public void onAdvance(List<Body> bodies, double time) {
		currTime.setText("Time: " + time);
	}

	public void onDeltaTimeChanged(double dt) {}

	public void onForceLawsChanged(String fLawsDesc) {
		fl = ctrl.getForceLaws();
		currLaws.setText("Laws: " + fl);
	}

}