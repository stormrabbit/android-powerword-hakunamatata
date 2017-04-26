package com.siegfrield.hakuna_matata.service.presenter;

import com.siegfrield.hakuna_matata.model.data.ResultData;
import com.siegfrield.hakuna_matata.model.data.ReturnType;

/**
 * Created by 欧阳夏昱 on 2017/4/27.
 */

public interface OnHttp {
    /**
     *
     * @param requestCode 请求code
     * @param type 返回成功或者失败
     * @param resultData 返回值
     */
    void onHttpResult(int requestCode, ReturnType type, ResultData resultData);
}
