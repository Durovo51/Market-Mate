//Jake Bryant

package dev.durovo.Market_Mate;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

import static dev.durovo.Market_Mate.MainView.*;

// The text inside quote marks is the URL (e.g., localhost:8080/cart)
@Route("cart")
public class CartView extends VerticalLayout {
    private static final String COLOR = "#89CFF0";

    private H1 totalDisplay = new H1();

    public CartView() {
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        totalDisplay.getStyle().set("color", "white");

        add(
                createHeader(),
                createCartItems(),
                createBackButton(),
                createCheckOutButton()
        );
    }

    private Component createBackButton() {
        Button backButton = new Button("Back");
        backButton.getStyle().set("color", "black");
        backButton.getStyle().set("background-color", "white");
        backButton.getStyle().set("cursor", "pointer");
        backButton.addClickListener(e -> {
            backButton.getUI().ifPresent(ui ->
                    ui.navigate(MainView.class)
            );
        });

        return backButton;
    }
    private Component createCheckOutButton() {
        Button checkoutButton = new Button("Checkout");
        checkoutButton.getStyle().set("color", "black");
        checkoutButton.getStyle().set("background-color", "white");
        checkoutButton.getStyle().set("cursor", "pointer");
        HorizontalLayout header = new HorizontalLayout(totalDisplay);
        checkoutButton.addClickListener(e -> {
            ArrayList<Double> prices = getCartItemPrices();
            double sum = 0;
            for (double price : prices) {
                sum += price;
            }
            totalDisplay.setText("Your total is $" + sum);

            totalDisplay.getStyle().set("color", "white");
            totalDisplay.getStyle().set("font-family", "Georgia, serif");
            totalDisplay.getStyle().set("margin-top", "20px");

            header.setWidthFull();
            header.setJustifyContentMode(JustifyContentMode.CENTER);
            add(header);
        });
        return checkoutButton;
    }




    private Component createHeader() {
        H1 cartTitle = new H1("Your Shopping Cart");
        cartTitle.getStyle().set("color", "white");
        cartTitle.getStyle().set("font-family", "Georgia, serif");
        cartTitle.getStyle().set("margin-top", "20px");

        HorizontalLayout header = new HorizontalLayout(cartTitle);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        return header;
    }

    private Component createCartItems() {
        // Create a container to hold the rows so we can return one Component at the end
        VerticalLayout itemsContainer = new VerticalLayout();
        itemsContainer.setAlignItems(Alignment.CENTER);
        // Add some spacing between the cards
        itemsContainer.getStyle().set("gap", "15px");

        ArrayList<String> names = getCartItemNames();
        ArrayList<Double> prices = getCartItemPrices();

        if (names.isEmpty()) {
            Span emptyText = new Span("Your cart is empty.");
            emptyText.getStyle().set("color", "white");
            emptyText.getStyle().set("font-size", "1.2rem");
            itemsContainer.add(emptyText);
        } else {
            for (int i = 0; i < names.size(); i++) {
                String itemName = names.get(i);
                Double itemPrice = prices.get(i);

                HorizontalLayout card = new HorizontalLayout();

                //Structure & Size
                card.setWidth("60%");
                card.setJustifyContentMode(JustifyContentMode.BETWEEN);
                card.setAlignItems(Alignment.CENTER);

                //Visual Styling
                card.getStyle().set("background-color", "white");
                card.getStyle().set("padding", "20px");
                card.getStyle().set("border-radius", "12px");
                card.getStyle().set("box-shadow", "0 4px 6px rgba(0,0,0,0.1)");

                // Make the card look clickable
                card.getStyle().set("cursor", "pointer");

                //Text Styling
                Span nameText = new Span(itemName);
                nameText.getStyle().set("font-weight", "bold");
                nameText.getStyle().set("font-size", "1.1rem");
                nameText.getStyle().set("color", "#333");

                Span priceText = new Span("$" + String.format("%.2f", itemPrice));
                priceText.getStyle().set("color", "#2c3e50");
                priceText.getStyle().set("font-weight", "600");

                card.add(nameText, priceText);

                // Add Click Listener to the whole card
                card.addClickListener(e -> {
                    int index = names.indexOf(itemName);
                    if (index != -1) {
                        names.remove(index);
                        prices.remove(index);
                        itemsContainer.remove(card);
                        Notification.show(itemName + " removed from cart");

                        if (names.isEmpty()) {
                            itemsContainer.add(new Span("Your cart is empty."));
                        }
                    }
                });

                itemsContainer.add(card);
            }
        }
        return itemsContainer;
    }
}