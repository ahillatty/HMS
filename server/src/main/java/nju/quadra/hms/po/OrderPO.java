package nju.quadra.hms.po;

import nju.quadra.hms.model.OrderState;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderPO {
    /**
     * 订单ID
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 酒店ID
     */
    private int hotelId;
    /**
     * 起始时间
     */
    private Date startDate;
    /**
     * 终止时间
     */
    private Date endDate;
    /**
     * 客房ID
     */
    private int roomId;
    /**
     * 客房数量
     */
    private int roomCount;
    /**
     * 入住人数
     */
    private int personCount;
    /**
     * 入住人员列表
     */
    private ArrayList<String> persons;
    /**
     * 有无儿童
     */
    private boolean hasChildren;
    /**
     * 订单价格
     */
    private double price;
    /**
     * 订单状态
     */
    private OrderState state;
    /**
     * 评分
     */
    private int rank;
    /**
     * 评价内容
     */
    private String comment;

    public OrderPO(int id, String username, int hotelId, Date startDate, Date endDate, int roomId, int roomCount, int personCount, ArrayList<String> persons, boolean hasChildren, double price, OrderState state, int rank, String comment) {
        this.id = id;
        this.username = username;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.roomCount = roomCount;
        this.personCount = personCount;
        this.persons = persons;
        this.hasChildren = hasChildren;
        this.price = price;
        this.state = state;
        this.rank = rank;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public ArrayList<String> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<String> persons) {
        this.persons = persons;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}