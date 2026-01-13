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
import java.util.List;

import com.vaadin.flow.component.splitlayout.SplitLayout;

@Route("Main")
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
        shelf.setJustifyContentMode(JustifyContentMode.START);
        shelf.getStyle().set("gap", "20px");
        SplitLayout buttonSplit= new SplitLayout();
        buttonSplit.addToPrimary(loginViewButton());
        buttonSplit.addToSecondary(checkoutScreenButton());
        buttonSplit.setWidthFull();


        connectItemInfoDB db = new connectItemInfoDB();
        List<Item> allItems = db.getAllItems();
        for (Item item : allItems) {
            shelf.add(createItem(item.getName(), item.getPrice(), "https://dummyimage.com/300x200/000/fff"));

        }

        add(
                addSpacer(),
                shelf,
                buttonSplit
        );
    }



    public static Component createItem(String name, String price, String imageUrl) {
        //The Container for a single item
        VerticalLayout card = new VerticalLayout();

        // setSpacing(false) brings the text closer to the image
        card.setSpacing(false);
        card.setPadding(true);
        card.setAlignItems(Alignment.CENTER); // Center text horizontally

        // Give the card a fixed width so they grid nicely
        card.setWidth("250px");

        //Makes it look like card
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
            try {
                String cleanPrice = price.replaceAll("[^0-9.]", "");
                double itemPrice = Double.parseDouble(cleanPrice);
                cartItemName.add(name);
                cartItemPrice.add(itemPrice);
                Notification.show("Added " + name + " to cart.");

            } catch (NumberFormatException e) {
                String priceLower = price.toLowerCase();
                if(priceLower.equals("free")) {
                    cartItemName.add(name);
                    cartItemPrice.add(0.0);
                    Notification.show("Added " + name + " to cart.");
                } else {
                    Notification.show("Invalid input: " + price + " cannot be converted to a double.");
                }

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

    private Component checkoutScreenButton() {
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

    private Component loginViewButton() {
        Button sendButton = new Button("Go to login screen");
        sendButton.setWidthFull();
        sendButton.getStyle().set("background-color", "white");
        sendButton.getStyle().set("cursor", "pointer");
        sendButton.addClickListener(event -> {
            sendButton.getUI().ifPresent(ui ->
                    ui.navigate(LoginView.class)
            );
        });
        return sendButton;
    }

private Component addSpacer() {
    Div spacer = new Div();
    spacer.setHeight("40px");
    return spacer;
}


public static ArrayList<String> getCartItemNames() {
    return cartItemName;
}

    // Getter for the Item Prices
    public static ArrayList<Double> getCartItemPrices() {
        return cartItemPrice;
    }


    }
