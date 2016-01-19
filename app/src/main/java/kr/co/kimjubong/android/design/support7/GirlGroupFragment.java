package kr.co.kimjubong.android.design.support7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by xmfow_000 on 2016-01-18.
 */
public class GirlGroupFragment extends Fragment{
    public static int increment;
    static MainActivity owner;
    public GirlGroupFragment(){}
    public static GirlGroupFragment newInstance(int initValue){
        GirlGroupFragment girlFragment = new GirlGroupFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",initValue);
        girlFragment.setArguments(bundle);
        return girlFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView rv = (RecyclerView)inflater.inflate(R.layout.girls_group_fragment, container, false);
        Bundle initBundle = getArguments();
        increment += initBundle.getInt("value");
        owner = (MainActivity)getActivity();
        RecyclerView.LayoutManager lm= new RecyclerView.LayoutManager() {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        if(initBundle.getInt("index")==1)
            rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        else if(initBundle.getInt("index")==2)
            rv.setLayoutManager(new LinearLayoutManager(GirlsApplication.getGirlsContext()));
        else
            rv.setLayoutManager(new LinearLayoutManager(GirlsApplication.getGirlsContext()));

        rv.setAdapter(new GirlsGroupRecyclerViewAdapter(
                GirlsApplication.getGirlsContext(),RandomArrayList.getSuffleArrayList()));
        return rv;
    }

    public static class GirlsGroupRecyclerViewAdapter extends RecyclerView.Adapter<GirlsGroupRecyclerViewAdapter.ViewHolder> {
        private ArrayList<Integer> girlsImages;
        public GirlsGroupRecyclerViewAdapter(Context context,ArrayList<Integer> resources){
            girlsImages = resources;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            public final View mView;
            public final ImageView girlsImage;
            public final TextView memberName;

            public ViewHolder(View view){
                super(view);
                mView = view;
                girlsImage = (ImageView)view.findViewById(R.id.girls_group_member_image);
                memberName = (TextView)view.findViewById(R.id.member_name);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.girls_group_item, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position){
            holder.memberName.setText(RandomArrayList.getGirlGroupName(girlsImages.get(position)));
            Glide.with(GirlsApplication.getGirlsContext()).load(girlsImages.get(position)).into(holder.girlsImage);
            holder.mView.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v){
                    Intent intent = new Intent(GirlsApplication.getGirlsContext(), GirlsMemberDetailActivity.class);
                    intent.putExtra("memberImage",girlsImages.get(position));
                    intent.putExtra("memberName",holder.memberName.getText().toString());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(owner,holder.girlsImage,ViewCompat.getTransitionName(holder.girlsImage));
                    ActivityOptionsCompat.makeSceneTransitionAnimation(owner, holder.girlsImage, ViewCompat.getTransitionName(holder.girlsImage));
                    ActivityCompat.startActivity(owner, intent, options.toBundle());
                }
            });
        }
        @Override
        public int getItemCount(){
            return girlsImages.size();
        }
    }
}
