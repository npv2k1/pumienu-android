package com.example.test_giua_ki.Cart.Model;

import com.example.test_giua_ki.Dress.Model.Dress;
import com.example.test_giua_ki.Dress.Repository.DressRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    int id, staff_id;
    public static Map<Integer, Integer> cartList = new HashMap<>();
    private Object keys[];

    public DressRepository dressRepository = new DressRepository();
    private static float  totalPrice;

    public Cart(int id, int staff_id) {
        this.id = id;
        this.staff_id = staff_id;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addCart (Dress dress){
        Integer quantity = cartList.getOrDefault(dress.getId(), 0);
        if (quantity >= 10) return;
        cartList.put(dress.getId(), quantity + 1);
        totalPrice += dress.getPrice();
    }

    public Dress getDressByOrder(Integer pos){
        keys = cartList.keySet().toArray();
        return dressRepository.getDress(Integer.parseInt(keys[pos].toString()));
    }
    public float getLinePrice (Dress p){
        return p.getPrice() * cartList.getOrDefault(p.getId(), 0);
    }
    public float getLinePrice (Integer pid){
        Dress p = dressRepository.getDress(pid);
        return cartList.get(pid) * p.getPrice();
    }

    public void removeCart(Dress p){
        Integer quantity = cartList.getOrDefault(p.getId(), 0);
        if (quantity <= 0) return;
        cartList.put(p.getId(), quantity - 1);
        totalPrice -= p.getPrice();
    }

    public ArrayList<Dress> getDressesFromCart() {
        ArrayList<Dress> dresses = new ArrayList<>();
        for (Integer dressId : cartList.keySet()) {
            Dress dress = dressRepository.getDress(dressId);
            if (dress != null) {
                dresses.add(dress);
            }
        }
        return dresses;
    }

}
