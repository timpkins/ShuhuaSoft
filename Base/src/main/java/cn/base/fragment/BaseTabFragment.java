package cn.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author timpkins
 */
public class BaseTabFragment extends Fragment {
    protected static final String KEY_MESSAGE = "kmessage";

    public static BaseTabFragment newInstance(String message){
        BaseTabFragment baseTabFragment = new BaseTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        baseTabFragment.setArguments(bundle);
        return baseTabFragment;
    }
}
