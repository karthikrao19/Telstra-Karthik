package app.task.com.softTsk.Home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.task.com.softTsk.Model.DetailsResponse;
import app.task.com.softTsk.Model.TelResponse;
import app.task.com.softTsk.Network.ErrorUtils;
import app.task.com.softTsk.R;
import app.task.com.softTsk.Utils.ConnectionDetector;
import app.task.com.softTsk.Utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerViewList;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog pd;
    private HomePresenter homePresenter;
    private LinearLayoutManager linearLayoutManager;
    private HomeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        toolbar.setTitle("Telstra");
        setSupportActionBar(toolbar);
        pd = new Util().waitingMessage(HomeActivity.this);

        recyclerViewList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewList.setLayoutManager(linearLayoutManager);


        homePresenter = new HomePresenter(this);

        // To load home page
        loadHomePage();

        // To refresh the list
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadHomePage();
                swiperefresh.setRefreshing(false);
            }
        });
    }

    private void loadHomePage() {

        if (new ConnectionDetector(this).isConnectingToInternet()) {
            homePresenter.onLoadData();
        } else {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onDestroy() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
        homePresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        pd.show();
    }

    @Override
    public void hideProgress() {
        if (pd.isShowing()) {
            pd.hide();
        }
    }

    @Override
    public void onResponse(TelResponse productLst) {
        try {
            if (productLst != null) {
                toolbar.setTitle(productLst.getTitle());
                onListLoadData(productLst.getResults());
            } else {
                Toast.makeText(this, "Please try Later", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        String ErrMSg = ErrorUtils.responseError(this, t);
        Toast.makeText(this, ErrMSg, Toast.LENGTH_SHORT).show();

        Log.e("onFailure", "" + t);
    }

    private void onListLoadData(List<DetailsResponse> productList) {

        adapter = new HomeAdapter(getApplicationContext(), productList, new HomeAdapter.OnItemClickListener() {
            @Override
            public void onClick(DetailsResponse products) {
                Toast.makeText(HomeActivity.this, products.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewList.setAdapter(adapter);
    }


}
