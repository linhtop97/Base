package vn.com.misa.base.di

import dagger.Module
import dagger.Provides
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.misa.base.AppContext
import vn.com.misa.base.iteractors.IDataModule
import vn.com.misa.base.iteractors.databases.IDbModule
import javax.inject.Singleton

@Module
class AppModule(private val appContext: AppContext) {
    @Singleton
    @Provides
    fun provideEvenBus(): RxBus<IEvent> = RxBus()

    @Singleton
    @Provides
    fun provideAppContext(): AppContext = appContext

    @Singleton
    @Provides
    fun provideDataModule(): IDataModule = DataModule(appContext.appComponent)

    @Singleton
    @Provides
    fun provideDbModule(): IDbModule = DbModule(appContext.appComponent)

    @Singleton
    @Provides
    fun provideApiModule(): ApiModule = ApiModule()
}
