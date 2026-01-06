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

@Route("Admin")
public class AdminView extends VerticalLayout {
    private static final String COLOR = "#89CFF0";

    public AdminView() {
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setAlignItems(Alignment.CENTER);
        setPadding(false);
        setSpacing(false);
        add(
                createHeader()
        );
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

}
