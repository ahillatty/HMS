package nju.quadra.hms.ui.hotelStaffUI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class HotelPromotionEditView extends Parent {

    private HotelStaffController controller;
    private HotelPromotionVO hotelPromotionVO;
    private SuccessHandler onSuccess;
    private ArrayList<String> selectedCompany = new ArrayList<>();
    private final HashMap<String, ObservableValue<Boolean>> companySelection = new HashMap<>();

    @FXML
    private
    TextField editName;
    @FXML
    private TextField editPromotion;
    @FXML
    private
    ChoiceBox<HotelPromotionType> choiceType;
    @FXML
    private
    DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private
    Button btnSave;
    @FXML
    private
    Pane paneCompany;
    @FXML
    private Pane paneSelectCompany;
    @FXML
    private
    Label labelCompany;
    @FXML
    private
    ListView<String> listCompany;

    public HotelPromotionEditView(HotelPromotionVO vo, HotelStaffController controller, boolean readOnly, SuccessHandler successHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceType.getItems().addAll(HotelPromotionType.values());
        choiceType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(HotelPromotionType.COMPANY_PROMOTION)) {
                paneCompany.setVisible(true);
            } else {
                paneCompany.setVisible(false);
            }
        });
        choiceType.getSelectionModel().select(0);

        listCompany.setCellFactory(CheckBoxListCell.forListView(companySelection::get));

        this.hotelPromotionVO = vo;
        this.controller = controller;
        this.onSuccess = successHandler;
        if (vo != null) {
            editName.setText(vo.name);
            choiceType.setValue(vo.type);
            dateStart.setValue(vo.startTime);
            dateEnd.setValue(vo.endTime);
            editPromotion.setText(new DecimalFormat("0.00").format(vo.promotion));
            if (vo.type.equals(HotelPromotionType.COMPANY_PROMOTION) && vo.cooperation != null) {
                selectedCompany = vo.cooperation;
                labelCompany.setText("已选择 " + selectedCompany.size() + " 个企业");
            }
        }
        if (readOnly) {
            editName.setEditable(false);
            choiceType.setDisable(true);
            dateStart.setDisable(true);
            dateEnd.setDisable(true);
            editPromotion.setEditable(false);
            btnSave.setVisible(false);
            listCompany.setDisable(true);
        }
    }

    @FXML
    protected void onOpenSelectorAction() {
        companySelection.clear();
        listCompany.getItems().clear();
        ArrayList<String> company = controller.getAllCompany();
        if (company != null) {
            for (String s : company) {
                companySelection.put(s, new SimpleBooleanProperty(selectedCompany.contains(s)));
                listCompany.getItems().add(s);
            }
        }
        paneSelectCompany.setVisible(true);
    }

    @FXML
    protected void onCloseSelectorAction() {
        selectedCompany.clear();
        for (Map.Entry<String, ObservableValue<Boolean>> s : companySelection.entrySet()) {
            if (s.getValue().getValue()) {
                selectedCompany.add(s.getKey());
            }
        }
        labelCompany.setText("已选择 " + selectedCompany.size() + " 个企业");
        paneSelectCompany.setVisible(false);
    }

    @FXML
    protected void onSaveAction() throws IOException {
        String name;
        HotelPromotionType type;
        LocalDate startTime;
        LocalDate endTime;
        double promotion;
        try {
            name = editName.getText();
            type = choiceType.getValue();
            startTime = dateStart.getValue();
            endTime = dateEnd.getValue();
            promotion = Double.parseDouble(editPromotion.getText());
        } catch (Exception e) {
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }

        if (hotelPromotionVO == null) {
            // add
            ResultMessage result = controller.addHotelPromotion(new HotelPromotionVO(0, 0, name, type, startTime, endTime, promotion, selectedCompany));
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("增加促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("增加促销策略失败: " + result.message);
            }
        } else {
            // modify
            hotelPromotionVO.name = name;
            hotelPromotionVO.type = type;
            hotelPromotionVO.startTime = startTime;
            hotelPromotionVO.endTime = endTime;
            hotelPromotionVO.promotion = promotion;
            if (type.equals(HotelPromotionType.COMPANY_PROMOTION)) {
                hotelPromotionVO.cooperation = selectedCompany;
            }
            ResultMessage result = controller.modifyHotelPromotion(hotelPromotionVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("修改促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("修改促销策略失败: " + result.message);
            }
        }
    }

    @FXML
    private void onCancelAction() {
        getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }

}
