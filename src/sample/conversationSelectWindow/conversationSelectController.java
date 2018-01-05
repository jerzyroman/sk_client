package sample.conversationSelectWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Conversation.Conversation;
import sample.Conversation.ConversationSingleton;
import sample.MessageWindow.MessageWindowSingleton;
import sample.ServerConnection.ReadMessageThread;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Master Faster on 16.11.2017.
 */
public class conversationSelectController implements Initializable {


    public TableView loginTableView;
    private ObservableList<Conversation> conversationList;
    private ReadMessageThread readMessageThread;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readMessageThread = new ReadMessageThread();
        readMessageThread.start();
        TableColumn loginTableColumn = new TableColumn("Login");
        loginTableColumn.setCellValueFactory(new PropertyValueFactory<Conversation, String>("friendLogin"));
        conversationList = FXCollections.observableList(ConversationSingleton.getConversationSingleton().getConversationList());
        loginTableView.setItems(conversationList);
        loginTableView.getColumns().addAll(loginTableColumn);
        loginTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println(((Conversation)loginTableView.getSelectionModel().getSelectedItem()).getFriendLogin());
                MessageWindowSingleton.getMessageWindowSingleton()
                        .createMessageWindow(((Conversation)loginTableView.getSelectionModel().getSelectedItem()).getFriendLogin());
            }
        });
    }

}

/*
public void connectToServer() {
            System.out.println("Trying to connect...");
            ServerSingleton serverSingleton = ServerSingleton.getServerSingleton();
            serverSingleton.createConnection(ipTextField.getText(),Integer.parseInt(portTextField.getText()), loginTextField.getText(), psswdTextField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MessageWindow.fxml"));
            try{
                Parent root = (Parent)fxmlLoader.load();
                MessageWindowController messageWindowController = fxmlLoader.getController();
                messageWindowController.setFriendLogin("Serwer");
                Stage stage = new Stage();
                stage.setTitle("Messages");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(true);
                stage.setScene(new Scene(root, 450, 600));
                stage.showAndWait();
            }catch(Exception ex){
                System.out.println(ex);
            }
            serverSingleton.closeSocket();
            System.out.println("socket closed");
    }
 */