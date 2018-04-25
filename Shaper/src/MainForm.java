import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;

public class MainForm extends Application {

	// Override the start method in the Application class
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		String sep = File.separator;
		String file = "C:" + sep + "Users" + sep + "Karakoz" + sep + "Downloads" + sep + "Civ6_bg" + ".png";
		File file1 = new File(file);
		// Creating an image object
		Image image = new Image(file1.toURI().toString());
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(850);
		imageView.setFitHeight(650);

		VBox menu0 = new VBox(10);

		menu0.setTranslateX(100);
		menu0.setTranslateY(200);

		MenuButton but_shapes = new MenuButton("SHAPES");

		but_shapes.setOnMouseClicked(event -> {
			primaryStage.setTitle("SHAPER");
			GridPane gridPane = new GridPane();
			// Pane Pane = new Pane();
			gridPane.setAlignment(Pos.CENTER);
			gridPane.setStyle("-fx-background-color:khaki");
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			gridPane.setPadding(new Insets(10));

			Label label = new Label("Shape");
			gridPane.add(label, 1, 1, 2, 1);
			label.setTextFill(Color.BLACK);
			label.setFont(Font.font(30));

			ComboBox<Shape> shapes = new ComboBox<Shape>(FXCollections.observableArrayList(getShapes()));
			shapes.setPromptText("Choose your shape");
			//shapes.getSelectionModel().select(0);
			shapes.setPrefWidth(300);
			Button but_Draw = new Button("Draw");
			but_Draw.setPrefWidth(100);
			but_Draw.setFont(Font.font(13));

			Button but_back = new Button("Back");
			but_back.setPrefWidth(100);
			but_back.setFont(Font.font(13));

			gridPane.add(shapes, 3, 1, 2, 1);
			gridPane.add(but_Draw, 4, 2, 1, 1);
			gridPane.add(but_back, 2, 2, 1, 1);
			but_Draw.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					new DrawShape(shapes.getValue());
				}
			});
			but_back.setOnAction(even -> {
				Stage stage = (Stage) but_back.getScene().getWindow();
				stage.close();
			});

			Stage mainStage = new Stage();
			mainStage.initStyle(StageStyle.UNDECORATED);
			mainStage.setScene(new Scene(new StackPane(gridPane), 850, 650));
			mainStage.show();
		});
		MenuButton but_about = new MenuButton("About");
		but_about.setOnMouseClicked(event -> {
			GridPane gridPane = new GridPane();
			gridPane.setStyle("-fx-background-color:lightsteelblue");

			Label label = new Label();
			label.setFont(Font.font(20));
			gridPane.add(label, 1, 1, 2, 1);
			label.setText("\n    This program created by future programmer "
					+ " \n                Madiyar Zhorakhan        "
					+ " \n  This program draw geometric shapes. Draw this "
					+ " \n shapes: circle, ellipse, square, rectangle, trape-"
					+ " \n soid, triangle, parallelogram , rombus, kite, penta-" + " \n gon and hexagon !!!    ");

			Button butBack = new Button("BACK");
			gridPane.add(butBack, 1, 2, 1, 1);
			butBack.setPrefWidth(100);

			butBack.setOnAction(e -> {
				Stage stage = (Stage) butBack.getScene().getWindow();
				stage.close();
			});
			Stage aboutStage = new Stage();
			aboutStage.initStyle(StageStyle.UNDECORATED);
			aboutStage.setScene(new Scene(new StackPane(gridPane), 450, 510));
			aboutStage.show();
		});

		MenuButton but_exit = new MenuButton("EXIT");
		but_exit.setOnMouseClicked(event -> {
			System.exit(0);

		});

		menu0.getChildren().addAll(but_shapes, but_about, but_exit);

		root.getChildren().addAll(imageView, menu0);

		Scene scene = new Scene(root, 850, 650);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private ArrayList<Shape> getShapes() {

		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(new Shape("Circle", 1));
		list.add(new Shape("Ellipse", 2));
		list.add(new Shape("Triangle", 3));
		list.add(new Shape("Square/Rectangle/Trapezoid/Parallelogram/Rhombus/Kite", 4));
		list.add(new Shape("Pentagon", 5));
		list.add(new Shape("Hexagon", 6));

		return list;
	}

	private static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(250, 30);
			bg.setOpacity(0.6);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(5));

			setAlignment(Pos.CENTER_LEFT);
			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);

			});

			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);

			});

		//	DropShadow drop = new DropShadow(50, Color.WHITE);
		//	drop.setInput(new Glow());

		//	setOnMousePressed(event -> setEffect(drop));
		//	setOnMouseReleased(event -> setEffect(null));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
