package com.athene.mmi;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.athene.Constants;
import com.athene.IntentConstants;
import com.athene.R;
import com.athene.utils.Cache;

import cn.trinea.android.common.util.StringUtils;

/**
 * view image
 * 
 * @author gxwu@lewatek.com 2012-11-23
 */
public class FullscreenViewFragment extends Fragment {

    private ImageView         imageView;
    private RelativeLayout    loadingLayout;

    private String            imageUrl;
    /** image url list which we can fling to show **/
    private ArrayList<String> imageUrlList;
    private String            source;
    private boolean           isFitXy;

    private Context           context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();

        View v = inflater.inflate(R.layout.image_activity, container, false);
        imageView = (ImageView)v.findViewById(R.id.image);
        imageView.setTag(R.id.tag_always_vertical, true);
        loadingLayout = (RelativeLayout)v.findViewById(R.id.loading_tip_layout);

        Bundle bundle = getArguments();
        if (bundle != null) {
            imageUrl = bundle.getString(IntentConstants.EXTRA_IMAGE_URL);
            imageUrlList = bundle.getStringArrayList(IntentConstants.EXTRA_IMAGE_URLS);
            source = bundle.getString(IntentConstants.EXTRA_SOURCE);
            boolean isNoDefaultIcon = bundle.getBoolean(IntentConstants.EXTRA_NO_DEFAULT_ICON, false);
            if (isNoDefaultIcon) {
                imageView.setTag(R.id.tag_no_default_icon, true);
            }
            isFitXy = bundle.getBoolean(IntentConstants.EXTRA_FITXY, false);
            if (isFitXy) {
                imageView.setScaleType(ScaleType.FIT_XY);
            }
            if (!IntentConstants.SOURCE_IMAGE_PAGER.equals(source)) {
                v.setBackgroundResource(R.drawable.frame_square);
            }
            if (!StringUtils.isEmpty(imageUrl)) {
                // DEMO: Disable get data from internet
//                if (Cache.IMAGE_CACHE.get(imageUrl, imageView)) {
//                    loadingLayout.setVisibility(View.GONE);
//                }

                // DEMO: Load image from local file
                imageView.setBackgroundDrawable(Drawable.createFromPath(imageUrl));
                loadingLayout.setVisibility(View.GONE);
            }

            if (!StringUtils.isEmpty(source)) {
                imageView.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        if (IntentConstants.EXTRA_SOURCE_APP.equals(source)) {
                            Intent intent = new Intent(context, Constants.class);
                            intent.putExtra(IntentConstants.EXTRA_IMAGE_URL, imageUrl);
                            intent.putStringArrayListExtra(IntentConstants.EXTRA_IMAGE_URLS, imageUrlList);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        } else if (IntentConstants.SOURCE_IMAGE_PAGER.equals(source)) {
                            getActivity().finish();
                        }
                    }
                });
            }
        }
        return v;
    }
}
