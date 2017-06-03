package jrdcom.com.androidpmuse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by longcheng on 2017/6/3.
 */

public class JrdRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<JrdAppModel> appModelList;
    public JrdRecyclerAdapter(Context context, List<JrdAppModel> list){
        mContext = context;
        appModelList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        RecyclerView.ViewHolder holder = new JrdViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JrdAppModel jrdAppModel = appModelList.get(position);
        ((JrdViewHolder)holder).appName.setText(jrdAppModel.getAppName());
        ((JrdViewHolder)holder).appLabel.setText(jrdAppModel.getAppLabel());
        ((JrdViewHolder)holder).appIcon.setImageDrawable(jrdAppModel.getAppIcon());
    }

    @Override
    public int getItemCount() {
        return appModelList.size();
    }

    class JrdViewHolder extends RecyclerView.ViewHolder{
        private TextView appName;
        private TextView appLabel;
        private ImageView appIcon;
        public JrdViewHolder(View view){
            super(view);
            appName = (TextView)view.findViewById(R.id.text_info);
            appLabel = (TextView)view.findViewById(R.id.text_title);
            appIcon = (ImageView)view.findViewById(R.id.app_icon);
        }
    }

    public void notifyListChange(List<JrdAppModel> list){
        appModelList = list;
        notifyDataSetChanged();
    }
}
