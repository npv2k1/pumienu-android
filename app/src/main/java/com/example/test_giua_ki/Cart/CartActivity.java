package com.example.test_giua_ki.Cart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_giua_ki.Cart.Adapter.CartAdapter;
import com.example.test_giua_ki.Cart.Model.Cart;
import com.example.test_giua_ki.DBHelper;
import com.example.test_giua_ki.Dress.Model.Dress;
import com.example.test_giua_ki.R;
import com.example.test_giua_ki.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvDress;
    private TextView tvTotal;
    Button btnSave;
    private Cart cart = new Cart();
    private ActivityCartBinding binding;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvTotal = binding.tvTotal;

        rvDress = binding.rvdress;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvDress.setLayoutManager(mLayoutManager);

        CartAdapter rvAdapter  = new CartAdapter(this, this.cart);
        rvDress.setAdapter(rvAdapter);
        tvTotal.setText("Total: $" + this.cart.getTotalPrice());
        dbHelper = new DBHelper(this);

        btnSave = findViewById(R.id.btnSaveCart);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cart_id = (int) dbHelper.createCart(1);
                ArrayList<Dress> dresses = cart.getDressesFromCart();
                for (Dress p : dresses) {
                    dbHelper.insertDressToCart(cart_id, p.getId());
                }
                Toast.makeText(CartActivity.this, "Save cart successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateData() {
        tvTotal.setText("Total: $" + this.cart.getTotalPrice());
    }
}
