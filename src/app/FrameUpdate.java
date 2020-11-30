package app;

import java.util.TimerTask;
import javax.swing.JFrame;

public class FrameUpdate extends TimerTask
{
	private JFrame frame;
	
	public FrameUpdate(JFrame localFrame) {
		this.frame = localFrame;
	}
	
	@Override
	public void run() {
		frame.repaint();
		
	}
}
