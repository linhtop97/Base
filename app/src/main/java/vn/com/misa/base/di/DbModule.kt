package vn.com.misa.base.di

import dagger.Module
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.misa.base.iteractors.databases.IDbModule
import javax.inject.Inject

@Module
class DbModule(component: AppComponent) : IDbModule {
    @Inject
    lateinit var rxBus: RxBus<IEvent>

    init {
        component.inject(this)
    }
}
