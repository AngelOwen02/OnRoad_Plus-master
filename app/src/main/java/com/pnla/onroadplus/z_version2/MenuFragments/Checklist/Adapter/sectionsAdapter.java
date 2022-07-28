package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;

public class sectionsAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private int mposition;
    private Questions mview;
    private  int sizedots;

    private QuestionsAdapter adapter;
    private RecyclerView rv;

    @Override
    public int getCount() {
        return sizedots;
    }
    public sectionsAdapter(int size, Questions mview, Context context) {//(FragmentManager childFragmentManager, List<Banners> banners, Context context) {
        this.context= context;
        this.mview=mview;
        this.sizedots=size;
        layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView=layoutInflater.inflate(R.layout.item_pager_questions,container,false);

        this.mposition=position;
        /**colocar glide images*/
//       // ImageView iv=itemView.findViewById(R.id.iviewP);
//        Glide.with(context)
//                .load(R.drawable.car) // .load(banners.get(position).getUrls())
//                //.diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(iv);
        rv=itemView.findViewById(R.id.rvQuestions);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        adapter=new QuestionsAdapter(context);
        rv.setAdapter(adapter);

        container.addView(itemView);

     //   mview.movedots(position);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }
}
