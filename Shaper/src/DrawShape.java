
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawShape {
	private TextField[] textFields;
	private Shape shape;

	public DrawShape(Shape shape) {
		this.shape = shape;
		start();
	}

	public void start() {
		int r = 0;
		Canvas canvas = new Canvas(500, 500);
		canvas.setStyle("-fx-background-color:khaki");
		GraphicsContext gc = canvas.getGraphicsContext2D();

		GridPane inputGrid = new GridPane();
		inputGrid.setHgap(10);
		inputGrid.setVgap(10);
		inputGrid.setPadding(new Insets(5));
        inputGrid.setStyle("-fx-background-color:snow");
		if (shape.getCountOfPoints() == 1 && (shape.getId() == 0 || shape.getId() == 1)) {
			textFields = new TextField[3 + shape.getId()];
			inputGrid.add(new Label("CenterX:"), 0, r);

			for (int i = 0; i < textFields.length; i++)
				textFields[i] = new TextField();
			inputGrid.add(textFields[0], 1, r);
			inputGrid.add(new Label("CenterY:"), 3, r);
			inputGrid.add(textFields[1], 4, r);
			r++;

			inputGrid.add(new Label(shape.getId() == 0 ? "Radius:" : "Width:"), 0, r);
			inputGrid.add(textFields[2], 1, r);

			if (shape.getId() == 1) {
				inputGrid.add(new Label("Height:"), 3, r);
				inputGrid.add(textFields[3], 4, r);
			}
			r++;
		} else {
			textFields = new TextField[shape.getCountOfPoints() * 2];

			int k = 1;
			for (int i = 0; i < textFields.length; i++) {
				inputGrid.add(new Label("X" + k), 0, r);
				inputGrid.add(new Label("Y" + k), 3, r);
				textFields[i] = new TextField();
				inputGrid.add(textFields[i], 1, r);
				textFields[++i] = new TextField();
				inputGrid.add(textFields[i], 4, r);
				r++;
				k++;
			}
		}
		r += 2;
		Label lblErr = new Label(" ");
		lblErr.setTextFill(Color.RED);
		inputGrid.add(lblErr, 1, r--, 5, 1);

		Button doButton = new Button("Draw");
		doButton.setPrefWidth(100);
		inputGrid.add(doButton, 3, r, 5, 1);
		doButton.setOnAction(e -> getArea(lblErr, gc));

		Button but_back = new Button("Back");
		but_back.setPrefWidth(100);
		inputGrid.add(but_back, 1, r, 4, 1);
		but_back.setOnAction(e -> {
			Stage stage = (Stage) but_back.getScene().getWindow();
			stage.close();
		});

		Stage drawStage = new Stage();
		drawStage.setTitle(shape.getName());
		drawStage.setScene(new Scene(new VBox(10, inputGrid, canvas), 720, 650));
		drawStage.show();
	}

	private void getArea(final Label lbl, final GraphicsContext gc) {
		lbl.setText(" ");
		try {
			drawG(gc);

		} catch (NumberFormatException ex) {
			lbl.setText(String.format(lbl.getText(), ex.getMessage()));
		} catch (ArithmeticException ex) {
			lbl.setText(String.format(lbl.getText(), ex.getMessage()));
		} catch (Exception ex) {
			lbl.setText(String.format(lbl.getText(), ex.getMessage()));
		}

	}

	private void drawG(final GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 360, 360);

		gc.setLineWidth(0.5);
		gc.setStroke(Color.GRAY);

		for (int i = 0; i < 370; i += 10) {
			if (i % 20 == 0)
				gc.setStroke(Color.BLACK);
			else
				gc.setStroke(Color.GRAY);
			gc.strokeLine(i, 0, i, 360);
			gc.strokeLine(0, i, 360, i);
		}

		//
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);

		gc.strokeLine(180, 0, 180, 360);
		gc.strokeLine(0, 180, 360, 180);

		gc.setStroke(Color.BLACK);
		gc.setFill(Color.RED);
		if (shape.getId() == 0) {
			double x = Double.parseDouble(textFields[0].getText() + "") + 150,
					y = -Double.parseDouble(textFields[1].getText() + "") + 150,
					w = Double.parseDouble(textFields[2].getText() + "") / 2;
			gc.fillOval(x - w / 2, y - w / 2, w, w);

		} else if (shape.getId() == 1) {
			double x = Double.parseDouble(textFields[0].getText() + "") + 150,
					y = -Double.parseDouble(textFields[1].getText() + "") + 150,
					w = Double.parseDouble(textFields[2].getText() + ""),
					h = Double.parseDouble(textFields[3].getText() + "");
			gc.fillOval(x - w / 2, y - h / 2, w, h);
		} else {
			for (int i = 0; i <= textFields.length; i++) {
				if (i == 0) {
					gc.strokeLine(150 + Double.parseDouble(textFields[i++].getText() + ""),
							150 - Double.parseDouble(textFields[i++].getText() + ""),
							150 + Double.parseDouble(textFields[i++].getText() + ""),
							150 - Double.parseDouble(textFields[i].getText() + ""));
				} else if (i == textFields.length) {
					i -= 2;
					gc.strokeLine(150 + Double.parseDouble(textFields[i++].getText() + ""),
							150 - Double.parseDouble(textFields[i++].getText() + ""),
							150 + Double.parseDouble(textFields[0].getText() + ""),
							150 - Double.parseDouble(textFields[1].getText() + ""));
				} else {
					i -= 2;
					gc.strokeLine(150 + Double.parseDouble(textFields[i++].getText() + ""),
							150 - Double.parseDouble(textFields[i++].getText() + ""),
							150 + Double.parseDouble(textFields[i++].getText() + ""),
							150 - Double.parseDouble(textFields[i].getText() + ""));
				}
			}

		}
	}
}
