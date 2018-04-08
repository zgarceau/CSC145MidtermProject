import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import static javafx.scene.text.FontPosture.REGULAR;
import javafx.scene.text.FontWeight;
import static javafx.scene.text.FontWeight.BOLD;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Name: Zachary Garceau & Blake Duschel
//Course: CSC 145
//Project: Midterm Project Option 2
//File Name: TurtleWalkingSimulator.java
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//Note: The project has audio. If the wav file is not
//      found, the program will still run just as intended

public class Frame extends Application
{
    private Spinner<Integer> steps;
    private Button run;
    private Rectangle grass, line1, line2;
    private Color area = Color.GREEN;
    private Image background, turtle;
    private ImageView bg, franklin;
    private Text directions, directionsOpener;
    private int stepCounter;
    private int x = -900;
    private int y = 100;
    private int x2 = -3;
    private int y2 = 1;
    
    public void start(Stage primaryStage)
    {
        //Unfilled HBox. 
        //This is supposed to display text where the turtle walks
        directionsOpener = new Text("Directions: ");
        directionsOpener.setFont(Font.font("Helvetica", BOLD, REGULAR, 36));
        directions = new Text();
        directions.setFont(Font.font("Helvetica", BOLD, REGULAR, 36));
        HBox top = new HBox(directionsOpener, directions);
        
        //Giant rectangle area for turtle
        grass = new Rectangle(886, 600);
        grass.setFill(Color.GREEN);
        grass.setTranslateY(100);
        grass.setStroke(Color.DARKGREEN);
        grass.setStrokeWidth(10);
        Group vertG = new Group();

        //For loops to repeat space dividers
        //Vertical lines
        for (int i = 1; i <= 8; i++)
        {
            if (y == 100)
                x = x + 100;
            else
                break;
            
            line1 = new Rectangle(x, y, 10, 600);
            line1.setFill(Color.DARKGREEN);
            vertG.getChildren().add(line1);  
            vertG.setTranslateX(-805);
            vertG.setTranslateY(100);
        }
      
        //Horizontal lines
        Group horzG = new Group();
        for (int j = 1; j <= 7; j++)
        {
            if (x2 == -3)
                y2 = y2 + 100;
            else
                break;
            
            line2 = new Rectangle(x2, y2, 890, 10);
            line2.setFill(Color.DARKGREEN);
            horzG.getChildren().add(line2);  
            horzG.setTranslateX(-1600);
            horzG.setTranslateY(100);
        }
        
        //HBox for turtle walking area
        HBox middle = new HBox(grass, vertG, horzG);
        middle.setTranslateX(297);
        middle.setTranslateY(50);
         
        //Spinner that allows up to 100 max steps for turtle
        //Can be changed to text input instead
        SpinnerValueFactory.IntegerSpinnerValueFactory svf =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5);
        steps = new Spinner<Integer>(svf);
        steps.setScaleX(1.4);
        steps.setScaleY(1.4);
        steps.setTranslateX(-10);
        steps.setTranslateY(830);
        
        //Text title "Number of steps" in papryus just because
        Text numberSteps = new Text (120, 100, "Number of steps");
        Font font = Font.font("Papyrus", FontWeight.BOLD, 20);
        numberSteps.setFont(font);
        numberSteps.setStroke(Color.WHITE);
        numberSteps.setTranslateX(-200);
        numberSteps.setTranslateY(795);
        
        //Execute turtle walk button
        run = new Button("SKATE");
        run.setScaleX(2.3);
        run.setScaleY(2.3);
        run.setTranslateX(330);
        run.setTranslateY(825);
        
        
        //Ellipse to help buttons stand out
        Ellipse back = new Ellipse(140,50);
        back.setFill(Color.CADETBLUE);
        back.setTranslateX(400);
        back.setTranslateY(790);
          
        //Ellipse two
        Ellipse back2 = new Ellipse(100,50);
        back2.setFill(Color.CADETBLUE);
        back2.setTranslateX(600);
        back2.setTranslateY(790);
        
        //HBox to put all controls in
        HBox bottom = new HBox(back, back2, steps, run, numberSteps);
        
        //Image of turtle
        turtle = new Image("FranklinSkateboardMaster.png");
        franklin = new ImageView(turtle);
        franklin.setScaleX(.40);
        franklin.setScaleY(.40);
        franklin.setX(650);
        franklin.setY(290);
        
        //Cool epic background image I created in powerpoint
        background = new Image("CoolBackground.png");
        bg = new ImageView(background);
        bg.setScaleX(1.3);
        bg.setScaleY(1.3);
        bg.setTranslateX(160);
        bg.setTranslateY(100);
        
        run.setOnAction(this::processButtonPress);

        //Groups all three HBoxes, and images together
        Group root = new Group(bg, top, middle, bottom, franklin);
        
        Scene scene = new Scene(root, 1490, 900, Color.WHITE);
        
        primaryStage.setTitle("Turtle Walking Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Opening audio (Hopefully some bonus points)
        try {
            File file = new File("Opening.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    //Event Process
        public void processButtonPress(ActionEvent event)
    {
        //Franklin flies to the left corner when I hit run everytime.
        directions.setText("");
        String directionText = "";
        Integer stepsInt = steps.getValue();
        stepCounter = stepsInt;
        
        Random generator = new Random();
        for(int s = 0; s < stepCounter; s++)
        {
            int direction = generator.nextInt(4);
            switch(direction)
            {
                case(0):
                    franklin.setTranslateX(100);
                    directionText += "RIGHT ";
                    break;
                case(1):
                    franklin.setTranslateY(100);
                    directionText += "DOWN ";
                    break;
                case(2):
                    franklin.setTranslateX(-100);
                    directionText += "LEFT ";
                    break;
                case(3):
                    franklin.setTranslateY(-100);
                    directionText += "UP ";
                    break;
            }
        }
        
        directions.setText(directionText);
    }
        
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
