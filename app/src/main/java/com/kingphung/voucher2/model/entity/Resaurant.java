package com.kingphung.voucher2.model.entity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.maps.android.clustering.ClusterItem;
import com.kingphung.voucher2.R;

import java.util.ArrayList;

public class Resaurant implements ClusterItem {
    private String id;
    private String name;
    private String address;
    private MyLatLng position;
    private ArrayList<Voucher> listVoucher;
    private int num_voucher;



    private int iconPicture;
    public static void CreateDataScript(DatabaseReference databaseReference){
        databaseReference.removeValue();
        Resaurant resaurant = new Resaurant("r1","Restauran 1",
                    "1 Đại Cồ Việt, P. Đồng Tâm, Quận Hai Bà Trưng, Hà Nội\n",
                           new MyLatLng(0,0), new ArrayList<Voucher>(), 8);
        for(int i=0; i<5;i++) resaurant.listVoucher.add(new Voucher(i+"", "title " + i, "description " + i,"link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        for(int i=5; i<8;i++) resaurant.listVoucher.add(new Voucher(i+"MT25hoangdeptrai", "title milk tea" + i, "description milk tea thom ngon dam da" + i,"link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));

        databaseReference.child(resaurant.getId()).setValue(resaurant);
        Resaurant resaurant2 = new Resaurant("r2","Restauran 2",
                "54 Giải Phóng, P. Đồng Tâm, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 5);
        for(int i=100; i<105;i++) resaurant2.listVoucher.add(new Voucher(i+"", "title " + i, "description " + i,"link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant2.getId()).setValue(resaurant2);
        Resaurant resaurant3 = new Resaurant("r3","Restauran 2",
                "353 Phố Huế, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 2);
        for(int i=105; i<105+resaurant3.num_voucher;i++) resaurant3.listVoucher.add(new Voucher(i+"", "title " + i, "description "+i,"link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant3.getId()).setValue(resaurant3);
        Resaurant resaurant4 = new Resaurant("r4","Restauran 2",
                "123 Phố Huế, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 8);
        for(int i=200; i<200+resaurant4.num_voucher;i++) resaurant4.listVoucher.add(new Voucher(i+"", "title " + i, "description " + i,"link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant4.getId()).setValue(resaurant4);
        Resaurant resaurant5 = new Resaurant("r5","Restauran 2",
                "180 Ngõ Bà Triệu, Bà Triệu, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 4);
        for(int i=100; i<100+resaurant5.num_voucher;i++) resaurant5.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant5.getId()).setValue(resaurant5);
        Resaurant resaurant6 = new Resaurant("r6","Restauran 2",
                "3 Ngõ Bà Triệu, Bà Triệu, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 2);
        for(int i=100; i<100+resaurant6.num_voucher;i++) resaurant6.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant6.getId()).setValue(resaurant6);
        Resaurant resaurant7 = new Resaurant("r7","Restauran 2",
                "Ngõ 433 Bạch Mai, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 7);
        for(int i=100; i<100+resaurant7.num_voucher;i++) resaurant7.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant7.getId()).setValue(resaurant7);
        Resaurant resaurant8 = new Resaurant("r8","Restauran 2",
                "Ngõ 75, Giải Phóng, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 9);
        for(int i=100; i<105;i++) resaurant8.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        for(int i=105; i<109;i++) resaurant8.listVoucher.add(new Voucher(i+"pizza", " pizza hut", "pizza nong hoi vua thoi vua an","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant8.getId()).setValue(resaurant8);
        Resaurant resaurant9 = new Resaurant("r9","Restauran 2",
                "23 Ngõ 357 Bạch Mai, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 7);
        for(int i=100; i<105;i++) resaurant9.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        for(int i=105; i<107;i++) resaurant9.listVoucher.add(new Voucher(i+"pizza", "title pizza", "description dcm pizza va tea","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant9.getId()).setValue(resaurant9);
        Resaurant resaurant10 = new Resaurant("r10","Restauran 2",
                "2 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 4);
        for(int i=100; i<100+resaurant10.num_voucher;i++) resaurant10.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant10.getId()).setValue(resaurant10);
        Resaurant resaurant11 = new Resaurant("r11","Restauran 2",
                "125 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội",
               new MyLatLng(0,0), new ArrayList<Voucher>(), 6);
        for(int i=100; i<100+resaurant11.num_voucher;i++) resaurant11.listVoucher.add(new Voucher(i+"", "title", "description","link","https://suno.vn/blog/wp-content/uploads/2017/09/4-bi-quyet-ban-do-an-vat-qua-mang-kiem-tien-trieu-moi-ngay.jpg"));
        databaseReference.child(resaurant11.getId()).setValue(resaurant11);
    }
    public Resaurant() {
    }

    public Resaurant(String id, String name, String address, MyLatLng position, ArrayList<Voucher> listVoucher, int num_voucher) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.position = position;
        this.listVoucher = listVoucher;
        this.num_voucher = num_voucher;
    }

    public void setPosition(MyLatLng position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Voucher> getListVoucher() {
        return listVoucher;
    }

    public void setListVoucher(ArrayList<Voucher> listVoucher) {
        this.listVoucher = listVoucher;
    }

    public int getNum_voucher() {
        return num_voucher;
    }

    public void setNum_voucher(int num_voucher) {
        this.num_voucher = num_voucher;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(this.position.getLat(), this.position.getLng());
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return num_voucher+"";
    }

    public int getIconPicture() {
        return R.drawable.meal;
    }

    public void setIconPicture(int iconPicture) {
        this.iconPicture = iconPicture;
    }
}
