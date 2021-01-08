package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.FileNotFoundException;
import java.io.Serializable;

//Custom pane to create imageViews with specified characteristics
public class ImageClassApp  extends Pane implements Serializable {
    public ImageClassApp(String url, int x, int y, int width, int height) throws FileNotFoundException {

        //Creating the image with the given url, dimensions, and setting the aspect ratio to false (dimensions already preserve aspect ratio)
        Image image = new Image(url,width,height,false,false);

        //Declaring and initializing the imageView with the image created above
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(x);
        imageView.setY(y);



        //Adding the imageView to the pane
        getChildren().add(imageView);




    }
}

