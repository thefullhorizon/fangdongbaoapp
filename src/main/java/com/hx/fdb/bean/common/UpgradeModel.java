package com.hx.fdb.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 17/4/19.
 */

public class UpgradeModel implements Parcelable{

    public String version;

    public boolean upgrade;//是否需要更新

    public boolean forcedupgrade;//是否需要强制更新

    public String title;

    public String content;

    public String downloadurl;


    public UpgradeModel() {

    }

    protected UpgradeModel(Parcel in) {
        version = in.readString();
        upgrade = in.readByte() != 0;
        forcedupgrade = in.readByte() != 0;
        title = in.readString();
        content = in.readString();
        downloadurl = in.readString();
    }

    /**
     * 是否需要提示更新
     * @return
     */
    public boolean isUpgrade() {
        return upgrade || forcedupgrade;
    }

    /**
     * 是否是强制更新
     * @return
     */
    public boolean isForce() {
        return forcedupgrade;
    }

    public static final Creator<UpgradeModel> CREATOR = new Creator<UpgradeModel>() {
        @Override
        public UpgradeModel createFromParcel(Parcel in) {
            return new UpgradeModel(in);
        }

        @Override
        public UpgradeModel[] newArray(int size) {
            return new UpgradeModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(version);
        dest.writeByte((byte) (upgrade ? 1 : 0));
        dest.writeByte((byte) (forcedupgrade ? 1 : 0));
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(downloadurl);
    }
}
