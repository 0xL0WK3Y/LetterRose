import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LetterRoseGUI extends Application implements EventHandler<ActionEvent>
{
	String text;
	String processedText;
	int key;
	TextField inputedField;
	TextField convertedField;
	TextField keyField;
	Button encryptButton;
	Button decryptButton;
	Label inputedLabel;
	Label convertedLabel;
	Label keyLabel;
	String backImage = "bImage.jpg";
	ObservableList<String> tools = FXCollections.observableArrayList("Base64 Tool", "Caesar's Tool");
	ComboBox comboBox;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception //Create the GUI
	{
		primaryStage.setTitle("Letter Rose");
		inputedField = new TextField();
		convertedField = new TextField();
		convertedField.setEditable(false);
		keyField = new TextField();
		keyField.setStyle("-fx-control-inner-background: gray");
		keyField.setEditable(false);
		keyField.setPrefWidth(1000);
		encryptButton = new Button("Encrypt");
		encryptButton.setOnAction(this);
		decryptButton = new Button("Decrypt");
		decryptButton.setOnAction(this);
		
		inputedLabel = new Label("Inputed Text:");
		inputedLabel.setStyle("-fx-text-fill: white");
		convertedLabel = new Label("Converted Text:");
		convertedLabel.setStyle("-fx-text-fill: white");
		keyLabel = new Label("Key:");
		keyLabel.setStyle("-fx-text-fill: white");
		
		comboBox = new ComboBox(tools);
		comboBox.setValue("Base64 Tool");
		comboBox.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if(newValue.equals("Caesar's Tool")  )
				{
					keyField.setStyle("-fx-control-inner-background: white");
					keyField.setEditable(true);
				}
				else
				{
					keyField.setStyle("-fx-control-inner-background: gray");
					keyField.setText("");
					keyField.setEditable(false);
				}
			}
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setStyle("-fx-background-image: url('" + backImage + "'); ");
		grid.add(inputedLabel, 1, 1);
		grid.add(inputedField,1,2);
		grid.add(convertedLabel,1,3);
		grid.add(convertedField,1,4);
		grid.add(keyLabel,1,5);
		grid.add(keyField,1,6);
		grid.add(comboBox,1,7);
		grid.add(encryptButton,1,8);
		grid.add(decryptButton,1,9);
		
		Scene mainScene = new Scene(grid, 400, 390);
		mainScene.setRoot(grid);
		primaryStage.setScene(mainScene);
		primaryStage.getIcons().add(new Image("file:icon.png"));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> { System.exit(0); } );
	}
	
	@Override
	public void handle(ActionEvent event)
	{
		try
		{
			if(event.getSource().equals(encryptButton))
			{
				if(comboBox.getValue().equals("Caesar's Tool")) //Encrypt using Caesar's Cipher
				{
					text = inputedField.getText();
					key = Integer.parseInt(keyField.getText());
					Caesar caesar = new Caesar();
					processedText = caesar.encrypt(text,key);
					convertedField.setText(processedText);
				}
				else if(comboBox.getValue().equals("Base64 Tool")) //Encrypt using Base64
				{
					text = inputedField.getText();
					Base64Tool b64t = new Base64Tool();
					processedText = b64t.encrypt(text);
					convertedField.setText(processedText);
				}
			}
			else if(event.getSource().equals(decryptButton))
			{
				if(comboBox.getValue().equals("Caesar's Tool")) //Decrypt using Caesar's Cipher
				{
					text = inputedField.getText();
					key = Integer.parseInt(keyField.getText());
					Caesar caesar = new Caesar();
					processedText = caesar.decrypt(text,key);
					convertedField.setText(processedText);
				}
				else if(comboBox.getValue().equals("Base64 Tool")) //Decrypt using Base64
				{
					text = inputedField.getText();
					Base64Tool b64t = new Base64Tool();
					processedText = b64t.decrypt(text);
					convertedField.setText(processedText);
				}
			}
		}
		catch(Exception e)
		{
			convertedField.setText("Enter valid input!");
		}
	}
}
