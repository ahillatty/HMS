package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelRoomBLTest {
    private HotelRoomBLService hotelRoomBLService;

    @Before
    public void init() {
        hotelRoomBLService = new HotelRoomBL();
    }

    @Test
    public void test1_Add() {
        HotelRoomVO vo1 = new HotelRoomVO(0, 12345, "大床房", 69, 129.9);
        HotelRoomVO vo2 = new HotelRoomVO(0, 12345, "双人房", 35, 139.9);
        HotelRoomVO vo3 = new HotelRoomVO(0, 12345, "三人房", 22, 169.9);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBLService.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBLService.add(vo2).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBLService.add(vo3).result);
    }

    @Test
    public void test2_GetAll() {
       ArrayList<HotelRoomVO> voarr = hotelRoomBLService.getAll(12345);
       assertEquals(3, voarr.size());
       assertEquals(69, voarr.get(0).total);
       assertEquals(35, voarr.get(1).total);
       assertEquals(22, voarr.get(2).total);
    }

    @Test
    public void test3_Modify() {
        ArrayList<HotelRoomVO> voarr = hotelRoomBLService.getAll(12345);
        HotelRoomVO vo = voarr.get(0);
        vo.name = "小床";
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBLService.modify(vo).result);
    }

    @Test
    public void test4_Delete() {
    	ArrayList<HotelRoomVO> voarr = hotelRoomBLService.getAll(12345);
    	for (HotelRoomVO vo : voarr) {
            assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBLService.delete(vo.id).result);
        }
    }
}
