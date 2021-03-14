package com.example.weatherappthesis.dagger

import com.example.weatherappthesis.MainActivity
import com.example.weatherappthesis.dagger.annotations.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}