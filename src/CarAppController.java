
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author drake
 */
public class CarAppController implements Initializable {

    @FXML
    private CheckBox airConditioner;

    @FXML
    private TextArea description;

    @FXML
    private ImageView carView;

    @FXML
    private RadioButton none;

    @FXML
    private ChoiceBox<String> carType;

    @FXML
    private ChoiceBox<String> colorSelector;

    @FXML
    private RadioButton oneYear;

    @FXML
    private CheckBox powerLocks;

    @FXML
    private CheckBox powerWindows;

    @FXML
    private RadioButton threeYears;

    @FXML
    private Label totalPrice;

    Image lamboImage;
    Image ferrariImage;
    Image toyotaImage;
    String toyotaDescription;
    String lamboDescription;
    String ferrariDescription;

    public double price = 0;
    public double price1 = 0;
    public double price2 = 0;
    public double finalPrice = 0;

    // This method runs when the checkboxes on the Interface are selected
    @FXML
    void handleButton(ActionEvent event) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        double tempPrice = 0;
        if (powerWindows.isSelected()) { // Adds price if any of these values are selected
            tempPrice += 5000;
        }

        if (powerLocks.isSelected()) {
            tempPrice += 3000;
        }

        if (airConditioner.isSelected()) {
            tempPrice += 6000;
        }

        if (oneYear.isSelected()) {
            price2 = .10;
        } else if (threeYears.isSelected()) {
            price2 = .20;
        } else if (none.isSelected()) {
            price2 = 0;
        }

        price1 = tempPrice;

        totalPrice.setText(nf.format(price + price1 + price2 * (price + price1))); // computing and displaying the final
                                                                                   // price at the bottom of the screen

    }

    // If the exit button is pressed this method exits the application
    @FXML
    void exitApp(ActionEvent event) {
        System.exit(0);
    }

    // This method is called on start up and sets up the original interace displated
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String[] items = { "Toyota", "Lamborghini", "Ferrari" };

        ObservableList<String> list = FXCollections.observableArrayList(items);
        carType.setItems(list);
        carType.getSelectionModel().selectFirst();
        ChangeListener listener = new MyChangeListener();
        carType.valueProperty().addListener(listener);

        String[] colors1 = { "Grey", "Red" };
        ObservableList<String> colorSelector1 = FXCollections.observableArrayList(colors1); // Setting up the drop down menu
        colorSelector.setItems(colorSelector1);
        colorSelector.getSelectionModel().selectFirst(); // Selecting Automatic first color
        powerLocks.setDisable(true);
        price = 30000;
        totalPrice.setText(nf.format(price));

        // Assigning the images in teh resources folder to variables
        ferrariImage = new Image(getClass().getResourceAsStream("/resources/images/ferrari.jpg"));
        lamboImage = new Image(getClass().getResourceAsStream("/resources/images/lambo.jpg"));
        toyotaImage = new Image(getClass().getResourceAsStream("/resources/images/toyota.jpg"));

        carView.setImage(toyotaImage);

        // Initializing all of the descriptions
        toyotaDescription = "Toyota has car models that offer something for everyone. Whether you’re looking "
                + "for a compact car for commuting to work, a fun sports car, or a "
                + "family vehicle to safely transport your loved ones wherever they need to go, you can rely on Toyota. ";

        lamboDescription = "Manufacturing magnate Italian Ferruccio Lamborghini founded the company in 1963"
                + " with the objective of producing a refined grand touring car to compete with offerings from "
                + "established marques such as Ferrari. The company's first models, such as the 350 GT, were "
                + "released in the mid-1960s. ";

        ferrariDescription = "Ferrari road cars are generally seen as a symbol of speed, luxury and wealth. "
                + "Ferrari cars are built at the 165,000 square-metre "
                + "(16.5-hectare) Maranello factory.";

        description.setText(toyotaDescription);

        description.setEditable(false);

    }

    //Class that adds a listner to the drop down selections in the interface
    private class MyChangeListener implements ChangeListener {

        // Changes the Interface if a different slection in the drop down menu is selected
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

            // Adding color options to each car
            String newValue2 = (String) newValue; // This variable holds the value selected
            String[] colors3 = { "Silver", "Black" };
            ObservableList<String> colorSelector3 = FXCollections.observableArrayList(colors3);
            String[] colors2 = { "Blue", "Pink" };
            ObservableList<String> colorSelector2 = FXCollections.observableArrayList(colors2);
            String[] colors1 = { "Grey", "Red" };
            ObservableList<String> colorSelector1 = FXCollections.observableArrayList(colors1);

            double tempPrice = 0;

            // Chaning things in the interface if toyota is selected
            if (newValue2.equals("Toyota")) {
                colorSelector.setItems(colorSelector1);
                colorSelector.getSelectionModel().selectFirst();
                tempPrice += 30000;
                powerLocks.setDisable(true);
                airConditioner.setDisable(false);
                powerWindows.setDisable(false);
                carView.setImage(toyotaImage);
                description.setText(toyotaDescription);
                powerLocks.setSelected(false);
                airConditioner.setSelected(false);
                powerWindows.setSelected(false);
                price1 = 0;
                price2 = 0;
                none.setSelected(true);

            }

            // Chaning things in the interface if Lamborghini is selected
            if (newValue2.equals("Lamborghini")) {

                colorSelector.setItems(colorSelector2);
                colorSelector.getSelectionModel().selectFirst();
                tempPrice += 150000;
                powerLocks.setDisable(false);
                airConditioner.setDisable(false);
                powerWindows.setDisable(true);
                carView.setImage(lamboImage);
                description.setText(lamboDescription);
                powerLocks.setSelected(false);
                airConditioner.setSelected(false);
                powerWindows.setSelected(false);
                price1 = 0;
                price2 = 0;
                none.setSelected(true);

            }

            // Chaning things in the interface if Ferrari is selected
            if (newValue2.equals("Ferrari")) {
                colorSelector.setItems(colorSelector3);
                colorSelector.getSelectionModel().selectFirst();
                tempPrice += 200000;
                powerLocks.setDisable(false);
                airConditioner.setDisable(true);
                powerWindows.setDisable(false);
                carView.setImage(ferrariImage);
                description.setText(ferrariDescription);
                powerLocks.setSelected(false);
                airConditioner.setSelected(false);
                powerWindows.setSelected(false);
                price1 = 0;
                price2 = 0;
                none.setSelected(true);

            }
            price = tempPrice;

            totalPrice.setText(nf.format(price + price1 + price2 * (price + price1)));
        }
    }

}
