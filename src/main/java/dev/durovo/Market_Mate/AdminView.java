package dev.durovo.Market_Mate;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;

import static dev.durovo.Market_Mate.MainView.*;

@Route("Admin")
public class AdminView extends VerticalLayout {
    private static final String COLOR = "#89CFF0";
    TextArea setName = new TextArea("Product Name");
    TextArea setPrice = new TextArea("Product Price");
    VerticalLayout leftSide = new VerticalLayout();
    VerticalLayout rightSide = new VerticalLayout();

    SplitLayout splitLayout = new SplitLayout();
    public AdminView() {
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setAlignItems(Alignment.CENTER);
        setPadding(false);
        setSpacing(false);
        setName.getStyle().set("text", "Put item's name in here");
        setPrice.getStyle().set("text", "Put item's price in here");


        //Left Side logic
        leftSide.setPadding(true);
        leftSide.setSpacing(true);
        leftSide.getStyle().set("background-color", "#FFFFFF");
        leftSide.add(
                setName,
                setPrice,
                setButton()
        );

        rightSide.setPadding(true);
        rightSide.setSpacing(true);
        rightSide.getStyle().set("background-color", "#F0F0F0");
        rightSide.getStyle().set("overflow-y", "auto");


        splitLayout.setSizeFull();
        splitLayout.addToPrimary(leftSide);
        splitLayout.addToSecondary(rightSide);
        splitLayout.setSplitterPosition(25); // Keep your 25% split

        add(createHeader(), splitLayout, loginViewButton());
    }



    private Component createHeader() {
        H1 title = new H1("Market-Mate Admin View");


        title.getStyle().set("color", "white");
        title.getStyle().set("font-family", "Georgia, serif");
        title.getStyle().set("font-size", "50px");
        title.getStyle().set("margin", "0");
        HorizontalLayout header = new HorizontalLayout(title);

        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);



        header.getStyle().set("padding", "15px");
        header.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.2)");

        return header;
    }

    //LEFT SIDE LAYOUT STUFF


    private Component setButton(){
        Button submit = new Button("Submit item name and price");
        submit.addClickListener(event -> {
            String price = setPrice.getValue();
            String name = setName.getValue();

            if(name != null && price != null){
                Component newCard = cards(name, price);
                rightSide.add(newCard);
                createItem(name, price, "https://dummyimage.com/300x200/000/fff"); //Doesn't work right now because the arrayList isn't static,
                                                                                           // I should probably set up a locally ran database
                if(price.substring(0,1).equals("$")) {
                    Notification.show("Your item's name is set to " + name + " and price is set to " + price);
                } else  {
                    Notification.show("Your item's name is set to " + name + " and price is set to $" + price);
                }
            } else {
                Notification.show("Please fill out the name field and the price field");
            }



        });
        return submit;
    }

    //RIGHT SIDE LAYOUT STUFF

    private Component cards(String name, String price){
        VerticalLayout card = new VerticalLayout();
        card.setSpacing(false);
        card.setPadding(true);

        //Same card look as the main method

        card.setWidth("250px");

        card.getStyle().set("border", "1px solid #ddd");
        card.getStyle().set("border-radius", "8px");
        card.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
        card.getStyle().set("background-color", "white");
        Span nameSpan = new Span("Name: " + name);
        Span priceSpan = new Span("Price: " + price);
        card.add(nameSpan, priceSpan);
        return card;
    }

    //Button Movement to login
   private Component loginViewButton(){
        Button loginScreen = new Button("Return to Login Screen");
        loginScreen.getStyle().set("background-color", "white");
        loginScreen.getStyle().set("cursor", "pointer");
        loginScreen.addClickListener(event -> {
            loginScreen.getUI().ifPresent(ui ->
                    ui.navigate(LoginView.class)
            );
        });
        return loginScreen;
   }


}

