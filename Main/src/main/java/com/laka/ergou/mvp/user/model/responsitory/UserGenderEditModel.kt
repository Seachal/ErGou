package com.laka.ergou.mvp.user.model.responsitory

import com.laka.androidlib.util.rx.RxSchedulerComposer
import com.laka.androidlib.util.rx.callback.ResponseCallBack
import com.laka.ergou.common.util.rx.RxCustomSubscriber
import com.laka.ergou.mvp.user.contract.IUserGenderEdit
import com.laka.ergou.mvp.user.model.bean.CommonData
import io.reactivex.disposables.Disposable
import java.util.ArrayList

/**
 * @Author:summer
 * @Date:2019/1/11
 * @Description:
 */
class UserGenderEditModel : IUserGenderEdit.IUserGenderEditModel {

//    override fun onUserGenderEdit(params: HashMap<String, String?>): Observable<BaseResponse<CommonData>> {
//        return UserRetrofitHelper.instance.onUpdateUserInfoData(params).compose(RxSchedulerComposer.normalSchedulersTransformer())
//    }

    private lateinit var mView: IUserGenderEdit.IUserGenderEditView
    private val mDisposableList = ArrayList<Disposable>()

    override fun setView(v: IUserGenderEdit.IUserGenderEditView) {
        this.mView = v
    }

    override fun onViewDestory() {
        for (disposable in mDisposableList) {
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
        }
        mDisposableList.clear()
    }

    override fun onUserGenderEdit(params: HashMap<String, String?>, callBack: ResponseCallBack<CommonData>) {
        UserCustomRetrofitHelper.instance
                .onUpdateUserNickOrGenderData(params)
                .compose(RxSchedulerComposer.normalSchedulersTransformer())
                .subscribe(object : RxCustomSubscriber<CommonData, IUserGenderEdit.IUserGenderEditView>(mView, callBack) {
                    override fun onSubscribe(d: Disposable) {
                        super.onSubscribe(d)
                        mDisposableList.add(d)
                    }
                })
    }


}