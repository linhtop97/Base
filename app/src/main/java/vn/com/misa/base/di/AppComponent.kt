package vn.com.misa.base.di
import dagger.Component
import vn.com.misa.base.AppContext
import vn.com.misa.base.commons.activities.BaseAppCompatActivity
import vn.com.misa.base.commons.presenters.IBaseActivityMvpPresenter
import vn.com.misa.base.commons.presenters.IBaseFragmentMvpPresenter
import vn.com.misa.base.commons.views.IBaseActivityMvpView
import vn.com.misa.base.commons.views.IBaseFragmentMvpView
import vn.com.misa.base.commons.views.IBaseMvpView
import vn.com.misa.base.iteractors.IDataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, DbModule::class, DataModule::class, AppModule::class])
interface AppComponent {
    fun inject(dataModule: DataModule)

    fun inject(dbModule: DbModule)

    fun inject(appContext: AppContext)

    fun inject(activity: BaseAppCompatActivity<IBaseActivityMvpView, IBaseActivityMvpPresenter<IBaseActivityMvpView>>)

//    fun inject(presenter: BaseMvpPresenter<IBaseMvpView>)
//
//    fun inject(fragment: BaseMvpFragment<IBaseFragmentMvpView, IBaseFragmentMvpPresenter<IBaseFragmentMvpView>>)
//
//    fun inject(fragment: BaseMvpDialogFragment<IBaseFragmentMvpView, IBaseFragmentMvpPresenter<IBaseFragmentMvpView>>)
}
