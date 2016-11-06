package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockCreditRecordBL;
import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class CreditRecordBLTest {
    CreditRecordBLService creditRecordBL;

    @Before
    public void init() {
        creditRecordBL = new MockCreditRecordBL();
    }

    @Test
    public void testGet() {
        String username = "quadra";
        assertEquals(true, creditRecordBL.get(username).size() > 0);
    }

    @Test
    public void testAdd() {
        CreditRecordVO vo = new CreditRecordVO(1, "quadra2", System.currentTimeMillis(), 1, CreditAction.ORDER_FINISHED, 99.9, 199.9);
        assertEquals(ResultMessage.RESULT_SUCCESS, creditRecordBL.add(vo).result);
    }
}