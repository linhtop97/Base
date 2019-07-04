package vn.com.misa.base.di

import dagger.Module
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.misa.base.iteractors.IDataModule
import vn.com.misa.base.iteractors.databases.IDbModule
import vn.com.misa.base.iteractors.services.IApiService
import vn.com.misa.base.iteractors.services.IFileApiService
import vn.com.misa.base.iteractors.services.ILongApiService
import javax.inject.Inject

@Module
class DataModule(component: AppComponent) : IDataModule {
    @Inject
    lateinit var rxBus: RxBus<IEvent>

    @Inject
    override lateinit var apiService: IApiService

    @Inject
    override lateinit var longApiService: ILongApiService

    @Inject
    override lateinit var fileApiService: IFileApiService

    @Inject
    override lateinit var dbModule: IDbModule

    init {
        component.inject(this)
    }
}

