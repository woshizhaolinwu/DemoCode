package jrdcom.com.androidpmuse;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.system_app)
    Button systemApp;
    @Bind(R.id.third_app)
    Button thirdApp;
    @Bind(R.id.sd_app)
    Button sdApp;
    private RecyclerView recyclerView;
    private List<JrdAppModel> appModelList;
    private final static int SYSTEM_APP = 0;
    private final static int THIRD_APP = 1;
    private final static int SD_APP =2;
    private PackageManager pm;
    private JrdRecyclerAdapter jrdRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        appModelList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //设置adapter
        jrdRecyclerAdapter = new JrdRecyclerAdapter(this, appModelList);
        recyclerView.setAdapter(jrdRecyclerAdapter);
        //设置Linearlayout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        pm = getPackageManager();
    }


    @OnClick({R.id.system_app, R.id.third_app, R.id.sd_app})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.system_app:
                appModelList = getAppModelList(SYSTEM_APP);
                break;
            case R.id.third_app:
                appModelList = getAppModelList(THIRD_APP);

                break;
            case R.id.sd_app:
                appModelList = getAppModelList(SD_APP);
                break;
        }
        jrdRecyclerAdapter.notifyListChange(appModelList);
    }

    private List<JrdAppModel> getAppModelList(int app_type){
        //获取所有应用
        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<JrdAppModel> appModels =  new ArrayList<>();

        //筛选出对应type的应用
        switch (app_type){
            case SYSTEM_APP:
                appModels.clear();
                for(ApplicationInfo applicationInfo: applicationInfos){
                    if((applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)!=0){
                        appModels.add(getJrdAppModel(applicationInfo));
                    }
                }
                break;
            case THIRD_APP:
                for(ApplicationInfo applicationInfo: applicationInfos){
                    if((applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)<=0){
                        appModels.add(getJrdAppModel(applicationInfo));
                    }
                }
                break;
            case SD_APP:
                for(ApplicationInfo applicationInfo: applicationInfos){
                    if((applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)!=0){
                        appModels.add(getJrdAppModel(applicationInfo));
                    }
                }
                break;

        }
        return appModels;
    }

    private JrdAppModel getJrdAppModel(ApplicationInfo applicationInfo){
        JrdAppModel jrdAppModel = new JrdAppModel();
        jrdAppModel.setAppName(applicationInfo.packageName);
        jrdAppModel.setAppLabel((String) applicationInfo.loadLabel(pm));
        jrdAppModel.setAppIcon(applicationInfo.loadIcon(pm));
        return jrdAppModel;
    }


}
