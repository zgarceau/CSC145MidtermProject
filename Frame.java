import javafx.scene.shape.Rectangle;
import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Frame extends Application
{
    
    private Spinner<Integer> steps;
    private Button run;
    private Rectangle grass, line1, line2;
    private Color area = Color.GREEN;
    private int x = -900;
    private int y = 100;
    private int x2 = -3;
    private int y2 = 1;
    
    public void start(Stage primaryStage)
    {
        
        HBox top = new HBox();
        
        
        grass = new Rectangle(886, 600);
        grass.setFill(Color.GREEN);
        grass.setTranslateY(100);
        grass.setStroke(Color.DARKGREEN);
        grass.setStrokeWidth(10);
        Group vertG = new Group();

        
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
        
        HBox middle = new HBox(grass, vertG, horzG);
        middle.setTranslateX(297);
        middle.setTranslateY(50);
         
        SpinnerValueFactory.IntegerSpinnerValueFactory svf =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 5);
        steps = new Spinner<Integer>(svf);
        steps.setScaleX(1.4);
        steps.setScaleY(1.4);
        steps.setTranslateX(-10);
        steps.setTranslateY(830);
        
        Text numberSteps = new Text (120, 100, "Number of steps");
        Font font = Font.font("Papyrus", FontWeight.BOLD, 20);
        numberSteps.setFont(font);
        numberSteps.setStroke(Color.WHITE);
        numberSteps.setTranslateX(-200);
        numberSteps.setTranslateY(795);
        
        run = new Button("RUN");
        run.setScaleX(2.3);
        run.setScaleY(2.3);
        run.setTranslateX(330);
        run.setTranslateY(825);
        
        Ellipse back = new Ellipse(140,50);
        back.setFill(Color.CADETBLUE);
        back.setTranslateX(400);
        back.setTranslateY(790);
                
        Ellipse back2 = new Ellipse(100,50);
        back2.setFill(Color.CADETBLUE);
        back2.setTranslateX(600);
        back2.setTranslateY(790);
        
        HBox bottom = new HBox(back, back2, steps, run, numberSteps);
        
        Image img = new Image("CoolBackground.png");
        ImageView imgView = new ImageView(img);
        
        imgView.setScaleX(1.3);
        imgView.setScaleY(1.3);
        imgView.setTranslateX(160);
        imgView.setTranslateY(100);
        

        
        
        Group root = new Group(imgView, top, middle, bottom);
        
        Scene scene = new Scene(root, 1490, 900, Color.WHITE);
        
        primaryStage.setTitle("Turtle Walk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
    
}
