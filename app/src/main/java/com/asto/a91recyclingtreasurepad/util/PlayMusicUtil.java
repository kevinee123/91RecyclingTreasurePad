package com.asto.a91recyclingtreasurepad.util;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.asto.a91recyclingtreasurepad.R;

/**
 * Created by zj on 2018/7/12.
 * is use for: 音乐播放工具类
 */

public class PlayMusicUtil {
    private Context context;
    private SoundPool sp=null;//声明一个SoundPool的引用
    private HashMap <Integer,Integer> hm;//声明一个HashMap来存放声音资源
    public static final int LOOP = -1;
    public static final int NOR_LOOP = 0;

    public PlayMusicUtil(Context context){
        this.context = context;
        initSoundPool();
    }
    public void initSoundPool(){
        sp=new SoundPool(3,AudioManager.STREAM_MUSIC,0);//创建SoundPool对象
        hm=new HashMap<Integer,Integer>();//创建HashMap对象
        hm.put(0, sp.load(context, R.raw.guobangzhong, 0));
        hm.put(1, sp.load(context,R.raw.weidingjia,1));
    }

    //loop指定是否循环，0为不循环，-1为循环
    public int playSound(int soundID,int loop) {
        int id = 0;
        AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volume = streamVolumeCurrent/streamVolumeMax;

        id = sp.play(hm.get(soundID), volume, volume, 1, loop, 1f);
        return id;
    }

    //参数：1、Map中取值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度
    public void release(){
        if(sp !=null){
            sp.release();
        }
    }

    public void stopSound(int soundID){
        if(sp !=null){
            sp.autoPause();
            sp.stop(soundID);
        }
    }

    public void finishSound(int soundID){
        if(sp !=null){
            sp.stop(soundID);
        }
    }
}
