package mandelbrotset.example;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MandelbrotSetViewer extends JFrame {

	private JPanel contentPane;
	private MandelbrotSetModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MandelbrotSetViewer frame = new MandelbrotSetViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MandelbrotSetViewer() {
		model = new MandelbrotSetModel();
		// 各点において、発散するか否かを判定する
		model.calc();
		// 描画する
		paint();
	}

	private void paint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				// the size of 1 cell on panel
				double dx = contentPane.getWidth() / (double) MandelbrotSetModel.ARRAY_WIDTH;
				double dy = contentPane.getHeight() / (double) MandelbrotSetModel.ARRAY_HEIGHT;

				for (int yArrayIndex = 0; yArrayIndex < MandelbrotSetModel.ARRAY_HEIGHT; yArrayIndex++) {
					double coordinateY = dy * yArrayIndex;
					for (int xArrayIndex = 0; xArrayIndex < MandelbrotSetModel.ARRAY_WIDTH; xArrayIndex++) {
						double coordinateX = dx * xArrayIndex;
						Cell cell = model.getArray()[yArrayIndex][xArrayIndex];

						// decide the color of each cell
						if (cell.isConvergent) {
							g2.setPaint(Color.BLUE);
						} else {
							if (cell.calcCount % 3 == 0) {
								g2.setPaint(Color.YELLOW);
							} else if (cell.calcCount % 2 == 0) {
								g2.setPaint(Color.RED);
							} else {
								g2.setPaint(Color.GREEN);
							}
						}

						// draw the cell
						Rectangle2D rect = new Rectangle2D.Double(coordinateX, coordinateY, dx, dy);
						rect.setFrame(rect);
						g2.fill(rect);
					}
				}
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
