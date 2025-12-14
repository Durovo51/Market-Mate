//Jake Bryant

package dev.durovo.Market_Mate;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

import static dev.durovo.Market_Mate.MainView.*;

// The text inside quote marks is the URL (e.g., localhost:8080/cart)
@Route("cart")
public class CartView extends VerticalLayout {
    private static final String COLOR = "#89CFF0";

    public CartView() {
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        ArrayList<String> names = getCartItemNames();
        ArrayList<Double> prices = getCartItemPrices();

        add(new H1("Your Shopping Cart"));
        add("Items in your cart: ");
        if (names.isEmpty()) {
            add(new Span("Your cart is empty."));
        } else {
            for (int i = 0; i < names.size(); i++) {
                String itemName = names.get(i);
                Double itemPrice = prices.get(i);


                HorizontalLayout row = new HorizontalLayout();


                Span nameText = new Span(itemName);
                Span priceText = new Span("$" + itemPrice); // Convert Double to String here

                row.add(nameText, priceText);

                // Add the row to the main view
                add(row);
            }


        }
    }
}