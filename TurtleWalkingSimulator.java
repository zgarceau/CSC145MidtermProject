
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Name: Zachary Garceau & Blake Duschel
//Course: CSC 145
//Project: Midterm Project Option 2
//File Name: TurtleWalkingSimulator.java

public class TurtleWalkingSimulator extends Application
{
    private Button walk;
    private TextField steps;
    private ImageView image;
    private Image turtle;
    private Text finalWalkDistance;
    private int stepCounter;
    
    public void start(Stage primaryStage)
    {
        turtle = new Image("CommunistDoggo.jpg");
        image = new ImageView(turtle);
        image.setScaleX(.20);
        image.setScaleY(.20);
        
        steps = new TextField();
        steps.setMaxWidth(50);
        
        walk = new Button("Walk");
        walk.setOnAction(this::processButtonPress);
        
        HBox guide = new HBox(steps, walk);
        
        Group root = new Group(guide, image);
        Scene scene = new Scene(root, 700, 700, Color.BLACK);
        
        primaryStage.setTitle("Turtle Walking Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void processButtonPress(ActionEvent event)
    {
        Integer stepsInt = new Integer(steps.getText());
        stepCounter = stepsInt;
        
        Random generator = new Random();
        for(int s = 0; s < stepCounter; s++)
        {
            int direction = generator.nextInt(4);
            switch(direction)
            {
                case(0):
                    image.setTranslateX(5);
                case(1):
                    image.setTranslateY(5);
                case(2):
                    image.setTranslateX(-5);
                case(3):
                    image.setTranslateY(-5);
            }
        }    
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
