package com.appstreettaskapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstreettaskapplication.R;
import com.appstreettaskapplication.constants.AppConstants;
import com.appstreettaskapplication.imageutils.DownloadImageTask;
import com.appstreettaskapplication.imageutils.ImagesCache;
import com.appstreettaskapplication.model.ListResponseModel;
import com.appstreettaskapplication.utils.TextViewUtils;

public class DetailPageActivity extends AppCompatActivity {

     private ListResponseModel itentData;
     private TextView tvName;
     private TextView tvUserName;
     private TextView tvRepo;
     private TextView tvDescription;
     private ImageView ivAvatarImage;
     private ImagesCache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        getIntentData(getIntent());
        initilizeElement();
        cache = ImagesCache.getInstance(); //Singleton instance handled in ImagesCache class.
        updateUI();
    }

    private void updateUI() {

        TextViewUtils.setSpannable(tvName, "Name:", " " + (!TextUtils.isEmpty(itentData.getName()) ? itentData.getName() : " "), ContextCompat.getColor(this, R.color.colorPrimary));
        TextViewUtils.setSpannable(tvUserName, "UserName:", " " + (!TextUtils.isEmpty(itentData.getUsername()) ? itentData.getUsername() : " "), ContextCompat.getColor(this, R.color.colorPrimary));
        TextViewUtils.setSpannable(tvRepo, "RepoName:", " " + (!TextUtils.isEmpty(itentData.getRepo().getName()) ? itentData.getRepo().getName() : " "), ContextCompat.getColor(this, R.color.colorPrimary));
        TextViewUtils.setSpannable(tvDescription, "Description:", " " + (!TextUtils.isEmpty(itentData.getRepo().getDescription()) ? itentData.getRepo().getDescription() : " "), ContextCompat.getColor(this, R.color.colorPrimary));

        Bitmap bm = cache.getImageFromWarehouse(itentData.getAvatar());
        if (bm != null) {
            ivAvatarImage.setImageBitmap(bm);
        } else {
            ivAvatarImage.setImageBitmap(null);
            DownloadImageTask imgTask = new DownloadImageTask(cache, ivAvatarImage, 300, 300);
            imgTask.execute(itentData.getAvatar());

        }
    }

    private void initilizeElement() {
        tvName = findViewById(R.id.tvName);
        tvUserName = findViewById(R.id.tvUserName);
        tvRepo = findViewById(R.id.tvRepo);
        tvDescription = findViewById(R.id.tvDescription);
        ivAvatarImage = findViewById(R.id.ivAvatarImage);

    }

    private void getIntentData(Intent intent) {
        if(intent != null){
            itentData = intent.getParcelableExtra(AppConstants.LIST_DATA);
        }
    }


    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
