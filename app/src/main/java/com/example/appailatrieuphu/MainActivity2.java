package com.example.appailatrieuphu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    ArrayList<TienThuong> lstTienThuong;
    TienThuongAdapter adapter;
    TextView noiDung, dapAnA, dapAnB, dapAnC, dapAnD;
    String noiDungCauHoi, dapAnDung;
    int soCauHoi = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // kiểm tra đã đc cấp quyền truy cập bộ nhớ
        boolean isHasPermission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        // nếu chưa cấp thì request xin quyền nữa
        if (!isHasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

        anhXa();

        adapter = new TienThuongAdapter(this, R.layout.moc_cau_hoi, lstTienThuong);
        listView.setAdapter(adapter);

        // nếu có quyền mới thực hiện đọc file cau_hoi.txt
        if (isHasPermission) {
            docCauHoi(soCauHoi);
            setCauHoi(noiDungCauHoi);
        }
        setClick();
    }

    private void anhXa() {
        listView = (ListView) findViewById(R.id.listViewTienThuong);
        noiDung = (TextView) findViewById(R.id.noiDungCauHoi);
        dapAnA = (TextView) findViewById(R.id.dapAnA);
        dapAnB = (TextView) findViewById(R.id.dapAnB);
        dapAnC = (TextView) findViewById(R.id.dapAnC);
        dapAnD = (TextView) findViewById(R.id.dapAnD);
        lstTienThuong = new ArrayList<>();

        lstTienThuong.add(new TienThuong("15", "150.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("14", "85.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("13", "60.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("12", "40.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("11", "30.00.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("10", "22.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("9", "14.00.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("8", "10.00.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("7", "6.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("6", "3.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("5", "2.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("4", "1.000.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("3", "600.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("2", "400.000", R.drawable.diamond));
        lstTienThuong.add(new TienThuong("1", "200.000", R.drawable.diamond));
    }

    private void docCauHoi(int soCauHoi) {
        String filePath = "Download/CauHoi/CauHoi_" + soCauHoi + ".txt";
        filePath = "CauHoi_" + soCauHoi + ".txt";

//        File cauHoi = new File(Environment.getExternalStorageDirectory().toString() + "/Download/CauHoi/", filePath);
        File cauHoi = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "CauHoi/CauHoi_" + soCauHoi + ".txt");
        Log.e("TAG", "==============: " + cauHoi.exists());
        cauHoi.setWritable(true);
        cauHoi.setReadable(true);
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(cauHoi);
            br = new BufferedReader(fr);
            ArrayList<String> tapCauHoi = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                tapCauHoi.add(line);
            }
            if (tapCauHoi.isEmpty()) {
                noiDungCauHoi = "";
                return;
            }
            Random random = new Random();
            int index = random.nextInt(tapCauHoi.size());
            noiDungCauHoi = tapCauHoi.get(index);
            fr.close();
            br.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setCauHoi(String line) {
        // nên bắt try catch
        try {
            String[] tachCau = line.split("|");
            noiDung.setText(tachCau[0]);
            dapAnA.setText(tachCau[1]);
            dapAnB.setText(tachCau[2]);
            dapAnC.setText(tachCau[3]);
            dapAnD.setText(tachCau[4]);
            dapAnDung = tachCau[5];
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void ktraCauTraLoi(String cauTraLoi) {
        if (cauTraLoi.equals(dapAnDung)) {
            soCauHoi++;
            docCauHoi(soCauHoi);
            setCauHoi(noiDungCauHoi);
        } else {
            Toast.makeText(MainActivity2.this,
                    "Bạn đã trả lời sai. Game over", Toast.LENGTH_LONG).show();
        }
    }

    private void setClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktraCauTraLoi(((TextView) v).getText().toString());
            }
        };
        dapAnA.setOnClickListener(listener);
        dapAnB.setOnClickListener(listener);
        dapAnC.setOnClickListener(listener);
        dapAnD.setOnClickListener(listener);
    }

    // hàm này là hàm nhận kết quả request xin quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // kiểm tra quyền vừa xin đã đc cấp hay chưa
        boolean isAcceptAllPermisison = true;
        for (String s : permissions) {
            if (ActivityCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                isAcceptAllPermisison = false;
            }
        }

        // nếu quyền đã đc cấp thì thực hiện đọc file
        if (isAcceptAllPermisison) {
            docCauHoi(soCauHoi);
            setCauHoi(noiDungCauHoi);
        }
    }
}