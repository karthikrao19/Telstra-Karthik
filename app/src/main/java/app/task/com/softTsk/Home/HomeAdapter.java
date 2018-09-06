package app.task.com.softTsk.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.task.com.softTsk.Model.DetailsResponse;
import app.task.com.softTsk.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Raos
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<DetailsResponse> productLists;
    private Context context;

    public HomeAdapter(Context context, List<DetailsResponse> data, OnItemClickListener listener) {
        this.productLists = data;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_adapter, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final DetailsResponse products = productLists.get(position);
        holder.click(products, listener);

        if(productLists.get(position).getTitle() != null && productLists.get(position).getDescription() != null ) {
            holder.productName.setText(productLists.get(position).getTitle());
            holder.descrption.setText(productLists.get(position).getDescription());
            String images = productLists.get(position).getImageHref();

            Glide.with(context)
                    .load(images)
                    .apply(new RequestOptions().error(R.drawable.noimageicon))
                    .into(holder.productImage);

        }

    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public interface OnItemClickListener {
        void onClick(DetailsResponse Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.productName)
        TextView productName;

        @BindView(R.id.descrption)
        TextView  descrption;

        @BindView(R.id.productImage)
        ImageView productImage;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final DetailsResponse products, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(products);
                }
            });
        }

    }
}
