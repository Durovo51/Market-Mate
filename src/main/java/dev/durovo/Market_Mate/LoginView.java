package dev.durovo.Market_Mate;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

@Route("")
public class LoginView extends VerticalLayout {
    private static final String COLOR = "#89CFF0";

    private int loginCount = 0;
    //Real password doesn't matter becuase program makes you set a password either way before login
    private String realPassword = "";

    public LoginView() {
        getStyle().set("background-color", COLOR);
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        add(
                createLoginSection(),
                createShopfrontScreenButton()
        );
    }

    //To go to user's view
    private Component createShopfrontScreenButton() {
        Button sendButton = new Button("Go to shopfront");
        sendButton.setWidth("300px");
        sendButton.getStyle().set("background-color", "white");
        sendButton.getStyle().set("cursor", "pointer");
        sendButton.addClickListener(event -> {
            sendButton.getUI().ifPresent(ui ->
                    ui.navigate(MainView.class)
            );
        });
        return sendButton;
    }


    private Component createLoginSection() {
        VerticalLayout container = new VerticalLayout();
        container.setAlignItems(Alignment.CENTER);
        container.setSpacing(true);
        container.setPadding(false);

        PasswordField passwordField = new PasswordField();
        passwordField.setWidth("300px");
        passwordField.getStyle().set("background-color", "white");

        if (!AuthService.isPasswordSet()) {
            passwordField.setLabel("Create Admin Password");
            passwordField.setHelperText("This will be the global password for all users.");
            //Sets global password variable for program
        } else {
            passwordField.setLabel("Login");
        }

        Button submitButton = new Button("Submit");
        submitButton.setWidth("300px");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getStyle().set("cursor", "pointer");

        submitButton.addClickListener(event -> {
            String input = passwordField.getValue();

            if (!AuthService.isPasswordSet()) {

                AuthService.setPassword(input);
                Notification.show("Global Admin Password Set!");

                passwordField.clear();
                passwordField.setLabel("Login");
                passwordField.setHelperText("");
            } else {
                if (AuthService.checkPassword(input)) {
                    Notification.show("Login Successful!")
                            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                    submitButton.getUI().ifPresent(ui ->
                            ui.navigate(AdminView.class)
                    );
                } else {
                    Notification.show("Invalid Password")
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });

        container.add(passwordField, submitButton);
        return container;
    }
}