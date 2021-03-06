package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.util.ClientConfig;

class NetworkSettingsView extends Stage {

    public NetworkSettingsView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("networksettings.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("网络设置");
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        textServer.setText(ClientConfig.getConfig().getServerHost());
    }

    @FXML
    private
    TextField textServer;

    /**
     * 处理保存按钮事件
     */
    @FXML
    protected void onSaveAction() {
        String server = textServer.getText().trim();
        ClientConfig.getConfig().setServerHost(server);
        this.close();
    }

    /**
     * 处理取消按钮事件
     */
    @FXML
    protected void onCancelAction() {
        this.close();
    }

}
