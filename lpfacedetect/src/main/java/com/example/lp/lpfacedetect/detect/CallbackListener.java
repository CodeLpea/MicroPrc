package com.example.lp.lpfacedetect.detect;




import java.util.List;
/**
 * 人力识别结果
 * lp
 * 2019/06/15
 * */
public interface CallbackListener {
     void  onGetFace(List<LpVisionDetRet> results);
     void  onNoFace(String detail) ;
}
