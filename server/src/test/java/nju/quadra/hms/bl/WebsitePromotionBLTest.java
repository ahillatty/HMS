package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.WebsitePromotionBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.vo.WebsitePromotionVO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebsitePromotionBLTest {
    private WebsitePromotionBLService websitePromotionBL;
    @Before
    public void init() {
        websitePromotionBL = new WebsitePromotionBL();
    }

    @Test
    public void test1_Add() {
        WebsitePromotionVO vo1 = new WebsitePromotionVO(0, "TEST|name1", WebsitePromotionType.TIME_PROMOTION,
                LocalDate.parse("2016-11-10"), LocalDate.parse("2016-11-10"), 0.8, -10, null);
        WebsitePromotionVO vo2 = new WebsitePromotionVO(0, "TEST|name2", WebsitePromotionType.LEVEL_PROMOTION,
                LocalDate.parse("2015-11-10"), LocalDate.parse("2015-11-10"), 0.5, -10, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.add(vo2).result);
    }

    @Test
    public void test2_Modify() {
        WebsitePromotionVO vo = websitePromotionBL.get().get(0);
        vo.name = "TEST|name11";
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.modify(vo).result);
    }

    @Test
    public void test3_Get() {
        ArrayList<WebsitePromotionVO> voarr = websitePromotionBL.get();
        assertTrue(voarr.size() >= 2);
        //assertEquals("TEST|name11", voarr.get(0).name);
    }

    @Test
    public void test4_Delete() {
        ArrayList<WebsitePromotionVO> voarr = websitePromotionBL.get();
        for(WebsitePromotionVO vo: voarr) {
            if(vo.areaId == -10) {
                assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.delete(vo.id).result);
            }
        }
    }
}
