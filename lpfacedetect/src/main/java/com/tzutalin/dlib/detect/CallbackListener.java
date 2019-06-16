package com.tzutalin.dlib.detect;




import com.tzutalin.dlib.VisionDetRet;

import java.util.List;

/**
 * 人力识别结果
 * lp
 * 2019/06/15
 * */
public interface CallbackListener {
     void  onGetFace(List<VisionDetRet> results);
     void  onNoFace(String detail) ;
}
