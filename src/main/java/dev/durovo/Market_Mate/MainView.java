//Jake Bryant

package dev.durovo.Market_Mate;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;

@Route("")
public class MainView extends VerticalLayout {

    private static final String COLOR = "#89CFF0";
    //Parallel Array Lists
    static ArrayList<String> cartItemName = new ArrayList<String>();
    static ArrayList<Double> cartItemPrice = new ArrayList<Double>();

    public MainView() {
        //Apply the background color to the MainView itself
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        //Outer Parent
        FlexLayout shelf = new FlexLayout();
        shelf.setWidthFull();
        shelf.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        shelf.setJustifyContentMode(JustifyContentMode.BETWEEN);
        shelf.getStyle().set("gap", "20px");

        for (int i = 1; i <= 12; i++) {
            shelf.add(createItem(
                    "Sample Item " + i,
                    "$49.99",
                    "https://dummyimage.com/300x200/000/fff"
            ));
        }

        add(
                createHeader(),
                shelf,
                nextPageButton()
        );
    }

    private Component createHeader() {
        H1 title = new H1("Welcome to Market-Mate!");

        // This text will now be visible because the background is green
        title.getStyle().set("color", "white");
        title.getStyle().set("font-family", "Georgia, serif");
        title.getStyle().set("margin-top", "20px");

        HorizontalLayout header = new HorizontalLayout(title);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        return header;
    }

    private Component createItem(String name, String price, String imageUrl) {
        //The Container for a single item
        VerticalLayout card = new VerticalLayout();

        // setSpacing(false) brings the text closer to the image
        card.setSpacing(false);
        card.setPadding(true);
        card.setAlignItems(Alignment.CENTER); // Center text horizontally

        // Give the card a fixed width so they grid nicely
        card.setWidth("250px");

        //Adds a border/shadow to make it look like a "card"
        card.getStyle().set("border", "1px solid #ddd");
        card.getStyle().set("border-radius", "8px");
        card.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
        card.getStyle().set("background-color", "white");


        //The image of items up for sale
        Image image = new Image(imageUrl, name);
        image.setWidth("100%"); // Fit the card width
        image.setHeight("180px"); // Fixed height for uniformity
        image.getStyle().set("object-fit", "cover"); // Prevent image distortion
        image.getStyle().set("border-radius", "8px 8px 0 0"); // Round top corners
        image.getStyle().set("cursor", "pointer"); //Make cursor look like a hand on image

        //Adding objects to cart
        image.addClickListener(event -> {
            Notification.show("Added " + name + " to cart."); //Need to make SQLite logic to have a cart for when
            // you're on the website, could use parallel array list for now to show functionality
            try {
                cartItemName.add(name);
                String noDollarSign = price.substring(1);
                double itemPrice = Double.parseDouble(noDollarSign);
                cartItemPrice.add(itemPrice);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: " + price + " cannot be converted to a double."); //Handle the input value being in wrong format
            }
        });


        //The text below the item's image
        Span nameSpan = new Span(name);
        nameSpan.getStyle().set("font-weight", "bold");
        nameSpan.getStyle().set("margin-top", "10px");

        Span priceSpan = new Span(price);
        priceSpan.getStyle().set("color", "green");
        priceSpan.getStyle().set("font-size", "0.9em");

        //Stacking the images, name and price vertically
        card.add(image, nameSpan, priceSpan);

        return card;
    }

    private Component nextPageButton() {
        Button sendButton = new Button("Go to cart");
        sendButton.setWidthFull();
        sendButton.getStyle().set("background-color", "white");
        sendButton.getStyle().set("cursor", "pointer");
        sendButton.addClickListener(event -> {
            sendButton.getUI().ifPresent(ui ->
                    ui.navigate(CartView.class)
            );
        });
        return sendButton;
}

// static ArrayList<String> cartItemName = new ArrayList<String>();
//    static ArrayList<Double> cartItemPrice = new ArrayList<Double>();

public static ArrayList<String> getCartItemNames() {
    return cartItemName;
}

    // Getter for the Item Prices
    public static ArrayList<Double> getCartItemPrices() {
        return cartItemPrice;
    }


    }
